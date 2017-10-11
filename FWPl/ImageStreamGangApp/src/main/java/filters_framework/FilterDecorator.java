package filters_framework;

import utils_framework.Image;

/**
 * Allows the addition of behavior to a Filter object transparently
 * without affecting the behavior of other Filter objects it
 * encapsulates.  Plays the role of the "Decorator" in the Decorator
 * pattern and the role of the "Abstract Class" in the Template Method
 * pattern.
 */
public abstract class FilterDecorator extends filters_framework.Filter {
    /**
     * The Filter that's being decorated.
     */
    protected filters_framework.Filter mFilter;

    /**
     * Constructor initializes superclass and data member with the
     * given @a filter.
     */
    public FilterDecorator(filters_framework.Filter filter) {
        super(filter.getName());
        mFilter = filter;
    }

    /**
     * An abstract hook method that "decorates" the Filter data member
     * by being applied to the imageEntity after it's been filtered.
     */
    protected abstract utils_framework.Image decorate(utils_framework.Image imageEntity);

    /**
     * This hook method is also a template method that forwards to the
     * decorated filter to filter the @a imageEntity parameter.
     */
    @Override
    protected utils_framework.Image applyFilter(utils_framework.Image image) {
        return decorate(mFilter.filter(image));
    }
}