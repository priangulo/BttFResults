package example.imagetaskgang_plugin;

import java.net.URL;

/**
 * @class ImageEntity
 *
 * @brief Stores meta-data about an Image and also provides methods
 *        for common image- and file-related tasks, such as decoding
 *        raw byte arrays into an Image and setting/getting filter and
 *        file names.
 */
public class ImageEntity extends example.imagetaskgang_framework.ImageEntity {

    /**
     * Construct an ImageEntity from a byte array of @a imageData
     * downloaded from a URL @a source.
     */
    public ImageEntity(URL sourceURL,
                       byte[] imageData) {
        // Set the URL.
        mSourceUrl = sourceURL;

        // Initialize other data members.
        mFilterName = null;
        mSucceeded = true;
        
        // Decode the imageData into a Bitmap.
        setImage(imageData);
    }

    /**
     * Construct a new ImageEntity from an @a Image.
     */
    public ImageEntity(URL sourceURL,
                       example.imagetaskgang_framework.Image image) {
        // Set the URL.
        mSourceUrl = sourceURL;

        // Initialize other data members.
        mFilterName = null;
        mSucceeded = true;

        // Store the image in the data member.
        mImage = image;
    }

    /**
     * Decodes a byte[] into an @a Image that can be used in the rest
     * of the application.
     */
    public void setImage(byte[] imageData) {
        mImage = PlatformStrategy.instance().makeImage(imageData);
    }
}