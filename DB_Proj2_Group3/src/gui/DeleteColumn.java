/**
 * @author Joshua Bartle
 * This class is used to create the delete column module and connect it to the DB
 */

package gui;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import mySQLInterface.DataBase;

// implement actionlistener and windowlistener
public class DeleteColumn implements ActionListener, WindowListener{
	
	//instance variable
	private JFrame frame;
	private static DeleteColumn delCol = null; // delete column module instance
	
	/**
	 * Function for creating a single instance of the delete column module
	 * @return, the delete column instance
	 */
	public static DeleteColumn createDeleteColumnModule()
	{
		if(DeleteColumn.delCol == null)	
			DeleteColumn.delCol = new DeleteColumn();
		return DeleteColumn.delCol;
	}
	
	/**
	 * Function for getting the delete column instance
	 * @return, the instance of delete column
	 */
	public static  DeleteColumn getDeleteColumnModule()
	{
		return DeleteColumn.delCol;
	}
	
	/**
	 * Function for closing the window for the module
	 */
	public void closeWindow()
	{
		frame.dispose(); // close the window
		this.frame = null;
		DeleteColumn.delCol = null; // set instance to null
	}
	
	/**
	 * Function for getting instance of the jframe 
	 * @return, the frame
	 */
	public JFrame getFrame()
	{
		return frame;
	}
	
	/**
	 * Functin called when opening the module window
	 */
	public void openWindow()
	{
		this.createFrame(); //create the frame
	}

	/**
	 * Function for creating the jframe
	 */
	private void createFrame()
	{
		frame = new JFrame("Delete COLUMN");
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(this.createLayout());
		frame.addWindowListener(this);
		frame.setVisible(true);
		frame.setLocation(600, 210);
		frame.pack();
	}
	
	// instance variables
	JPanel pane = new JPanel(); // jpanel that hols the contents of the window
	JButton button = new JButton("SUBMIT"); // the submit button
	JRadioButton radButtons[]; // radio buttons for selecting columns to delete
	private JPanel createLayout()
	{
		String colNames[] = Table.getTable().getColNamesFromDB();
		radButtons = new JRadioButton[Table.getTable().getColCount()];
		pane.setLayout(new GridBagLayout());
		GridBagConstraints con = new GridBagConstraints();
		for(int i = 0; i < radButtons.length; i++)
		{
			// set grid bag contraints of radio buttons
			con.gridx = 0;
			con.gridy = i;
			con.weighty = 1;
			con.ipadx = 80;
			con.ipady = 20;
			con.anchor = GridBagConstraints.FIRST_LINE_START;
			radButtons[i] = new JRadioButton(colNames[i]); // set name of button
			radButtons[i].setName(colNames[i]); // name the button with the column name
			radButtons[i].setBackground(new Color(220, 220, 220));
			pane.add(radButtons[i], con); // add the buttons to the panel
			
		}
		pane.setBackground(new Color(220, 220, 220));
		con.gridy = radButtons.length;
		con.anchor = GridBagConstraints.CENTER;
		pane.add(this.button, con); // add the submit button
		
		button.addActionListener(this);
		return pane;
	}

	/**
	 * Action function for when the submit button is clicked
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button)
		{
			
				try { // loop through the radio buttons
					for(int i = 0; i < radButtons.length; i++)
					{
						if(radButtons[i].isSelected()) // if the radio button is selected
						{
							System.out.println(radButtons[i].getName());
							// send command to delete the selected column to the database
							DataBase.getDataBase().AddData("ALTER TABLE " + Table.getTable().getName() + " drop column " + radButtons[i].getName());
							Table.getTable().removeColumn(radButtons[i].getName()); // remove from the table gui
						}
					}
					
				} catch (Exception e1) {
				
					e1.printStackTrace();
				}
				
			
			if(this.getFrame() != null) // close the window when done
				this.closeWindow();
		}
		
	}


	@Override
	public void windowActivated(WindowEvent arg0) {
	}

	/**
	 * Function for when the window is closed with the x button
	 */
	@Override
	public void windowClosed(WindowEvent e) {
		if(e.getWindow() == frame) 
		{
			// set the delete Column instance to null
			DeleteColumn.delCol = null;
		}
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {	
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
	}
}
