package com.example.expressiontree_plugin;

import com.example.expressiontree_plugin.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @class CalculatorGUIVerbose
 * 
 * @brief This class formats the verbose GUI for the Android version
 *        of the expression tree application.     
 */
public class CalculatorGUIVerbose extends Activity {
    /** 
     * TextView object displays the output of the expression Tree
     * calculate button.
     */
    private static TextView textview;
		
    /** EditText object intakes user input for the interpreter. */
    private static EditText edittext;
		
    /** Button 'Enter' triggers the evaluation of the expression. */
    private static Button b;
		
    /** RadioGroup. */
    private static RadioGroup rg1;
    private static RadioGroup rg2;
    private static RadioGroup rg3;
		
    /** RadioButton. */
    private static RadioButton rb;
		
    /** CheckBox. */
    private static CheckBox cb;
		
    /** InputHandler. */
    InputHandler handler;
		
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** Sets the content view to the xml file, activity_main. */
        setContentView(R.layout.verbose_activity_main); 

        /** 
         * Creates the TextView object and coordinates the xml layout
         * with the respective java code.
         */
        textview = (TextView)findViewById(R.id.tv);
			
        /**
         * Creates the EditText object and coordinates the xml layout
         * with the respective java code.
         */
        edittext = (EditText)findViewById(R.id.et);
			
        /** Creates generic button. */
        b = new Button(this);
			
        /** 
         * Initializes the Platform singleton with the appropriate
         * Platform strategy, which in this case will be the
         * AndroidPlatform.
         */
        com.example.expressiontree_framework.Platform.instance(new PlatformFactory(edittext,
                                               textview,
                                               this).makePlatform());

        /** Initializes the Options singleton. */
        String args[] = new String[] { "CalculatorGUIVerbose", "-v" };
        Options.instance().parseArgs(args);
		
        /** RadioGroup Format. */
        rg1 = (RadioGroup) findViewById(R.id.rad1);
        
        /** RadioGroup Eval. */
        rg2 = (RadioGroup) findViewById(R.id.rad2);

        /** RadioGroup print. */
        rg3 = (RadioGroup) findViewById(R.id.rad3);
			
        /**
         * Create an InputHandler to process the user input expression
         * where the edittext contains the input and the textview will
         * be the output.  Then prompt the user.
         */
        
                                                            
                                                            
