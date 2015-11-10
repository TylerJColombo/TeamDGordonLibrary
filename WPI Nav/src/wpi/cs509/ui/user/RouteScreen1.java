
package wpi.cs509.ui.user;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;

import wpi.cs509.dataManager.DataManager;
import wpi.cs509.dataModel.Point;
import wpi.cs509.routeFinder.RouteFinder;
import wpi.cs509.ui.components.HeaderPanel;
import wpi.cs509.ui.components.ImagePanel;
import wpi.cs509.ui.components.Line;
import wpi.cs509.ui.components.SolidPoint;
import wpi.cs509.ui.util.*;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import com.sun.xml.internal.ws.org.objectweb.asm.Label;

import javafx.scene.control.ComboBox;
import sun.launcher.resources.launcher;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.awt.event.ItemEvent;

public class RouteScreen1 {

	private JFrame frame;
	JComboBox comboBox_1 = new JComboBox(DataManager.getBuildings().toArray());
	JComboBox comboBox = new JComboBox(DataManager.getBuildings().toArray());
	ImagePanel  imagePanelCmap;
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

		  
		frame = new JFrame();
		frame.setBounds(0, 0, 1024, 730);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
        ////////////
		// Header //
		////////////
		HeaderPanel headerPanel = new HeaderPanel("Route between different buildings in campus",false, frame); 
		headerPanel.setBounds(0, 0, 1024, 730);
		frame.getContentPane().add(headerPanel);
        
		
//imagepanel for map		

		//imagePanelCmap = new ImagePanel("maps//campusmap.gif", 640, 480);
		imagePanelCmap = new ImagePanel(DataManager.getMapPathByName("Campus,Basement"), 640, 480);
       		

		imagePanelCmap.setLayout(null);
		imagePanelCmap.setBounds(330, 180, 640, 480);
		frame.getContentPane().add(imagePanelCmap);

		
//label for source		
		JLabel lblSource = new JLabel("source:");
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
						imagePanelCmap.removeAll();
						imagePanelCmap.repaint();
						Pflag=false;
					    System.out.println("remove1111");}
					   
				    if (sourceofSoLid!=null) {
						imagePanelCmap.remove(sourceofSoLid);
						System.out.println("remove");
						imagePanelCmap.repaint();
							
						}
//				}
			source =comboBox.getSelectedItem().toString();
			Osource = DataManager.getPointByBuildingName(source);
			
		
    		sourceofSoLid = new SolidPoint(Color.red, Osource.getX(), Osource.getY());  
 //   		destinationofSolid = new SolidPoint(Color.red,Odestination.getX(), Odestination.getY());  
     		if(destinationofSolid!=null){imagePanelCmap.add(destinationofSolid);}
    		imagePanelCmap.add(sourceofSoLid);
            imagePanelCmap.repaint();}
			
				}
			}
		);
		
//label for destination
		JLabel lblDestination = new JLabel("destination:");
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
						imagePanelCmap.removeAll();
						imagePanelCmap.repaint();
						Pflag=false;
						System.out.println("remove1111");}
					if (destinationofSolid!=null) {
						imagePanelCmap.remove(destinationofSolid);
						System.out.println("remove");
						imagePanelCmap.repaint();
							
						}
				destination =comboBox_1.getSelectedItem().toString();
			    Odestination = DataManager.getPointByBuildingName(destination);
				destinationofSolid = new SolidPoint(Color.red,Odestination.getX(), Odestination.getY());  
//				sourceofSoLid = new SolidPoint(Color.red, Osource.getX(), Osource.getY()); 
	    	
	    	    imagePanelCmap.add(sourceofSoLid);
				imagePanelCmap.add(destinationofSolid);
	            imagePanelCmap.repaint();}
		}
		});
		
//BUTTON		
		JButton btnFindingRoute = new JButton("Finding Route");
		btnFindingRoute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
               // use string of source and destination to get the their pointsid than send it to the RoutFinder
     		    ArrayList <Point> PointsofPath =RouteFinder.computePaths(Osource,DataManager.getGraphOfCampus(),Odestination);
	            Pflag=Util.drawPath(imagePanelCmap,PointsofPath);		
			}
		});
		btnFindingRoute.setBounds(20, 420, 300, 40);
		btnFindingRoute.setForeground(Color.decode("#F1F1F1"));
		btnFindingRoute.setBackground(Color.decode("#AB2A36"));
		btnFindingRoute.setOpaque(true);
		btnFindingRoute.setBorderPainted(false);
		frame.getContentPane().add(btnFindingRoute);
		

	
	
	}

 
	

}
