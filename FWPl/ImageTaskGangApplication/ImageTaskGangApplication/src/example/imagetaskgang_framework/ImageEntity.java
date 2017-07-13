package example.imagetaskgang_framework;

public abstract class ImageEntity {
    /**
     * The Image our ImageEntity stores.
     */
     public Image mImage;

    /**
     * The source URL from which the result was downloaded.
     */
     public java.net.URL mSourceUrl;

    /**
     * The name of the filter that was applied to this result.
     */
     public String mFilterName;
    
    /**
     * Keeps track of whether operations on this ImageEntity succeed.
     */
     public boolean mSucceeded;

	abstract public void setImage( byte[] imageData);

    /**
     * Returns the @a Image stored by this ImageEntity.
     */
    public Image getImage() {
        return mImage;
    }

    /**
     * Modifies the source URL of this result. Necessary for when the
     * result is constructed before it is associated with data.
     */
    public void setSourceURL(java.net.URL url) {
        mSourceUrl = url;
    }

    /**
     * Returns the source URL this result was constructed from.
     */
    public java.net.URL getSourceURL() {
        return mSourceUrl;
    }

    /**
     * Sets the name of the filter applied to this result.
     */
    public void setFilterName(Filter filter) {
        mFilterName = filter.getName();
    }

    /**
     * Returns the name of the filter applied to this result.
     */
    public String getFilterName() {
        return mFilterName;
    }

    /**
     * Sets whether operations on the ImageEntity succeeded or failed.
     */
    public void setSucceeded(boolean succeeded) {
        mSucceeded = succeeded;
    }

    /**
     * Returns true if operations on the ImageEntity succeeded, else
     * false.
     */
    public boolean getSucceeded() {
        return mSucceeded;
    }

    /**
     * Returns the file name from the URL this ImageEntity was
     * constructed from.
     */
    public String getFileName() {
        return mSourceUrl.getFile().substring(mSourceUrl.getFile().lastIndexOf('/'));
    }

    /**
     * Returns the format of the image from the URL in string form.
     */
    public String getFormatName() {
        String format =
            mSourceUrl.getFile().substring(mSourceUrl.getFile().lastIndexOf('.') + 1);
        format = format.equalsIgnoreCase("jpeg") ? "jpg" : format;
        return format;
    }
}