import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;


public class DrawingPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private String text = "N/A";
	
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.drawString(text, 5, 20);
	}
	
	public void setText(String text)
	{
		this.text = text;
	}
}
