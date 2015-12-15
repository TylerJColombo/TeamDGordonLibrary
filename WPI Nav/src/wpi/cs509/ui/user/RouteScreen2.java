package wpi.cs509.ui.user;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sun.security.jca.GetInstance;
import wpi.cs509.dataManager.DataManager;
import wpi.cs509.dataManager.DirectorGraph;
import wpi.cs509.dataModel.Graph;
import wpi.cs509.dataModel.Point;
import wpi.cs509.routeFinder.RouteFinder;
import wpi.cs509.ui.components.EndPin;
import wpi.cs509.ui.components.HeaderPanel;
import wpi.cs509.ui.components.ImagePanel;
import wpi.cs509.ui.components.StartPin;

public class RouteScreen2 {

	private JFrame frame;
	private JComboBox<String> buildingSelection,floorSelection,sourceSelection,destinationSelection;
	private ArrayList<String> buildingList, floorList, floorSelected;
	private ArrayList<Point> sourceList, destinationList,locations,p,restroomlist,vendinglist,droplist,printerlist;
	private StartPin source1;
	private EndPin destination1;
	private ImagePanel sameFloorMap, startlable, endlable;
	private Restroom restroom1;	
	private Vending vending1;
	private Drop drop1;	
	private Printer printer1;
	private int x1,x2,y1,y2;
	private ItemListener sListener,dListener;
	private String buildingselected,floorselected,newsource,newdestination;
	private int ss,ds,intsource,intdestination,flag=0;
	private JLabel starttext, endtext;
	private Point source0;
	private Point destination0;
	

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
		frame.getContentPane().setLayout(null);
		
		////////////
		// Header //
		////////////
		HeaderPanel headerPanel = new HeaderPanel("Route between two locations on the same floor of a building", false, frame);
		headerPanel.setBounds(0, 0, 1024, 730);
		frame.getContentPane().add(headerPanel);
		
		////////////
		// Map    //
		////////////
//		JPanel sameFloorSearchPanel = new JPanel();
//		sameFloorSearchPanel.setLayout(null);
//		frame.getContentPane().add(sameFloorSearchPanel);
//		sameFloorSearchPanel.setBounds(0, 180, 1024, 480);
		
		//controlPanel
		JPanel sameFloorControl = new JPanel();
		frame.add(sameFloorControl);
		sameFloorControl.setBounds(0, 180, 350, 480);
		sameFloorControl.setLayout(null);
		sameFloorControl.setBackground(Color.decode("#F1F1F1"));
		
		//buildingLabel
		JLabel building = new JLabel("Building");
		sameFloorControl.add(building);
		building.setBounds(25, 0, 300, 20);
		
		//buildingList
		sameFloorControl.add(buildingSelection);
		buildingSelection.setBounds(20, 30, 300, 20);
		buildingList = DataManager.getBuildings();
		buildingSelection.addItem("Please Choose Building");
		for(int i=0;i<buildingList.size();i++){	
			buildingSelection.addItem(buildingList.get(i));
		}
		
		floorList = DataManager.getFloorsMapsByBuildingName(buildingSelection.getItemAt(1).toString());
		floorSelection.addItem("Please Choose Floor");
		for(int i = 0;i<floorList.size();i++){
			floorSelection.addItem(floorList.get(i));
		}
		
