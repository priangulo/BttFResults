package example.imagetaskgang_framework;

/**
 * @class FilterDecorator
 *
 * @brief Allows the addition of behavior to a Filter object
 *        transparently without affecting the behavior of other Filter
 *        objects it encapsulates.  Plays the role of the "Decorator"
 *        in the Decorator pattern and the role of the "Abstract
 *        Class" in the Template Method pattern.
 */
public abstract class FilterDecorator extends example.imagetaskgang_framework.Filter {
    /**
     * The Filter that's being decorated.
     */
    protected example.imagetaskgang_framework.Filter mFilter;

    /**
     * Constructor initializes superclass and data member with the
     * given @a filter.
     */
    public FilterDecorator(example.imagetaskgang_framework.Filter filter) {
        super(filter.getName());
        mFilter = filter;
    }

    /**
     * An abstract hook method that "decorates" the Filter data member
     * by being applied to the imageEntity after it's been filtered.
     */
    protected abstract example.imagetaskgang_plugin.ImageEntity decorate(example.imagetaskgang_plugin.ImageEntity imageEntity);

    /**
     * This hook method is also a template method that forwards to the
     * decorated filter to filter the @a imageEntity parameter.
     */
    @Override
    protected example.imagetaskgang_plugin.ImageEntity applyFilter(example.imagetaskgang_plugin.ImageEntity imageEntity) {
        return decorate(mFilter.filter(imageEntity));
    }
}