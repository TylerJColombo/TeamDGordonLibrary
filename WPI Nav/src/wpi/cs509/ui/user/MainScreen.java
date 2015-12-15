package wpi.cs509.ui.user;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import wpi.cs509.dataManager.DataManager;
import wpi.cs509.dataManager.DirectorGraph;
import wpi.cs509.ui.components.HeaderPanel;

public class MainScreen {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new MainScreen();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the window.
	 */
	public MainScreen() {
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		DirectorGraph.g = DataManager.getGraphByNameWithDB("123", "234");
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.decode("#F1F1F1"));
		frame.setBounds(0, 0, 1024, 730);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		////////////
		// Header //
		////////////
		HeaderPanel headerPanel = new HeaderPanel("WPI Navigation System", true, frame);
		headerPanel.setBounds(0, 0, 1024, 730);
		frame.getContentPane().add(headerPanel);
		
		/////////////////
		// Nav buttons //
		/////////////////
		JPanel panel = new JPanel();
		panel.setBackground(Color.decode("#F1F1F1"));
		panel.setBounds(262, 300, 500, 500);
		frame.getContentPane().add(panel);
		
		JButton btnNewButton = new JButton("Route between two locations on the campus map");
		btnNewButton.setPreferredSize(new Dimension(500, 36));
		btnNewButton.setForeground(Color.decode("#F1F1F1"));
		btnNewButton.setBackground(Color.decode("#AB2A36"));
		btnNewButton.setOpaque(true);
		btnNewButton.setBorderPainted(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RouteScreen1();
				frame.dispose();
			}
		});
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Route between two locations on the same floor of a building");
		btnNewButton_1.setPreferredSize(new Dimension(500, 36));
		btnNewButton_1.setForeground(Color.decode("#F1F1F1"));
		btnNewButton_1.setBackground(Color.decode("#AB2A36"));
		btnNewButton_1.setOpaque(true);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RouteScreen2();
				frame.dispose();
			}
		});
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Route between two locations between different floors of a building");
		btnNewButton_2.setPreferredSize(new Dimension(500, 36));
		btnNewButton_2.setForeground(Color.decode("#F1F1F1"));
		btnNewButton_2.setBackground(Color.decode("#AB2A36"));
		btnNewButton_2.setOpaque(true);
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RouteScreen3();
				frame.dispose();
			}
		});
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("General Route");
		btnNewButton_3.setPreferredSize(new Dimension(500, 36));
		btnNewButton_3.setForeground(Color.decode("#F1F1F1"));
		btnNewButton_3.setBackground(Color.decode("#AB2A36"));
		btnNewButton_3.setOpaque(true);
		btnNewButton_3.setBorderPainted(false);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RouteScreen4();
				frame.dispose();
			}
		});
		panel.add(btnNewButton_3);
		
		///////////////////////////////////////////////////
		
		
	}
}
