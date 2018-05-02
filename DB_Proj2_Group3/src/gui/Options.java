/**
 * @author Joshua Bartle
 * This class creates the options module containing all options for a selected table
 */

package gui;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

// implement action listener
public class Options implements ActionListener {
	
	// instance variables
	JFrame frame;
	private static Options op = null; // options menu instance
	
	/**
	 * Function for creating a single instance of the options menu
	 * @return, the instance of options
	 */
	public static Options createOptionsModule()
	{
		if(Options.op == null)	
			Options.op = new Options();
		return Options.op;
	}
	
	/**
	 * Function for getting the instance of the options module
	 * @return, the instance of options
	 */
	public static Options getOptionsModule()
	{
		return Options.op;
	}
	
	/**
	 * Function for closing the options module window
	 */
	public void closeOptions()
	{
		frame.dispose(); //close the window
		Options.op = null; // set instance to null
		if(AddColumn.getAddColumnModule() != null) // if the add column module is open, close it
			AddColumn.getAddColumnModule().closeWindow();
	}
	
	/**
	 * Function for opening the module window
	 */
	public void openWindow()
	{
		this.createFrame(); // create the frame
		this.createLayout(); // set the layout
	}
	
	/**
	 * function for creating the frame
	 */
	private void createFrame()
	{
		frame = new JFrame("OPTIONS");
		frame.setSize(500, 500);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(pane);
		frame.setVisible(true);
		frame.setSize(200, 600);
		frame.setLocation(500, 200);
	}
	
	// instance variables
	JPanel pane = new JPanel(new GridLayout(6,1)); // jpanel with a 6x1 grid for options
	JButton button[] = new JButton[6]; //jbuttons for options to be selected
	
	/**
	 * Function for creating the buttons, the layout, and setting the text
	 */
	private void createLayout()
	{
		// set up the buttons and add them to the panel
		for(int i = 0; i < button.length; i++)
		{
			button[i] = new JButton("button " + i);
			button[i].setBorder(BorderFactory.createLineBorder(Color.CYAN));
			button[i].setBackground(new Color(220, 220, 220));
			button[i].addActionListener(this);
			pane.add(button[i]);
		}
		// name all of the buttons
		button[0].setText("ADD ROW");
		button[1].setText("DELETE ROW");
		button[2].setText("UPDATE ROW");
		button[3].setText("ADD COLUMN");
		button[4].setText("DELETE COLUMN");
		button[5].setText("ADD/REMOVE PRIMARY KEY");
		button[6].setText("ADD/REMOVE FOREIGN KEY");
	}

	/**
	 * Function for when an option is clicked for the selected table
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button[0])
		{
			if(AddRow.getAddRowModule() == null)
				AddRow.createAddRowModule().openWindow();
		}
		if(e.getSource() == button[1])
		{
			if(DeleteRow.getDeleteRowModule() == null)
				DeleteRow.createDeleteRowModule().openWindow();
		}
		if(e.getSource() == button[2])
		{
			if(UpdateRow.getUpdateRowModule() == null)
				UpdateRow.createUpdateRowModule().openWindow();
		}
		if(e.getSource() == button[3])
		{
			if(AddColumn.getAddColumnModule() == null)
				AddColumn.createAddColumnModule().openWindow();
		}
		if(e.getSource() == button[4])
		{
			if(DeleteColumn.getDeleteColumnModule() == null)
				DeleteColumn.createDeleteColumnModule().openWindow();;
		}
	}

}
