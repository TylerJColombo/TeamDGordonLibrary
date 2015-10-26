package wpi.cs509.ui.components;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class HeaderPanel extends JComponent{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4951272990505867350L;

	public HeaderPanel() {
		super();
		
		// Customize header
		this.setBackground(Color.decode("#F1F1F1"));
		
		ImagePanel logo = new ImagePanel("maps\\wpi-logo.gif", 461, 67);
		logo.setBounds(50, 50, 461, 67);
		this.add(logo);
		
		JPanel panel_2 = new JPanel();
		panel_2.setForeground(Color.decode("#AB2A36"));
		panel_2.setBackground(Color.decode("#F1F1F1"));
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_2.setBounds(50, 120, 461, 35);
		this.add(panel_2);
		
		JLabel lblNewLabel = new JLabel("WPI Navigation System");
		lblNewLabel.setForeground(Color.decode("#AB2A36"));
		lblNewLabel.setBackground(Color.decode("#F1F1F1"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_2.add(lblNewLabel);
		
		Line separator = new Line(Color.decode("#929292"), 0, 160, 1024, 160);
		this.add(separator, new Integer(1), 0);
	}
}
