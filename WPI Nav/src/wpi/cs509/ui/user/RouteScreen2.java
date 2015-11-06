package wpi.cs509.ui.user;

import java.awt.Color;
import java.awt.Event;
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
import wpi.cs509.dataModel.Point;
import wpi.cs509.ui.components.HeaderPanel;
import wpi.cs509.ui.components.ImagePanel;
import wpi.cs509.ui.components.Line;
import wpi.cs509.ui.components.SolidPoint;

public class RouteScreen2 {

	private JFrame frame;
	private JComboBox<String> buildingSelection,floorSelection,sourceSelection,destinationSelection;
	private ArrayList<String> buildingList, floorList;
	private ArrayList<Point> sourceList, destinationList;
	private SolidPoint source1,destination1;
	private ImagePanel sameFloorMap;
	private int x1,x2,y1,y2;
	

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
		buildingSelection = new JComboBox<String>();
		floorSelection = new JComboBox<String>();
		sourceSelection = new JComboBox<String>();
		destinationSelection = new JComboBox<String>();
			
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.decode("#F1F1F1"));
		frame.setBounds(0, 0, 1024, 730);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		////////////
		// Header //
		////////////
		HeaderPanel headerPanel = new HeaderPanel("Route between two locations on the same floor of a building", false, frame);
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
		sameFloorControl.setBounds(0, 180, 350, 480);
		sameFloorControl.setLayout(null);
		
		//buildingLabel
		JLabel building = new JLabel("Building");
		sameFloorControl.add(building);
		building.setBounds(25, 0, 300, 20);
		
		//buildingList
		sameFloorControl.add(buildingSelection);
		//buildingSelection.setSelectedIndex(0);
		buildingSelection.setBounds(20, 30, 300, 20);
		buildingList = DataManager.getBuildings();
		for(int i=0;i<buildingList.size();i++){	
			buildingSelection.addItem(buildingList.get(i));
		}
		buildingSelection.setSelectedIndex(0);
		floorList = DataManager.getFloorsMapsByBuildingName(buildingSelection.getSelectedItem().toString());
		for(int i = 0;i<floorList.size();i++){
			floorSelection.addItem(floorList.get(i));
		}
		floorSelection.setSelectedIndex(0);
		sourceList = DataManager.getLocationsByMapID(buildingSelection.getSelectedItem().toString(), floorSelection.getSelectedItem().toString());
		destinationList = DataManager.getLocationsByMapID(buildingSelection.getSelectedItem().toString(), floorSelection.getSelectedItem().toString());
		for(int i=0;i<sourceList.size();i++){
			sourceSelection.addItem(sourceList.get(i).getName());
			destinationSelection.addItem(destinationList.get(i).getName());
		}
		buildingSelection.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				floorSelection.removeAllItems();
				sourceSelection.removeAllItems();
				destinationSelection.removeAllItems();
				// TODO Auto-generated method stub
				if(e.getStateChange()==ItemEvent.SELECTED)
				{
					String building = buildingSelection.getSelectedItem().toString();
					floorList = DataManager.getFloorsMapsByBuildingName(building);
					for(int i = 0;i<floorList.size();i++){
						floorSelection.addItem(floorList.get(i));
					}
				}
				sameFloorMap.repaint();	
				System.out.println("You have chosen building"+" "+buildingSelection.getSelectedItem());
			}
		});
		
		//floorLabel
		JLabel floor = new JLabel("Floor");
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
				if(e.getStateChange()==ItemEvent.SELECTED)
				{
					String building = buildingSelection.getSelectedItem().toString();
					String floor = floorSelection.getSelectedItem().toString();
					ArrayList<Point> locations = DataManager.getLocationsByMapID(building, floor);
					for(int i = 0;i<locations.size();i++){
						sourceSelection.addItem(locations.get(i).getName().toString());
						destinationSelection.addItem(locations.get(i).getName().toString());
					}
					sameFloorMap.repaint();
					System.out.println("You have chosen floor"+" "+floorSelection.getSelectedItem());
			}
			}
		});
		
		//sourceLabel
		JLabel source = new JLabel("Source");
		sameFloorControl.add(source);
		source.setBounds(25, 120, 300, 20);
		
		//sourceList
		sameFloorControl.add(sourceSelection);
		sourceSelection.setBounds(20, 150, 300, 20);
		sourceSelection.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange()==ItemEvent.SELECTED){
//					int i = sourceSelection.getSelectedIndex();
//					int x1 = sourceList.get(i).getX();
//					int y1 = sourceList.get(i).getY();
					x1 = 400;
					y1 = 400;
					source1 =new SolidPoint(Color.red, x1, y1);
					sameFloorMap.add(source1);
					sameFloorMap.repaint();
				    System.out.println("You have chosen source"+" "+sourceSelection.getSelectedItem());
				}
			}
		});
			
		//destinationLabel
		JLabel destination = new JLabel("Destination");
		sameFloorControl.add(destination);
		destination.setBounds(25, 180, 300, 20);
		
		//destinationList
		sameFloorControl.add(destinationSelection);
		destinationSelection.setBounds(20, 210, 300, 20);
		destinationSelection.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange()==ItemEvent.SELECTED){
					x2 = 300;
					y2 = 300;
//					int i = destinationSelection.getSelectedIndex();
//					int x2 = destinationList.get(i).getX();
//					int y2 = destinationList.get(i).getY();
					destination1 =new SolidPoint(Color.red, x2,y2);
					sameFloorMap.add(destination1);
					sameFloorMap.repaint();
					System.out.println("You have chosen destination"+" "+destinationSelection.getSelectedItem());
				}
			}
		});
		
		//buttonPanel
		JPanel buttonPanel = new JPanel();
		sameFloorControl.add(buttonPanel);
		buttonPanel.setBounds(20, 240, 300, 40);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		//Find route button
		JButton findRoute = new JButton("Find Route");
		buttonPanel.add(findRoute);
		findRoute.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Line separator = new Line(Color.decode("#929292"), x1, y1, x2, y2);
				sameFloorMap.add(separator, new Integer(1), 0);
				sameFloorMap.repaint();
			}
		});
		
		
		//map
//		String selectenBuilding = buildingSelection.getSelectedItem().toString();
//		String selectedFloor = floorSelection.getSelectedItem().toString();
//		String filename = DataManager.getMapPathByName(selectenBuilding);
//		System.out.println(filename);
		sameFloorMap = new ImagePanel("maps//project center.png", 640, 480);
		sameFloorMap.setBounds(350, 180, 640, 480);
		sameFloorSearchPanel.add(sameFloorMap);
		sameFloorMap.setLayout(null);
		
	}
}
