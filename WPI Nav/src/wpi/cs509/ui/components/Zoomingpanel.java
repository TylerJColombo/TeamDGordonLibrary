package wpi.cs509.ui.components;



import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import wpi.cs509.dataModel.Point;
import wpi.cs509.ui.util.Util;
import wpi.cs509.ui.util.Zutil;


public class Zoomingpanel extends JPanel{

 private static final long serialVersionUID = 1L;
 public static final int startWidth = 640;
 public static final int startHeight = 480;
 public JPanel   zPanel= new JPanel();
 public ArrayList<Point> pathofpoints;
 public Zoompanel earthPanel;
 private JScrollPane scroll;

 public Zoomingpanel(ArrayList<Point> path) {
     pathofpoints=path;
	   initComponents();
 }

 public void initComponents() {
   
    earthPanel = new Zoompanel(640, 480);
    earthPanel.setLayout(null);
    Zutil.drawPath(earthPanel,pathofpoints);

    scroll = new JScrollPane(earthPanel,
          JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
          JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    MySlider slider = new MySlider(this);
    slider.SCALE=1;
    slider.setPreferredSize(new Dimension(640, 35));
    
    zPanel.add(scroll, BorderLayout.CENTER);
    zPanel.add(slider, BorderLayout.SOUTH);
 }

 
 public void setEarthPanelSize(Dimension size) {
    earthPanel.setPreferredSize(size);
 }


 public void revalidateViewport() {
    scroll.getViewport().revalidate();
    scroll.getViewport().repaint();
 }
}



class MySlider extends JSlider implements ChangeListener {

 private static final long serialVersionUID = 1L;
 public static float SCALE = 1;
 private Zoomingpanel zoomingpanel;

 public MySlider(Zoomingpanel zoomingpanel) {
    super(JSlider.HORIZONTAL, 100, 200, 100);
    this.zoomingpanel = zoomingpanel;
    setMajorTickSpacing(50);
    setMinorTickSpacing(10);
    setPaintTicks(true);
    setPaintLabels(true);
    addChangeListener(this);
 }

 @Override
 public void stateChanged(ChangeEvent arg0) {
    int value = ((JSlider) arg0.getSource()).getValue();
    SCALE = (float) (value / 100.0);
    int w = (int) (Zoomingpanel.startWidth * MySlider.SCALE);
    int h = (int) (Zoomingpanel.startHeight * MySlider.SCALE);
    Dimension size = new Dimension(w, h);
    zoomingpanel.setEarthPanelSize(size); 
    zoomingpanel.revalidateViewport(); 
    zoomingpanel.earthPanel.removeAll();
    Zutil.drawPath(zoomingpanel.earthPanel, zoomingpanel.pathofpoints);
    
 }
}

