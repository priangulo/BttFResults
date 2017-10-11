package BI_framework;

public abstract class Gui extends javax.swing.JFrame {

	public Gui( String name) {
		super(name);
	}

	abstract protected void EnterActionPerformed( java.awt.event.ActionEvent evt);

	abstract protected void PlusActionPerformed( java.awt.event.ActionEvent evt);

	abstract protected void MinusActionPerformed( java.awt.event.ActionEvent evt);

	abstract protected void DivActionPerformed( java.awt.event.ActionEvent evt);
}