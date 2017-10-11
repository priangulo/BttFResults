package logicGates_plugin;

public class Factory extends logicGates_framework.Factory {

	public InputPort newInputPort( String name) {
		return new InputPort(name);
	}
}