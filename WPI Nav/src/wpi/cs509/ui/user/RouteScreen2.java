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
import wpi.cs509.dataManager.DataManager;
import wpi.cs509.dataModel.Graph;
import wpi.cs509.dataModel.Point;
import wpi.cs509.routeFinder.RouteFinder;
import wpi.cs509.ui.components.HeaderPanel;
import wpi.cs509.ui.components.ImagePanel;
import wpi.cs509.ui.components.SolidPoint;

public class RouteScreen2 {

	private JFrame frame;
	private HeaderPanel headerPanel;
	private JPanel sameFloorSearchPanel, sameFloorControl, buttonPanel;
	private ImagePanel sameFloorMap;
	private JComboBox<String> buildingSelection,floorSelection,sourceSelection,destinationSelection;
	private SolidPoint source1,destination1;
	private JLabel building, floor, source, destination;
	private JButton findRoute;
	
	private ItemListener sListener,dListener;
	
	private ArrayList<String> buildingList, floorSelected;
	private ArrayList<Point> locations;
	private String buildingselected,floorselected, filename;
	private int x1,x2,y1,y2,ss,ds;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RouteScreen2 window = new RouteScreen2();
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
	public RouteScreen2() {
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		headerPanel = new HeaderPanel("Route between two locations on the same floor of a building", false, frame);
		sameFloorSearchPanel = new JPanel();
		sameFloorControl = new JPanel();
		buttonPanel = new JPanel();
		sameFloorMap = new ImagePanel("maps//campusmap.gif", 640, 480);
		building = new JLabel("Building");
		floor = new JLabel("Floor");
		source = new JLabel("Source");
		destination = new JLabel("Destination");
		buildingSelection = new JComboBox<String>();
		floorSelection = new JComboBox<String>();
		sourceSelection = new JComboBox<String>();
		destinationSelection = new JComboBox<String>();
		findRoute = new JButton("Find Route");
			
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.decode("#F1F1F1"));
		frame.setBounds(0, 0, 1024, 730);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		////////////
		// Header //
		////////////
		headerPanel.setBounds(0, 0, 1024, 730);
		frame.getContentPane().add(headerPanel);
		
		////////////
		// Map    //
		////////////
		//sameFloorSearchPanel
		sameFloorSearchPanel.setLayout(null);
		frame.getContentPane().add(sameFloorSearchPanel);
		sameFloorSearchPanel.setBounds(0, 180, 1024, 480);
		
		//controlPanel
		sameFloorSearchPanel.add(sameFloorControl);
		sameFloorControl.setBounds(0, 180, 350, 480);
		sameFloorControl.setLayout(null);
		
		//buildingLabel
		sameFloorControl.add(building);
		building.setBounds(25, 0, 300, 20);
		
		//buildingList
		sameFloorControl.add(buildingSelection);
		buildingSelection.setBounds(20, 30, 300, 20);
		buildingList = DataManager.getBuildings();
		buildingSelection.addItem("Please choose building");
		for(int i=0;i<buildingList.size();i++){	
			buildingSelection.addItem(buildingList.get(i));
		}
		
		//combo box default
		floorSelection.addItem("Please choose floor");
		sourceSelection.addItem("Please choose source");
		destinationSelection.addItem("Please choose destination");
		
