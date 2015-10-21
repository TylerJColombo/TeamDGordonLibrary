package wpi.cs509.ui.user;

import java.awt.EventQueue;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import wpi.cs509.dataModel.Graph;
import wpi.cs509.ui.admin.AdminScreen;

public class MainScreen {

	private JFrame frame;
	private Graph graph;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen window = new MainScreen(new Graph());
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
	public MainScreen(Graph graph) {
		this.graph = graph;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1024, 730);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(262, 200, 500, 500);
		frame.getContentPane().add(panel);
		
		JButton btnNewButton = new JButton("Route between two locations on the campus map");
		btnNewButton.setPreferredSize(new Dimension(500, 36));
		btnNewButton.setMargin(new Insets(20, 20, 20, 20));
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Route between two locations on the same floor of a building");
		btnNewButton_1.setPreferredSize(new Dimension(500, 36));
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Route between two locations between different floors of a building");
		btnNewButton_2.setPreferredSize(new Dimension(500, 36));
		panel.add(btnNewButton_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(572, 104, 116, 42);
		frame.getContentPane().add(panel_1);
		
		JButton btnAdmin = new JButton("Admin");
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminScreen adminScreen = new AdminScreen(graph);
				frame.dispose();
			}
		});
		panel_1.add(btnAdmin);
	}
}
