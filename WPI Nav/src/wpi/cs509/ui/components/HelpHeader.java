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

public class HelpHeader extends JComponent{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4951272990505867350L;

	public HelpHeader(String title, Boolean isHelp, final JFrame frame) {
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

		panel_2.setBounds(105, 100, 600, 35);

		panel_2.setBounds(105, 100, 1024, 35);

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
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(774, 40, 100, 42);
		this.add(panel_3);
		
		if(isHelp){
			// Back Button
			JButton btnAdmin = new JButton("Back");
			btnAdmin.setPreferredSize(new Dimension(100, 36));
			btnAdmin.setForeground(Color.decode("#F1F1F1"));
			btnAdmin.setBackground(Color.decode("#AB2A36"));
			btnAdmin.setOpaque(true);
			btnAdmin.setBorderPainted(false);
			btnAdmin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.dispose();
				}
			});
			panel_1.add(btnAdmin);
			
			JButton btnHelp = new JButton("Help");
			btnHelp.setPreferredSize(new Dimension(100, 36));
			btnHelp.setForeground(Color.decode("#AB2A36"));
			btnHelp.setBackground(Color.lightGray);
			btnHelp.setOpaque(true);
			btnHelp.setBorderPainted(false);
			panel_3.add(btnHelp);
		}
		
		Line separator = new Line(Color.decode("#929292"), 0, 160, 1024, 160);
		this.add(separator, new Integer(1), 0);
	}
}
