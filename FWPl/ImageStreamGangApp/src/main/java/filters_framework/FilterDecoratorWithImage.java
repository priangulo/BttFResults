package filters_framework;

import utils_framework.Image;

/**
 * Command object that associates a filter with an image.
 */
public class FilterDecoratorWithImage {
    /**
     * The filter decorator.
     */
    public filters_framework.FilterDecorator mFilterDecorator;

    /**
     * The image.
     */
    public utils_framework.Image mImage;

    /**
     * Constructor initializes the fields.
     */
    public FilterDecoratorWithImage(filters_framework.FilterDecorator filterDecorator,
                                    utils_framework.Image image) {
        mFilterDecorator = filterDecorator;
        mImage = image;
    }

    /**
     * Run the filter decorator on the image.
     */
    public utils_framework.Image run() {
        return mFilterDecorator.filter(mImage);
    }
}