		sourceList = DataManager.getLocationsByMapID(buildingSelection.getItemAt(1).toString(), floorSelection.getItemAt(1).toString());
		destinationList = DataManager.getLocationsByMapID(buildingSelection.getItemAt(1).toString(), floorSelection.getItemAt(1).toString());
		for(int i=0;i<sourceList.size();i++){
			sourceSelection.addItem("Please Choose Source");
			sourceSelection.addItem(sourceList.get(i).getName());
			destinationSelection.addItem("Please Choose Destination");
			destinationSelection.addItem(destinationList.get(i).getName());
		}
		
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
					floorSelection.addItem("Please Choose Floor");
					for(int i = 0;i<floorSelected.size();i++){
						floorSelection.addItem(floorSelected.get(i));
					}
				}
					System.out.println("You have chosen building"+" "+buildingSelection.getSelectedItem());
			}
		});
		
		//floorLabel
		JLabel floor = new JLabel("Floor");
		sameFloorControl.add(floor);
		floor.setBounds(25, 60, 300, 20);
		

		final JPanel panel_4 = new JPanel();
		panel_4.setBounds(350, 180, 640, 550);

		

		panel_4.setLayout(null);
		// add by Jiawei Sun
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
					String filename = DataManager.getMapPathByName(buildingselected,floorselected);  //this need to modify  ---Jiawei Sun
					System.out.println(filename);
					//filename = "maps\\GordonLibraryBasement.png";
					//filename = "F:\\GordonLibraryFirstFloor.jpg";
					sameFloorMap = new ImagePanel(filename, 640, 480);
					sameFloorMap.setBounds(0, 0, 640, 480);
					sameFloorMap.setBackground(Color.decode("#F1F1F1"));
				
					panel_4.removeAll();
					panel_4.add(startlable);
					startlable.repaint();
					panel_4.add(endlable);
					endlable.repaint();
					panel_4.add(starttext);
					starttext.repaint();
					panel_4.add(endtext);
					endtext.repaint();
					panel_4.add(sameFloorMap);
					panel_4.repaint();
					
					frame.add(panel_4);
					sameFloorMap.setLayout(null);
					//frame.repaint();
					//sameFloorMap.repaint();
					if(destination1!=null){
						destination1 = null;
						sourceSelection.removeAllItems();
						destinationSelection.removeAllItems();
						sourceSelection.removeItemListener(sListener);
						destinationSelection.removeItemListener(dListener);
					}
					if(source1!=null){
						source1=null;
						sourceSelection.removeAllItems();
						destinationSelection.removeAllItems();
						sourceSelection.removeItemListener(sListener);
						destinationSelection.removeItemListener(dListener);
					}
					if(source1==null&&destination1==null){
						sourceSelection.removeAllItems();
						destinationSelection.removeAllItems();
						sourceSelection.removeItemListener(sListener);
						destinationSelection.removeItemListener(dListener);
					}
					sourceSelection.addItem("Please Choose Source");
					destinationSelection.addItem("Please Choose Destination");
					for(int i = 0;i<locations.size();i++){
						sourceSelection.addItem(locations.get(i).getName().toString());
						destinationSelection.addItem(locations.get(i).getName().toString());
					}
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
		sListener = new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange()==ItemEvent.SELECTED){
					buildingselected = buildingSelection.getSelectedItem().toString();
					floorselected = floorSelection.getSelectedItem().toString();
					locations = DataManager.getLocationsByMapID(buildingselected, floorselected);	
						intsource = sourceSelection.getSelectedIndex()-1;
						x1 = locations.get(intsource).getX();
						y1 = locations.get(intsource).getY();
						source1 =new StartPin(x1, y1);
						sameFloorMap.removeAll();
						if(destination1==null){
							sameFloorMap.add(source1);
						}
						else{
							sameFloorMap.add(destination1);
							sameFloorMap.add(source1);
						}
						System.out.println(destinationSelection.getSelectedItem());
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
		destination.setBounds(25, 180, 150, 20);
		
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
						intdestination = destinationSelection.getSelectedIndex()-1;
						x2 = locations.get(intdestination).getX();
						y2 = locations.get(intdestination).getY();
						destination1 =new EndPin(x2, y2);
						sameFloorMap.removeAll();
						if(source1==null){
							sameFloorMap.add(destination1);
						}
						else{
							sameFloorMap.add(source1);
							sameFloorMap.add(destination1);
						}
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
		buttonPanel.setBounds(20, 250, 300, 40);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.setBackground(Color.decode("#F1F1F1"));
		
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
				buildingselected = buildingSelection.getSelectedItem().toString();
				floorselected = floorSelection.getSelectedItem().toString();
				locations = DataManager.getLocationsByMapID(buildingselected, floorselected);
				Graph rf = DirectorGraph.getInstance().getGraph();
//				Graph rf = DataManager.getGraphByNameWithDB(buildingselected, floorselected);
				intsource= sourceSelection.getSelectedIndex()-1;
				intdestination = destinationSelection.getSelectedIndex()-1;
				source0 = locations.get(intsource);
				System.out.println(source0+"bbbbbbbbbbbb");
				destination0 = locations.get(intdestination);
				System.out.println(destination0+"dddddddddddddd");
				p = RouteFinder.computePaths(source0, rf, destination0);
				DirectorGraph.getInstance().clearGraph();
				System.out.println(p.size());
				wpi.cs509.ui.util.Util.drawPath(sameFloorMap, p);
				sameFloorMap.setComponentZOrder(destination1, 0);
				System.out.println(source0+" "+destination0);
			}
		});
		
		JButton reverse = new JButton(new ImageIcon("maps//reverse.png"));
		reverse.setBounds(160, 175, 25, 25);
		
		sameFloorControl.add(reverse);
		
		reverse.setForeground(Color.decode("#F1F1F1"));
		reverse.setBackground(Color.decode("#AB2A36"));
		reverse.setOpaque(true);
		reverse.setBorderPainted(false);
		
		reverse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				newdestination = sourceSelection.getSelectedItem().toString();
				newsource = destinationSelection.getSelectedItem().toString();
				sourceSelection.setSelectedItem(newsource);
				destinationSelection.setSelectedItem(newdestination);
				System.out.println(intsource+"9999");
				if(p != null){
					Graph rf2 = DirectorGraph.getInstance().getGraph();
//					Graph rf2 = DataManager.getGraphByNameWithDB(buildingselected, floorselected);
					p = RouteFinder.computePaths(source0, rf2, destination0);
					DirectorGraph.getInstance().clearGraph();
					System.out.println(p.size());
					wpi.cs509.ui.util.Util.drawPath(sameFloorMap, p);
				}
			}
		});
