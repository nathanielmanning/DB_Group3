package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainMenu implements WindowListener, ActionListener{
	private JFrame frame;
	private static MainMenu main = null;
	
	public static MainMenu createMainMenuModule()
	{
		if(main == null)
			main = new MainMenu();
		return main;
	}
	
	public static MainMenu getMainMenuModule()
	{
		return MainMenu.main;
	}
	
	public void closeWindow()
	{
		frame.dispose();
		this.frame = null;
		MainMenu.main = null;
	}
	
	public JFrame getFrame()
	{
		return frame;
	}
	
	public void openWindow()
	{
		this.createFrame();
		this.createLayout();
	}
	
	private void createFrame()
	{
		frame = new JFrame("Main Menu");
		frame.setSize(300, 100);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(pane);
		frame.addWindowListener(this);
		frame.setVisible(true);
		frame.setLocation(600, 210);
	}
	

	JPanel pane = new JPanel(new GridLayout(2,1));
	JButton getTables = new JButton("Table List");
	JButton customQuery = new JButton("Custom Query");
	private void createLayout()
	{
		this.getTables.setBackground(new Color(220, 220, 220));
		this.customQuery.setBackground(new Color(220, 220, 220));
		pane.add(this.getTables);
		pane.add(this.customQuery);
		this.getTables.addActionListener(this);
		this.customQuery.addActionListener(this);;
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		if(TableSelect.getSelectModule() != null)
			TableSelect.getSelectModule().closeWindow();
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == getTables)
		{
			if(TableSelect.getSelectModule() != null)
				TableSelect.createSelectModule().closeWindow();
			TableSelect.createSelectModule().openWindow();
		}
	}
}
