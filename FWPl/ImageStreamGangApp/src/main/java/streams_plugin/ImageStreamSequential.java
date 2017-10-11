package streams_plugin;

import static java.util.stream.Collectors.toList;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import filters_framework.Filter;
import filters_framework.FilterDecoratorWithImage;
import utils_framework.Image;
import utils_framework.StreamsUtils;

/**
 * This implementation strategy customizes ImageStreamGang to use a
 * Java 8 stream to download, process, and store images sequentially.
 */
public class ImageStreamSequential extends streams_framework.ImageStreamGang {
    /**
     * Constructor initializes the superclass.
     */
    public ImageStreamSequential(filters_framework.Filter[] filters,
                                 Iterator<List<URL>> urlListIterator) {
        super(filters, urlListIterator);
    }

    /**
     * This hook method uses a Java 8 stream to download, process, and
     * store images sequentially.
     */
    @Override
    protected void processStream() {
    	List<utils_framework.Image> collect = new ArrayList<utils_framework.Image>();
    	for(URL url : getInput()){
    		if(!urlCached(url)){
    			collect.add((utils_framework.Image) applyFilters(streams_framework.ImageStreamGang.downloadImage(url)));
    		}
    	}
    	
//        List<Image> collect = getInput()
//            // Convert the URLs in the input list into a stream and
//            // process them sequentially.
//            .stream()
//
//            // Use filter() to ignore URLs that are already cached locally,
//            // i.e., only download non-cached images.
//            .filter(StreamsUtils.not(this::urlCached))
//
//            // Use map() to transform each URL to an image (i.e.,
//            // synchronously download each image via its URL).
//            .map(ImageStreamGang::downloadImage)
//
//            // Use flatMap() to create a stream containing multiple filtered
//            // versions of each image.
//            .flatMap(this::applyFilters)
//
//            // Terminate the stream and collect the results into
//            // list of images.
//            .collect(toList());

        System.out.println(TAG
                           + ": processing of "
                           + collect.size()
                           + " image(s) is complete");
    }

    /**
     * @return true if the @a url is already in the cache, else false.
     */
    @Override
    protected boolean urlCached(URL url) {
    	long count = 0;
    	for(filters_framework.Filter filter : mFilters){
    		if( urlCached(url, filter.getName())){
    			count++;
    		}
    	}
    	
//        // Iterate through the list of image filters sequentially and use
//        // the filter aggregate operation to exclude those already cached.
//        long count = mFilters
//            .stream()
//            .filter(filter ->
//                    urlCached(url, filter.getName()))
//            .count();

        // A count > 0 means the url has already been cached.
        return count > 0;
    }

    /**
     * Apply the image filters to each @a image sequentially.
     */
    private Stream<utils_framework.Image> applyFilters(utils_framework.Image image) {
    	List<utils_framework.Image> list = new ArrayList<utils_framework.Image>();
    	for(filters_framework.Filter filter : mFilters){
    		filters_framework.FilterDecoratorWithImage fim =  makeFilterDecoratorWithImage(filter, image);
    		list.add(fim.run());
    	}
    	
    	return list.stream();
    	
//        return mFilters
//            // Iterate through the list of image filters sequentially and
//            // apply each one to the image.
//            .stream()
//
//            // Use map() to create an OutputFilterDecorator for each image.
//            .map(filter -> makeFilterDecoratorWithImage(filter, image))
//
//            // Use map() to apply the OutputFilterDecorator to each
//            // image and store it in an output file.
//            .map(FilterDecoratorWithImage::run);
    }
}