/**
 * @author Joshua Bartle
 * This class is used to create the tables with the information from the database
 */

package gui;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import mySQLInterface.DataBase;


//implements ActionListener for buttons and windowlistener for exiting window
public class Table implements ActionListener, WindowListener{
	
	//instance variables
	private JFrame frame;
	private ArrayList<String> col = new ArrayList<String>();
	private int colNum = 0;
	private JPanel pane;
	private String tableName = "";
	private static Table currentTable = null;
	
	/**
	 * This function determines if a column is a primary key
	 * @param colNum, the column we want to know if it is PK or not
	 * @return, true if primary key
	 */
	public boolean isPrimaryKey(int colNum)
	{
		String keys[] = this.getColumnAttributes(4);
		if(keys[colNum].equals("PRI"))
		{
			return true;
		}
		return false;
	}
	
	/**
	 * This function determines if a column is a foreign key
	 * @param colNum
	 * @return, true if foreign key
	 */
	public boolean isForeignKey(int colNum)
	{
		String keys[] = this.getColumnAttributes(4);
		if(keys[colNum].equals("FOR"))
		{
			return true;
		}
		return false;
	}
	
	/**
	 * This function is used to get an attribute from a column. This could be the column name, type, key type, etc.
	 * @param num, the attribute we want to know
	 * @return an array of the information returned from the DB
	 */
	public String[] getColumnAttributes(int num)
	{
		try {
			// get column info from database
			ResultSet r = DataBase.getDataBase().retrieveData("show columns from " + this.tableName + ";");
			ArrayList<String> list = new ArrayList<String>();
			while(r.next())
			{
				list.add(r.getString(num));
			}
			String data[] = new String[list.size()];
			for(int j = 0; j < data.length; j++)
				data[j] = list.get(j);
			return data;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Function for getting column names from the database
	 * @return, an array of the column names
	 */
	public String[] getColNamesFromDB()
	{
		return this.getColumnAttributes(1);
	}
	
	/**
	 * Function for getting the column types from the DB
	 * @return, an array of the column types
	 */
	public String[] getColTypeFromDB()
	{
		String type[] = this.getColumnAttributes(2);
		for(int i = 0; i < type.length; i++)
			type[i] = "Type = " + type[i];
		return type;
	}
	
	/**
	 * Function for getting the column data from the database
	 * @param colNum, the column number to retrieve the data for
	 * @return, an array of the data
	 */
	public String[] getColInfoFromDB(int colNum)
	{
		try {
			//mySQL statement to get the data
			ResultSet r = DataBase.getDataBase().retrieveData("select * from " + this.tableName + ";");
			ArrayList<String> list = new ArrayList<String>();
			while(r.next())
			{
				//add the returned data to an arraylist
				list.add(r.getString(colNum + 1));
			}
			//create array to store data in
			String data[] = new String[list.size()];
			for(int j = 0; j < data.length; j++)
				data[j] = list.get(j);
			//return the data
			return data;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Function for retrieving the number of columns for a table
	 * @return, the number of columns
	 */
	public int getColCount()
	{
		return this.colNum;
	}
	
	/**
	 * Function for retrieving the name of the table
	 * @return, the tableName
	 */
	public String getName()
	{
		return this.tableName;
	}
	
	/**
	 * 
	 * @param colNames
	 */
	public void addMultiColumns(String[] colNames)
	{
		if(colNames == null)
			return;
		for(int i = 0; i < colNames.length; i++)
		{
			this.col.add(colNames[i]);
			this.colNum++;
		}
		this.refreshWindow();
	}
	
	/**
	 * This is a static function used to create the table as a single instance.
	 * @param tableName, the name of the table
	 * @param colNames, the column names (this is retrieved from the db)
	 * @return, the table instance
	 */
	public static Table createTable(String tableName, String[] colNames)
	{
		if(Table.currentTable == null)
			Table.currentTable = new Table(tableName, colNames);
		return Table.currentTable;
	}
	
	/**
	 * Function for getting the current instance of the table being worked with
	 * @return, the table instance
	 */
	public static Table getTable()
	{
		return Table.currentTable;
	}
	
	/**
	 * Function for closing the window
	 */
	public void closeWindow()
	{
		// close all other modules associated with the table
		if(Options.getOptionsModule()!=null)
			Options.getOptionsModule().closeOptions();
		this.frame.dispose(); //dispose of the jframe
		Table.currentTable = null; // set instance to null
	}
	
	/**
	 * Function for adding a single column to the table
	 * @param name, the column name
	 */
	public void addColumn(String name)
	{
		col.add(name);
		colNum = col.size();
		this.refreshWindow(); //refresh the window to show changes
	}
	
	/**
	 * Function for removing a column from the table by name
	 * @param name
	 */
	public void removeColumn(String name)
	{
		for(int i = 0; i < col.size(); i++)
		{
			if(col.get(i).equals(name))
				col.remove(col.get(i));
		}
		colNum = col.size();
		this.refreshWindow(); // refresh the window to show changed
	}
	
	public void addRow() {
		this.refreshWindow(); // when a row is added by the addrow class the window is refreshed
		
	}
	
	/**
	 * Constructor for creating a table
	 * @param tableName, the name of the table
	 * @param colNames, the column names (from DB)
	 */
	private Table(String tableName, String[] colNames)
	{
		if(tableName != null)
			this.tableName = tableName;
		if(colNames == null) // if there are not columns just return
			return;
		if(colNames.length > 0)
		{
			if(colNames != null && colNames.length > 0)
			{
				for(int i = 0; i < colNames.length; i++)
				{
					col.add(colNames[i]);
				}
				colNum = col.size();
			}
		}
	}
	
	/**
	 * Function that creates the window by calling the create frame function
	 */
	public void openWindow()
	{
		this.createFrame();
	}
	
	/**
	 * Function for refreshing the window
	 */
	public void refreshWindow()
	{
		// remove the panel from the frame
		frame.remove(pane);
		// recreate the layout and add it to the frame
		frame.add(this.createLayout());
		frame.revalidate();
		frame.repaint();
		frame.pack();
	}

	/**
	 * Function for creating the JFrame
	 */
	private void createFrame()
	{
		frame = new JFrame(this.tableName);
		frame.setSize(500, 500);
		frame.add(this.createLayout());
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.addWindowListener(this);
		frame.pack();
	}
	
	// Some more instance variables for the layout of the table
	JButton options, exit;;
	// The drawing panels are a class that extends jpanel for painting components
	DrawingPanel panel[];
	DrawingPanel panel2[];
	DrawingPanel type[];
	JPanel keyPanel = new JPanel(new GridLayout(1, 2));
	
	/**
	 * 
	 * @return the panel that contains the new layout
	 */
	private JPanel createLayout()
	{
		pane = new JPanel();
		panel = new DrawingPanel[this.colNum];
		panel2 = new DrawingPanel[this.colNum];
		type = new DrawingPanel[this.colNum];
		
		JPanel buttonPanel = new JPanel(new GridLayout(1,2));
		pane.setLayout(new GridBagLayout());
		GridBagConstraints con = new GridBagConstraints();
		pane.setBackground(Color.CYAN);
		
		// some gridbag specifications
		con.ipady = 20;
		con.gridx = colNum-1;
		con.gridy = 0;
		
		options = new JButton("OPTIONS");	
		exit = new JButton("CLOSE");
		options.setBackground(new Color(220, 220, 220));
		exit.setBackground(new Color(220, 220, 220));
		pane.add(buttonPanel, con);
		
		buttonPanel.add(exit);
		buttonPanel.add(options);
		// add the column names and information panels in this loop
		for(int i = 0; i < panel.length; i++)
		{
			String text[] = {this.col.get(i)};
			if(this.isPrimaryKey(i))
				text[0] = text[0] + " (PK)";
			if(this.isForeignKey(i))
				text[0] = text[0] + " (FK)";
			
			panel[i] = new DrawingPanel();
			// set column names
			panel[i].setText(text);
			panel2[i] = new DrawingPanel();
			text = this.getColInfoFromDB(i);
			// set column data
			panel2[i].setText(text);
			panel[i].setBackground(Color.white);
			
			type[i] = new DrawingPanel();
			text = this.getColTypeFromDB();
			String tmp[] = {text[i]};
			type[i].setBackground(Color.white);
			// set column types
			type[i].setText(tmp);
		}
		
		// grid bag constraints for the data type boxes
		con.fill = GridBagConstraints.HORIZONTAL;
		for(int i = 0; i < panel.length; i++)
		{
			con.ipadx = 120;
			con.insets = new Insets(0, 0, 0, 0);
			con.gridx = i;
			con.gridy = 1;
			con.ipady = 25;
			type[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			pane.add(type[i], con);
		}
		//grid bag constraints for the column name panels
		for(int i = 0; i < panel.length; i++)
		{
			con.ipadx = 120;
			con.insets = new Insets(0, 0, 0, 0);
			con.gridx = i;
			con.gridy = 2;
			con.ipady = 25;
			panel[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			pane.add(panel[i], con);
		}
		//grid bag constraints for the data panels 
		for(int i = 0; i < panel.length; i++)
		{
			con.gridwidth = 1;
			con.gridheight = 1;
			con.ipadx = 120;
			con.ipady = 500;
			con.insets = new Insets(0, 0, 0, 0);
			con.gridx = i;
			con.gridy = 4;
			pane.add(panel2[i], con);
		}
		options.addActionListener(this);
		exit.addActionListener(this);
		return pane;//return the jpanel containing everything
	}

	/**
	 * Action function for buttons when they are clicked
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.options) //if we select options
		{
			//if window is already open, close it
			if(Options.getOptionsModule() != null) 
				Options.getOptionsModule().closeOptions();
			//if window is null (not open), open it
			if(Options.getOptionsModule() == null)
			{
				Options.createOptionsModule().openWindow();
			}
		}
		if(e.getSource() == this.exit) //if exit is clicked
		{
			// close any open options menu 
			if(Options.getOptionsModule()!=null)
				Options.getOptionsModule().closeOptions();
			//dispose of the window
			this.frame.dispose();
		}
	}
	
	@Override
	public void windowActivated(WindowEvent arg0) {
	}

	/**
	 * When the exit button is pressed
	 */
	@Override
	public void windowClosed(WindowEvent e) {
		if(e.getWindow() == frame)
		{
			// close any option menus
			if(Options.getOptionsModule()!=null)
				Options.getOptionsModule().closeOptions();
			this.frame.dispose(); // close the window
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
