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
import javax.swing.JPanel;

import com.mysql.jdbc.Util;

import wpi.cs509.dataManager.DataManager;
import wpi.cs509.dataModel.Graph;
import wpi.cs509.dataModel.Point;
import wpi.cs509.routeFinder.RouteFinder;
import wpi.cs509.ui.components.HeaderPanel;
import wpi.cs509.ui.components.ImagePanel;
import wpi.cs509.ui.components.Line;
import wpi.cs509.ui.components.SolidPoint;

public class RouteScreen3 {

	private JFrame frame;
	private JComboBox<String> buildingSelection,tofloorSelection,fromfloorSelection,sourceSelection,destinationSelection;
	private ArrayList<String> buildingList, floorList;
	private ArrayList<Point> sourceList, destinationList,locations;
	private SolidPoint source1,destination1;
	private ImagePanel sameFloorMap;
	private int x1,x2,y1,y2;
	private ItemListener sListener,dListener,fListener,bListener;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RouteScreen3 window = new RouteScreen3();
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
	public RouteScreen3() {
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		buildingSelection = new JComboBox<String>();
		tofloorSelection = new JComboBox<String>();
		fromfloorSelection = new JComboBox<String>();
		sourceSelection = new JComboBox<String>();
		destinationSelection = new JComboBox<String>();
		ImagePanel  imagePanelmap;
			
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.decode("#F1F1F1"));
		frame.setBounds(0, 0, 1024, 730);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		////////////
		// Header //
		////////////
		HeaderPanel headerPanel = new HeaderPanel("Route between two locations on different floor of a building", false, frame);
		headerPanel.setBounds(0, 0, 1024, 730);
		frame.getContentPane().add(headerPanel);
		
		////////////
		// Map    //
		////////////
		JPanel sameFloorSearchPanel = new JPanel();
		sameFloorSearchPanel.setLayout(null);
		frame.getContentPane().add(sameFloorSearchPanel);
		sameFloorSearchPanel.setBounds(0, 180, 1024, 480);
		
		//controlPanel
		JPanel sameFloorControl = new JPanel();
		sameFloorSearchPanel.add(sameFloorControl);
		sameFloorControl.setBounds(0, 0, 350, 480);
		sameFloorControl.setLayout(null);
		
		//buildingLabel
		JLabel building = new JLabel("Building");
		sameFloorControl.add(building);
		building.setBounds(25, 0, 300, 20);
		
		//buildingList
		sameFloorControl.add(buildingSelection);
		buildingSelection.setBounds(20, 30, 300, 20);
		buildingList = DataManager.getBuildings();
		for(int i=0;i<buildingList.size();i++){	
			buildingSelection.addItem(buildingList.get(i));
		}
		
		floorList = DataManager.getFloorsMapsByBuildingName(buildingSelection.getSelectedItem().toString());
		for(int i = 0;i<floorList.size();i++){
			tofloorSelection.addItem(floorList.get(i));
		}
		for(int i = 0;i<floorList.size();i++){
			fromfloorSelection.addItem(floorList.get(i));
		}
		
		sourceList = DataManager.getLocationsByMapID(buildingSelection.getSelectedItem().toString(), fromfloorSelection.getSelectedItem().toString());
		destinationList = DataManager.getLocationsByMapID(buildingSelection.getSelectedItem().toString(), tofloorSelection.getSelectedItem().toString());
		for(int i=0;i<sourceList.size();i++){
			sourceSelection.addItem(sourceList.get(i).getName());
			
		}
		for(int i=0;i<destinationList.size();i++){
			
			destinationSelection.addItem(destinationList.get(i).getName());
		}
		
