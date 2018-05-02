/**
 * @author Joshua Bartle
 * Class for painting text onto a jpanel 
 */

package gui;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

// extend jpanel
public class DrawingPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private String text[] = {"N/A"}; // an array of text
	
	/**
	 * Function for painting the jpanel component
	 */
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		// write the text to the panel, each index counts as a new line
		for(int i = 0; i < text.length; i++)
		{
			if(text[i] != null)
				g.drawString(text[i], 5, 20 * (i + 1));
			else
				g.drawString("NULL", 5, 20 * (i + 1));
		}
	}
	
	/**
	 * Function for setting the string of text to be printed
	 * @param text, an array of text (each index is a new line)
	 */
	public void setText(String text[])
	{
		this.text = text;
	}
}
