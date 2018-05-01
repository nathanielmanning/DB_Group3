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

/****************************************************
 * A window that allows the user to enter a custom  *
 * query and display results if needed.             *
 * @author Emmitt Frankenberry                      *
 *                                                  *
 ****************************************************/
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
			frame.dispose();
			//System.out.println(query);
			if(query.contains("alter")||query.contains("ALTER")){
				DataBase.getDataBase().addData(query);
			}else
			{
				ResultSet rs = DataBase.getDataBase().retrieveData(query);
				ArrayList<String> list = new ArrayList<String>();
				while(rs.next())
				{
					list.add(rs.getString(num));
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
				JPane p = new JPane();
				for(int t =0; t<data.length; t++){
					p.add(data[t]+"\t");
				}
				f.add(p);
			}
			//runQuery(query);
			
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