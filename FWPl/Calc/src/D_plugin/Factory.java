package D_plugin;

public class Factory extends D_framework.Factory {

	public Gui newGui( String name) {
		return new Gui(name);
	}
}