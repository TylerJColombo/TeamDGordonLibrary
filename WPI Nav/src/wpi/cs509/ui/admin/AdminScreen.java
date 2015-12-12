package wpi.cs509.ui.admin;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

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
import wpi.cs509.dataManager.DataManagerWithMem;
import wpi.cs509.dataModel.Edge;
import wpi.cs509.dataModel.Map;
import wpi.cs509.dataModel.Point;
import wpi.cs509.ui.components.HeaderPanel;
import wpi.cs509.ui.components.ImagePanel;
import wpi.cs509.ui.components.Line;
import wpi.cs509.ui.components.SolidPoint;

public class AdminScreen {

	private JFrame frame;
	private int currentPoint = 1;
	private ImagePanel imagePanelMap;
	private SolidPoint pointUI1;
	private SolidPoint pointUI2;
	private Line edgeLine;
	private Map selectedMap;
	
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
		HeaderPanel headerPanel = new HeaderPanel("Administration", false, frame);
		headerPanel.setBounds(0, 0, 1024, 730);
		frame.getContentPane().add(headerPanel);
		
		//////////
		// Tabs //
		//////////
		final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(20, 180, 970, 500);
//		tabbedPane.setForeground(Color.decode("#929292"));
		frame.getContentPane().add(tabbedPane);
		
		/////////////////
		// Add Map Tab //
		/////////////////
		final JPanel addMapTab = new JPanel();
		addMapTab.setLayout(null);
		tabbedPane.addTab("Add/Delete Map", null, addMapTab, null);
		
