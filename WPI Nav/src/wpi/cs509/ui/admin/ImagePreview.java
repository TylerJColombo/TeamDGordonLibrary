package wpi.cs509.ui.admin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class ImagePreview extends JPanel implements PropertyChangeListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFileChooser fc;
    private Image img;

    public ImagePreview(JFileChooser fc) {
         this.fc = fc;
         Dimension sz = new Dimension(200,300);
         setPreferredSize(sz);
    }

    public void propertyChange(PropertyChangeEvent evt) {
        try {
              File file = fc.getSelectedFile();
              if(file != null){
                  updateImage(file);
              }
             } catch (IOException ex) {
                  //ex.printStackTrace();
         }
    }
    public void updateImage(File file) throws IOException {
     if(file == null) {
          return;
     }
     img = ImageIO.read(file);
         repaint();
    }
    public void paintComponent(Graphics g) {
          g.setColor(Color.white);
          g.fillRect(0,0,getWidth(),getHeight());
     if(img != null) {
          int w = img.getWidth(null);
          int h = img.getHeight(null);
          int side = Math.max(w,h);
          double scale = 200.0/(double)side;
          w = (int)(scale * (double)w);
          h = (int)(scale * (double)h);
          g.drawImage(img,0,0,w,h,null);
  
          String dim = w + " x " + h;
          g.setColor(Color.black);
          g.drawString(dim,31,196);
          g.setColor(Color.white);
          g.drawString(dim,30,195);
          g.setColor(Color.black);
     }
}
}
