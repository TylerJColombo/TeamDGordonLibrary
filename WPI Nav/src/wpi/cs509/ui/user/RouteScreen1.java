package wpi.cs509.ui.user;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import wpi.cs509.dataManager.DataManager;
import wpi.cs509.dataModel.Point;
import wpi.cs509.routeFinder.RouteFinder;
import wpi.cs509.ui.components.HeaderPanel;
import wpi.cs509.ui.components.ImagePanel;
import wpi.cs509.ui.components.SolidPoint;
import wpi.cs509.ui.components.Zoomingpanel;
import wpi.cs509.ui.util.Util;

public class RouteScreen1 {
	
	
   
	private JFrame frame;
	ImagePanel  imagePanelCmap;
	Zoomingpanel   zoomcmap;
	String source;
	String destination;
	Point Odestination=null;
	Point Osource=null;
	SolidPoint destinationofSolid;
	SolidPoint sourceofSoLid;
    Boolean Pflag = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		 
  
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RouteScreen1 window = new RouteScreen1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RouteScreen1() {
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		ArrayList <Point> buildingoncampus =DataManager.getBuildingOnCampus();
		ArrayList <String> bns = new ArrayList<String>();
		String test = buildingoncampus.get(0).getName();
		for(Point bn:buildingoncampus){
			bns.add(bn.getName());	
		}
		
		
		JComboBox comboBox_1 = new JComboBox(bns.toArray());
		JComboBox comboBox = new JComboBox(bns.toArray());
		
		

		  
		frame = new JFrame();
		frame.setBounds(0, 0, 1024, 780);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(Color.decode("#F1F1F1"));
		frame.setResizable(false);
        ////////////
		// Header //
		////////////
		HeaderPanel headerPanel = new HeaderPanel("Route between different buildings in campus",false, frame); 
		headerPanel.setBounds(0, 0, 1024, 730);
		frame.getContentPane().add(headerPanel);
        
		
//imagepanel for map	

//		imagePanelCmap = new ImagePanel("maps//Campus.png", 640, 480);
		
		imagePanelCmap = new ImagePanel(DataManager.getMapPathByName("Campus", "Basement"), 640, 480);
		imagePanelCmap.setBackground(Color.decode("#F1F1F1"));
       		

		imagePanelCmap.setLayout(null);
		imagePanelCmap.setBounds(330, 180, 640, 480);
		frame.getContentPane().add(imagePanelCmap);
      
		
//label for source		
		JLabel lblSource = new JLabel("Source:");
		lblSource.setBounds(25, 180, 300, 20);
		frame.getContentPane().add(lblSource);

		
//combobox for source
		comboBox.setBounds(20, 210, 300, 20);
		frame.getContentPane().add(comboBox);
	    comboBox.setSelectedIndex(-1);

	    
	    comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED){
					if(Pflag==true){
						frame.remove(zoomcmap.zPanel);
						imagePanelCmap = new ImagePanel(DataManager.getMapPathByName("Campus", "Basement"), 640, 480);
						imagePanelCmap.setBackground(Color.decode("#F1F1F1"));
				       		

						imagePanelCmap.setLayout(null);
						imagePanelCmap.setBounds(330, 180, 640, 480);
						frame.getContentPane().add(imagePanelCmap);
						
//						imagePanelCmap.removeAll();
						imagePanelCmap.repaint();
						frame.repaint();
						Pflag=false;
					    System.out.println("remove1111");}
					   
				    if (sourceofSoLid!=null) {
						imagePanelCmap.remove(sourceofSoLid);
						System.out.println("remove");
						imagePanelCmap.repaint();
							
						}
//				}
			source =comboBox.getSelectedItem().toString();
			for(Point b:buildingoncampus){
				if(b.getName().equals(source))
				Osource=b;	
			}
//		    Osource=(Point) comboBox.getSelectedItem();
			
			
		
    		sourceofSoLid = new SolidPoint(Color.decode("#000000"), Osource.getX(), Osource.getY());  
 //   		destinationofSolid = new SolidPoint(Color.red,Odestination.getX(), Odestination.getY());  
     		if(destinationofSolid!=null){imagePanelCmap.add(destinationofSolid);}
    		imagePanelCmap.add(sourceofSoLid);
            imagePanelCmap.repaint();}
			
				}
			}
		);
		
