package wpi.cs509.ui.admin;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import wpi.cs509.dataModel.Graph;
import wpi.cs509.ui.components.HeaderPanel;
import wpi.cs509.ui.components.ImagePanel;

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
		ImagePanel imagePanel = new ImagePanel("D:\\WPI\\CS509\\campus_map1.jpg", 640, 480);
		imagePanel.setBounds(350, 200, 640, 480);
		frame.getContentPane().add(imagePanel);
	}
}
