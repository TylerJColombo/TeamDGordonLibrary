package wpi.cs509.ui.admin;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import wpi.cs509.dataModel.Graph;
import wpi.cs509.ui.components.HeaderPanel;
import wpi.cs509.ui.components.ImagePanel;
import javax.swing.JTabbedPane;

public class AdminScreen {

	private JFrame frame;
	private Graph graph;

	/**
	 * Create the window.
	 */
	public AdminScreen(Graph graph) {
		this.graph = graph;
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.decode("#F1F1F1"));
		frame.setBounds(0, 0, 1024, 730);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		////////////
		// Header //
		////////////
		HeaderPanel headerPanel = new HeaderPanel();
		headerPanel.setBounds(0, 0, 1024, 730);
		frame.getContentPane().add(headerPanel);
		
		/////////
		// Map //
		/////////
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(20, 180, 970, 500);
		frame.getContentPane().add(tabbedPane);
		
		// Add Map Tab
		JPanel addMapTab = new JPanel();
		addMapTab.setLayout(null);
		tabbedPane.addTab("Add Map", null, addMapTab, null);
		
		// Add Point Tab
		JPanel addPointTab = new JPanel();
		addPointTab.setLayout(null);
		tabbedPane.addTab("Add Point", null, addPointTab, null);
		
		ImagePanel imagePanel2 = new ImagePanel("D:\\WPI\\CS509\\campus_map1.jpg", 640, 480);
		imagePanel2.setBounds(330, 0, 640, 480);
		addPointTab.add(imagePanel2);
		
		// Add Edge Tab
		JPanel addEdgeTab = new JPanel();
		tabbedPane.addTab("Add Edge", null, addEdgeTab, null);
		
		
	}
}
