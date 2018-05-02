/**
 * @author Joshua Bartle
 * Class for creating the main menu module
 */
package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import mySQLInterface.DataBase;

// implement action listener and windowlistener
public class MainMenu implements WindowListener, ActionListener{
	
	//instance variables
	private JFrame frame;
	private static MainMenu main = null; // the instance of the module mainmenu
	
	/**
	 * Function for creating a single instance of the main menu model
	 * @return, the instance of mainmenu
	 */
	public static MainMenu createMainMenuModule()
	{
		if(main == null)
			main = new MainMenu();
		return main;
	}
	
	/**
	 * Function for getting the instance of mainmenu
	 * @return the instance of mainmenu
	 */
	public static MainMenu getMainMenuModule()
	{
		return MainMenu.main;
	}
	
	/**
	 * Function for closing the mainmenu module
	 */
	public void closeWindow()
	{
		frame.dispose(); //close the window
		this.frame = null;
		MainMenu.main = null; // set instance of mainmenu to null
	}
	
	/**
	 * Function for getting the jframe instance
	 * @return, the frame instance
	 */
	public JFrame getFrame()
	{
		return frame;
	}
	
	/**
	 * Function for opening the mainmenu module window
	 */
	public void openWindow()
	{
		// create the frame
		this.createFrame();
		// create the layout
		this.createLayout();
	}
	
	/**
	 * Function for creating the frame
	 */
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
	
	//instance variables
	JPanel pane = new JPanel(new GridLayout(2,1)); // jpanel with 2x1 layout
	JButton getTables = new JButton("Table List"); //get Tables button
	JButton customQuery = new JButton("Custom Query"); // custom query button
	private void createLayout()
	{
		this.getTables.setBackground(new Color(220, 220, 220));
		this.customQuery.setBackground(new Color(220, 220, 220));
		pane.add(this.getTables); // add the gettables button
		pane.add(this.customQuery); // add the custom query button
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
		try {
			DataBase.getDataBase().closeConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
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

	/**
	 * Action Function for when a button is clicked
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == getTables) // if get tables is clicked
		{
			if(TableSelect.getSelectModule() != null) //if the module is open, close it
				TableSelect.createSelectModule().closeWindow();
			// open the module
			TableSelect.createSelectModule().openWindow();
		}
	}
}
