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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import wpi.cs509.dataManager.DataManager;
import wpi.cs509.dataModel.Graph;
import wpi.cs509.dataModel.Map;
import wpi.cs509.dataModel.Point;
import wpi.cs509.routeFinder.RouteFinder;
import wpi.cs509.ui.components.EndPin;
import wpi.cs509.ui.components.HeaderPanel;
import wpi.cs509.ui.components.ImagePanel;
import wpi.cs509.ui.components.StartPin;
import wpi.cs509.ui.util.Util;
public class RouteScreen4 {

	private JFrame frame;
	private JComboBox<String> fromBuildingSelection, toBuildingSelection, tofloorSelection,fromfloorSelection;
	private JComboBox<Point> sourceSelection,destinationSelection;
	private ArrayList<String> buildingList, floorSelected;
	private ArrayList<Point> fromlocations,tolocations, route, mapToDisplayRoute;
	private int mapToDisplayIndex;
	private StartPin source1;
	private EndPin destination1;
	private ImagePanel currentFloorMap, startlable, endlable;
	private String buildingselected, fromfloorselected, fromfilename, tofloorselected, tofilename;
	private JLabel starttext, endtext;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RouteScreen4 window = new RouteScreen4();
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
	public RouteScreen4() {
		initialize();
		this.frame.setVisible(true);
	}
    
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		fromBuildingSelection = new JComboBox<String>();
		fromfloorSelection = new JComboBox<String>();
		sourceSelection = new JComboBox<Point>();
		toBuildingSelection = new JComboBox<String>();
		tofloorSelection = new JComboBox<String>();
		destinationSelection = new JComboBox<Point>();
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.decode("#F1F1F1"));
		frame.setBounds(0, 0, 1024, 730);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		////////////
		// Header //
		////////////
		HeaderPanel headerPanel = new HeaderPanel("Route between any two locations", false, frame);
		headerPanel.setBounds(0, 0, 1024, 730);
		frame.getContentPane().add(headerPanel);
		
		///////////////
		// Map Panel //
		///////////////
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(350, 180, 640, 480);
		panel_4.setLayout(null);
		frame.add(panel_4);
		
		JPanel panelPrev = new JPanel();
		panelPrev.setBounds(350, 660, 250, 50);
		panelPrev.setLayout(null);
		panelPrev.add(new JLabel("prev"));
		frame.add(panelPrev);
		
		JPanel panelCurrent = new JPanel();
		panelCurrent.setBounds(600, 660, 140, 50);
		panelCurrent.setLayout(null);
		panelCurrent.add(new JLabel("test"));
		frame.add(panelCurrent);
		
		JPanel panelNext = new JPanel();
		panelNext.setBounds(740, 660, 250, 50);
		panelNext.setLayout(null);
		panelNext.add(new JLabel("next"));
		frame.add(panelNext);
		
		/////////////////////
		// Left side panel //
		/////////////////////
		JPanel difFloorSearchPanel = new JPanel();
		difFloorSearchPanel.setLayout(null);
		difFloorSearchPanel.setBounds(0, 180, 1024, 480);
		frame.getContentPane().add(difFloorSearchPanel);
		
		// Control Panel
		JPanel difFloorControl = new JPanel();
		difFloorSearchPanel.add(difFloorControl);
		difFloorControl.setBounds(0, 0, 350, 480);
		difFloorControl.setLayout(null);
		
		// From building Label
		JLabel building = new JLabel("From Building");
		difFloorControl.add(building);
		building.setBounds(25, 0, 300, 20);
		
		// From building List
		difFloorControl.add(fromBuildingSelection);
		fromBuildingSelection.setBounds(20, 30, 300, 20);
		buildingList = DataManager.getBuildings();
		fromBuildingSelection.addItem(null);
		for(int i=0;i<buildingList.size();i++){
			fromBuildingSelection.addItem(buildingList.get(i));
		}
		
		// From building listener 
		fromBuildingSelection.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				fromfloorSelection.removeAllItems();
				sourceSelection.removeAllItems();
				if(e.getStateChange()==ItemEvent.SELECTED){
					buildingselected = fromBuildingSelection.getSelectedItem().toString();
					floorSelected = DataManager.getFloorsMapsByBuildingName(buildingselected);
					for(int i = 0;i<floorSelected.size();i++){
						fromfloorSelection.addItem(floorSelected.get(i));
					}
				}
				System.out.println("You have chosen from building"+" "+fromBuildingSelection.getSelectedItem());
			}
		});
		
		// From floor Label
		JLabel fromfloor = new JLabel("From Floor");
		difFloorControl.add(fromfloor);
		fromfloor.setBounds(25, 60, 300, 20);

		// From floor List
		difFloorControl.add(fromfloorSelection);
		fromfloorSelection.setBounds(20, 90, 300, 20);
		fromfloorSelection.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED){
					buildingselected = fromBuildingSelection.getSelectedItem().toString();
					fromfloorselected = fromfloorSelection.getSelectedItem().toString();
					fromlocations = DataManager.getLocationsByMapID(buildingselected, fromfloorselected);
					fromfilename = DataManager.getMapPathByName(buildingselected, fromfloorselected);
					
					sourceSelection.removeAllItems();
					for(int i = 0;i<fromlocations.size();i++){
						sourceSelection.addItem(fromlocations.get(i));
					}
					
					System.out.println("You have chosen floor"+" "+fromfloorSelection.getSelectedItem());
				}
			}
		});
		
		// From Location Label
		JLabel source = new JLabel("From Location");
		difFloorControl.add(source);
		source.setBounds(25, 120, 300, 20);
		
		// From Location List
		difFloorControl.add(sourceSelection);
		sourceSelection.setBounds(20, 150, 300, 20);
		
		// Source listener
		sourceSelection.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED){
					panel_4.removeAll();
					
					currentFloorMap = new ImagePanel(fromfilename, 640, 480);
					currentFloorMap.setBounds(0,0, 640, 480);
					panel_4.add(currentFloorMap);
					
					Point point = (Point)sourceSelection.getSelectedItem();
					source1 = new StartPin(point.getX(), point.getY());
					currentFloorMap.add(source1);
						
					panel_4.repaint();
				}
			}
		});	
		
		/////////////////////
		// To fields group //
		/////////////////////
		
		// To building Label
		JLabel lblToBuilding = new JLabel("To Building");
		difFloorControl.add(lblToBuilding);
		lblToBuilding.setBounds(25, 180, 300, 20);
				
		// To building List
		difFloorControl.add(toBuildingSelection);
		toBuildingSelection.setBounds(20, 210, 300, 20);
		buildingList = DataManager.getBuildings();
		toBuildingSelection.addItem(null);
		for(int i=0;i<buildingList.size();i++){
			toBuildingSelection.addItem(buildingList.get(i));
		}
				
		// To building listener 
		toBuildingSelection.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				tofloorSelection.removeAllItems();
				destinationSelection.removeAllItems();
				if(e.getStateChange()==ItemEvent.SELECTED){
					buildingselected = toBuildingSelection.getSelectedItem().toString();
					floorSelected = DataManager.getFloorsMapsByBuildingName(buildingselected);
					for(int i = 0;i<floorSelected.size();i++){
						tofloorSelection.addItem(floorSelected.get(i));
					}
				}
				System.out.println("You have chosen to building"+" "+toBuildingSelection.getSelectedItem());
			}
		});
						
		// To floor Label
		JLabel tofloor = new JLabel("To Floor");
		difFloorControl.add(tofloor);
		tofloor.setBounds(25, 240, 300, 20);
				
		// To floor List
		difFloorControl.add(tofloorSelection);
		tofloorSelection.setBounds(20, 270, 300, 20);
		tofloorSelection.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED){
					buildingselected = toBuildingSelection.getSelectedItem().toString();
					tofloorselected = tofloorSelection.getSelectedItem().toString();
					tolocations = DataManager.getLocationsByMapID(buildingselected, tofloorselected);
					tofilename = DataManager.getMapPathByName(buildingselected, tofloorselected);
					
					destinationSelection.removeAllItems();
					for(int i = 0;i<tolocations.size();i++){
						destinationSelection.addItem(tolocations.get(i));
					}
					
					System.out.println("You have chosen floor"+" "+tofloorSelection.getSelectedItem());
				}
			}
		});
		
		// To Location Label
		JLabel destination = new JLabel("To Location");
		difFloorControl.add(destination);
		destination.setBounds(25, 300, 300, 20);
		
		// To Location List
		difFloorControl.add(destinationSelection);
		destinationSelection.setBounds(20, 330, 300, 20);
		
		destinationSelection.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED){
					panel_4.removeAll();
					
					currentFloorMap = new ImagePanel(tofilename, 640, 480);
					currentFloorMap.setBounds(0,0, 640, 480);
					Point point = (Point)destinationSelection.getSelectedItem();
					panel_4.add(currentFloorMap);
					
					destination1 =new EndPin(point.getX(), point.getY());
					currentFloorMap.add(destination1);
					
					panel_4.repaint();
				}
			}
		});
		
		/////////////
		// Buttons //
		/////////////
		
		//buttonPanel
		JPanel buttonPanel = new JPanel();
		difFloorControl.add(buttonPanel);
		buttonPanel.setBounds(20, 360, 300, 40);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		//Find route button
		JButton findRoute = new JButton("Find Route");
		buttonPanel.add(findRoute);
		findRoute.setForeground(Color.decode("#F1F1F1"));
		findRoute.setBackground(Color.decode("#AB2A36"));
		findRoute.setOpaque(true);
		findRoute.setBorderPainted(false);
		findRoute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buildingselected = fromBuildingSelection.getSelectedItem().toString();
                fromfloorselected = fromfloorSelection.getSelectedItem().toString();
                tofloorselected = tofloorSelection.getSelectedItem().toString();
                    
                Graph graph = DataManager.getGraphByNameWithDB(buildingselected, tofloorselected);
                Point source = (Point)sourceSelection.getSelectedItem();
                Point destination = (Point)destinationSelection.getSelectedItem();
                    
                route = RouteFinder.computePaths(source, graph, destination);
                mapToDisplayIndex = 0;
                
                // call drawing function
                drawCurrentMap();
            }
            
            private void drawCurrentMap(){
    			mapToDisplayRoute = new ArrayList<Point>();
                int currentMapIndex = 0;
                for(int i = 0; i < route.size(); i++){
                	if(i > 0 && route.get(i).getMapId() != route.get(i-1).getMapId()){
                		currentMapIndex++;
                		if(mapToDisplayIndex == currentMapIndex){
                			mapToDisplayRoute.add(route.get(i));
                		}
                	}
                	if(mapToDisplayIndex == currentMapIndex && (i == 0 || route.get(i).getMapId() == route.get(i-1).getMapId())){
                		mapToDisplayRoute.add(route.get(i));
                	}
                }
                
                // draw map and route
                panel_4.removeAll();
                panelPrev.removeAll();
            	panelNext.removeAll();
    			panelCurrent.removeAll();
    			
    			currentFloorMap = new ImagePanel(DataManager.getMapPathById(mapToDisplayRoute.get(0).getMapId()), 640, 480);
    			currentFloorMap.setBounds(0,0, 640, 480);
    			panel_4.add(currentFloorMap);
    			
    			if(mapToDisplayIndex == 0){
    				source1 = new StartPin(mapToDisplayRoute.get(0).getX(), mapToDisplayRoute.get(0).getY());
    				currentFloorMap.add(source1);
    			} else {
    				// prev call
    				ImagePanel imgArrow = null;
    				JLabel lblPrev = null;
    				
    				Point currentPoint = mapToDisplayRoute.get(0);
    				Point prevPoint = route.get(route.indexOf(currentPoint)-1);
    				if(currentPoint.getMapId() == 6) {
    					// we are in campus, prev is "enter prev building"
    					lblPrev = new JLabel("Enter previous building");
    					lblPrev.setBounds(50, 0, 150, 30);
        				imgArrow = new ImagePanel("maps//arrow-left.png", 30, 30);
        			} else if (prevPoint.getMapId() == 6){
    					// current is building, prev is campus, "go out of current building"
        				lblPrev = new JLabel("Go out of current building");
        				lblPrev.setBounds(50, 0, 150, 30);
        				imgArrow = new ImagePanel("maps//arrow-left.png", 30, 30);
        			} else if (DataManager.getMapById(currentPoint.getMapId()).getFloorNum() > DataManager.getMapById(prevPoint.getMapId()).getFloorNum()){
    					// go down 
        				lblPrev = new JLabel("Go Down");
        				lblPrev.setBounds(150, 0, 50, 30);
        				imgArrow = new ImagePanel("maps//arrow-down.png", 30, 30);
        			} else {
    					// go up
        				lblPrev = new JLabel("Go Up");
        				lblPrev.setBounds(170, 0, 50, 30);
        				imgArrow = new ImagePanel("maps//arrow-up.png", 30, 30);
        			}
    				
    				imgArrow.setBounds(210, 0, 30, 30);
    				imgArrow.addMouseListener(new MouseListener() {
    					@Override
						public void mouseClicked(MouseEvent e) {
    						mapToDisplayIndex --;
    						drawCurrentMap();
						}
						@Override
						public void mouseReleased(MouseEvent e) {
						}
						@Override
						public void mousePressed(MouseEvent e) {
						}
						@Override
						public void mouseExited(MouseEvent e) {
						}
						@Override
						public void mouseEntered(MouseEvent e) {
						}
					});
    				
    				panelPrev.add(imgArrow);
    				panelPrev.add(lblPrev);
    			}
    			if(mapToDisplayIndex == currentMapIndex){
    				destination1 =new EndPin(mapToDisplayRoute.get(mapToDisplayRoute.size()-1).getX(), mapToDisplayRoute.get(mapToDisplayRoute.size()-1).getY());
    				currentFloorMap.add(destination1);
    			} else {
    				// next call
    				ImagePanel imgArrow = null;
    				JLabel lblNext = null;
    				
    				Point currentPoint = mapToDisplayRoute.get(mapToDisplayRoute.size()-1);
    				Point nextPoint = route.get(route.indexOf(currentPoint)+1);
    				if(currentPoint.getMapId() == 6) {
    					// we are in campus, prev is "enter prev building"
    					lblNext = new JLabel("Enter next building");
    					imgArrow = new ImagePanel("maps//arrow-right.png", 30, 30);
        			} else if (nextPoint.getMapId() == 6){
    					// current is building, prev is campus, "go out of current building"
        				lblNext = new JLabel("Go out of current building");
        				imgArrow = new ImagePanel("maps//arrow-right.png", 30, 30);
        			} else if (DataManager.getMapById(currentPoint.getMapId()).getFloorNum() > DataManager.getMapById(nextPoint.getMapId()).getFloorNum()){
    					// go down 
        				lblNext = new JLabel("Go Down");
        				imgArrow = new ImagePanel("maps//arrow-down.png", 30, 30);
        			} else {
    					// go up
        				lblNext = new JLabel("Go Up");
        				imgArrow = new ImagePanel("maps//arrow-up.png", 30, 30);
        			}
    				
    				imgArrow.setBounds(10, 0, 30, 30);
    				imgArrow.addMouseListener(new MouseListener() {
    					@Override
						public void mouseClicked(MouseEvent e) {
    						mapToDisplayIndex ++;
    						drawCurrentMap();
						}
						@Override
						public void mouseReleased(MouseEvent e) {
						}
						@Override
						public void mousePressed(MouseEvent e) {
						}
						@Override
						public void mouseExited(MouseEvent e) {
						}
						@Override
						public void mouseEntered(MouseEvent e) {
						}
					});
    				lblNext.setBounds(50, 0, 150, 30);
    				
    				panelNext.add(imgArrow);
    				panelNext.add(lblNext);
    			}
    			Util.drawPath(currentFloorMap, mapToDisplayRoute);
    			
    			// add map name to panel current
    			Map currentMap = DataManager.getMapById(mapToDisplayRoute.get(0).getMapId());
    			JLabel lblMapName = new JLabel(currentMap.getBuildingName() + ", " + currentMap.getFloorNum());
    			lblMapName.setBounds(20, 0, 120, 30);
    			panelCurrent.add(lblMapName);
				
    			panel_4.repaint();
    			panelPrev.repaint();
    			panelNext.repaint();
    			panelCurrent.repaint();
    		}
        });
		
