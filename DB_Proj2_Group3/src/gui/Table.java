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
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import mySQLInterface.DataBase;

public class Table implements ActionListener, WindowListener{
	
	private JFrame frame;
	private ArrayList<String> col = new ArrayList<String>();
	private int colNum = 0;
	private JPanel pane;
	private String tableName = "";
	private static Table currentTable = null;
	
	
	public String[] getColumnAttributes(int num)
	{
		try {
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
	
	public String[] getColNamesFromDB()
	{
		return this.getColumnAttributes(1);
	}
	
	public String[] getColTypeFromDB()
	{
		String type[] = this.getColumnAttributes(2);
		for(int i = 0; i < type.length; i++)
			type[i] = "Type = " + type[i];
		return type;
	}
	
	public String[] getColInfoFromDB(int colNum)
	{
		try {
			ResultSet r = DataBase.getDataBase().retrieveData("select * from " + this.tableName + ";");
			ArrayList<String> list = new ArrayList<String>();
			while(r.next())
			{
				list.add(r.getString(colNum + 1));
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
	public int getColCount()
	{
		return this.colNum;
	}
	
	public String getName()
	{
		return this.tableName;
	}
	
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
	
	public static Table createTable(String tableName, String[] colNames)
	{
		if(Table.currentTable == null)
			Table.currentTable = new Table(tableName, colNames);
		return Table.currentTable;
	}
	
	public static Table getTable()
	{
		return Table.currentTable;
	}
	
	public void removeTable()
	{
		this.currentTable = null;
	}
	
	public void addColumn(String name)
	{
		col.add(name);
		colNum = col.size();
		this.refreshWindow();
	}
	
	public void removeColumn(String name)
	{
		for(int i = 0; i < col.size(); i++)
		{
			if(col.get(i).equals(name))
				col.remove(col.get(i));
		}
		colNum = col.size();
		this.refreshWindow();
	}
	
	public void addRow() {
		// TODO Auto-generated method stub
		
	}
	
	private Table(String tableName, String[] colNames)
	{
		if(tableName != null)
			this.tableName = tableName;
		if(colNames == null)
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
	
	public void openWindow()
	{
		this.createFrame();
	}
	
	public void refreshWindow()
	{
		frame.remove(pane);
		frame.add(this.createLayout());
		frame.revalidate();
		frame.repaint();
		frame.pack();
	}
	
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
	
	JButton options, exit;;
	DrawingPanel panel[];
	DrawingPanel panel2[];
	DrawingPanel type[];
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
		con.gridx = colNum-1;
		con.gridy = 0;
		
		options = new JButton("OPTIONS");	
		exit = new JButton("CLOSE");
		options.setBackground(new Color(220, 220, 220));
		exit.setBackground(new Color(220, 220, 220));
		pane.add(buttonPanel, con);
		
		buttonPanel.add(exit);
		buttonPanel.add(options);
		for(int i = 0; i < panel.length; i++)
		{
			String text[] = {this.col.get(i)};
			panel[i] = new DrawingPanel();
			panel[i].setText(text);
			panel2[i] = new DrawingPanel();
			text = this.getColInfoFromDB(i);
			panel2[i].setText(text);
			panel[i].setBackground(Color.white);
			
			type[i] = new DrawingPanel();
			text = this.getColTypeFromDB();
			String tmp[] = {text[i]};
			type[i].setBackground(Color.white);
			type[i].setText(tmp);
		}
		
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
		
		for(int i = 0; i < panel.length; i++)
		{
			con.gridwidth = 1;
			con.gridheight = 1;
			con.ipadx = 120;
			con.ipady = 500;
			con.insets = new Insets(0, 0, 0, 0);
			con.gridx = i;
			con.gridy = 3;
			pane.add(panel2[i], con);
		}
		options.addActionListener(this);
		exit.addActionListener(this);
		return pane;	
	}

	Options op = null;
			
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.options)
		{
			if(Options.getOptionsModule() != null)
				Options.getOptionsModule().closeOptions();
			if(Options.getOptionsModule() == null)
			{
				Options.createOptionsModule().openWindow();
			}
		}
		if(e.getSource() == this.exit)
		{
			if(Options.getOptionsModule()!=null)
				Options.getOptionsModule().closeOptions();
			this.frame.dispose();
		}
	}
	
	@Override
	public void windowActivated(WindowEvent arg0) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
		if(e.getWindow() == frame)
		{
			if(Options.getOptionsModule()!=null)
				Options.getOptionsModule().closeOptions();
			this.frame.dispose();
			Table.currentTable = null;
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
