import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class CustomQuery implements ActionListener, WindowListener{
	private JFrame frame;
	private static CustomQuery query = null;
	
	public static CustomQuery createCustomQueryModule()
	{
		if(CustomQuery.query == null)	
			CustomQuery.query = new CustomQuery();
		return CustomQuery.query;
	}
	
	public static  CustomQuery getCustomQueryModule()
	{
		return CustomQuery.query;
	}
	
	
	public void closeWindow()
	{
		frame.dispose();
		this.frame = null;
		CustomQuery.query = null;
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
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == button){
			String query = t.getText();
			//System.out.println(query);
			runQuery(query);
		}
		
	}
	
	public void runQuery(String q){
		DataBase.getDataBase().createPreparedStatement(q);
		DataBase.getDataBase().executePreparedStatement();
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