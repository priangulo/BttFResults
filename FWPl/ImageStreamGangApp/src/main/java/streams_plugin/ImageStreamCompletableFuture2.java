package streams_plugin;

import java.net.URL;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

import filters_framework.Filter;
import filters_framework.FilterDecoratorWithImage;
import utils_framework.Image;
import utils_framework.StreamsUtils;

import static java.util.stream.Collectors.summingInt;

/**
 * This is another asynchronous implementation strategy that
 * customizes the ImageStreamCompletableFutureBase super class to
 * download, process, and store images asynchronously and concurrently
 * in a thread in the executor's thread pool.
 */
public class ImageStreamCompletableFuture2 extends streams_plugin.ImageStreamCompletableFutureBase {
    /**
     * Constructor initializes the superclass and data members.
     */
    public ImageStreamCompletableFuture2(filters_framework.Filter[] filters,
                                        Iterator<List<URL>> urlListIterator) {
        super(filters, urlListIterator);
    }

    /**
     * Use Java 8 CompletableFutures to download, process, and store
     * images concurrently.
     */
    @Override
    protected void processStream() {
    	List<CompletableFuture<List<utils_framework.Image>>> listOfFutures = null;
    	
    	for(URL url : getInput()){
    		if(!(urlCached(url))){
    			CompletableFuture<utils_framework.Image> image = downloadImageAsync(url);
    			CompletableFuture<List<filters_framework.FilterDecoratorWithImage>> listImage = null;
				try {
					listImage = makeFilterDecorators(image);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			try {
					applyFiltersAsync(listImage);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			try {
					for(filters_framework.FilterDecoratorWithImage i : listImage.get()){
						listOfFutures.add(this.applyFiltersAsync(listImage));
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
    	
    	
//        // Create a list of completable futures to a list of images.
//        final List<CompletableFuture<List<Image>>> listOfFutures = getInput()
//            // Convert the URLs in the input list into a sequential
//            // stream.
//            .stream()
//
//            // Use filter() to ignore URLs that are already cached locally,
//            // i.e., only download non-cached images.
//            .filter(StreamsUtils.not(this::urlCached))
//
//            // Use map() to transform each URL to a completable future
//            // to an image (i.e., asynchronously download each image
//            // via its URL).
//            .map(this::downloadImageAsync)
//
//            // Use map() to call makeFilterDecorators(), which returns
//            // a List of FilterDecoratorWithImage objects associated
//            // with a completable future.
//            .map(this::makeFilterDecorators)
//
//            // Use map() to call the applyFiltersAsync() method
//            // reference, which returns a list of filtered Image
//            // futures.
//            .map(this::applyFiltersAsync)
//            
//            // Terminate the stream, which returns a list of
//            // completable futures to a list of images.
//            .collect(toList());

        // Create a CompletableFuture that can be used to wait for all
        // operations associated with the futures to complete.
        CompletableFuture<List<List<utils_framework.Image>>> allImagesDone =
                utils_framework.StreamsUtils.joinAll(listOfFutures);

        Integer imagesProcessed = 0;
        for(List<utils_framework.Image> listIma : allImagesDone.join()){
        	imagesProcessed = imagesProcessed + listIma.size();
        }
        
//        // The call to join() is needed here to blocks the calling
//        // thread until all the futures have completed.
//        Integer imagesProcessed = allImagesDone
//            // join() returns a list of list of images, so we convert
//            // this to a stream and then sum up the size of each of
//            // these lists to get the final count of images processed.
//            .join()
//            .stream()
//            .collect(summingInt(List::size);

        System.out.println(TAG
                           + ": processing of "
                           + imagesProcessed
                           + " image(s) is complete");
    }


    /**
     * A factory method that makes all the image processing filters
     * for the @a imageFuture after its downloaded.
     * @throws ExecutionException 
     * @throws InterruptedException 
     */
    private CompletableFuture<List<filters_framework.FilterDecoratorWithImage>> makeFilterDecorators
                (CompletableFuture<utils_framework.Image> imageFuture) throws InterruptedException, ExecutionException {
    	
    	List<filters_framework.FilterDecoratorWithImage> results = new ArrayList<filters_framework.FilterDecoratorWithImage>();
    	for(filters_framework.Filter filter : mFilters){
    		filters_framework.FilterDecoratorWithImage toadd =  makeFilterDecoratorWithImage(filter, imageFuture.get()); 
    		results.add(toadd);
    	}
    	return imageFuture.thenApply((Function<? super utils_framework.Image, ? extends List<filters_framework.FilterDecoratorWithImage>>) results);
    	
//        // Returns a new CompletionStage that, when this stage
//        // completes normally, is executed with this stage's result as
//        // the argument to the supplied lambda expression.
//        return imageFuture.thenApply(image -> mFilters
//            // Convert all the image filters into a sequential stream.
//            .stream()
//
//            // Create a FilterDecoratorWithImage object for each
//            // filter/image combo.
//            .map(filter -> makeFilterDecoratorWithImage(filter, image))
//
//            // Return a completable future to a list of
//            // FilterDecoratorWithImage objects.
//            .collect(toList()));
    }

    /**
     * Asynchronously apply all the filters to images and store them
     * in an output file on the local computer.
     * @throws ExecutionException 
     * @throws InterruptedException 
     */
    private CompletableFuture<List<utils_framework.Image>> applyFiltersAsync
      (CompletableFuture<List<filters_framework.FilterDecoratorWithImage>> decoratedFiltersWithImageFuture) throws InterruptedException, ExecutionException {
    	
    	List<CompletableFuture<utils_framework.Image>> listOfFutures = new ArrayList<CompletableFuture<utils_framework.Image>>();
    	for(filters_framework.FilterDecoratorWithImage ima : decoratedFiltersWithImageFuture.get()){
    		listOfFutures.add(filterImageAsync(ima));
    	}
    	
    	return decoratedFiltersWithImageFuture.thenCompose((Function<? super List<filters_framework.FilterDecoratorWithImage>, ? extends CompletionStage<List<utils_framework.Image>>>) utils_framework.StreamsUtils.joinAll(listOfFutures));
    	
    	
//        // Returns a new CompletionStage that, when this stage
//        // completes normally, is executed with this stage's result as
//        // the argument to the supplied lambda expression.
//        return decoratedFiltersWithImageFuture.thenCompose(list -> {
//                List<CompletableFuture<Image>> listOfFutures = list
//                    // Converts the list of FilterDecoratorWithImage
//                    // objects into a sequential stream.
//                    .stream()
//
//                    // Asynchronously apply a filter to an Image.
//                    .map(this::filterImageAsync)
//
//                    // Collect the list of futures.
//                    .collect(toList());
//
//                // Return a CompletableFuture to a list of images that
//                // will be available when all the CompletableFutures
//                // in listOfFutures complete.
//                return StreamsUtils.joinAll(listOfFutures);
//            });
    }
}