package gui;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Options implements ActionListener {
	
	JFrame frame;
	private static Options op = null;
	
	public static Options createOptionsModule()
	{
		if(Options.op == null)	
			Options.op = new Options();
		return Options.op;
	}
	
	public static Options getOptionsModule()
	{
		return Options.op;
	}
	
	private Options()
	{
	
	}
	
	public void closeOptions()
	{
		frame.dispose();
		Options.op = null;
		if(AddColumn.getAddColumnModule() != null)
			AddColumn.getAddColumnModule().closeWindow();
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
		for(int i = 0; i < button.length; i++)
		{
			button[i] = new JButton("button " + i);
			button[i].setBorder(BorderFactory.createLineBorder(Color.CYAN));
			button[i].setBackground(new Color(220, 220, 220));
			button[i].addActionListener(this);
			pane.add(button[i]);
		}
		button[0].setText("ADD ROW");
		button[1].setText("DELETE ROW");
		button[2].setText("ADD COLUMN");
		button[3].setText("DELETE COLUMN");
		button[4].setText("ADD/REMOVE PRIMARY KEY");
		button[5].setText("ADD/REMOVE FOREIGN KEY");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button[2])
		{
			if(AddColumn.getAddColumnModule() == null)
				AddColumn.createAddColumnModule().openWindow();
		}
		if(e.getSource() == button[3])
		{
			if(DeleteColumn.getDeleteColumnModule() == null)
				DeleteColumn.createDeleteColumnModule().openWindow();;
		}
	}

}
