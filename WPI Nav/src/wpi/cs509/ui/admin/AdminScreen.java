package wpi.cs509.ui.admin;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import wpi.cs509.dataManager.DataManager;
import wpi.cs509.dataModel.Map;
import wpi.cs509.dataModel.Point;
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
		frame.setResizable(false);
		
		////////////
		// Header //
		////////////
		HeaderPanel headerPanel = new HeaderPanel("Adminstration", false, frame);
		headerPanel.setBounds(0, 0, 1024, 730);
		frame.getContentPane().add(headerPanel);
		
		//////////
		// Tabs //
		//////////
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(20, 180, 970, 500);
//		tabbedPane.setForeground(Color.decode("#929292"));
		frame.getContentPane().add(tabbedPane);
		
		/////////////////
		// Add Map Tab //
		/////////////////
		JPanel addMapTab = new JPanel();
		addMapTab.setLayout(null);
		tabbedPane.addTab("Add Map", null, addMapTab, null);
		
		// Add Map left box
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 10, 300, 500);
		panel_1.setLayout(null);
		addMapTab.add(panel_1);
		
		JLabel lblNewLabel_6 = new JLabel("Map Name:");
		lblNewLabel_6.setBounds(20, 20, 280, 20);
		panel_1.add(lblNewLabel_6);
				
		JTextField txtMapName = new JTextField();
		txtMapName.setBounds(20, 50, 280, 20);
		panel_1.add(txtMapName);
				
		JLabel lblNewLabel_7 = new JLabel("Map File Location:");
		lblNewLabel_7.setBounds(20, 90, 280, 20);
		panel_1.add(lblNewLabel_7);
				
		JTextField txtMapPath = new JTextField();
		txtMapPath.setBounds(20, 120, 280, 20);
		panel_1.add(txtMapPath);
				
		JLabel lblNewLabel_8 = new JLabel("Map Scale:");
		lblNewLabel_8.setBounds(20, 160, 280, 20);
		panel_1.add(lblNewLabel_8);
				
		JTextField txtMapScale = new JTextField();
		txtMapScale.setBounds(20, 190, 280, 20);
		panel_1.add(txtMapScale);
				
		JButton btnSaveMap = new JButton("Add Map");
		btnSaveMap.setBounds(20, 230, 280, 25);
		btnSaveMap.setForeground(Color.decode("#F1F1F1"));
		btnSaveMap.setBackground(Color.decode("#AB2A36"));
		btnSaveMap.setOpaque(true);
		btnSaveMap.setBorderPainted(false);
		panel_1.add(btnSaveMap);
		
		// Add Map Action Listeners
		btnSaveMap.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DataManager.saveMap(txtMapName.getText(), txtMapPath.getText(), Float.parseFloat(txtMapScale.getText()));
			}
		});
		
		///////////////////
		// Add Point Tab //
		///////////////////
		JPanel addPointTab = new JPanel();
		addPointTab.setLayout(null);
		tabbedPane.addTab("Add Point", null, addPointTab, null);
		
		// Add Map left box
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 320, 500);
		panel.setLayout(null);
		addPointTab.add(panel);
		
		JLabel lblNewLabel = new JLabel("1) Selec map: ");
		lblNewLabel.setBounds(20, 20, 100, 20);
		panel.add(lblNewLabel);
		
		JComboBox<Map> comboMaps = new JComboBox<Map>();
		comboMaps.setBounds(120, 20, 180, 20);
		panel.add(comboMaps);
		
		JLabel lblNewLabel_1 = new JLabel("2) Click the point on the map.");
		lblNewLabel_1.setBounds(20, 60, 300, 20);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("3) Fill data and click Add Point button");
		lblNewLabel_2.setBounds(20, 100, 300, 20);
		panel.add(lblNewLabel_2);
		
		JLabel lblX = new JLabel("X: ");
		lblX.setBounds(20, 140, 30, 20);
		panel.add(lblX);
		
		JLabel lblXValue = new JLabel("0");
		lblXValue.setBounds(40, 140, 70, 20);
		panel.add(lblXValue);
		
		JLabel lblY = new JLabel("Y: ");
		lblY.setBounds(90, 140, 30, 20);
		panel.add(lblY);
		
		JLabel lblYValue = new JLabel("0");
		lblYValue.setBounds(110, 140, 70, 20);
		panel.add(lblYValue);
		
		JLabel lblNewLabel_5 = new JLabel("Location Name: ");
		lblNewLabel_5.setBounds(20, 180, 100, 20);
		panel.add(lblNewLabel_5);
		
		JTextField txtLocationName = new JTextField();
		txtLocationName.setBounds(120, 180, 180, 20);
		panel.add(txtLocationName);
		txtLocationName.setColumns(10);
		
		JCheckBox chckbxIsEnterance = new JCheckBox("is enterance");
		chckbxIsEnterance.setBounds(20, 220, 300, 20);
		panel.add(chckbxIsEnterance);
		
		JCheckBox chckbxIsLocation = new JCheckBox("is location");
		chckbxIsLocation.setBounds(20, 260, 300, 20);
		panel.add(chckbxIsLocation);
		
		JButton btnSavePoint = new JButton("Add Point");
		btnSavePoint.setBounds(20, 300, 280, 25);
		btnSavePoint.setForeground(Color.decode("#F1F1F1"));
		btnSavePoint.setBackground(Color.decode("#AB2A36"));
		btnSavePoint.setOpaque(true);
		btnSavePoint.setBorderPainted(false);
		panel.add(btnSavePoint);
		
		// Add Point Action Listeners 
		comboMaps.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// load selected map
				Map selectedMap = (Map)comboMaps.getSelectedItem();
				if(selectedMap != null){
					ImagePanel imagePanel2 = new ImagePanel(selectedMap.getFileLocation(), 640, 480);
					imagePanel2.setLayout(null);
					imagePanel2.setBounds(330, 0, 640, 480);
					
					imagePanel2.addMouseListener(new MouseListener() {
			            @Override
			            public void mouseClicked(MouseEvent e) {
			                System.out.println(":MOUSE_CLICK_EVENT:" + e.getX() + "," + e.getY());
			                lblXValue.setText(e.getX() + "");
			                lblYValue.setText(e.getY() + "");
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
					addPointTab.repaint();
				}
			}
		});
		
		btnSavePoint.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DataManager.addPoint(((Map)comboMaps.getSelectedItem()).getName(), Integer.parseInt(lblXValue.getText()), Integer.parseInt(lblYValue.getText()), chckbxIsEnterance.isSelected(), chckbxIsLocation.isSelected(), txtLocationName.getText());
			}
		});
		
		//////////////////
		// Add Edge Tab //
		//////////////////
		JPanel addEdgeTab = new JPanel();
		addEdgeTab.setLayout(null);
		tabbedPane.addTab("Add Edge", null, addEdgeTab, null);
		
		// Add Edge left box
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 10, 300, 500);
		panel_3.setLayout(null);
		addEdgeTab.add(panel_3);
		
		JLabel lblNewLabel_9 = new JLabel("First Point:");
		lblNewLabel_9.setBounds(20, 20, 280, 20);
		panel_3.add(lblNewLabel_9);
		
		JComboBox<Point> comboFirstPoint = new JComboBox<Point>();
		comboFirstPoint.setBounds(20, 50, 280, 20);
		for(Point point: DataManager.getAllPoints()){
			comboFirstPoint.addItem(point);
		}
		panel_3.add(comboFirstPoint);
		
		JLabel lblNewLabel_10 = new JLabel("Second Point:");
		lblNewLabel_10.setBounds(20, 90, 280, 20);
		panel_3.add(lblNewLabel_10);
				
		JComboBox<Point> comboSecondPoint = new JComboBox<Point>();
		comboSecondPoint.setBounds(20, 120, 280, 20);
		for(Point point: DataManager.getAllPoints()){
			comboSecondPoint.addItem(point);
		}
		panel_3.add(comboSecondPoint);
		
		JLabel lblNewLabel_11 = new JLabel("Weight:");
		lblNewLabel_11.setBounds(20, 160, 280, 20);
		panel_3.add(lblNewLabel_11);
				
		JTextField txtEdgeWeight = new JTextField();
		txtEdgeWeight.setBounds(20, 190, 280, 20);
		panel_3.add(txtEdgeWeight);
				
		JButton btnSaveEdge = new JButton("Add Edge");
		btnSaveEdge.setBounds(20, 230, 280, 25);
		btnSaveEdge.setForeground(Color.decode("#F1F1F1"));
		btnSaveEdge.setBackground(Color.decode("#AB2A36"));
		btnSaveEdge.setOpaque(true);
		btnSaveEdge.setBorderPainted(false);
		panel_3.add(btnSaveEdge);
		
		// Add Map Action Listeners
		btnSaveEdge.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DataManager.addEdge(((Point)comboFirstPoint.getSelectedItem()).getId(), ((Point)comboSecondPoint.getSelectedItem()).getId());
				//Float.parseFloat(txtEdgeWeight.getText()
			}
		});
		
		// tab change action listener
		tabbedPane.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				System.out.println("tab num: " + tabbedPane.getSelectedIndex());
				
				if(tabbedPane.getSelectedIndex() == 1){
					// refresh Maps combo box
					if(comboMaps.getItemCount() > 0){
						comboMaps.removeAllItems();
					}
					for(Map map: DataManager.getAllMaps()){
						comboMaps.addItem(map);
					}
				}
			}
		});
	}
}
