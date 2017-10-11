package logicGates_framework;

public abstract class InputPort extends logicGates_framework.Gate {
    
    //@Feature(Feature.tables)     
    static public java.util.LinkedList<Gate> table;

	public InputPort( String name) {
		super(name);
	}
    
    public Pins_framework.OutputPin getOutput() {
        return outputs.get("o");
    }
    
    public static void resetTable() {
        logicGates_framework.InputPort.table = new java.util.LinkedList<Gate>();
    }
    
    public static java.util.LinkedList<Gate> getTable() { 
        return logicGates_framework.InputPort.table;
    }

	abstract public void setValue( int v);
}