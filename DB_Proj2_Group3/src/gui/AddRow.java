package gui;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mySQLInterface.DataBase;

public class AddRow implements ActionListener, WindowListener {
	private JFrame frame;
	private static AddRow addRow = null;

	public static AddRow createAddRowModule() {
		if (addRow == null)
			addRow = new AddRow();
		return addRow;
	}

	public static AddRow getAddRowModule() {
		return addRow;
	}

	private AddRow() {

	}

	public void closeWindow() {
		frame.dispose();
		this.frame = null;
		addRow = null;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void openWindow() {
		this.createFrame();
		this.createLayout();
	}

	private void createFrame() {
		frame = new JFrame("ADD ROW");
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
	JPanel pane = new JPanel(new GridLayout(10, 1));
	JButton button = new JButton("SUBMIT");
	
	private void createLayout() {
		for(int i = 0; i < colCount; i++)
		{
			textFields[i] = new JTextField(colNames[i]);
			pane.add(textFields[i]);
		}
		pane.add(this.button);

		button.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button) {

			try {
				String columns = Arrays.toString(Table.getTable().getColNamesFromDB()).replaceAll("\\[", "").replaceAll("\\]", "");
				System.out.println(columns);
//				String values = ;
//				DataBase.getDataBase()
//						.AddData("insert into Test " + this.colName.getText() + " (" + columns + " (" + values + ")") ;
//				Table.getTable().addRow();
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
			addRow = null;
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
