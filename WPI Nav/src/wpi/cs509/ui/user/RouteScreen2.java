package wpi.cs509.ui.user;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import wpi.cs509.ui.components.HeaderPanel;
import wpi.cs509.ui.components.ImagePanel;

public class RouteScreen2 {

	private JFrame frame;

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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.decode("#F1F1F1"));
		frame.setBounds(0, 0, 1024, 730);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		////////////
		// Header //
		////////////
		HeaderPanel headerPanel = new HeaderPanel();
		headerPanel.setBounds(0, 0, 1024, 730);
		frame.getContentPane().add(headerPanel);
		
		////////////
		// Map    //
		////////////
		JPanel sameFloorSearchPanel = new JPanel();
		sameFloorSearchPanel.setLayout(null);
		frame.getContentPane().add(sameFloorSearchPanel);
		sameFloorSearchPanel.setBounds(20, 180, 1024, 480);
		
		//controlPanel
		JPanel sameFloorControl = new JPanel();
		sameFloorSearchPanel.add(sameFloorControl);
		sameFloorControl.setBounds(20, 180, 300, 480);
		
		
		//buildingPanel
		JPanel buildingSelectionPanel = new JPanel();
		sameFloorControl.add(buildingSelectionPanel);
		
		//buildingLabel
		JLabel building = new JLabel("building");
		buildingSelectionPanel.add(building);
		
		//buildingList
		JComboBox<String> buildingSelection = new JComboBox<>();
		buildingSelectionPanel.add(buildingSelection);
		buildingSelection.addItem("building1");
		buildingSelection.addItem("biulding2");
		buildingSelection.addItem("building3");
		
		
		//floorPanel
		JPanel floorSelectionPanel = new JPanel();
		sameFloorControl.add(floorSelectionPanel);
		
		//floorLabel
		JLabel floor = new JLabel("floor");
		floorSelectionPanel.add(floor);
		
		//floorList
		JComboBox<String> floorSelection = new JComboBox<>();
		floorSelectionPanel.add(floorSelection);
		floorSelection.addItem("floor1");
		floorSelection.addItem("floor2");
		floorSelection.addItem("floor3");
		
		
		//sourcePanel
		JPanel sourceSelectionPanel = new JPanel();
		sameFloorControl.add(sourceSelectionPanel);
		
		//sourceLabel
		JLabel source = new JLabel("source");
		sourceSelectionPanel.add(source);
		
		//sourceList
		JComboBox<String> sourceSelection = new JComboBox<>();
		sourceSelectionPanel.add(sourceSelection);
		sourceSelection.addItem("source1");
		sourceSelection.addItem("source2");
		sourceSelection.addItem("source3");
		
		
		//destinationPanel
		JPanel destinationSelectionPanel = new JPanel();
		sameFloorControl.add(destinationSelectionPanel);
		
		//destinationLabel
		JLabel destination = new JLabel("destination");
		destinationSelectionPanel.add(destination);
		
		//destinationList
		JComboBox<String> destinationSelection = new JComboBox<>();
		destinationSelectionPanel.add(destinationSelection);
		destinationSelection.addItem("destination1");
		destinationSelection.addItem("destination2");
		destinationSelection.addItem("destination3");
		
		
		//map
		ImagePanel sameFloorMap = new ImagePanel("", 640, 480);
		sameFloorMap.setBounds(350, 180, 640, 480);
		sameFloorSearchPanel.add(sameFloorMap);
		sameFloorMap.setBackground(Color.BLACK);
		
		
		
		//640 480(map size)
		//left side(drop down boxes: label+ box)
		//same building same floor
		//same image panel x,y,z (350,180,640,480)
		//
	}

}
