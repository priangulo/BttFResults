package example.imagetaskgang_framework;

public abstract class Factory {

	abstract public example.imagetaskgang_plugin.ImageEntity newImageEntity( java.net.URL sourceURL, byte[] imageData);

	abstract public example.imagetaskgang_plugin.ImageEntity newImageEntity( java.net.URL sourceURL, Image image);

	abstract public example.imagetaskgang_plugin.ImageTaskGang newImageTaskGang( Filter[] filters, java.util.Iterator<java.util.List<java.net.URL>> urlListIterator, Runnable completionHook);
}