// restroom button		
		JButton restroom = new JButton(new ImageIcon("maps//restroom.png"));
		restroom.setBounds(300, 300, 30, 30);
		sameFloorControl.add(restroom);
		restroom.setForeground(Color.decode("#F1F1F1"));
		restroom.setBackground(Color.decode("#AB2A36"));
		restroom.setOpaque(true);
		restroom.setBorderPainted(false);
		
		restroom.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				buildingselected = buildingSelection.getSelectedItem().toString();
				floorselected = floorSelection.getSelectedItem().toString();
				locations = DataManager.getLocationsByMapID(buildingselected, floorselected);
				restroomlist = new ArrayList<Point>();
				




				for( Point tp:locations){
					if ("RestRoom".equals(tp.getAttribute())){
                       restroomlist.add(tp);
					}
				}
				
				if(restroomlist!=null && restroomlist.size() >0){
					if (flag == 1){
						for(int i=0; i<restroomlist.size(); i++){
							restroom1 =new Restroom(restroomlist.get(i).getX(), restroomlist.get(i).getY());
		         			sameFloorMap.remove(restroom1);

						};
						sameFloorMap.repaint();
						flag = flag - 1;
					}
					else if(flag == 0){
					for(int i=0; i<restroomlist.size(); i++){
						restroom1 =new Restroom(restroomlist.get(i).getX(), restroomlist.get(i).getY());
	         			sameFloorMap.add(restroom1);

					}
					flag = flag + 1;
					sameFloorMap.repaint();
					}

				}
         			//sameFloorMap.add(restroom1);
					//sameFloorMap.repaint();
					//SolidPoint restroompoint = new SolidPoint(Color.red, restroomlist.get(0).getX(), restroomlist.get(0).getY());
					//sameFloorMap.add(restroompoint);
					//sameFloorMap.repaint();
				
				}

			
		});		
		// vending machine button		
				JButton vending = new JButton(new ImageIcon("maps//vending mechine.png"));
				vending.setBounds(300, 340, 30, 30);
				sameFloorControl.add(vending);
				vending.setForeground(Color.decode("#F1F1F1"));
				vending.setBackground(Color.decode("#AB2A36"));
				vending.setOpaque(true);
				vending.setBorderPainted(false);
				
				vending.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						buildingselected = buildingSelection.getSelectedItem().toString();
						floorselected = floorSelection.getSelectedItem().toString();
						locations = DataManager.getLocationsByMapID(buildingselected, floorselected);
						vendinglist = new ArrayList<Point>();
						for( Point tp:locations){
							if ("VendingMachine".equals(tp.getAttribute())){
		                       vendinglist.add(tp);
							}
						}
						if(vendinglist!=null && vendinglist.size() >0){

							for(int i=0; i<vendinglist.size(); i++){
								vending1 =new Vending(vendinglist.get(i).getX(), vendinglist.get(i).getY());
			         			sameFloorMap.add(vending1);
								sameFloorMap.repaint();
							}
							}

						}

					
				});	
				