                                                            InputDispatcher.instance().makeHandlerAndPromptUser(true, edittext, textview, this);
    }

    /** 
     * Creates on options menu to switch between verbose and succinct.
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        /** 
         * Inflate the menu; this adds items to the action bar if it
         * is present.
         */
         getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /** 
     * Determines the actions of the choices selected from the options
     * menu.
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;

        switch(item.getItemId()) {
        case R.id.Succinct:
            
                           
                           Toast.makeText(getApplicationContext(), "Switching to succinct mode", Toast.LENGTH_SHORT).show();
            /** 
             * Sets an intent for switching between the verbose
             * and succinct activities. 
             */
            intent = new Intent(getApplicationContext(),
                                CalculatorGUISuccinct.class);
            break;
       case R.id.Verbose:
           
        		   		  
    		   			  Toast.makeText(getApplicationContext(), "Switching to verbose mode", Toast.LENGTH_SHORT).show();
           /** 
            * Sets an intent for switching between the verbose
            * and succinct activities.
            */
           intent = new Intent(getApplicationContext(),
        		   			   CalculatorGUIVerbose.class);
           break;
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        return false;
    }
		
    /** Sets the action of the calculator button on click state. */
    public void enterButtonClicked(View view) {
        /** A short toast appears on the screen. */
        
                       
                       Toast.makeText(this, "Calculating "
                       + edittext.getText().toString()
                       + "...", Toast.LENGTH_SHORT).show();
			
        /** Process the user input expression. */
        InputDispatcher.instance().dispatchOneInput();
        edittext.setText("");
    }
		
    /** Clears the EditText or input view. */
    public void clearButtonClicked(View view) {
        unCheckAll();
        enableAllRadioGroups(true);
        enableCheckbox(true);
        textview.setText("");
        edittext.setText("");
    }
		
    /** Inputs a Character into the EditText. */
    public void characterButtonClicked(View view) {
        edittext.setText(edittext.getText().toString() +((Button)view).getText());
    }
		
    /** 
     * Retrieves the previous answer and adds the value to the Input
     * field.
     */
    public void ansButtonClicked(View view) {
        edittext.setText(edittext.getText().toString() + textview.getText().toString());
    }
		
    /** Quit Button. */
    public void quitButtonClicked(View view) {
        finish();
    }
		
    /** Back button removes character from input field. */
    public void backButtonClicked(View view) {
    	String text = edittext.getText().toString();
    	
    	if(!text.equals("")) {
            String textMinusLastChar =  text.substring(0, text.length() - 1);
            edittext.setText(textMinusLastChar);
        }
    }
		
    /** 
     * Set action of the format radio button to disable others not in
     * the group.
     */
    public void formatRadioButtonClick(View view) {
        /** A short toast appears on the screen. */
        
                       
                       Toast.makeText(this, "Format " + textOfSelectedRadioButton(rg1), Toast.LENGTH_SHORT).show();

    	/** Disable other GUI Fields */
         enableOtherRadioGroups(rg1, false);
    	enableCheckbox(false);
    	
        /** Process the user input expression. */
        InputDispatcher.instance().dispatchOneInput();
    }
		
    /** 
     * Set action of the print radio button to disable others not in
     * the group.
     */
    public void printRadioButtonClick(View view) {
        /** A short toast appears on the screen. */
        
                       
                       Toast.makeText(this, "Print " + textOfSelectedRadioButton(rg2), Toast.LENGTH_SHORT).show();
    	
    	/** Disable other GUI Fields */
         enableOtherRadioGroups(rg2, false);
    	enableCheckbox(false);
    	
        /** Process the user input expression. */
        InputDispatcher.instance().dispatchOneInput();
    }
		
    /** 
     * Set action of the eval radio button to disable others not in
     * the group.
     */
    public void evalRadioButtonClick(View view) {
        /** A short toast appears on the screen. */
        
                       
                       Toast.makeText(this, "Eval " + textOfSelectedRadioButton(rg3), Toast.LENGTH_SHORT).show();

    	/** Disable other GUI Fields */
         enableOtherRadioGroups(rg3, false);
    	enableCheckbox(false);
    	
        /** Process the user input expression. */
        InputDispatcher.instance().dispatchOneInput();
    }
		
    /** Set action of the checkbox to disable others not in the group. */
    public void setCheckBoxClick(View view) {
    	/** Disable all Radio Groups */
    	enableAllRadioGroups(false);
    }
		
    /** Enables/Disables every other radio group besides the given one */
    private void enableOtherRadioGroups(RadioGroup rg, boolean enable) {
    	if (!rg1.equals(rg))
    		 enableRadioGroup(rg1, enable);
    	
    	if (!rg2.equals(rg))
    		 enableRadioGroup(rg2, enable);
    	
    	if (!rg3.equals(rg))
    		 enableRadioGroup(rg3, enable);
    }
    
    /** Enables/Disables all radio buttons of a radio group */
    private void enableRadioGroup(RadioGroup rg, boolean enable) {
    	for (int i = 0; i < rg.getChildCount(); i++)
    		rg.getChildAt(i).setEnabled(enable);
    }

    /** Enables/Disables all radio groups */
    private void enableAllRadioGroups(boolean enable) {
		 enableRadioGroup(rg1, enable);
		 enableRadioGroup(rg2, enable);
		 enableRadioGroup(rg3, enable);
    }
    
    /** Enables/Disables the Check Box */
    private void enableCheckbox(boolean enable) {
    	CheckBox cb = (CheckBox) findViewById(R.id.cb);
    	cb.setEnabled(enable);
    }
    
    /** Returns the text of the selected radio button in a given radio group */
    private String textOfSelectedRadioButton (RadioGroup rg) {
    	int selectedId = rg.getCheckedRadioButtonId();
    	RadioButton selected = (RadioButton) findViewById(selectedId);
    	return selected.getText().toString();
    }
    
    /** Enables every button (debugging).*/
    private void enableAll() {
        for(int i = 0; i < rg3.getChildCount(); i++) {
            (rg3.getChildAt(i)).setEnabled(true);
            (rg1.getChildAt(i)).setEnabled(true);
            (rg2.getChildAt(i)).setEnabled(true);
				
            ((RadioButton) (rg1.getChildAt(i))).setChecked(false);
            ((RadioButton) (rg2.getChildAt(i))).setChecked(false);
            ((RadioButton) (rg3.getChildAt(i))).setChecked(false);
        }
    }
		
    /** Disables every button (debugging).*/
    private void disableAll() {
        for(int i = 0; i < rg3.getChildCount(); i++) {
            (rg3.getChildAt(i)).setEnabled(false);
            (rg1.getChildAt(i)).setEnabled(false);
            (rg2.getChildAt(i)).setEnabled(false);
				
            ((RadioButton) (rg1.getChildAt(i))).setChecked(false);
            ((RadioButton) (rg2.getChildAt(i))).setChecked(false);
            ((RadioButton) (rg3.getChildAt(i))).setChecked(false);
        }
    }
		
    /** Resets the GUI. */
    public void unCheckAll() {
    	 checkRadioGroup(rg1, false);
    	 checkRadioGroup(rg2, false);
    	 checkRadioGroup(rg3, false);
        CheckBox cb = (CheckBox) findViewById(R.id.cb);
        cb.setChecked(false);
    }
	
    /** Checks/Unckecks the radio buttons of a radio group */
    private void checkRadioGroup(RadioGroup rg, boolean check) {
    	for(int i = 0; i < rg.getChildCount(); i++)
    		((RadioButton) (rg.getChildAt(i))).setChecked(check);
    }
    
    /** Returns true if GUI is verbose. */
    public boolean returnVerbose() {
        return Options.instance().verbose();
    }
}