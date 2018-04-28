import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import mySQLInterface.DataBase;

public class DeleteColumn implements ActionListener, WindowListener{
	private JFrame frame;
	private static DeleteColumn delCol = null;
	
	public static DeleteColumn createDeleteColumnModule()
	{
		if(DeleteColumn.delCol == null)	
			DeleteColumn.delCol = new DeleteColumn();
		return DeleteColumn.delCol;
	}
	
	public static  DeleteColumn getDeleteColumnModule()
	{
		return DeleteColumn.delCol;
	}
	
	
	public void closeWindow()
	{
		frame.dispose();
		this.frame = null;
		DeleteColumn.delCol = null;
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
	
	
	JPanel pane = new JPanel();
	JButton button = new JButton("SUBMIT");
	JRadioButton radButtons[];
	private JPanel createLayout()
	{
		String colNames[] = Table.getTable().getColNamesFromDB();
		radButtons = new JRadioButton[Table.getTable().getColCount()];
		pane.setLayout(new GridBagLayout());
		GridBagConstraints con = new GridBagConstraints();
		for(int i = 0; i < radButtons.length; i++)
		{
			con.gridx = 0;
			con.gridy = i;
			con.weighty = 1;
			con.ipadx = 80;
			con.ipady = 20;
			con.anchor = GridBagConstraints.FIRST_LINE_START;
			radButtons[i] = new JRadioButton(colNames[i]);
			radButtons[i].setName(colNames[i]);
			radButtons[i].setBackground(new Color(220, 220, 220));
			pane.add(radButtons[i], con);
			pane.add(radButtons[i], con);
		}
		pane.setBackground(new Color(220, 220, 220));
		con.gridy = radButtons.length;
		con.anchor = GridBagConstraints.CENTER;
		pane.add(this.button, con);
		
		button.addActionListener(this);
		return pane;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button)
		{
			
				try {
					for(int i = 0; i < radButtons.length; i++)
					{
						if(radButtons[i].isSelected())
						{
							System.out.println(radButtons[i].getName());
							DataBase.getDataBase().AddData("ALTER TABLE " + Table.getTable().getName() + " drop column " + radButtons[i].getName());
							Table.getTable().removeColumn(radButtons[i].getName());
						}
					}
					
				} catch (Exception e1) {
				
					e1.printStackTrace();
				}
				
			
			if(this.getFrame() != null)
				this.closeWindow();
		}
		
	}
	
	private boolean checkTextFields()
	{
		
		return true;
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
		if(e.getWindow() == frame)
		{
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
