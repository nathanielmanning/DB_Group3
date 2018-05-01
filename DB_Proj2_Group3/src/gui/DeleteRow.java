package gui;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mySQLInterface.DataBase;

public class DeleteRow implements ActionListener, WindowListener {
	private JFrame frame;
	private static DeleteRow deleteRow = null;

	public static DeleteRow createDeleteRowModule() {
		if (deleteRow == null)
			deleteRow = new DeleteRow();
		return deleteRow;
	}

	public static DeleteRow getDeleteRowModule() {
		return deleteRow;
	}

	private DeleteRow() {

	}

	public void closeWindow() {
		frame.dispose();
		this.frame = null;
		deleteRow = null;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void openWindow() {
		this.createFrame();
		this.createLayout();
	}

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
	*                                                                          *
	* @param colCount: The total count of columns in the table.                *
	* @param textFields: An array of all text fields, the size is              *
	*              determined by the number of columns in the table.           *
	* @param colNames: Names of all columns in the table.                      *
	***************************************************************************/
	int colCount = Table.getTable().getColCount();
	JTextField[] textFields = new JTextField[Table.getTable().getColCount()];
	String[] colNames = Table.getTable().getColNamesFromDB();
	String[] colTypes = Table.getTable().getColTypeFromDB();
	JPanel pane = new JPanel();
	GridBagConstraints con = new GridBagConstraints();
	JButton button = new JButton("SUBMIT");
	
	private void createLayout() {
		pane.setLayout(new GridBagLayout());
		con.fill = GridBagConstraints.HORIZONTAL;
		con.ipady = 10;
		con.ipadx = 100;
		for(int i = 0; i < colCount; i++)
		{
			textFields[i] = new JTextField(colNames[i]);
			con.gridy = i;
			pane.add(textFields[i], con);
		}
		con.gridx+= 1;
		con.gridy+= 1;
		pane.add(this.button, con);

		button.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button) {

			try {
				ArrayList<String> values = new ArrayList<String>();
				ArrayList<String> pk = new ArrayList<String>();
				for(int i = 0; i < colNames.length; i++)
				{
					if(Table.getTable().isPrimaryKey(i))
					{
						pk.add(colNames[i]);
						if(colTypes[i].contains("char"))
						{
							values.add("'" + textFields[i].getText() + "'");
						}
						else
						{
							values.add(textFields[i].getText());
						}
					}
				}
				for(int i = 0; i < pk.size(); i++)
				{
					System.out.println("delete from " + Table.getTable().getName() + " where " + pk.get(i) + " = " + values.get(i) + ";");
				}
				for(int i = 0; i < pk.size(); i++)
				{
					DataBase.getDataBase()
					.AddData("delete from " + Table.getTable().getName() + "where " + pk.get(i) + " = " + values.get(i) + ";");
				}
				
//				System.out.println("update " + Table.getTable().getName() + "set " + colName + " = " + value + " where " + pk + " = "   + ";");
//				DataBase.getDataBase()
//						.AddData("update " + Table.getTable().getName() + "set " + colName + " = " + value + " where " + Table.getTable() + ";") ;
				Table.getTable().refresh();
			} catch (Exception e1) {

				e1.printStackTrace();
			}
			
			if (this.getFrame() != null)
				this.closeWindow();
		}

	}

	private boolean checkTextFields() {

		return true;
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
