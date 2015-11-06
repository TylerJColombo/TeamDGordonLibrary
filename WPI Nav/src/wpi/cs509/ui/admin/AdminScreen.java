package wpi.cs509.ui.admin;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import wpi.cs509.ui.components.HeaderPanel;
import wpi.cs509.ui.components.ImagePanel;
import wpi.cs509.ui.components.SolidPoint;

public class AdminScreen {

	private JFrame frame;
	
	/**
	 * Create the window.
	 */
	public AdminScreen() {
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
		HeaderPanel headerPanel = new HeaderPanel("Adminstration", false, frame);
		headerPanel.setBounds(0, 0, 1024, 730);
		frame.getContentPane().add(headerPanel);
		
		/////////
		// Tabs //
		/////////
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(20, 180, 970, 500);
//		tabbedPane.setForeground(Color.decode("#929292"));
		frame.getContentPane().add(tabbedPane);
		
		// Add Map Tab
		JPanel addMapTab = new JPanel();
		addMapTab.setLayout(null);
		tabbedPane.addTab("Add Map", null, addMapTab, null);
		
		// Add Point Tab
		JPanel addPointTab = new JPanel();
		addPointTab.setLayout(null);
		tabbedPane.addTab("Add Point", null, addPointTab, null);
		
		
		
		// Add Map left box
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 300, 500);
		addPointTab.add(panel);
		
		JLabel lblNewLabel = new JLabel("1) Selec map: ");
//		lblNewLabel.setForeground(Color.decode("#929292"));
		panel.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		panel.add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("2) Click the point on the map.");
//		lblNewLabel_1.setForeground(Color.decode("#929292"));
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("3) Fill data and click Add Point button");
//		lblNewLabel_2.setForeground(Color.decode("#929292"));
		panel.add(lblNewLabel_2);
		
		JLabel lblX = new JLabel("X: 0");
//		lblX.setForeground(Color.decode("#929292"));
		panel.add(lblX);
		
		JLabel lblY = new JLabel("Y: 0");
//		lblY.setForeground(Color.decode("#929292"));
		panel.add(lblY);
		
		JLabel lblNewLabel_5 = new JLabel("Location Name: ");
//		lblNewLabel_5.setForeground(Color.decode("#929292"));
		panel.add(lblNewLabel_5);
		
		JTextField textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("is enterance");
//		chckbxNewCheckBox.setForeground(Color.decode("#929292"));
		panel.add(chckbxNewCheckBox);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("is location");
//		chckbxNewCheckBox_1.setForeground(Color.decode("#929292"));
		panel.add(chckbxNewCheckBox_1);
		
		JButton btnNewButton = new JButton("Add Point");
		btnNewButton.setForeground(Color.decode("#F1F1F1"));
		btnNewButton.setBackground(Color.decode("#AB2A36"));
		panel.add(btnNewButton);
		
		
		
		
		
		ImagePanel imagePanel2 = new ImagePanel("D:\\WPI\\CS509\\campus_map1.jpg", 640, 480);
		imagePanel2.setLayout(null);
		imagePanel2.setBounds(330, 0, 640, 480);
		
		imagePanel2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(":MOUSE_CLICK_EVENT:" + e.getX() + "," + e.getY());
                lblX.setText("X: " + e.getX());
                lblY.setText("Y: " + e.getY());
                SolidPoint pointUI = new SolidPoint(Color.RED, e.getX(), e.getY());
                imagePanel2.add(pointUI);
                imagePanel2.repaint();
            }

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
        });
		addPointTab.add(imagePanel2);
		
				
		// Add Edge Tab
		JPanel addEdgeTab = new JPanel();
		tabbedPane.addTab("Add Edge", null, addEdgeTab, null);
		
		
		
	}
}
