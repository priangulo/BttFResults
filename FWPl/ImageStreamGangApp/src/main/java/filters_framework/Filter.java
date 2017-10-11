package filters_framework;

import utils_framework.Image;

/**
 * An abstract class that defines an interface for applying filtering
 * operations to an Image. Each Filter has a name and an abstract
 * method whose implementation must be overridden by a subclass.
 * Plays the role of the "Abstract Class" in the Template Method
 * pattern and the role of the "Component" in the Decorator pattern.
 */
public abstract class Filter {
    /**
     * Debug logging tag.
     */
    protected static final String TAG = "Filter";

    /**
     * The name of the filter, which defaults to the "canonical name"
     * of the subclass Filter instance.
     */
    protected String mName;

    /**
     * Constructs the filter with the default name.
     */
    public Filter() {
        // Default uses the class name without the package prefix.
    	mName = this.getClass().getSimpleName();
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
    protected abstract utils_framework.Image applyFilter(utils_framework.Image imageEntity);

    /**
     * This template method calls the applyFilter() hook method (which
     * must be defined by a subclass) to filter the @a imageEntity
     * parameter and sets the filterName of the result to the name of
     * the filter.
     */
    public utils_framework.Image filter(utils_framework.Image image) {
        // Call the applyFilter() hook method.
        utils_framework.Image filteredResult = applyFilter(image);
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