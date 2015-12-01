package wpi.cs509.ui.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class StartPin  extends JPanel{

    /**
	 * 
	 */
	private static final long serialVersionUID = -3411503227151851491L;
	private int x;
	private int y;
	private Image img;
	private String filename;
	
    public StartPin(int x, int y) {
    	super();
    	
    	// Custom Setting
    	this.x = x;
    	this.y = y;
    	this.setBounds(0, 0, 1024, 730);
    	this.setBackground(new Color(Color.TRANSLUCENT));
    	this.setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
    	filename = "maps//startpin.png";
    	try {
			img = ImageIO.read(new File(filename));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	g.drawImage(img, this.x-12, this.y-23, 25, 25, null); 
        
    }

}