		//buildingSelection Listener
		buildingSelection.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				floorSelection.removeAllItems();
				sourceSelection.removeAllItems();
				destinationSelection.removeAllItems();
				sourceSelection.removeItemListener(sListener);
				destinationSelection.removeItemListener(dListener);
				// TODO Auto-generated method stub
				if(e.getStateChange()==ItemEvent.SELECTED)
				{
					
					buildingselected = buildingSelection.getSelectedItem().toString();
					floorSelected = DataManager.getFloorsMapsByBuildingName(buildingselected);
					filename = DataManager.getMapPathByName(buildingselected+","+floorSelected.get(0).toString());
					sameFloorMap = new ImagePanel(filename, 640, 480);
					sameFloorMap.setBounds(350, 180, 640, 480);
					sameFloorSearchPanel.add(sameFloorMap);
					sameFloorMap.setLayout(null);
					sameFloorMap.repaint();
					floorSelection.addItem("Please choose floor");
					for(int i = 0;i<floorSelected.size();i++){
						floorSelection.addItem(floorSelected.get(i));
					}
				}
			}
		});
		
		//floorLabel
		sameFloorControl.add(floor);
		floor.setBounds(25, 60, 300, 20);
		
		//floorList
		sameFloorControl.add(floorSelection);
		floorSelection.setBounds(20, 90, 300, 20);
		floorSelection.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				sourceSelection.removeAllItems();
				destinationSelection.removeAllItems();
				sourceSelection.removeItemListener(sListener);
				destinationSelection.removeItemListener(dListener);
				if(e.getStateChange()==ItemEvent.SELECTED)
				{
					buildingselected = buildingSelection.getSelectedItem().toString();
					floorselected = floorSelection.getSelectedItem().toString();
					locations = DataManager.getLocationsByMapID(buildingselected, floorselected);
					filename = DataManager.getMapPathByName(buildingselected+","+floorselected);
					sameFloorMap = new ImagePanel(filename, 640, 480);
					sameFloorMap.setBounds(350, 180, 640, 480);
					sameFloorSearchPanel.add(sameFloorMap);
					sameFloorMap.setLayout(null);
					sameFloorMap.repaint();
					sourceSelection.addItem("Please choose source");
					destinationSelection.addItem("Please choose destination");
					for(int i = 0;i<locations.size();i++){
						sourceSelection.addItem(locations.get(i).getName().toString());
						destinationSelection.addItem(locations.get(i).getName().toString());
					}
				}
			}
		});
		
		//sourceLabel
		sameFloorControl.add(source);
		source.setBounds(25, 120, 300, 20);
		
		//sourceList
		sameFloorControl.add(sourceSelection);
		sourceSelection.setBounds(20, 150, 300, 20);
		sListener = new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange()==ItemEvent.SELECTED){
					buildingselected = buildingSelection.getSelectedItem().toString();
					floorselected = floorSelection.getSelectedItem().toString();
					locations = DataManager.getLocationsByMapID(buildingselected, floorselected);
					ss = sourceSelection.getSelectedIndex()-1;
					x1 = locations.get(ss).getX();
					y1 = locations.get(ss).getY();
					source1 =new SolidPoint(Color.red, x1, y1);
					sameFloorMap.removeAll();
					sameFloorMap.add(source1);
					sameFloorMap.repaint();
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
		sameFloorControl.add(destination);
		destination.setBounds(25, 180, 300, 20);
		
		//destinationList
		sameFloorControl.add(destinationSelection);
		destinationSelection.setBounds(20, 210, 300, 20);
		dListener = new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange()==ItemEvent.SELECTED){
					buildingselected = buildingSelection.getSelectedItem().toString();
					floorselected = floorSelection.getSelectedItem().toString();
					locations = DataManager.getLocationsByMapID(buildingselected, floorselected);
					ds = destinationSelection.getSelectedIndex()-1;
					x2 = locations.get(ds).getX();
					y2 = locations.get(ds).getY();
					destination1 =new SolidPoint(Color.red, x2,y2);
					sameFloorMap.removeAll();
					sameFloorMap.add(source1);
					sameFloorMap.add(destination1);
					sameFloorMap.repaint();
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
		sameFloorControl.add(buttonPanel);
		buttonPanel.setBounds(20, 240, 300, 40);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		//Find route button
		buttonPanel.add(findRoute);
		findRoute.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				buildingselected = buildingSelection.getSelectedItem().toString();
				floorselected = floorSelection.getSelectedItem().toString();
				locations = DataManager.getLocationsByMapID(buildingselected, floorselected);
				Graph rs2graph = DataManager.getGraphByNameWithDB(buildingselected, floorselected);
				int i = sourceSelection.getSelectedIndex()-1;
				int j = destinationSelection.getSelectedIndex()-1;
				Point source0 = locations.get(i);
				Point destination0 = locations.get(j);
				ArrayList<Point> p = RouteFinder.computePaths(source0, rs2graph, destination0);
				wpi.cs509.ui.util.Util.drawPath(sameFloorMap, p);
			}
		});
//		findRoute.setForeground(Color.decode("#F1F1F1"));
//		findRoute.setBackground(Color.decode("#AB2A36"));
		
		
		//map
		sameFloorMap.setBounds(350, 180, 640, 480);
		sameFloorSearchPanel.add(sameFloorMap);
		sameFloorMap.setLayout(null);
	}
}
