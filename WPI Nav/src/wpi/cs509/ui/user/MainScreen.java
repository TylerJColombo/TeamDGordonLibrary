package wpi.cs509.ui.user;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import wpi.cs509.dataModel.Graph;
import wpi.cs509.ui.admin.AdminScreen;
import wpi.cs509.ui.components.HeaderPanel;

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
		
		/////////////
		// buttons //
		/////////////
		
		// Admin Button
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(824, 50, 150, 42);
		frame.getContentPane().add(panel_1);
		
		JButton btnAdmin = new JButton("Admin");
		btnAdmin.setPreferredSize(new Dimension(150, 36));
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminScreen adminScreen = new AdminScreen(graph);
				frame.dispose();
			}
		});
		panel_1.add(btnAdmin);
		
		// Nav buttons
		JPanel panel = new JPanel();
		panel.setBackground(Color.decode("#F1F1F1"));
		panel.setBounds(262, 300, 500, 500);
		frame.getContentPane().add(panel);
		
		JButton btnNewButton = new JButton("Route between two locations on the campus map");
		btnNewButton.setPreferredSize(new Dimension(500, 36));
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Route between two locations on the same floor of a building");
		btnNewButton_1.setPreferredSize(new Dimension(500, 36));
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Route between two locations between different floors of a building");
		btnNewButton_2.setPreferredSize(new Dimension(500, 36));
		panel.add(btnNewButton_2);
		
		///////////////////////////////////////////////////
		
		
	}
}