		buildingSelection.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				tofloorSelection.removeAllItems();
				fromfloorSelection.removeAllItems();
				sourceSelection.removeAllItems();
				destinationSelection.removeAllItems();
				sourceSelection.removeItemListener(sListener);
				destinationSelection.removeItemListener(dListener);
				// TODO Auto-generated method stub
				if(e.getStateChange()==ItemEvent.SELECTED)
				{
					String buildingselected = buildingSelection.getSelectedItem().toString();
					ArrayList<String> floorselected = DataManager.getFloorsMapsByBuildingName(buildingselected);
					for(int i = 0;i<floorselected.size();i++){
						tofloorSelection.addItem(floorselected.get(i));
					}
					for(int i = 0;i<floorselected.size();i++){
						fromfloorSelection.addItem(floorselected.get(i));
					}
				}
					System.out.println("You have chosen building"+" "+buildingSelection.getSelectedItem());
			}
		});
		
		//FromfloorLabel
		JLabel fromfloor = new JLabel("From Floor");
		sameFloorControl.add(fromfloor);
		fromfloor.setBounds(25, 60, 300, 20);
		
		//FromfloorList
		sameFloorControl.add(fromfloorSelection);
		fromfloorSelection.setBounds(20, 90, 300, 20);
		fromfloorSelection.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				sourceSelection.removeAllItems();
				
						sourceSelection.removeItemListener(sListener);

				if(e.getStateChange()==ItemEvent.SELECTED)
				{
					String buildingselected = buildingSelection.getSelectedItem().toString();
					String floorselected = fromfloorSelection.getSelectedItem().toString();
					ArrayList<Point>locations = DataManager.getLocationsByMapID(buildingselected, floorselected);
					for(int i = 0;i<locations.size();i++){
						sourceSelection.addItem(locations.get(i).getName().toString());
						destinationSelection.addItem(locations.get(i).getName().toString());
					}
					System.out.println("You have chosen floor"+" "+fromfloorSelection.getSelectedItem());
					}
					}
				});
		
		//TofloorLabel
				JLabel tofloor = new JLabel("ToFloor");
				sameFloorControl.add(tofloor);
				tofloor.setBounds(25, 120, 300, 20);
				
		//TofloorList
		sameFloorControl.add(tofloorSelection);
		tofloorSelection.setBounds(20, 150, 300, 20);
		tofloorSelection.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				
				destinationSelection.removeAllItems();
						
				destinationSelection.removeItemListener(dListener);
				if(e.getStateChange()==ItemEvent.SELECTED)
				{
					String buildingselected = buildingSelection.getSelectedItem().toString();
					String floorselected = tofloorSelection.getSelectedItem().toString();
					ArrayList<Point>locations = DataManager.getLocationsByMapID(buildingselected, floorselected);
					for(int i = 0;i<locations.size();i++){
						sourceSelection.addItem(locations.get(i).getName().toString());
						destinationSelection.addItem(locations.get(i).getName().toString());
					}
					System.out.println("You have chosen floor"+" "+tofloorSelection.getSelectedItem());
					}
					}
				});
		
		//sourceLabel
		JLabel source = new JLabel("Source");
		sameFloorControl.add(source);
		source.setBounds(25, 180, 300, 20);
		
		//sourceList
		sameFloorControl.add(sourceSelection);
		sourceSelection.setBounds(20, 210, 300, 20);
		sListener = new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange()==ItemEvent.SELECTED){
					String buildingselected = buildingSelection.getSelectedItem().toString();
					String floorselected = fromfloorSelection.getSelectedItem().toString();
					ArrayList<Point>locations = DataManager.getLocationsByMapID(buildingselected, floorselected);
					int i = sourceSelection.getSelectedIndex();
					x1 = locations.get(i).getX();
					y1 = locations.get(i).getY();
					source1 =new SolidPoint(Color.red, x1, y1);
					sameFloorMap.removeAll();
					sameFloorMap.add(source1);
					sameFloorMap.repaint();
				    System.out.println("You have chosen source"+" "+sourceSelection.getSelectedItem());

				}
			}
		};
		sourceSelection.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange()==ItemEvent.SELECTED){
					sourceSelection.addItemListener(sListener);
				}
			}
		});	
		
		
		//destinationLabel
		JLabel destination = new JLabel("Destination");
		sameFloorControl.add(destination);
		destination.setBounds(25, 240, 300, 20);
		
		//destinationList
		sameFloorControl.add(destinationSelection);
		destinationSelection.setBounds(20, 270, 300, 20);
		dListener = new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange()==ItemEvent.SELECTED){
					String buildingselected = buildingSelection.getSelectedItem().toString();
					String floorselected = tofloorSelection.getSelectedItem().toString();
					ArrayList<Point>locations = DataManager.getLocationsByMapID(buildingselected, floorselected);
					int i = destinationSelection.getSelectedIndex();
					x2 = locations.get(i).getX();
					y2 = locations.get(i).getY();
					destination1 =new SolidPoint(Color.red, x2,y2);
					sameFloorMap.removeAll();
					sameFloorMap.add(source1);
					sameFloorMap.add(destination1);
					sameFloorMap.repaint();
					System.out.println("You have chosen destination"+" "+destinationSelection.getSelectedItem());
				}
			}
		};
		destinationSelection.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange()==ItemEvent.SELECTED){
					destinationSelection.addItemListener(dListener);
				}
			}
		});
		
		
		//buttonPanel
		JPanel buttonPanel = new JPanel();
		sameFloorControl.add(buttonPanel);
		buttonPanel.setBounds(20, 300, 300, 40);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		//From button
		JButton frombutton = new JButton("From");
		buttonPanel.add(frombutton);
		frombutton.setForeground(Color.decode("#F1F1F1"));
		frombutton.setBackground(Color.decode("#AB2A36"));
		frombutton.setOpaque(true);
		frombutton.setBorderPainted(false);
		frombutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String selectedBuilding = buildingSelection.getSelectedItem().toString();
				String selectedFloor = fromfloorSelection.getSelectedItem().toString();
				String filename = DataManager.getMapPathByName(selectedBuilding);
				System.out.println(filename);
				sameFloorMap = new ImagePanel(filename, 640, 480);
				sameFloorMap.setBounds(350, 180, 640, 480);
				sameFloorMap.setLayout(null);
				sameFloorMap.repaint();
			}
		});
		
		//to button
		JButton tobutton = new JButton("To");
		buttonPanel.add(tobutton);
		tobutton.setForeground(Color.decode("#F1F1F1"));
		tobutton.setBackground(Color.decode("#AB2A36"));
		tobutton.setOpaque(true);
		tobutton.setBorderPainted(false);
        tobutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String selectedBuilding = buildingSelection.getSelectedItem().toString();
				String selectedFloor = tofloorSelection.getSelectedItem().toString();
				String filename = DataManager.getMapPathByName(selectedBuilding);
				System.out.println(filename);
				sameFloorMap = new ImagePanel(filename, 640, 480);
				sameFloorMap.setBounds(350, 180, 640, 480);
				sameFloorMap.setLayout(null);
				sameFloorMap.repaint();
			}
		});
		
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
                // TODO Auto-generated method stub
                String buildingselected = buildingSelection.getSelectedItem().toString();
                String fromfloorselected = fromfloorSelection.getSelectedItem().toString();
                String tofloorselected =tofloorSelection.getSelectedItem().toString();
                ArrayList<Point> fromlocation = DataManager.getLocationsByMapID(buildingselected, fromfloorselected);
                ArrayList<Point> tolocation = DataManager.getLocationsByMapID(buildingselected, tofloorselected);
                Graph tograph = DataManager.getGraphByNameWithDB(buildingselected, tofloorselected);
                Graph fromgraph = DataManager.getGraphByNameWithDB(buildingselected, fromfloorselected);
                Graph rf = new Graph();
                rf= fromgraph.addGraph(tograph);

                int i = sourceSelection.getSelectedIndex();
                int j = destinationSelection.getSelectedIndex();
                Point source0 = locations.get(i);
                Point destination0 = locations.get(j);
                ArrayList<Point> p = RouteFinder.computePaths(source0, rf, destination0);
                
                int from = 0;
                int to = 0;
                for(int c = 0; c<p.size()-1;c++){
                	if(p.get(c).getFloorNum()==p.get(c+1).getFloorNum()){
                		continue;
                	}
                	else{
                		from = c;
                		to = c+1;
                	}
                }
                
                for(int f = 0;f<from ; f++){
                	Point frompoint = p.get(f);
            		ArrayList<Point> frompointlist = new ArrayList<>();
            		frompointlist.set(f, frompoint);
            		wpi.cs509.ui.util.Util.drawPath(sameFloorMap, frompointlist);
                }
                
                for(int t = to;t<p.size() ; t++){
                	Point topoint = p.get(t);
            		ArrayList<Point> topointlist = new ArrayList<>();
            		topointlist.set(t, topoint);
            		wpi.cs509.ui.util.Util.drawPath(sameFloorMap, topointlist);
                }
                
                
                for(int k = 1;k<p.size()-1;k++){
                    SolidPoint conjection = new SolidPoint(Color.black, p.get(k).getX(), p.get(k).getY());
                    sameFloorMap.add(conjection);
                    sameFloorMap.repaint();
                }
                
                System.out.println(source0+" "+destination0);
            }
        });

		
/*		String buildingselected = buildingSelection.getSelectedItem().toString();
		//String floorselected = floorSelection.getSelectedItem().toString();
		//ArrayList<Point> locations = DataManager.getLocationsByMapID(buildingselected, floorselected);
		//Graph rf = DataManager.getGraphByNameWithDB(buildingselected, floorselected);
		int i = sourceSelection.getSelectedIndex();
		int j = destinationSelection.getSelectedIndex();
		Point source0 = locations.get(i);
		Point destination0 = locations.get(j);
		ArrayList<Point> p = RouteFinder.computePaths(source0, rf, destination0);
		wpi.cs509.ui.util.Util.drawPath(sameFloorMap, p);*/
		
		//map
//		String selectedBuilding = buildingSelection.getSelectedItem().toString();
//		String selectedFloor = floorSelection.getSelectedItem().toString();
//		String filename = DataManager.getMapPathByName(selectedBuilding);
//		System.out.println(filename);
		sameFloorMap = new ImagePanel("maps//project center.png", 640, 480);
		sameFloorMap.setBounds(350, 180, 640, 480);
		sameFloorSearchPanel.add(sameFloorMap);
		sameFloorMap.setLayout(null);
	}
}
