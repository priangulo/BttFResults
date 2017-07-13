package example.imagetaskgang_plugin;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
//import URL;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.net.URL;

import android.annotation.SuppressLint;

/**
 * @class ImageTaskGang
 *
 * @brief Customizes the TaskGang framework to use the Java
 *        ExecutorCompletionService to concurrently download a List of
 *        images from web servers, apply image processing filters to
 *        each image, and store the results in files that can be
 *        displayed to users via various means defined by the context
 *        in which this class is used.
 *
 *        This class implements the roles of the "Proactive Initiator"
 *        and "Completion Handler" in the Proactor pattern and also
 *        plays the role of the "Concrete Class" in the Template
 *        Method pattern.
 */
public class ImageTaskGang extends example.imagetaskgang_framework.ImageTaskGang {

    /**
     * Constructor initializes the superclass and data members.
     */
    public ImageTaskGang(example.imagetaskgang_framework.Filter[] filters,
                         Iterator<List<URL>> urlListIterator,
                         Runnable completionHook) {
        // Store the Filters to apply as a List.
        mFilters = Arrays.asList(filters);

        // Create an Iterator for the array of URLs to download.
        mUrlListIterator = urlListIterator;

        // Set the completion hook that's called when all the images
        // are downloaded and processed.
        mCompletionHook = completionHook;

        // Initialize the Executor with a cached pool of Threads,
        // which grow and shrink dynamically as new tasks are
        // executed.
        setExecutor(Executors.newCachedThreadPool());

        // Connect the Executor with the CompletionService to process
        // result Futures concurrently.
        mCompletionService =
            new ExecutorCompletionService<ImageEntity>(getExecutor());
    }

    /**
     * Hook method that runs in a background Thread to download,
     * process, and store an image via the ExecutorCompletionService.
     */
    @Override
    protected boolean processInput(URL urlToDownload) {
        // Download an image into a new ImageEntity object.
    	final ImageEntity downloadedImage = 
            makeImageEntity(urlToDownload);

        // For each filter in the List of Filters, submit a task to
        // the ExecutorCompletionService that filters the image
        // downloaded from the given URL, stores the results in a
        // file, and puts the results of the filtered image in the
        // completion queue.
        for (final example.imagetaskgang_framework.Filter filter : mFilters) {
        	
            // The ExecutorCompletionService receives a Callable and
            // invokes its call() method, which returns the filtered
            // ImageEntity.
            mCompletionService.submit(new Callable<ImageEntity>() {
                    @Override
                    public ImageEntity call() {
                    	// Create an OutputFilterDecorator that
                        // encapsulates the original filter.
                        example.imagetaskgang_framework.Filter decoratedFilter =
                            new OutputFilterDecorator(filter);

                        // Process the downloaded image, store it
                        // into a file, return the result.
                        return decoratedFilter.filter(downloadedImage);
                    }
                });
        }

        return true;
    }

    /**
     * Removes result Futures from the ExecutorCompletionService's
     * completion queue until all the processed downloads have been
     * received and prints diagnostics indicating if the image
     * downloading, processing, and storing worked properly.
     */
    protected void concurrentlyProcessFilteredResults(int resultsCount) {
        // Loop for the designated number of results.
        for (int i = 0; i < resultsCount; ++i) 
            try {
                // Take the next available Future off the
                // ExecutorCompletionService's completion queue.
                final Future<ImageEntity> resultFuture =
                    mCompletionService.take();

                // The get() call will not block since the results
                // should be ready before they are added to the
                // completion queue.
                ImageEntity imageEntity = resultFuture.get();
    
                // Indicate success or failure for this URL.
                
                     PlatformStrategy.instance().errorLog("ImageTaskGang", "Operations on file " 
                     + imageEntity.getSourceURL()
                     + (imageEntity.getSucceeded() == true 
                        ? " succeeded" 
                        : " failed"));
            } catch (ExecutionException e) {
                
                                                     PlatformStrategy.instance().errorLog("ImageTaskGang", "get() ExecutionException");
            } catch (InterruptedException e) {
                
                                                     PlatformStrategy.instance().errorLog("ImageTaskGang", "get() InterruptedException");
            }
    }

	public ImageEntity newImageEntity( java.net.URL sourceURL, byte[] imageData) {
		return new ImageEntity(sourceURL, imageData);
	}
}