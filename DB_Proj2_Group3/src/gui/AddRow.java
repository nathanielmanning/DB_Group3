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

	public static AddRow getAddRModule() {
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
		frame.setSize(300, 100);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(pane);
		frame.addWindowListener(this);
		frame.setVisible(true);
		frame.setLocation(600, 210);
	}

	JTextField colName = new JTextField("COLUMN NAME");
	JTextField value = new JTextField("VALUE");
	JTextField not_null = new JTextField("NOT NULL");
	JTextField fk = new JTextField("FK");
	JTextField fk_Reference = new JTextField("FK TABLE REFERENCE");
	JTextField pk = new JTextField("PK");
	JPanel pane = new JPanel(new GridLayout(6, 2));
	JButton button = new JButton("SUBMIT");

	private void createLayout() {
		pane.add(this.colName);
		pane.add(this.value);
		pane.add(this.not_null);
		pane.add(this.fk);
		pane.add(this.fk_Reference);
		pane.add(this.pk);
		pane.add(this.button);

		button.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button) {

			try {
				String columns = Arrays.toString(Table.getTable().getColNamesFromDB()).replaceAll("\\[", "").replaceAll("\\]", "");
				//String values = Arrays.toString(Table.getTable().getRowValuesFromDB()).replaceAll("\\[", "").replaceAll("\\]", "");
			//	DataBase.getDataBase()
			//			.AddData("insert into Test " + this.colName.getText() + " (" + columns + " (" + values + ")") ;
			//	Table.getTable().addRow();
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
