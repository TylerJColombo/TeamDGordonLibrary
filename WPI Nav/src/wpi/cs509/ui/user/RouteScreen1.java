package wpi.cs509.ui.user;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import com.sun.org.apache.xpath.internal.axes.ReverseAxesWalker;
import com.sun.xml.internal.fastinfoset.algorithm.BooleanEncodingAlgorithm;

import wpi.cs509.dataManager.DataManager;
import wpi.cs509.dataManager.DirectorGraph;
import wpi.cs509.dataModel.Graph;
import wpi.cs509.dataModel.Map;
import wpi.cs509.dataModel.Point;
import wpi.cs509.routeFinder.RouteFinder;
import wpi.cs509.ui.components.EndPin;
import wpi.cs509.ui.components.HeaderPanel;
import wpi.cs509.ui.components.ImagePanel;
import wpi.cs509.ui.components.Line;
import wpi.cs509.ui.components.SolidPoint;
import wpi.cs509.ui.components.StartPin;
import wpi.cs509.ui.components.Zoomingpanel;
import wpi.cs509.ui.util.Util;

public class RouteScreen1 {
	
	
   
	private JFrame frame;
	private JLabel starttext, endtext;
	ImagePanel  imagePanelCmap, startlable, endlable;
	Zoomingpanel   zoomcmap;
	String source;
	String destination;
	Point Odestination=null;
	Point Osource=null;
	EndPin destinationofSolid;
	StartPin sourceofSoLid;
    Boolean Pflag = false;
    JButton reverse = new JButton(new ImageIcon("maps//reverse.png"));
 

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
		
		final ArrayList <Point> buildingoncampus =DataManager.getBuildingOnCampus();
		
//		ArrayList <String> bns = new ArrayList<String>();
//		String test = buildingoncampus.get(0).getName();
//		for(Point bn:buildingoncampus){
//			bns.add(bn.getName());	
//		}
		
		
		final JComboBox<Point> comboBox_1 = new JComboBox();
		for(Point P: buildingoncampus){
			comboBox_1.addItem(P);
		}
		final JComboBox <Point>comboBox = new JComboBox();
		for(Point P: buildingoncampus){
			comboBox.addItem(P);
		}
		

		  
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
		JPanel panelnozoom=new JPanel();
		panelnozoom.setBounds(330, 180, 640, 480);
		panelnozoom.setLayout(null);
		
		imagePanelCmap = new ImagePanel(DataManager.getMapPathByName("Campus", "Basement"), 640, 480);
		imagePanelCmap.setBackground(Color.decode("#F1F1F1"));
		imagePanelCmap.setLayout(null);
		imagePanelCmap.setBounds(0, 0, 640, 480);
		
		panelnozoom.add(imagePanelCmap);	
		frame.getContentPane().add(panelnozoom);
      
		
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
						imagePanelCmap.setBounds(0, 0, 640, 480);
					    panelnozoom.removeAll();
					    panelnozoom.add(imagePanelCmap);
					    panelnozoom.repaint();
						frame.getContentPane().add(panelnozoom);
						

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
			Osource =(Point)comboBox.getSelectedItem();
			System.out.println(Osource.toString()+"source");

			
			
		
    		sourceofSoLid = new StartPin( Osource.getX(), Osource.getY());  
 //   		destinationofSolid = new SolidPoint(Color.red,Odestination.getX(), Odestination.getY());  
     		if(destinationofSolid!=null){imagePanelCmap.add(destinationofSolid);}
    		imagePanelCmap.add(sourceofSoLid);
            imagePanelCmap.repaint();
//            if(source!=null||destination!=null){
//            reverse.setEnabled(true);//}
			}
			
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
						imagePanelCmap.setBounds(0, 0, 640, 480);
						imagePanelCmap.repaint();
					    panelnozoom.removeAll();
						panelnozoom.add(imagePanelCmap);
						panelnozoom.repaint();
						frame.getContentPane().add(panelnozoom);
						
						
						Pflag=false;
						frame.repaint();
						System.out.println("remove1111");}
					if (destinationofSolid!=null) {
						imagePanelCmap.remove(destinationofSolid);
						System.out.println("remove");
						imagePanelCmap.repaint();
							
						}
				
				Odestination =(Point)comboBox_1.getSelectedItem();
				
				destinationofSolid = new EndPin(Odestination.getX(), Odestination.getY());  
                System.out.println(Odestination.toString()+"destination");
	    	
	    	    imagePanelCmap.add(sourceofSoLid);
				imagePanelCmap.add(destinationofSolid);
	            imagePanelCmap.repaint();
	            
//	            if(source!=null||destination!=null){
//	                reverse.setEnabled(true);
	                //}
   
	            }
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
				Graph rf=DirectorGraph.g;
     		    ArrayList <Point> PointsofPath =RouteFinder.computePaths(Osource,rf,Odestination);
               
     		    frame.remove(panelnozoom);	
	            zoomcmap=new Zoomingpanel(PointsofPath);
	    		
	    		zoomcmap.zPanel.setBounds(327, 173, 660, 575);         
	    	    frame.getContentPane().add(zoomcmap.zPanel);

                frame.setVisible(true);
	          
	            Pflag=true;
	           
			}
		});
		btnFindingRoute.setForeground(Color.decode("#F1F1F1"));
		btnFindingRoute.setBackground(Color.decode("#AB2A36"));
		btnFindingRoute.setOpaque(true);
		btnFindingRoute.setBorderPainted(false);
		buttonPanel.add(btnFindingRoute);
		
		
		//Button for reverse
		JButton reverse = new JButton(new ImageIcon("maps//reverse.png"));
		reverse.setBounds(150, 237, 25, 25);
		
		frame.add(reverse);
		
		reverse.setForeground(Color.decode("#F1F1F1"));
		reverse.setBackground(Color.decode("#AB2A36"));
		reverse.setOpaque(true);
		reverse.setBorderPainted(false);
