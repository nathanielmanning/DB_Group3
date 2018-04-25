import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class AddColumn implements ActionListener, WindowListener{
	
	private JFrame frame;
	private static AddColumn addCol = null;
	
	public static AddColumn createAddColumnModule()
	{
		if(AddColumn.addCol == null)	
			AddColumn.addCol = new AddColumn();
		return AddColumn.addCol;
	}
	
	public static  AddColumn getAddColumnModule()
	{
		return AddColumn.addCol;
	}
	
	private AddColumn(){
		
	}
	
	public void closeWindow()
	{
		frame.dispose();
		this.frame = null;
		AddColumn.addCol = null;
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
		frame = new JFrame("ADD COLUMN");
		frame.setSize(300, 100);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(pane);
		frame.addWindowListener(this);
		frame.setVisible(true);
		frame.setLocation(600, 210);
	}
	
	JTextField colName = new JTextField("COLUMN_NAME");
	JTextField DefaultValue = new JTextField("DEFAULT_VALUE");
	JTextField DataType = new JTextField("Data_Type");
	JPanel pane = new JPanel(new GridLayout(2,2));
	JButton button = new JButton("SUBMIT");
	
	private void createLayout()
	{
		pane.add(this.colName);
		pane.add(this.DataType);
		pane.add(this.DefaultValue);
		pane.add(this.button);
		
		button.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button)
		{
			Table.getTable().addColumn(colName.getText());
			
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
			AddColumn.addCol = null;
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
