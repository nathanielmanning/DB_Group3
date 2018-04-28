package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import mySQLInterface.DataBase;

public class TableSelect implements ActionListener{
	
	private JFrame frame;
	private static TableSelect sel = null;
	
	public String[] getTableNamesFromDB()
	{
		try {
			ResultSet r = DataBase.getDataBase().retrieveData("show tables;");
			ArrayList<String> list = new ArrayList<String>();
			while(r.next())
			{
				list.add(r.getString(1));
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
	
	public static TableSelect createSelectModule()
	{
		if(sel == null)
			sel = new TableSelect();
		return sel;
	}
	
	public static TableSelect getSelectModule()
	{
		return TableSelect.sel;
	}
	
	public void closeWindow()
	{
		if(Table.getTable() != null)
			Table.getTable().closeWindow();
		frame.dispose();
		this.frame = null;
		TableSelect.sel = null;
	}
	
	public JFrame getFrame()
	{
		return frame;
	}
	
	public void openWindow()
	{
		this.createFrame();
	}
	
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
	
	JPanel pane = new JPanel();
	JButton tableNames[];
	private JPanel createLayout()
	{
		String tables[] = this.getTableNamesFromDB();
		pane.setLayout(new GridBagLayout());
		GridBagConstraints con = new GridBagConstraints();
		this.tableNames = new JButton[tables.length];
		for(int i = 0; i < this.tableNames.length; i++)
		{
			con.gridx = 0;
			con.gridy = i;
			con.ipadx = 80;
			con.ipady = 30;
			con.anchor = GridBagConstraints.CENTER;
			this.tableNames[i] = new JButton(tables[i]);
			this.tableNames[i].addActionListener(this);
			pane.add(this.tableNames[i], con);
		}
		return pane;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		System.out.println(Table.getTable());
		if(Table.getTable() != null)
			Table.getTable().closeWindow();;
		
		for(int i = 0; i < tableNames.length; i++)
		{
			if(e.getSource() == tableNames[i])
			{
				System.out.println(tableNames[i].getText());
				Table.createTable(tableNames[i].getText(), null);
				Table.getTable().openWindow();
				Table.getTable().addMultiColumns(Table.getTable().getColNamesFromDB());
				return;
			}
			
		}
		
	}
	
}
