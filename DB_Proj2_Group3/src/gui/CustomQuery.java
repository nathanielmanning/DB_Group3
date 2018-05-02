package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import mySQLInterface.DataBase;

/****************************************************
 * A window that allows the user to enter a custom  *
 * query and display results if needed.             *
 * @author Emmitt Frankenberry                      *
 *                                                  *
 ****************************************************/
public class CustomQuery implements ActionListener, WindowListener{
	private JFrame frame;
	private static CustomQuery query = null;
	
	/*
	 * Creates the module
	 */
	public static CustomQuery createCustomQueryModule()
	{
		if(CustomQuery.query == null)	
			CustomQuery.query = new CustomQuery();
		return CustomQuery.query;
	}
	
	/*
	 * returns the module
	 */
	public static  CustomQuery getCustomQueryModule()
	{
		return CustomQuery.query;
	}
	
	/*
	 * Properly disposes window
	 */
	public void closeWindow()
	{
		frame.dispose();
		this.frame = null;
		CustomQuery.query = null;
	}
	
	/*
	 * returns the frame
	 */
	public JFrame getFrame()
	{
		return frame;
	}
	
	/*
	 * sets up and opens the window
	 */
	public void openWindow()
	{
		this.createFrame();
	}
	
	/*
	 * holds the information for the window
	 */
	private void createFrame()
	{
		frame = new JFrame("Custom Query");
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(this.createLayout());
		frame.addWindowListener(this);
		frame.setVisible(true);
		frame.setLocation(600, 210);
		frame.setSize(480, 120);
		//frame.pack();
	}
	
	JPanel pane = new JPanel();
	JButton button = new JButton("SUBMIT");
	JTextField t = new JTextField("Enter Query Here");
	JRadioButton radButtons[];
	/*
	 * creates the layout of the window
	 */
	private JPanel createLayout()
	{
		
		Dimension s = new Dimension(440, 30);
		t.setPreferredSize(s);
		pane.add(t);
		pane.setBackground(new Color(220, 220, 220));
		pane.add(button);
		
		button.addActionListener(this);
		return pane;
	}
	
	/*
	 * runs the command when submit is pressed
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == button){
			String query = t.getText();
			frame.dispose();
			//System.out.println(query);
			if(query.contains("alter")||query.contains("ALTER")){
				DataBase.getDataBase().AddData(query);
			}else
			{
				ResultSet rs = DataBase.getDataBase().retrieveData(query);
				ArrayList<String> list = new ArrayList<String>();
				int num = 0;
				while(rs.next())
				{
					list.add(rs.getString(num));
					num++;
				}
				String data[] = new String[list.size()];
				for(int j = 0; j < data.length; j++)
					data[j] = list.get(j);
				JFrame f = new JFrame("Results");
				f.setResizable(false);
				f.setVisible(true);
				f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				f.add(this.createLayout());
				f.addWindowListener(this);
				f.setVisible(true);
				f.setLocation(600, 210);
				f.setSize(480, 120);
				JPanel p = new JPanel();
				for(int t =0; t<data.length; t++){
					p.add(data[t]+"\t");
				}
				f.add(p);
			}
			//runQuery(query);
			
		}
		
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
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
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}