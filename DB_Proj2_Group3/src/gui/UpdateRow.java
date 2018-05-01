package gui;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mySQLInterface.DataBase;

public class UpdateRow implements ActionListener, WindowListener {
	private JFrame frame;
	private static UpdateRow updateRow = null;

	public static UpdateRow createUpdateRowModule() {
		if (updateRow == null)
			updateRow = new UpdateRow();
		return updateRow;
	}

	public static UpdateRow getUpdateRowModule() {
		return updateRow;
	}

	private UpdateRow() {

	}

	public void closeWindow() {
		frame.dispose();
		this.frame = null;
		updateRow = null;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void openWindow() {
		this.createFrame();
		this.createLayout();
	}

	private void createFrame() {
		frame = new JFrame("DELETE ROW");
		frame.setSize(300, 300);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(pane);
		frame.addWindowListener(this);
		frame.setVisible(true);
		frame.setLocation(600, 210);
	}
	
	/***************************************************************************
	* Creates the layout by first creating the text fields and adds the name   *
	* of the column where the value will be stored.                            *
	* Then, adds the text fields to the pane after they are created.           *
	*                                                                          *
	* @param colCount: The total count of columns in the table.                *
	* @param textFields: An array of all text fields, the size is              *
	*              determined by the number of columns in the table.           *
	* @param colNames: Names of all columns in the table.                      *
	***************************************************************************/
	int colCount = Table.getTable().getColCount();
	JTextField[] textFields = new JTextField[Table.getTable().getColCount()];
	//JTextField[] pks;
	JTextField condition; //condition for the update
	String[] colNames = Table.getTable().getColNamesFromDB();
	String[] colTypes = Table.getTable().getColTypeFromDB();
	JPanel pane = new JPanel();
	GridBagConstraints con = new GridBagConstraints();
	JButton button = new JButton("SUBMIT");
	
	private void createLayout() {
		pane.setLayout(new GridBagLayout());
		con.fill = GridBagConstraints.HORIZONTAL;
		con.ipady = 10;
		con.ipadx = 100;
		for(int i = 0; i < colCount; i++)
		{
			textFields[i] = new JTextField(colNames[i]);
			con.gridy = i;
			pane.add(textFields[i], con);
		}
		int numKeys = 0; //Number of keys in the table
		for(int i = 0; i < colNames.length; i++)
		{
			if(Table.getTable().isPrimaryKey(i))
			{
				numKeys++;
			}
		}
		/*
		 * After getting the number of keys, can make an array of textfields for each.
		 * This way you get the primary key value you are looking for, and can still edit
		 * what the primary key was if you wanted to.
		 */
//		pks = new JTextField[numKeys];
//		for(int i = 0; i < numKeys; i++)
//		{
//			pks[i] = new JTextField("pk" + (i+1));
//			pane.add(pks[i], con);
//			con.gridy = i;
//		}
		
		con.gridy++;
		condition = new JTextField("Condition");
		pane.add(condition, con);
		con.gridy++;
		con.gridx++;
		pane.add(this.button, con);

		button.addActionListener(this);
	}

	/**
	 * Reads in the input from the text fields after the user hits submit.
	 * If you changed the value from what was originally in the text box,
	 * it adds it to the list of values to change.
	 * If the type of the column includes "char", that means it needs
	 * "'" on either side of it so it can be added to the db.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button) {

			try {
				ArrayList<String> pkValue = new ArrayList<String>();
				ArrayList<String> changes = new ArrayList<String>();
				ArrayList<String> values = new ArrayList<String>();
				ArrayList<String> pk = new ArrayList<String>();
				for(int i = 0; i < colNames.length; i++)
				{
					if(textFields[i].getText() != textFields[i].getName())
					{
//						if(Table.getTable().isPrimaryKey(i))
//						{
//							pk.add(colNames[i]);
//							pkValue.add(textFields[i].getText());
//						}
						if(colTypes[i].contains("char"))
						{
							values.add("'" + textFields[i].getText() + "'");
						}
						else
						{
							values.add(textFields[i].getText());
						}
						changes.add(colNames[i]);
					}
					
				}
				for(int i = 0; i < changes.size(); i++)
				{
					System.out.println("update " + Table.getTable().getName() + " set " + changes.get(i) + " = " + values.get(i) + " where " + condition.getText() + ";");
				}
				//Update <table> set <column> where <condition>
				//Updates a value in the table based on the condition
				for(int i = 0; i < changes.size(); i++)
				{
					DataBase.getDataBase()
					.AddData("update " + Table.getTable().getName() + "set " + changes.get(i) + " = " + values.get(i) + " where " + condition.getText() + ";") ;
				}
				Table.getTable().refresh();
			} catch (Exception e1) {

				e1.printStackTrace();
			}
			
			if (this.getFrame() != null)
				this.closeWindow();
		}

	}

	private boolean checkTextFields() {

		return true;
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
		if (e.getWindow() == frame) {
			updateRow = null;
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
