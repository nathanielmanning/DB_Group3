/**
 * @author Joshua Bartle
 * This class creates the module for Adding Columns and connects it to the DB
 */

package gui;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import mySQLInterface.*;

// implement action listener and window listener
public class AddColumn implements ActionListener, WindowListener{
	
	// instance variable
	private JFrame frame;
	private static AddColumn addCol = null; // AddColumn instance
	
	/**
	 * Creates a single instance of add column
	 * @return AddColumn instance
	 */
	public static AddColumn createAddColumnModule()
	{
		if(AddColumn.addCol == null)	
			AddColumn.addCol = new AddColumn();
		return AddColumn.addCol;
	}
	
	/**
	 * get the instance of the column
	 * @return the instance of AddColumn
	 */
	public static  AddColumn getAddColumnModule()
	{
		return AddColumn.addCol;
	}
	
	/**
	 * Function for closing the window
	 */
	public void closeWindow()
	{
		frame.dispose(); // close the window
		this.frame = null;
		AddColumn.addCol = null;
	}

	/**
	 * Get the frame instance for the window
	 * @return, the jframe
	 */
	public JFrame getFrame()
	{
		return frame;
	}
	
	/**
	 * Function called when opening the window for AddColumn
	 */
	public void openWindow()
	{
		this.createFrame(); // create the frame
		this.createLayout(); // create the layout
	}
	
	/**
	 * Function for creating the jframe
	 */
	private void createFrame()
	{
		frame = new JFrame("ADD COLUMN");
		frame.setSize(300, 100);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(pane);
		frame.addWindowListener(this);
		frame.setVisible(true);
		frame.setLocation(600, 210);
	}
	
	// instance variable for the fields in the window
	JTextField colName = new JTextField("COLUMN_NAME"); // column name text field
	JTextField DefaultValue = new JTextField("DEFAULT_VALUE"); // default value (if any)
	JTextField DataType = new JTextField("Data_Type"); // data type text field
	JPanel pane = new JPanel(new GridLayout(2,2));
	JButton button = new JButton("SUBMIT"); // submit button
	
	/**
	 * Function for creating the layout for the panel within the frame
	 */
	private void createLayout()
	{
		pane.add(this.colName); // add the colName text field
		pane.add(this.DataType); // add the data type text field
		pane.add(this.DefaultValue); // add the default value text field
		pane.add(this.button); // add the submit button
		
		button.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button)
		{
			
				try {
					String command = "";
					// if default value is the parameter or nothing, set value to null
					if(this.DefaultValue.getText().equals("DEFAULT_VALUE") || this.DefaultValue.getText().equals(""))
						command = "alter table " + Table.getTable().getName() + " add " + this.colName.getText() + " " + this.DataType.getText();
					else //else set the value to the specified value
					{
						if(this.DataType.getText().contains("char")) // if char or varchar
							command = "alter table " + Table.getTable().getName() + " add " + this.colName.getText() + " " + this.DataType.getText() + " NOT NULL DEFAULT '" + this.DefaultValue.getText() + "'";
						else // if integer
							command = "alter table " + Table.getTable().getName() + " add " + this.colName.getText() + " " + this.DataType.getText() + " NOT NULL DEFAULT " + this.DefaultValue.getText();
					}
					DataBase.getDataBase().AddData(command); // send the command to the DB
					Table.getTable().addColumn(colName.getText()); // add the column to the table gui
				} catch (Exception e1) {
				
					e1.printStackTrace();
				}
				
			
			if(this.getFrame() != null)
				this.closeWindow();
		}
		
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
	}

	/**
	 *Function for when the window closes with x button
	 */
	@Override
	public void windowClosed(WindowEvent e) {
		if(e.getWindow() == frame) // set AddColumn to null
		{
			AddColumn.addCol = null;
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
