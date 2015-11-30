package wpi.cs509.ui.components;



import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import wpi.cs509.dataManager.DataManager;

public class Zoompanel extends JPanel {

	   private static final long serialVersionUID = 1L;
	   private BufferedImage img;
	   private int width, height;

	   public Zoompanel(int larghezzaFrame, int altezzaFrame) {
	      setOpaque(false); 
	      width = larghezzaFrame;
	      height = altezzaFrame;

	      this.setPreferredSize(new Dimension(width, height));

	      img = loadImage();
	   }

	   public BufferedImage loadImage() {
	      BufferedImage bimg = null;
	      BufferedImage ret = null;

	      try {                
	          bimg = ImageIO.read(new File(DataManager.getMapPathByName("Campus", "0")));
	          bimg = this.resize(bimg, width, height);
	       } catch (IOException ex) {
	            // handle exception...
	       }
	      ret = new BufferedImage(bimg.getWidth(), bimg.getHeight(),
	            BufferedImage.TYPE_INT_ARGB);
	      Graphics2D g = ret.createGraphics();
	      g.drawImage(bimg, 0, 0, null);
	      g.dispose();

	      return ret;
	   }

	   @Override
	   protected void paintComponent(Graphics g) {
	     

	      Graphics2D g2d = (Graphics2D) g;
	      g2d.scale(MySlider.SCALE, MySlider.SCALE);
	      g2d.drawImage(img, 0, 0, null);
	      super.paintComponent(g2d);
	   }
	   public BufferedImage resize(BufferedImage img, int newW, int newH) { 
	       Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	       BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	       Graphics2D g2d = dimg.createGraphics();
	       g2d.drawImage(tmp, 0, 0, null);
	       g2d.dispose();

	       return dimg;
	   } 

	}
