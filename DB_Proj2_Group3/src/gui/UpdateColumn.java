import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**************************************************
 * Updates certain items that are already in the  *
 * database.                                      *
 * @author Emmitt Frankenberry                    *
 *                                                *
 **************************************************/
public class UpdateColumn implements ActionListener, WindowListener{
	private JFrame frame;
	private static UpdateColumn column = null;
	
	/*
	 * Creates the module
	 */
	public static UpdateColumn createUpdateColumnModule()
	{
		if(UpdateColumn.column == null)	
			UpdateColumn.column = new UpdateColumn();
		return UpdateColumn.column;
	}
	
	/*
	 * return the module
	 */
	public static  UpdateColumn getUpdateColumnModule()
	{
		return UpdateColumn.column;
	}
	
	/*
	 * disposes of the window properly
	 */
	public void closeWindow()
	{
		frame.dispose();
		this.frame = null;
		UpdateColumn.column = null;
	}
	
	/*
	 * returns the frame
	 */
	public JFrame getFrame()
	{
		return frame;
	}
	
	/*
	 * Sets up the frame
	 */
	public void openWindow()
	{
		this.createFrame();
	}
	
	/*
	 * Holds all the frame information
	 */
	private void createFrame()
	{
		frame = new JFrame("Update Item");
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(this.createLayout());
		frame.addWindowListener(this);
		frame.setVisible(true);
		frame.setLocation(600, 210);
		frame.setSize(480, 150);
		//frame.pack();
	}
	
	JPanel pane = new JPanel();
	JButton button = new JButton("SUBMIT");
	JTextField t = new JTextField("New Values(column1=value1, column2=value2)");
	JTextField t2 = new JTextField("Conditions");
	JRadioButton radButtons[];
	/*
	 * Creates the layout of the window
	 */
	private JPanel createLayout()
	{
		
		Dimension s = new Dimension(440, 30);
		t.setPreferredSize(s);
		t2.setPreferredSize(s);
		pane.add(t);
		pane.add(t2);
		pane.setBackground(new Color(220, 220, 220));
		pane.add(button);
		
		button.addActionListener(this);
		return pane;
	}
	
	/*
	 * Runs the update command when submit is pressed
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == button){
			String query = "update " + Table.getTable().getName() + " set "+ t.getText() + " where " + t2.getText();
			DataBase.getDataBase().AddData(query);
			closeWindow();
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
