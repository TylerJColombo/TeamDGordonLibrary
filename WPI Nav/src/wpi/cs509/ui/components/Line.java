package wpi.cs509.ui.components;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Line extends JPanel
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 879309659307886465L;
	
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private Color color;

	public Line(Color color, int x1, int y1, int x2, int y2) {
		super();
		
		// Custom Setting
		this.color = color;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.setBounds(0, 0, 1024, 730);
		this.setBackground(new Color(Color.TRANSLUCENT));
		this.setOpaque(false);
		
	}
	
	@Override public void paint(Graphics g)
    {
        //draw in black
        g.setColor(this.color);
        //draw a centered horizontal line
        g.drawLine(x1, y1, x2, y2);
    }
}