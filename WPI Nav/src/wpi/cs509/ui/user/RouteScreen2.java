package wpi.cs509.ui.user;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.text.AbstractDocument.LeafElement;

import javafx.scene.shape.Box;
import wpi.cs509.ui.components.HeaderPanel;
import wpi.cs509.ui.components.ImagePanel;
import wpi.cs509.ui.components.Line;

public class RouteScreen2 {

	private JFrame frame;
	private Graphics g;

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
		sameFloorControl.setBounds(20, 180, 300, 350);
		sameFloorControl.setLayout(new GridLayout(9, 0));
		
		
		//buildingPanel
//		JPanel buildingSelectionPanel = new JPanel();
//		sameFloorControl.add(buildingSelectionPanel);
		
		
		//buildingLabel
		JLabel building = new JLabel("Building");
		sameFloorControl.add(building);
		building.setFont(new Font("Serif", Font.PLAIN, 16));
		
		//buildingList
		String[] buildingList = new String[]{"building1","building2","building3"};
		JComboBox<String> buildingSelection = new JComboBox<>(buildingList);
		sameFloorControl.add(buildingSelection);
		buildingSelection.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange()==ItemEvent.SELECTED)
					System.out.println("You have chosen"+" "+buildingSelection.getSelectedItem());
			}
		});
		
		
		//floorPanel
//		JPanel floorSelectionPanel = new JPanel();
//		sameFloorControl.add(floorSelectionPanel);
		
		//floorLabel
		JLabel floor = new JLabel("Floor");
		sameFloorControl.add(floor);
		floor.setFont(new Font("Serif", Font.PLAIN, 16));
		
		//floorList
		String[] floorList = new String[]{"floor1","floor2","floor3"};
		JComboBox<String> floorSelection = new JComboBox<>(floorList);
		sameFloorControl.add(floorSelection);
		floorSelection.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange()==ItemEvent.SELECTED)
					System.out.println("You have chosen"+" "+floorSelection.getSelectedItem());
			}
		});
		
		//sourcePanel
//		JPanel sourceSelectionPanel = new JPanel();
//		sameFloorControl.add(sourceSelectionPanel);
		
		//sourceLabel
		JLabel source = new JLabel("Source");
		sameFloorControl.add(source);
		source.setFont(new Font("Serif", Font.PLAIN, 16));
		
		//sourceList
		String[] sourceList = new String[]{"source1","source2","source3"};
		JComboBox<String> sourceSelection = new JComboBox<>(sourceList);
		sameFloorControl.add(sourceSelection);
		sourceSelection.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange()==ItemEvent.SELECTED)
					System.out.println("You have chosen"+" "+sourceSelection.getSelectedItem());
			}
		});
		
		//destinationPanel
//		JPanel destinationSelectionPanel = new JPanel();
//		sameFloorControl.add(destinationSelectionPanel);
		
		//destinationLabel
		JLabel destination = new JLabel("Destination");
		sameFloorControl.add(destination);
		destination.setFont(new Font("Serif", Font.PLAIN, 16));
		
		//destinationList
		String[] destinationList = new String[]{"destination1","destination2","destination2"};
		JComboBox<String> destinationSelection = new JComboBox<>(destinationList);
		sameFloorControl.add(destinationSelection);
		destinationSelection.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange()==ItemEvent.SELECTED)
					System.out.println("You have chosen"+" "+destinationSelection.getSelectedItem());
			}
		});
		
		//buttonPanel
		JPanel buttonPanel = new JPanel();
		sameFloorControl.add(buttonPanel);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		//Find route button
		JButton findRoute = new JButton("Find Route");
		buttonPanel.add(findRoute);	
//		findRoute.setBackground(Color.white);
//		findRoute.setForeground(Color.black);
//		findRoute.setOpaque(true);
//		findRoute.setBorderPainted(false);
		
		
		//map
		ImagePanel sameFloorMap = new ImagePanel("maps\\project center.png", 640, 480);
		sameFloorMap.setBounds(350, 180, 640, 480);
		sameFloorSearchPanel.add(sameFloorMap);
		
		
		
		//640 480(map size)
		//left side(drop down boxes: label+ box)
		//same building same floor
		//same image panel x,y,z (350,180,640,480)
		//
	}

}
