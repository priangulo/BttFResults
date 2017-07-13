package example.imagetaskgang_plugin;

public class Factory extends example.imagetaskgang_framework.Factory {

	public ImageEntity newImageEntity( java.net.URL sourceURL, byte[] imageData) {
		return new ImageEntity(sourceURL, imageData);
	}

	public ImageEntity newImageEntity( java.net.URL sourceURL, example.imagetaskgang_framework.Image image) {
		return new ImageEntity(sourceURL, image);
	}

	public ImageTaskGang newImageTaskGang( example.imagetaskgang_framework.Filter[] filters, java.util.Iterator<java.util.List<java.net.URL>> urlListIterator, Runnable completionHook) {
		return new ImageTaskGang(filters, urlListIterator, completionHook);
	}
}