//		JButton reverse = new JButton(new ImageIcon("maps//reverse.png"));
//		reverse.setBounds(160, 175, 25, 25);
//		difFloorControl.add(reverse);
//		
//		reverse.setForeground(Color.decode("#F1F1F1"));
//		reverse.setBackground(Color.decode("#AB2A36"));
//		reverse.setOpaque(true);
//		reverse.setBorderPainted(false);
//		
//		reverse.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				newtofloor = fromfloorSelection.getSelectedItem().toString();
//				newdestination = sourceSelection.getSelectedItem().toString();
//				newfromfloor = tofloorSelection.getSelectedItem().toString();
//				newsource = destinationSelection.getSelectedItem().toString();
//				
//				fromfloorSelection.setSelectedItem(newfromfloor);
//				tofloorSelection.setSelectedItem(newtofloor);
//				sourceSelection.setSelectedItem(newsource);
//				destinationSelection.setSelectedItem(newdestination);
//				
//				if(frombutton.getBackground() == Color.gray){
////					sourceSelection.setEnabled(false);
////					fromfloorSelection.setEnabled(false);
////					fromBuildingSelection.setEnabled(true);
////					destinationSelection.setEnabled(true);
////					tofloorSelection.setEnabled(true);
//					
//					tobutton.setBackground(Color.gray);
//					frombutton.setForeground(Color.decode("#F1F1F1"));
//					frombutton.setBackground(Color.decode("#AB2A36"));
//					frombutton.setOpaque(true);
//					frombutton.setBorderPainted(false);
//					
//					if(toFloorMap != null){
//						panel_4.removeAll();
//						panel_4.add(startlable);
//						startlable.repaint();
//						panel_4.add(endlable);
//						endlable.repaint();
//						panel_4.add(starttext);
//						starttext.repaint();
//						panel_4.add(endtext);
//						endtext.repaint();
//						panel_4.add(toFloorMap);
//						panel_4.repaint();
////						frame.add(panel_4);
//						toFloorMap.setLayout(null);
//					}
//				}
//				else if(tobutton.getBackground() == Color.gray){
////					destinationSelection.setEnabled(false);
////					tofloorSelection.setEnabled(false);	
////					sourceSelection.setEnabled(true);
////					fromfloorSelection.setEnabled(true);
////					fromBuildingSelection.setEnabled(true);
//					
//					frombutton.setBackground(Color.gray);
//					tobutton.setForeground(Color.decode("#F1F1F1"));
//					tobutton.setBackground(Color.decode("#AB2A36"));
//					tobutton.setOpaque(true);
//					tobutton.setBorderPainted(false);
//					
//					if(fromFloorMap != null){
//						panel_4.removeAll();
//						panel_4.add(startlable);
//						startlable.repaint();
//						panel_4.add(endlable);
//						endlable.repaint();
//						panel_4.add(starttext);
//						starttext.repaint();
//						panel_4.add(endtext);
//						endtext.repaint();
//						panel_4.add(fromFloorMap);
//						panel_4.repaint();
////						frame.add(panel_4);
//						fromFloorMap.setLayout(null);
//					}
//				}
//			}
//		});
		
		//////////////////////
		//Start/End labels //
		//////////////////////
		
		JPanel panelStartEndLabels = new JPanel();
		panelStartEndLabels.setBounds(0, 660, 300, 50);
		panelStartEndLabels.setLayout(null);
		frame.add(panelStartEndLabels);
		
		startlable = new ImagePanel("maps//startpin.png", 20, 20);
		starttext = new JLabel("Start");
		startlable.setBounds(90, 0, 30, 50);
		starttext.setBounds(120, 0, 60, 20);
		panelStartEndLabels.add(startlable);
		panelStartEndLabels.add(starttext);
		
		endlable = new ImagePanel("maps//endpin.png", 20, 20);
		endtext = new JLabel("End");
		endlable.setBounds(180, 0, 30, 50);
		endtext.setBounds(210, 0, 60, 20);
		panelStartEndLabels.add(endlable);
		panelStartEndLabels.add(endtext);
	}
}