//label for destination
		JLabel lblDestination = new JLabel("Destination:");
		lblDestination.setBounds(25, 240, 300, 20);
		frame.getContentPane().add(lblDestination);
		
		
//DESTINATION COMBOBOX
		comboBox_1.setBounds(20, 270, 300, 20);
	    frame.getContentPane().add(comboBox_1);
//	    comboBox_1.setSelectedIndex(0);
        comboBox_1.setSelectedIndex(-1);	   
	    
	    
		
	    comboBox_1.addItemListener(new ItemListener() {
	    
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED){
					if(Pflag==true){
						frame.remove(zoomcmap.zPanel);
						imagePanelCmap = new ImagePanel(DataManager.getMapPathByName("Campus", "Basement"), 640, 480);
						imagePanelCmap.setBackground(Color.decode("#F1F1F1"));
				       		

						imagePanelCmap.setLayout(null);
						imagePanelCmap.setBounds(330, 180, 640, 480);
						frame.getContentPane().add(imagePanelCmap);
//						imagePanelCmap.removeAll();
						imagePanelCmap.repaint();
						Pflag=false;
						frame.repaint();
						System.out.println("remove1111");}
					if (destinationofSolid!=null) {
						imagePanelCmap.remove(destinationofSolid);
						System.out.println("remove");
						imagePanelCmap.repaint();
							
						}
//				Odestination =(Point) comboBox_1.getSelectedItem();
				destination =comboBox_1.getSelectedItem().toString();
				for(Point c:buildingoncampus){
					if(c.getName().equals(destination))
					Odestination=c;	
				}
				destinationofSolid = new SolidPoint(Color.decode("#009966"),Odestination.getX(), Odestination.getY());  
//				sourceofSoLid = new SolidPoint(Color.red, Osource.getX(), Osource.getY()); 
	    	
	    	    imagePanelCmap.add(sourceofSoLid);
				imagePanelCmap.add(destinationofSolid);
	            imagePanelCmap.repaint();}
		}
		});
		
//BUTTON		
	    JPanel buttonPanel = new JPanel();
		frame.add(buttonPanel);
		buttonPanel.setBounds(20, 310, 300, 40);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.setBackground(Color.decode("#F1F1F1"));
	    
	    JButton btnFindingRoute = new JButton("Find Route");
		btnFindingRoute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				
               // use string of source and destination to get the their pointsid than send it to the RoutFinder
     		    ArrayList <Point> PointsofPath =RouteFinder.computePaths(Osource,DataManager.getGraphOfCampus(),Odestination);
     		    
//	            Pflag=Util.drawPath(imagePanelCmap,PointsofPath);
	            frame.remove(imagePanelCmap);	
	            zoomcmap=new Zoomingpanel(PointsofPath);
	    		
	    		zoomcmap.zPanel.setBounds(327, 173, 660, 575);
//	    	    zoomcmap.earthPanel.setBounds(330, 180, 640, 480);
                
	    	    frame.getContentPane().add(zoomcmap.zPanel);
//              frame.repaint();
                frame.setVisible(true);
	          
	            Pflag=true;
	           
			}
		});
		btnFindingRoute.setForeground(Color.decode("#F1F1F1"));
		btnFindingRoute.setBackground(Color.decode("#AB2A36"));
		btnFindingRoute.setOpaque(true);
		btnFindingRoute.setBorderPainted(false);
		buttonPanel.add(btnFindingRoute);
		

	
	
	}

 
	

}
