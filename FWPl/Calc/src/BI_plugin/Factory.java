package BI_plugin;

public class Factory extends BI_framework.Factory {

	public Gui newGui( String name) {
		return new Gui(name);
	}
}