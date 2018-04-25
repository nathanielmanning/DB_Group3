import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class AddColumn {
	
	JFrame frame;
	
	public void closeOptions()
	{
		frame.dispose();
	}
	
	
	public void openWindow()
	{
		this.createFrame();
		this.createLayout();
	}
	
	private void createFrame()
	{
		frame = new JFrame("OPTIONS");
		frame.setSize(500, 500);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(pane);
		frame.setVisible(true);
		frame.setSize(200, 600);
		frame.setLocation(500, 200);
	}
	
	JPanel pane = new JPanel(new GridLayout(6,1));
	JButton button[] = new JButton[6];
	
	private void createLayout()
	{
		
	}
}