// water fountain button		
				JButton drop = new JButton(new ImageIcon("maps//drop.png"));
				drop.setBounds(300, 420, 30, 30);
				sameFloorControl.add(drop);
				drop.setForeground(Color.decode("#F1F1F1"));
				drop.setBackground(Color.decode("#AB2A36"));
				drop.setOpaque(true);
				drop.setBorderPainted(false);
				
				drop.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						buildingselected = buildingSelection.getSelectedItem().toString();
						floorselected = floorSelection.getSelectedItem().toString();
						locations = DataManager.getLocationsByMapID(buildingselected, floorselected);
						droplist = new ArrayList<Point>();
						for( Point tp:locations){
							if ("Drop".equals(tp.getAttribute())){
								droplist.add(tp);
							}
						}
						if(droplist!=null && droplist.size() >0){

							for(int i=0; i<droplist.size(); i++){
								drop1 =new Drop(droplist.get(i).getX(), droplist.get(i).getY());
			         			sameFloorMap.add(drop1);
								sameFloorMap.repaint();
							}
							}
						}

					
				});	
// printer button		
				JButton printer = new JButton(new ImageIcon("maps//printer.png"));
				printer.setBounds(300, 380, 30, 30);
				sameFloorControl.add(printer);
				printer.setForeground(Color.decode("#F1F1F1"));
				printer.setBackground(Color.decode("#AB2A36"));
				printer.setOpaque(true);
				printer.setBorderPainted(false);
				
				printer.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						buildingselected = buildingSelection.getSelectedItem().toString();
						floorselected = floorSelection.getSelectedItem().toString();
						locations = DataManager.getLocationsByMapID(buildingselected, floorselected);
						printerlist = new ArrayList<Point>();
						for( Point tp:locations){
							if ("Printer".equals(tp.getAttribute())){
								printerlist.add(tp);
							}
						}
						if(printerlist!=null && printerlist.size() >0){

							for(int i=0; i<printerlist.size(); i++){
								printer1 =new Printer(printerlist.get(i).getX(), printerlist.get(i).getY());
			         			sameFloorMap.add(printer1);
								sameFloorMap.repaint();
							}
							}
						}

					
				});			
		
		startlable = new ImagePanel("maps//startpin.png", 20, 20);
		starttext = new JLabel("Start");
		starttext.setBounds(290, 480, 50, 20);
		startlable.setBounds(250, 480, 40, 50);
		
		endlable = new ImagePanel("maps//endpin.png", 20, 20);
		endtext = new JLabel("End");
		endtext.setBounds(410, 480, 50, 20);
		endlable.setBounds(370, 480, 40, 50);
	}
}
