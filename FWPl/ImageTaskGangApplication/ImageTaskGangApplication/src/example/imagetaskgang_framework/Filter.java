package example.imagetaskgang_framework;

/**
 * @class Filter
 *
 * @brief An abstract class that defines an interface for applying
 *        filtering operations to an Image. Each Filter has a name and
 *        an abstract method whose implementation must be overridden
 *        by a subclass.  Plays the role of the "Abstract Class" in
 *        the Template Method pattern and the role of the "Component"
 *        in the Decorator pattern.
 */
public abstract class Filter {
    /**
     * The name of the filter, which defaults to the "canonical name"
     * of the subclass Filter instance.
     */
    protected String mName;

    /**
     * Constructs the filter with the default name.
     */
    public Filter() {
    	String baseName = this.getClass().getCanonicalName();
        mName = baseName.substring(baseName.lastIndexOf(".") + 1);
    }

    /**
     * Constructs the filter with a custom name.
     */
    public Filter(String filterName) {
        mName = filterName;
    }

    /**
     * This abstract hook method must be overridden by a subclass to
     * define the logic for processing the given @a imageEntity.
     */
    protected abstract example.imagetaskgang_plugin.ImageEntity applyFilter(example.imagetaskgang_plugin.ImageEntity imageEntity);

    /**
     * This template method calls the applyFilter() hook method (which
     * must be defined by a subclass) to filter the @a imageEntity
     * parameter and sets the filterName of the result to the name of
     * the filter.
     */
    public example.imagetaskgang_plugin.ImageEntity filter(example.imagetaskgang_plugin.ImageEntity imageEntity) {
        // Call the applyFilter() hook method.
        example.imagetaskgang_plugin.ImageEntity filteredResult = applyFilter(imageEntity);
        filteredResult.setFilterName(this);
        return filteredResult;
    }

    /**
     * Sets the name of the filter.
     */
    public void setName(String filterName) {
        mName = filterName;
    }

    /**
     * Gets the name of the filter.
     */
    public String getName() {
        return mName;
    }
}