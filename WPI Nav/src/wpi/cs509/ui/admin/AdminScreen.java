package wpi.cs509.ui.admin;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import wpi.cs509.dataModel.Graph;
import wpi.cs509.ui.components.ImagePanel;

public class AdminScreen {

	private JFrame frame;
	private Graph graph;
	private final JLabel lblXhhh = new JLabel("x:hhh");
	private final JPanel panel = new JPanel();

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
		frame.setResizable(false);
		frame.setBounds(0, 0, 1024, 730);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel_header = new JPanel();
		panel_header.setBounds(0, 0, 800, 60);
		frame.getContentPane().add(panel_header);
		
		JLabel label_1 = new JLabel("label 1");
		panel_header.add(label_1);
		
		JLabel label_2 = new JLabel("label 2");
		panel_header.add(label_2);
		
		
		ImagePanel imagePanel = new ImagePanel();
		imagePanel.setBounds(80, 180, 640, 480);
		frame.getContentPane().add(imagePanel);
	}
}
