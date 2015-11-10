package wpi.cs509.ui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import wpi.cs509.ui.admin.AdminScreen;
import wpi.cs509.ui.user.MainScreen;

public class HeaderPanel extends JComponent{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4951272990505867350L;

	public HeaderPanel(String title, Boolean isHome, JFrame frame) {
		super();
		
		// Customize header
		this.setBackground(Color.decode("#F1F1F1"));
		
		ImagePanel logo = new ImagePanel("maps//wpi-logo.gif", 461, 67);
		logo.setBounds(30, 30, 461, 67);
		this.add(logo);
		
		JPanel panel_2 = new JPanel();
		panel_2.setForeground(Color.decode("#AB2A36"));
		panel_2.setBackground(Color.decode("#F1F1F1"));
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
<<<<<<< HEAD
		panel_2.setBounds(105, 100, 600, 35);
=======
		panel_2.setBounds(105, 100, 1024, 35);
>>>>>>> origin/master
		this.add(panel_2);
		
		JLabel lblNewLabel = new JLabel(title);
		lblNewLabel.setForeground(Color.decode("#AB2A36"));
		lblNewLabel.setBackground(Color.decode("#F1F1F1"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_2.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(884, 40, 100, 42);
		this.add(panel_1);
		
		if(isHome){
			// Admin Button
			JButton btnAdmin = new JButton("Admin");
			btnAdmin.setPreferredSize(new Dimension(100, 36));
			btnAdmin.setForeground(Color.decode("#F1F1F1"));
			btnAdmin.setBackground(Color.decode("#AB2A36"));
			btnAdmin.setOpaque(true);
			btnAdmin.setBorderPainted(false);
			btnAdmin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new AdminScreen();
					frame.dispose();
				}
			});
			panel_1.add(btnAdmin);
		} else {
			// Home Button
			JButton btnAdmin = new JButton("Home");
			btnAdmin.setPreferredSize(new Dimension(100, 36));
			btnAdmin.setForeground(Color.decode("#F1F1F1"));
			btnAdmin.setBackground(Color.decode("#AB2A36"));
			btnAdmin.setOpaque(true);
			btnAdmin.setBorderPainted(false);
			btnAdmin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new MainScreen();
					frame.dispose();
				}
			});
			panel_1.add(btnAdmin);
		}
		
		Line separator = new Line(Color.decode("#929292"), 0, 160, 1024, 160);
		this.add(separator, new Integer(1), 0);
	}
}