		// Add Map left box
		final JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 10, 300, 500);
		panel_1.setLayout(null);
		addMapTab.add(panel_1);
		
		final JLabel lblNewLabel_6 = new JLabel("Building Name:");
		lblNewLabel_6.setBounds(20, 20, 280, 20);
		panel_1.add(lblNewLabel_6);
				
		final JTextField txtMapName = new JTextField();
		txtMapName.setBounds(20, 50, 280, 20);
		panel_1.add(txtMapName);
				
		JLabel lblNewLabel_12 = new JLabel("Floor Number:");
		lblNewLabel_12.setBounds(20, 90, 280, 20);
		panel_1.add(lblNewLabel_12);
				
		final JTextField txtFloorNum = new JTextField();
		txtFloorNum.setBounds(20, 120, 280, 20);
		panel_1.add(txtFloorNum);
				
		JLabel lblNewLabel_7 = new JLabel("Map File Location:");
		lblNewLabel_7.setBounds(20, 160, 280, 20);
		panel_1.add(lblNewLabel_7);
				
		final JTextField txtMapPath = new JTextField();
		txtMapPath.setBounds(20, 190, 280, 20);
		panel_1.add(txtMapPath);
				
		JLabel lblNewLabel_8 = new JLabel("Map Scale:");
		lblNewLabel_8.setBounds(20, 230, 280, 20);
		panel_1.add(lblNewLabel_8);
				
		final JTextField txtMapScale = new JTextField();
		txtMapScale.setBounds(20, 260, 280, 20);
		panel_1.add(txtMapScale);
				
		JButton btnSaveMap = new JButton("Add Map");
		btnSaveMap.setBounds(20, 300, 280, 25);
		btnSaveMap.setForeground(Color.decode("#F1F1F1"));
		btnSaveMap.setBackground(Color.decode("#AB2A36"));
		btnSaveMap.setOpaque(true);
		btnSaveMap.setBorderPainted(false);
		panel_1.add(btnSaveMap);
		
		// Delete Map fields
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(400, 10, 300, 500);
		panel_5.setLayout(null);
		addMapTab.add(panel_5);
		
		JLabel lblNewLabel_15 = new JLabel("Map:");
		lblNewLabel_15.setBounds(20, 20, 280, 20);
		panel_5.add(lblNewLabel_15);
		
		final JComboBox<Map> comboMapsDelete = new JComboBox<Map>();
		comboMapsDelete.setBounds(20, 50, 280, 20);
		if(comboMapsDelete.getItemCount() > 0){
			comboMapsDelete.removeAllItems();
		}
		for(Map map: DataManager.getAllMaps()){
			comboMapsDelete.addItem(map);
		}
		panel_5.add(comboMapsDelete);
		
		JButton btnDeleteMap = new JButton("Delete Map");
		btnDeleteMap.setBounds(20, 90, 280, 25);
		btnDeleteMap.setForeground(Color.decode("#F1F1F1"));
		btnDeleteMap.setBackground(Color.decode("#AB2A36"));
		btnDeleteMap.setOpaque(true);
		btnDeleteMap.setBorderPainted(false);
		panel_5.add(btnDeleteMap);
		
		// Add Map Action Listeners
		btnSaveMap.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DataManager.saveMap(txtMapName.getText(), Integer.parseInt(txtFloorNum.getText()), txtMapPath.getText(), Float.parseFloat(txtMapScale.getText()));
				
				// Clean add map form
				txtMapName.setText("");
				txtFloorNum.setText("");
				txtMapPath.setText("");
				txtMapScale.setText("");
				
				// Refresh delete maps combo box
				if(comboMapsDelete.getItemCount() > 0){
					comboMapsDelete.removeAllItems();
				}
				for(Map map: DataManager.getAllMaps()){
					comboMapsDelete.addItem(map);
				}
				
			}
		});
		
		// Delete Map Action Listeners
		btnDeleteMap.addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent e) {
			    selectedMap = (Map)comboMapsDelete.getSelectedItem();
				DataManager.removeMap(selectedMap.getId());

				// Refresh delete maps combo box
				if(comboMapsDelete.getItemCount() > 0){
					comboMapsDelete.removeAllItems();
				}
				for(Map map: DataManager.getAllMaps()){
					comboMapsDelete.addItem(map);
				}
			}
		});
		
		///////////////////
		// Add Point Tab //
		///////////////////
		JPanel addPointTab = new JPanel();
		addPointTab.setLayout(null);
		tabbedPane.addTab("Add Edge/Points", null, addPointTab, null);
		
		// Add Map left box
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 320, 500);
		panel.setLayout(null);
		addPointTab.add(panel);
		
		JLabel lblNewLabel = new JLabel("1) Selec map: ");
		lblNewLabel.setBounds(20, 20, 100, 20);
		panel.add(lblNewLabel);
		
		final JComboBox<Map> comboMaps = new JComboBox<Map>();
		comboMaps.setBounds(120, 20, 180, 20);
		panel.add(comboMaps);
		
		// First Point
		JLabel lblNewLabel_1 = new JLabel("2) Click the first point on the map and fill its data.");
		lblNewLabel_1.setBounds(20, 60, 300, 20);
		panel.add(lblNewLabel_1);
		
		JLabel lblX1 = new JLabel("X1: ");
		lblX1.setBounds(20, 90, 30, 20);
		panel.add(lblX1);
		
		final JLabel lblX1Value = new JLabel("0");
		lblX1Value.setBounds(40, 90, 70, 20);
		panel.add(lblX1Value);
		
		JLabel lblY1 = new JLabel("Y1: ");
		lblY1.setBounds(90, 90, 30, 20);
		panel.add(lblY1);
		
		final JLabel lblY1Value = new JLabel("0");
		lblY1Value.setBounds(110, 90, 70, 20);
		panel.add(lblY1Value);
		
		final JLabel lblNewLabel_5 = new JLabel("Location Name: ");
		lblNewLabel_5.setBounds(20, 120, 100, 20);
		panel.add(lblNewLabel_5);
		
		final JTextField txtLocation1Name = new JTextField();
		txtLocation1Name.setBounds(120, 120, 180, 20);
		panel.add(txtLocation1Name);
		txtLocation1Name.setColumns(10);
		
		final JCheckBox chckbxIsEnterance1 = new JCheckBox("is entrance");
		chckbxIsEnterance1.setBounds(20, 150, 120, 20);
		panel.add(chckbxIsEnterance1);
		
		final JCheckBox chckbxIsLocation1 = new JCheckBox("is location");
		chckbxIsLocation1.setBounds(140, 150, 120, 20);
		panel.add(chckbxIsLocation1);
		
		// Second point
		final JLabel lblNewLabel_13 = new JLabel("3) Click the Second point on the map and fill its data.");
		lblNewLabel_13.setBounds(20, 190, 300, 20);
		panel.add(lblNewLabel_13);
		
		final JLabel lblX2 = new JLabel("X2: ");
		lblX2.setBounds(20, 220, 30, 20);
		panel.add(lblX2);
		
		final JLabel lblX2Value = new JLabel("0");
		lblX2Value.setBounds(40, 220, 70, 20);
		panel.add(lblX2Value);
		
		final JLabel lblY2 = new JLabel("Y2: ");
		lblY2.setBounds(90, 220, 30, 20);
		panel.add(lblY2);
		
		final JLabel lblY2Value = new JLabel("0");
		lblY2Value.setBounds(110, 220, 70, 20);
		panel.add(lblY2Value);
		
		final JLabel lblNewLabel_14 = new JLabel("Location Name: ");
		lblNewLabel_14.setBounds(20, 250, 100, 20);
		panel.add(lblNewLabel_14);
		
		final JTextField txtLocation2Name = new JTextField();
		txtLocation2Name.setBounds(120, 250, 180, 20);
		panel.add(txtLocation2Name);
		txtLocation2Name.setColumns(10);
		
		final JCheckBox chckbxIsEnterance2 = new JCheckBox("is entrance");
		chckbxIsEnterance2.setBounds(20, 280, 120, 20);
		panel.add(chckbxIsEnterance2);

		final JCheckBox chckbxIsLocation2 = new JCheckBox("is location");
		chckbxIsLocation2.setBounds(140, 280, 120, 20);
		panel.add(chckbxIsLocation2);
		
		
		JButton btnSavePoint = new JButton("Add Edge and Points");
		btnSavePoint.setBounds(20, 320, 280, 25);
		btnSavePoint.setForeground(Color.decode("#F1F1F1"));
		btnSavePoint.setBackground(Color.decode("#AB2A36"));
		btnSavePoint.setOpaque(true);
		btnSavePoint.setBorderPainted(false);
		panel.add(btnSavePoint);
		
		final JPanel panel_4 = new JPanel();
		panel_4.setBounds(330, 0, 640, 480);
		panel_4.setLayout(null);
		addPointTab.add(panel_4);
		
		// Add Point Action Listeners 
		comboMaps.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// load selected map
			    selectedMap = (Map)comboMaps.getSelectedItem();
				if(selectedMap != null){
					imagePanelMap = new ImagePanel(selectedMap.getFileLocation(), 640, 480);
					imagePanelMap.setLayout(null);
					imagePanelMap.setBounds(0, 0, 640, 480);
//					DataManager.getEdgesByMapID(selectedMap.getId());
					
					for(Edge edge:DataManagerWithMem.getEdgesByMapID(selectedMap.getId()) ){
						Point point1 = DataManager.getPointByID(edge.getsPointId());
						Point point2 = DataManager.getPointByID(edge.getePointId());
						
						imagePanelMap.add(new SolidPoint(Color.GREEN, point1.getX(), point1.getY()));
		            	imagePanelMap.add(new SolidPoint(Color.GREEN, point2.getX(), point2.getY()));
		                imagePanelMap.add(new Line(Color.GREEN, point1.getX(), point1.getY(), point2.getX(), point2.getY()));
		            }
				    imagePanelMap.repaint();
					
					panel_4.removeAll();
					panel_4.add(imagePanelMap);
					panel_4.repaint();
					
					currentPoint = 1;
					imagePanelMap.addMouseListener(new MouseListener() {
			            @Override
			            public void mouseClicked(MouseEvent e) {
			                System.out.println(":MOUSE_CLICK_EVENT:" + e.getX() + "," + e.getY());
			                
			                if(currentPoint == 1){
			                	currentPoint = 2;
			                	
			                	Point point1 = DataManager.findClosestPoint(selectedMap.getId(),e.getX(), e.getY());
			    				if(point1 == null){
			    					lblX1Value.setText(e.getX() + "");
					                lblY1Value.setText(e.getY() + "");
			    				} else {
			    					lblX1Value.setText(point1.getX() + "");
					                lblY1Value.setText(point1.getY() + "");
					                txtLocation1Name.setText(point1.getName());
					                // TODO - fill is enterance/ is location
			    				}
			                	
			    			    pointUI1 = new SolidPoint(Color.RED, Integer.parseInt(lblX1Value.getText()), Integer.parseInt(lblY1Value.getText()));
					            imagePanelMap.add(pointUI1);
					            imagePanelMap.repaint();
			    			
			                } else if (currentPoint == 2){
			                	currentPoint = 0;
			                	
			                	Point point2 = DataManager.findClosestPoint(selectedMap.getId(),e.getX(), e.getY());
			    				if(point2 == null){
			    					lblX2Value.setText(e.getX() + "");
					                lblY2Value.setText(e.getY() + "");
			    				} else {
			    					lblX2Value.setText(point2.getX() + "");
					                lblY2Value.setText(point2.getY() + "");
					                txtLocation2Name.setText(point2.getName());
					                // TODO - fill is entrance/ is location
			    				}
			                	
			    				pointUI2 = new SolidPoint(Color.RED, Integer.parseInt(lblX2Value.getText()), Integer.parseInt(lblY2Value.getText()));
				                imagePanelMap.add(pointUI2);
				                
				                edgeLine = new Line(Color.RED, Integer.parseInt(lblX1Value.getText()), Integer.parseInt(lblY1Value.getText()), Integer.parseInt(lblX2Value.getText()), Integer.parseInt(lblY2Value.getText()));
				                imagePanelMap.add(edgeLine);
				                imagePanelMap.repaint();
			                }
			            }
						@Override
						public void mouseEntered(MouseEvent arg0) {
							// Not used
						}
						@Override
						public void mouseExited(MouseEvent arg0) {
							// Not used
						}
						@Override
						public void mousePressed(MouseEvent arg0) {
							// Not used
						}
						@Override
						public void mouseReleased(MouseEvent arg0) {
							// Not used
						}
			        });
				}
			}
		});
		
		btnSavePoint.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Point point1 = DataManager.findClosestPoint(selectedMap.getId(),Integer.parseInt(lblX1Value.getText()), Integer.parseInt(lblY1Value.getText()));
				Point point2 = DataManager.findClosestPoint(selectedMap.getId(),Integer.parseInt(lblX2Value.getText()), Integer.parseInt(lblY2Value.getText()));
				if(point1 == null){
					point1 = DataManager.getPointByID(DataManager.addPoint(((Map)comboMaps.getSelectedItem()).getId(), Integer.parseInt(lblX1Value.getText()), Integer.parseInt(lblY1Value.getText()), chckbxIsEnterance1.isSelected(), chckbxIsLocation1.isSelected(), txtLocation1Name.getText()));
				}
				if(point2 == null){
					point2 = DataManager.getPointByID(DataManager.addPoint(((Map)comboMaps.getSelectedItem()).getId(), Integer.parseInt(lblX2Value.getText()), Integer.parseInt(lblY2Value.getText()), chckbxIsEnterance2.isSelected(), chckbxIsLocation2.isSelected(), txtLocation2Name.getText()));
				}
				DataManager.addEdge(point1.getId(), point2.getId());
				
				currentPoint = 1;
				
				imagePanelMap.remove(pointUI1);
				pointUI1 = new SolidPoint(Color.GREEN, Integer.parseInt(lblX1Value.getText()), Integer.parseInt(lblY1Value.getText()));
                imagePanelMap.add(pointUI1);
            	
                imagePanelMap.remove(pointUI2);
				pointUI2 = new SolidPoint(Color.GREEN, Integer.parseInt(lblX2Value.getText()), Integer.parseInt(lblY2Value.getText()));
                imagePanelMap.add(pointUI2);
                
                imagePanelMap.remove(edgeLine);
				edgeLine = new Line(Color.GREEN, Integer.parseInt(lblX1Value.getText()), Integer.parseInt(lblY1Value.getText()), Integer.parseInt(lblX2Value.getText()), Integer.parseInt(lblY2Value.getText()));
                imagePanelMap.add(edgeLine);
            
                imagePanelMap.repaint();
                
                lblX1Value.setText("0");
                lblY1Value.setText("0");
                txtLocation1Name.setText("");
                chckbxIsEnterance1.setSelected(false);
                chckbxIsLocation1.setSelected(false);
                
                lblX2Value.setText("0");
                lblY2Value.setText("0");
                txtLocation2Name.setText("");
                chckbxIsEnterance2.setSelected(false);
                chckbxIsLocation2.setSelected(false);
            }
		});
		
		//////////////////
		// Add Edge Tab //
		//////////////////
		JPanel addEdgeTab = new JPanel();
		addEdgeTab.setLayout(null);
		tabbedPane.addTab("Add Connection Edges", null, addEdgeTab, null);
		
		// Add Edge left box
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 10, 300, 500);
		panel_3.setLayout(null);
		addEdgeTab.add(panel_3);
		
		JLabel lblNewLabel_9 = new JLabel("First Map:");
		lblNewLabel_9.setBounds(20, 20, 280, 20);
		panel_3.add(lblNewLabel_9);
		
		final JComboBox<Map> comboMaps1stCon = new JComboBox<Map>();
		comboMaps1stCon.setBounds(20, 50, 280, 20);
		if(comboMaps1stCon.getItemCount() > 0){
			comboMaps1stCon.removeAllItems();
		}
		for(Map map: DataManager.getAllMaps()){
			comboMaps1stCon.addItem(map);
		}
		panel_3.add(comboMaps1stCon);
		
		JLabel lblNewLabel_10 = new JLabel("First Entrance:");
		lblNewLabel_10.setBounds(20, 90, 280, 20);
		panel_3.add(lblNewLabel_10);
				
		final JComboBox<Point> comboFirstPoint = new JComboBox<Point>();
		comboFirstPoint.setBounds(20, 120, 280, 20);
		for(Point p: DataManager.getEntranceByMapID(DataManager.getAllMaps().get(0).getId())){
			comboFirstPoint.addItem(p);
		}
		panel_3.add(comboFirstPoint);
		
		JLabel lblNewLabel_16 = new JLabel("Second Map:");
		lblNewLabel_16.setBounds(20, 160, 280, 20);
		panel_3.add(lblNewLabel_16);
		
		final JComboBox<Map> comboMaps2ndCon = new JComboBox<Map>();
		comboMaps2ndCon.setBounds(20, 190, 280, 20);
		if(comboMaps2ndCon.getItemCount() > 0){
			comboMaps2ndCon.removeAllItems();
		}
		for(Map map: DataManager.getAllMaps()){
			comboMaps2ndCon.addItem(map);
		}
		panel_3.add(comboMaps2ndCon);
		
		final JLabel lblNewLabel_17 = new JLabel("Second Entrance:");
		lblNewLabel_17.setBounds(20, 230, 280, 20);
		panel_3.add(lblNewLabel_17);
		
		final JComboBox<Point> comboSecondPoint = new JComboBox<Point>();
		comboSecondPoint.setBounds(20, 260, 280, 20);
		for(Point p: DataManager.getEntranceByMapID(DataManager.getAllMaps().get(0).getId())){
			comboSecondPoint.addItem(p);
		}
		panel_3.add(comboSecondPoint);
		
		final JButton btnSaveEdge = new JButton("Add Connection Edge");
		btnSaveEdge.setBounds(20, 300, 280, 25);
		btnSaveEdge.setForeground(Color.decode("#F1F1F1"));
		btnSaveEdge.setBackground(Color.decode("#AB2A36"));
		btnSaveEdge.setOpaque(true);
		btnSaveEdge.setBorderPainted(false);
		panel_3.add(btnSaveEdge);
		
		// Add connection edges Action Listeners
		comboMaps1stCon.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// Update point
				Map selectedMap = (Map)comboMaps1stCon.getSelectedItem();
				if(selectedMap != null){
					if(comboFirstPoint.getItemCount() > 0){
						comboFirstPoint.removeAllItems();
					}
					for(Point p: DataManager.getEntranceByMapID(selectedMap.getId())){
						comboFirstPoint.addItem(p);
					}
				}
			}
		});
		
		comboMaps2ndCon.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// Update point
				Map selectedMap = (Map)comboMaps2ndCon.getSelectedItem();
				if(selectedMap != null){
					if(comboSecondPoint.getItemCount() > 0){
						comboSecondPoint.removeAllItems();
					}
					for(Point p: DataManager.getEntranceByMapID(selectedMap.getId())){
						comboSecondPoint.addItem(p);
					}
				}
			}
		});
		
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
					// refresh add point Maps combo box
					if(comboMaps.getItemCount() > 0){
						comboMaps.removeAllItems();
					}
					for(Map map: DataManager.getAllMaps()){
						comboMaps.addItem(map);
					}
				} else if(tabbedPane.getSelectedIndex() == 2){
					// Refresh connection maps combo box
					if(comboMaps1stCon.getItemCount() > 0){
						comboMaps1stCon.removeAllItems();
					}
					for(Map map: DataManager.getAllMaps()){
						comboMaps1stCon.addItem(map);
					}
					if(comboMaps2ndCon.getItemCount() > 0){
						comboMaps2ndCon.removeAllItems();
					}
					for(Map map: DataManager.getAllMaps()){
						comboMaps2ndCon.addItem(map);
					}
				}
			}
		});
	}
}
