import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Table implements ActionListener{
	
	private JFrame frame;
	private ArrayList<String> col = new ArrayList<String>();
	private int colNum = 0;
	private JPanel pane;
	private String tableName = "";
	private static Table currentTable = null;
	
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
	
	public void addColumn(String name)
	{
		col.add(name);
		colNum = col.size();
		this.refreshWindow();
	}
	
	public void removeColumn(int index)
	{
		col.remove(index);
		colNum = col.size();
		this.refreshWindow();
	}
	
	private Table(String tableName, String[] colNames)
	{
		this.tableName = tableName;
		
		if(colNames != null && colNames.length > 0)
		{
			for(int i = 0; i < colNames.length; i++)
			{
				col.add(colNames[i]);
			}
			colNum = col.size();
		}
	}
	
	public void openWindow()
	{
		this.createFrame();
	}
	
	private void refreshWindow()
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
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.pack();
	}
	
	JButton options, exit;;
	DrawingPanel panel[];
	DrawingPanel panel2[];
	DrawingPanel panel3[];
	JRadioButton radioButtons[] = new JRadioButton[2];
	
	private JPanel createLayout()
	{
		pane = new JPanel();
		panel = new DrawingPanel[this.colNum];
		panel2 = new DrawingPanel[this.colNum];
		panel3 = new DrawingPanel[this.colNum];
		JPanel buttonPanel = new JPanel(new GridLayout(1,2));
		JPanel radioButtonPanel = new JPanel(new GridLayout(1,0));
		pane.setLayout(new GridBagLayout());
		GridBagConstraints con = new GridBagConstraints();
		pane.setBackground(Color.CYAN);
		con.gridx = colNum-1;
		con.gridy = 0;
		
		for(int j = 0; j < radioButtons.length; j++)
		{
			radioButtons[j] = new JRadioButton("button " + j);
			radioButtons[j].setMnemonic(KeyEvent.VK_1);
			radioButtons[j].setActionCommand("True");
			radioButtons[j].addActionListener(this);
			radioButtonPanel.add(radioButtons[j]);
		}
		
		
		for(int i = 0; i < radioButtons.length; i++)
		{
			
		}
		options = new JButton("OPTIONS");	
		exit = new JButton("EXIT");
		options.setBackground(new Color(220, 220, 220));
		exit.setBackground(new Color(220, 220, 220));
		pane.add(buttonPanel, con);
		
		buttonPanel.add(exit);
		buttonPanel.add(options);
		for(int i = 0; i < panel.length; i++)
		{
			panel[i] = new DrawingPanel();
			panel[i].setText(this.col.get(i));
			panel2[i] = new DrawingPanel();
			panel3[i] = new DrawingPanel();
			panel[i].setBackground(Color.white);
		}
		
		con.fill = GridBagConstraints.HORIZONTAL;
		for(int i = 0; i < panel.length; i++)
		{
			con.ipadx = 120;
			con.insets = new Insets(0, 0, 0, 0);
			con.gridx = i;
			con.gridy = 1;
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
			con.gridy = 2;
			pane.add(panel2[i], con);
			pane.add(panel3[i], con);
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
			if(op == null)
			{
				op = new Options();
				op.openWindow();
			}
		}
		if(e.getSource() == this.exit)
		{
			if(op != null)
				op.closeOptions();
			op = null;
			this.frame.dispose();
		}
	}
	
}