//		reverse.setEnabled(false);
		
		reverse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Odestination = (Point)comboBox.getSelectedItem();
				Osource = (Point)comboBox_1.getSelectedItem();
				comboBox.setSelectedItem(Osource);
				comboBox_1.setSelectedItem(Odestination);
				btnFindingRoute.doClick();
				
				
				

			}
		});
		
		//Button for reverse
				JButton Buidingonmap = new JButton("ShowBuidings");
				Buidingonmap.setBounds(100, 525, 137, 25);
				
				frame.add(Buidingonmap);
				
                Buidingonmap.setForeground(Color.decode("#F1F1F1"));
				Buidingonmap.setBackground(Color.decode("#AB2A36"));
				Buidingonmap.setOpaque(true);
				Buidingonmap.setBorderPainted(false);
				Buidingonmap.addActionListener(new ActionListener() {
				int y=1;	
					@Override
					public void actionPerformed(ActionEvent e) {
						if(Pflag=true){
							frame.remove(zoomcmap.zPanel);
							imagePanelCmap = new ImagePanel(DataManager.getMapPathByName("Campus", "Basement"), 640, 480);
							imagePanelCmap.setBackground(Color.decode("#F1F1F1"));
					       	
							
						    
							imagePanelCmap.setLayout(null);
							imagePanelCmap.setBounds(0, 0, 640, 480);
							imagePanelCmap.repaint();
						    panelnozoom.removeAll();
							panelnozoom.add(imagePanelCmap);
							panelnozoom.repaint();
							frame.getContentPane().add(panelnozoom);
							frame.repaint();
						   
						}
					
					  if(y==1){
						  
						for(Point Buiding:buildingoncampus){
							
							SolidPoint P= new SolidPoint(Color.decode("#D55E00"),Buiding.getX(), Buiding.getY());  
				    	    imagePanelCmap.add(P);
				            imagePanelCmap.repaint();
				            Buidingonmap.setText("Hidebuildings");
				            y=2;	
						}}else{
							Buidingonmap.setText("Showbuildings");
						    panelnozoom.remove(imagePanelCmap);
						    imagePanelCmap = new ImagePanel(DataManager.getMapPathByName("Campus", "Basement"), 640, 480);
							imagePanelCmap.setBackground(Color.decode("#F1F1F1"));
					       		

							imagePanelCmap.setLayout(null);
							imagePanelCmap.setBounds(0, 0, 640, 480);
							panelnozoom.add(imagePanelCmap);
							panelnozoom.repaint();

							y=1;
					
				      }
					
						
					}
				});
				
			
				panelnozoom.addMouseListener(new MouseListener() {
					JLabel buidlinglabel;
					JPanel panellabel;
					@Override
			        public void mouseClicked(MouseEvent arg0) {
		//	            System.out.println(":MOUSE_CLICK_EVENT:" + e.getX() + "," + e.getY());
			        
			            
			        }
					@Override
					public void mouseEntered(MouseEvent arg0) {
						// Not used
					}
					@Override
					public void mouseExited(MouseEvent arg0) {
						// Not used
					}
					@Override
					public void mousePressed(MouseEvent e) {
						// Not used
					try{
						Point specialbuidling  =DataManager.findClosestPoint(6, e.getX(), e.getY()) ; 
						panellabel =new JPanel();
						panellabel.setBounds(e.getX(), e.getY(), 100, 70);
						panellabel.setLayout(null);
						ImagePanel buildingpic = new ImagePanel(DataManager.getMapPathByName("Campus", "Basement"), 100, 55);
						buildingpic.setBounds(0, 0, 100, 55);
						panellabel.add(buildingpic);
						
				                buidlinglabel= new JLabel(specialbuidling.getName());
				                buidlinglabel.setBounds(0,55 , 100, 15);
				                buidlinglabel.setLayout(null);
//				                buidlinglabel.setBackground(Color.decode("#F1F1F1"));
				                panellabel.add(buidlinglabel);
				                panellabel.setComponentZOrder(buidlinglabel, 0);
				        
				                imagePanelCmap.add(panellabel);
				                imagePanelCmap.setComponentZOrder(panellabel, 0);			 
				                imagePanelCmap.repaint();}
					catch(NullPointerException exp){
						JOptionPane.showMessageDialog(null, "the point you clicked has no building");
							
						}
					}
					@Override
					public void mouseReleased(MouseEvent arg0) {
						imagePanelCmap.remove(panellabel);
						imagePanelCmap.repaint();
						
					}
			    });
			
		
		startlable = new ImagePanel("maps//startpin.png", 20, 20);
		starttext = new JLabel("Start");
		starttext.setBounds(280, 500, 50, 20);
		startlable.setBounds(240, 500, 40, 50);
		
		endlable = new ImagePanel("maps//endpin.png", 20, 20);
		endtext = new JLabel("End");
		endtext.setBounds(280, 560, 50, 20);
		endlable.setBounds(240, 560, 40, 50);
		frame.add(endtext);
		frame.add(endlable);
		frame.add(startlable);
		frame.add(starttext);
		
		
	
	}

	


 
	

}

