package example.imagetaskgang_framework;

public abstract class ImageTaskGang extends example.imagetaskgang_framework.TaskGang<java.net.URL> {
    /**
     * An iterator to the List of input URLs that are used to download
     * Images.
     */
     public java.util.Iterator<java.util.List<java.net.URL>> mUrlListIterator;

    /**
     * The List of filters to apply to the downloaded images.
     */
     public java.util.List<Filter> mFilters;

    /**
     * An ExecutorCompletionService used to concurrently download and
     * apply image processing tasks on designated URLs.  This plays
     * the role of the "Asynchronous Operation Processor" in the
     * Proactor pattern.
     */
     public java.util.concurrent.ExecutorCompletionService<example.imagetaskgang_plugin.ImageEntity> mCompletionService;

    /**
     * Clients of ImageTaskGang supply this hook so they know when the
     * all the images have been downloaded, processed, and stored, at
     * which point they can display the stored images.
     */
     public Runnable mCompletionHook;

    /**
     * A barrier synchronizer that's used to coordinate each iteration
     * cycle, i.e., each task in the TaskGang must wait on this
     * barrier for the other tasks to complete their processing before
     * they all attempt to move to the next cycle en masse.
     */
     public java.util.concurrent.CountDownLatch mIterationBarrier = null;
     public java.util.List<java.net.URL> getNextInput() {
        if (mUrlListIterator.hasNext()) {
            // Note that we're starting a new cycle.
            incrementCycle();

            // Return a List containing the URLs to download
            // concurrently.
            return mUrlListIterator.next();
        }
        else
            // Indicate that we're done.
            return null;
    }
     public void initiateTaskGang(int initialNumberOfURLs) {
        // Create a new iteration barrier with the appropriate size.
        mIterationBarrier = new java.util.concurrent.CountDownLatch(initialNumberOfURLs);

        // Enqueue each item in the input List for execution in the
        // Executor's Thread pool, which ensures there's a Thread
        // available to run each task concurrently.
        for (int i = 0; i < initialNumberOfURLs; ++i)
            getExecutor().execute(makeTask(i));
    }
     public void taskDone(int index) throws java.lang.IndexOutOfBoundsException {
        try {
            // Indicate that this task is done with its computations.
            mIterationBarrier.countDown();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } 
    }
     public void awaitTasksDone() {
        try {
            // Keeps track of the number of result Futures to process.
            int resultsCount = 0;

            // Loop for each iteration cycle of input URLs.
            for (;;) {
                // Increment the number of URLs to download.
                resultsCount += getInput().size();

                // Barrier synchronizer that wait until all tasks in
                // this iteration cycle are done.
                mIterationBarrier.await();

                // Check to see if there's another List of URLs
                // available to process.
                if (setInput(getNextInput()) == null)
                    break; // No more input, so we're done.
                else
                    // Invoke this hook method to initialize the gang
                    // of tasks for the next iteration cycle.
                    initiateTaskGang(getInput().size());
            } 


            // Account for all the downloaded images and all the
            // filters applied to these images.
            resultsCount *= mFilters.size();

            // Process all the result Futures asynchronously via the
            // ExecutorCompletionService's completion queue.
            concurrentlyProcessFilteredResults(resultsCount);

            // Only call the shutdown() and awaitTermination() methods
            // if we've actually got an ExecutorService (as opposed to
            // just an Executor).
            if (getExecutor() instanceof java.util.concurrent.ExecutorService) {
                java.util.concurrent.ExecutorService executorService = 
                    (java.util.concurrent.ExecutorService) getExecutor();

                // Tell the ExecutorService to initiate a graceful
                // shutdown.
                executorService.shutdown();

                // Wait for all the tasks in the Thread pool to
                // complete.
                
                                                 executorService.awaitTermination(java.lang.Long.MAX_VALUE, java.util.concurrent.TimeUnit.NANOSECONDS);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Run the completion hook now that all the image downloading,
        // processing and storing is now complete.
        mCompletionHook.run();
    }

	abstract protected void concurrentlyProcessFilteredResults( int resultsCount);

    /**
     * Factory method that retrieves the image associated with the @a
     * urlToDownload and creates an ImageEntity to encapsulate it.
     */
     public example.imagetaskgang_plugin.ImageEntity makeImageEntity(java.net.URL urlToDownload) {
        return new example.imagetaskgang_plugin.ImageEntity(urlToDownload,
                               downloadContent(urlToDownload));
    }

    /**
     * Download the contents found at the given URL and return them as
     * a raw byte array.
     */
    @android.annotation.SuppressLint("NewApi")
     public byte[] downloadContent(java.net.URL url) {
        // The size of the image downloading buffer.
        final int BUFFER_SIZE = 4096;

        // Creates a new ByteArrayOutputStream to write the downloaded
        // contents to a byte array, which is a generic form of the
        // image.
        java.io.ByteArrayOutputStream ostream = 
            new java.io.ByteArrayOutputStream();
        
        // This is the buffer in which the input data will be stored
        byte[] readBuffer = new byte[BUFFER_SIZE];
        int bytes;
        
        // Creates an InputStream from the inputUrl from which to read
    	// the image data.
        try{
        	java.io.InputStream istream = (java.io.InputStream) url.openStream();
            // While there is unread data from the inputStream,
            // continue writing data to the byte array.
            while ((bytes = istream.read(readBuffer)) > 0) 
                  ostream.write(readBuffer, 0, bytes);

            return ostream.toByteArray();
        } catch (java.io.IOException e) {
            // "Try-with-resources" will clean up the istream
            // automatically.
            e.printStackTrace();
            return null;
        }
    }
}