package wpi.cs509.ui.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import javax.swing.JPanel;

public class RouteLine extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 879309659307886465L;
	
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private Color color;

	public RouteLine(Color color, int x1, int y1, int x2, int y2) {
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
	
	@Override public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		Stroke stroke = new BasicStroke(2.0f);
		g2d.setStroke(stroke);
		g.setColor(this.color);
		g.drawLine(x1, y1, x2, y2);
		
    }
}