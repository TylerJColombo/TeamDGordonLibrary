<<<<<<< Updated upstream
package wpi.cs509.ui.user;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class RouteScreen1 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RouteScreen1 window = new RouteScreen1();
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
	public RouteScreen1() {
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
=======
package wpi.cs509.ui.user;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;

import wpi.cs509.ui.components.HeaderPanel;
import wpi.cs509.ui.components.ImagePanel;
import wpi.cs509.ui.components.Line;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import com.sun.xml.internal.ws.org.objectweb.asm.Label;

import sun.launcher.resources.launcher;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.awt.event.ItemEvent;

public class RouteScreen1 {

	private JFrame frame;
	JComboBox comboBox_1 = new JComboBox();
	String labels[] = { "A", "B", "C", "D", "E", "F" };
	JComboBox comboBox = new JComboBox(labelsofbuildings.toArray());
	ImagePanel  imagePanelCmap;
	String source;
	String destination;
	static ArrayList labelsofbuildings=new ArrayList();
	
	

	    
    

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		 
		  labelsofbuildings.add("a");
		  labelsofbuildings.add("b");
		  labelsofbuildings.add("c");
	  
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RouteScreen1 window = new RouteScreen1();
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
	public RouteScreen1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		  al.add("a");
		  al.add("b");
		  al.add("c"); 
		
		  
		frame = new JFrame();
		frame.setBounds(0, 0, 1024, 730);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
        ////////////
		// Header //
		////////////
		HeaderPanel headerPanel = new HeaderPanel(); 
		headerPanel.setBounds(0, 0, 1024, 730);
		frame.getContentPane().add(headerPanel);

		
//imagepanel for map		
		imagePanelCmap = new ImagePanel("maps//campusmap.gif", 640, 480);
		imagePanelCmap.setLayout(null);
		imagePanelCmap.setBounds(330, 180, 640, 480);
		frame.getContentPane().add(imagePanelCmap);

		
//label for source		
		JLabel lblSource = new JLabel("source:");
		lblSource.setBounds(55, 204, 150, 27);
		frame.getContentPane().add(lblSource);

		
//combobox for source
		comboBox.setBounds(50, 234, 150, 27);
		comboBox.setBounds(50, 234, 150, 27);
		frame.getContentPane().add(comboBox);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
			source =comboBox.getSelectedItem().toString();
			System.out.println(source);
			}
		});
		
//label for destination
		JLabel lblDestination = new JLabel("destination:");
		lblDestination.setBounds(55, 264, 81, 16);
		frame.getContentPane().add(lblDestination);
		
		
//DESTINATION COMBOBOX
		comboBox_1.setBounds(50, 284, 150, 27);
		comboBox_1.addItem("option1");
		comboBox_1.addItem("option2");
		comboBox_1.addItem("option3");
	    frame.getContentPane().add(comboBox_1);
		comboBox_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				destination =comboBox_1.getSelectedItem().toString();
				System.out.println(destination);
			}
		});
		
//BUTTON		
		JButton btnFindingRoute = new JButton("Finding Route");
		btnFindingRoute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("option2");
// use string of source and destination to get the their pointsid than send it to the RoutFinder
				
//		Util.drawPath(imagePanelCmap,RouteFinder(sourceid,destinationid));		
			}
		});
		btnFindingRoute.setBounds(55, 357, 117, 29);

		frame.getContentPane().add(btnFindingRoute);
		
//	    imagePanelCmap.repaint();  
	
	
	}

 
	

}
	

>>>>>>> Stashed changes
