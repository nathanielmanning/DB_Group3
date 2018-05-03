/**
 * DeleteRow.java
 * 
 * Deletes a row in the table
 * 
 * @author Nathaniel Manning
 */
package gui;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mySQLInterface.DataBase;

public class DeleteRow implements ActionListener, WindowListener {
	private JFrame frame;
	private static DeleteRow deleteRow = null;

	/*
	 * returns the instance of Addrow.
	 * If there wasn't an instance, it creates one.
	 */
	public static DeleteRow createDeleteRowModule() {
		if (deleteRow == null)
			deleteRow = new DeleteRow();
		return deleteRow;
	}

	/*
	 * returns the instance of AddRow
	 */
	public static DeleteRow getDeleteRowModule() {
		return deleteRow;
	}

	private DeleteRow() {

	}

	/*
	 * closes the window by getting rid of the frame
	 */
	public void closeWindow() {
		frame.dispose();
		this.frame = null;
		deleteRow = null;
	}

	/*
	 * returns the frame for the window
	 */
	public JFrame getFrame() {
		return frame;
	}

	/*
	 * On call, creates the window and the layout for the window
	 * to be displayed.
	 */
	public void openWindow() {
		this.createFrame();
		this.createLayout();
	}

	/*
	 * creates the frame for the update row window
	 */
	private void createFrame() {
		frame = new JFrame("DELETE ROW");
		frame.setSize(300, 300);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(pane);
		frame.addWindowListener(this);
		frame.setVisible(true);
		frame.setLocation(600, 210);
	}
	
	/***************************************************************************
	* Creates the layout by first creating the text fields and adds the name   *
	* of the column where the value will be stored.                            *
	* Then, adds the text fields to the pane after they are created.           *
	***************************************************************************/
	JTextField condition;
	JPanel pane = new JPanel();
	GridBagConstraints con = new GridBagConstraints();
	JButton button = new JButton("SUBMIT");
	
	private void createLayout() {
		pane.setLayout(new GridBagLayout());
		con.fill = GridBagConstraints.HORIZONTAL;
		con.ipady = 10;
		con.ipadx = 100;
		condition = new JTextField("Condition");
		con.gridy = 0;
		pane.add(condition, con);
		con.gridx+= 1;
		con.gridy+= 1;
		pane.add(this.button, con);

		button.addActionListener(this);
	}

	//when called, deletes a row based on some condition it is given
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button) {

			try {
				if(condition.getText() != condition.getName())
				{
					System.out.println("delete from " + Table.getTable().getName() + " where " + condition.getText() + ";");
					
					DataBase.getDataBase()
					.AddData("delete from " + Table.getTable().getName() + " where " + condition.getText()+ ";");
				}
				else
				{
					System.out.println("Going to need a condition to do that!");
				}
				Table.getTable().refresh();
			} catch (Exception e1) {

				e1.printStackTrace();
			}
			
			if (this.getFrame() != null)
				this.closeWindow();
		}

	}

	@Override
	public void windowActivated(WindowEvent arg0) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
		if (e.getWindow() == frame) {
			deleteRow = null;
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
