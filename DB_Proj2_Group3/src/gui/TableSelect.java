/**
 * @author Joshua Bartle
 * Class used to create the table selection menu
 */
package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import mySQLInterface.DataBase;

public class TableSelect implements ActionListener{
	
	// instance variables
	private JFrame frame;
	private static TableSelect sel = null; // instance of the module
	
	/**
	 * Function for getting the table names from the database
	 * @return an array of the table names
	 */
	public String[] getTableNamesFromDB()
	{
		try {
			//my SQL command for getting the table list
			ResultSet r = DataBase.getDataBase().retrieveData("show tables;");
			ArrayList<String> list = new ArrayList<String>();
			while(r.next())
			{
				list.add(r.getString(1)); // add to a temp array list
			}
			String data[] = new String[list.size()];
			for(int j = 0; j < data.length; j++)
				data[j] = list.get(j);
			return data; // return the list
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Static function for creating a single instance of the table select module
	 * @return
	 */
	public static TableSelect createSelectModule()
	{
		if(sel == null)
			sel = new TableSelect();
		return sel;
	}
	
	/**
	 * Function for getting the table select module instance
	 * @return, the instance of the table select module
	 */
	public static TableSelect getSelectModule()
	{
		return TableSelect.sel;
	}
	
	/**
	 * Function call for closing the window
	 */
	public void closeWindow()
	{
		// if a table is active, close it
		if(Table.getTable() != null)
			Table.getTable().closeWindow();
		frame.dispose(); // close the window
		this.frame = null;
		TableSelect.sel = null;
	}
	
	/**
	 * Function for opening the window
	 */
	public void openWindow()
	{
		// create the frame
		this.createFrame();
	}

	/**
	 * Function that creates the jFrame
	 */
	private void createFrame()
	{
		frame = new JFrame("Table Select");
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		frame.add(this.createLayout());
		frame.pack();
	}
	
	// instance variables for jpanel and buttons for the tables
	JPanel pane = new JPanel();
	JButton tableNames[];
	private JPanel createLayout()
	{
		// array that stores table names retrieved from the DB
		String tables[] = this.getTableNamesFromDB();
		pane.setLayout(new GridBagLayout());
		GridBagConstraints con = new GridBagConstraints();
		
		// create the buttons for the tables in this loop
		this.tableNames = new JButton[tables.length];
		for(int i = 0; i < this.tableNames.length; i++)
		{
			// set layout specifications
			con.gridx = 0;
			con.gridy = i;
			con.ipadx = 80;
			con.ipady = 30;
			con.anchor = GridBagConstraints.CENTER;
			// create the buttons that will have the table names from the DB
			this.tableNames[i] = new JButton(tables[i]);
			this.tableNames[i].addActionListener(this);
			this.tableNames[i].setBackground(new Color(220, 220, 220));
			tableNames[i].setBorderPainted(false);
			pane.add(this.tableNames[i], con); // add the buttons
		}
		pane.setBackground(new Color(220, 220, 220));
		return pane;
	}

	/**
	 * Action Function for when buttons are clicked
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
	
		// if a table is already open then close it
		if(Table.getTable() != null)
			Table.getTable().closeWindow();;
		
		for(int i = 0; i < tableNames.length; i++)
		{
			// get the button source (the table that was chosen)
			if(e.getSource() == tableNames[i])
			{
				// create the new selected table
				Table.createTable(tableNames[i].getText(), null);
				Table.getTable().openWindow();
				// add the columns from the DB
				Table.getTable().addMultiColumns(Table.getTable().getColNamesFromDB());
				return;
			}
			
		}
		
	}
	
}
