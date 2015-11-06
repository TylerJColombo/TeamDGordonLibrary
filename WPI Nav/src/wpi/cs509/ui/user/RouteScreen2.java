package wpi.cs509.ui.user;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import wpi.cs509.ui.components.HeaderPanel;
import wpi.cs509.ui.components.ImagePanel;

public class RouteScreen2 {

	private JFrame frame;
	private JComboBox<String> buildingSelection,floorSelection,sourceSelection,destinationSelection;
	

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
		
		
		String[] buildingList = new String[]{"building1","building2","building3"};
		String[][] floorList = new String[][]{{"b1floor1","b1floor2","b1floor3"},{"b2floor1","b2floor2","b2floor3"},{"b3floor1","b3floor2","b3floor3"}};
		String[][][] sourceList = new String[][][]{{{"b1f1source1","b1f1source2","b1f1source3"},{"b1f2source1","b1f2source2","b1f2source3"},{"b1f3source1","b1f3source2","b1f3source3"}},
			{{"b2f1source1","b2f1source2","b2f1source3"},{"b2f2source1","b2f2source2","b2f2source3"},{"b2f3source1","b2f3source2","b2f3source3"}},
			{{"b3f1source1","b3f1source2","b3f1source3"},{"b3f2source1","b3f2source2","b3f2source3"},{"b3f3source1","b3f3source2","b3f3source3"}}};
		String[][][] destinationList = new String[][][]{{{"b1f1source1","b1f1source2","b1f1source3"},{"b1f2source1","b1f2source2","b1f2source3"},{"b1f3source1","b1f3source2","b1f3source3"}},
			{{"b2f1source1","b2f1source2","b2f1source3"},{"b2f2source1","b2f2source2","b2f2source3"},{"b2f3source1","b2f3source2","b2f3source3"}},
			{{"b3f1source1","b3f1source2","b3f1source3"},{"b3f2source1","b3f2source2","b3f2source3"},{"b3f3source1","b3f3source2","b3f3source3"}}};
		buildingSelection = new JComboBox<String>(buildingList);
		floorSelection = new JComboBox<String>(floorList[0]);
		sourceSelection = new JComboBox<String>(sourceList[0][0]);
		destinationSelection = new JComboBox<String>(destinationList[0][0]);
			
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
		buildingSelection.setSelectedIndex(0);
		buildingSelection.setBounds(20, 30, 300, 20);
		buildingSelection.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				floorSelection.removeAllItems();
				sourceSelection.removeAllItems();
				destinationSelection.removeAllItems();
				// TODO Auto-generated method stub
				if(e.getStateChange()==ItemEvent.SELECTED)
				{
					int index = buildingSelection.getSelectedIndex();
					for(int i = 0;i<floorList[0].length;i++){
						for(int j=0;j<sourceList[0][0].length;j++){
							sourceSelection.addItem(sourceList[index][i][j]);
							destinationSelection.addItem(destinationList[index][i][j]);
						}
						floorSelection.addItem(floorList[index][i]);
					}
					
				}
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
					int index0 = buildingSelection.getSelectedIndex();
					int index1 = floorSelection.getSelectedIndex();
					for(int i = 0;i<sourceList[0][0].length;i++){
						sourceSelection.addItem(sourceList[index0][index1][i]);
						destinationSelection.addItem(destinationList[index0][index1][i]);
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
		sourceSelection.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange()==ItemEvent.SELECTED)
					System.out.println("You have chosen source"+" "+sourceSelection.getSelectedItem());
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
				if(e.getStateChange()==ItemEvent.SELECTED)
					System.out.println("You have chosen destination"+" "+destinationSelection.getSelectedItem());
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
		
		
		//map
		ImagePanel sameFloorMap = new ImagePanel("maps\\project center.png", 640, 480);
		sameFloorMap.setBounds(350, 180, 640, 480);
		sameFloorSearchPanel.add(sameFloorMap);
		sameFloorMap.setLayout(null);
//		Line separator = new Line(Color.decode("#929292"), 360, 200, 900, 200);
//		sameFloorMap.add(separator, new Integer(1), 0);
//		sameFloorMap.repaint();
		
	}


}
