package gui;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;


public class DrawingPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private String text[] = {"N/A"};
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		for(int i = 0; i < text.length; i++)
		{
			if(text[i] != null)
				g.drawString(text[i], 5, 20 * (i + 1));
			else
				g.drawString("NULL", 5, 20 * (i + 1));
		}
	}
	
	public void setText(String text[])
	{
		this.text = text;
	}
}
