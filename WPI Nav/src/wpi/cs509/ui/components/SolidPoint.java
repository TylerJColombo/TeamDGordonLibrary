package wpi.cs509.ui.components;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class SolidPoint  extends JPanel{

    /**
	 * 
	 */
	private static final long serialVersionUID = -3411503227151851491L;
	private int x;
	private int y;
	private Color color;
	
    public SolidPoint(Color color, int x, int y) {
    	super();
    	
    	// Custom Setting
    	this.color = color;
    	this.x = x;
    	this.y = y;
    	this.setBounds(0, 0, 1024, 730);
    	this.setBackground(new Color(Color.TRANSLUCENT));
    	this.setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
    	g.setColor(this.color);
        g.fillOval(x-5, y-5, 10, 10);            
    }

}
