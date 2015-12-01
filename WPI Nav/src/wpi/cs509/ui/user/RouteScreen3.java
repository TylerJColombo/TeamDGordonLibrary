package wpi.cs509.ui.user;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import wpi.cs509.dataManager.DataManager;
import wpi.cs509.dataModel.Graph;
import wpi.cs509.dataModel.Point;
import wpi.cs509.routeFinder.RouteFinder;
import wpi.cs509.ui.components.HeaderPanel;
import wpi.cs509.ui.components.ImagePanel;
import wpi.cs509.ui.components.SolidPoint;
public class RouteScreen3 {

	private JFrame frame;
	private JComboBox<String> buildingSelection,tofloorSelection,fromfloorSelection,sourceSelection,destinationSelection;
	private ArrayList<String> buildingList, floorSelected;
	private ArrayList<Point> fromlocations,tolocations;
	private SolidPoint source1,destination1;
	private ImagePanel toFloorMap,fromFloorMap;
	private int x1,x2,y1,y2;
	private ItemListener sListener,dListener;
	private String buildingselected, fromfloorselected, fromfilename, tofloorselected, tofilename;
	private JButton frombutton, tobutton;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RouteScreen3 window = new RouteScreen3();
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
	public RouteScreen3() {
		initialize();
		this.frame.setVisible(true);
	}
    
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		buildingSelection = new JComboBox<String>();
		tofloorSelection = new JComboBox<String>();
		fromfloorSelection = new JComboBox<String>();
		sourceSelection = new JComboBox<String>();
		destinationSelection = new JComboBox<String>();
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.decode("#F1F1F1"));
		frame.setBounds(0, 0, 1024, 730);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		////////////
		// Header //
		////////////
		HeaderPanel headerPanel = new HeaderPanel("Route between two locations on different floor of a building", false, frame);
		headerPanel.setBounds(0, 0, 1024, 730);
		frame.getContentPane().add(headerPanel);
		
		////////////
		// Map    //
		////////////
		JPanel difFloorSearchPanel = new JPanel();
		difFloorSearchPanel.setLayout(null);
		difFloorSearchPanel.setBounds(0, 180, 1024, 480);
		frame.getContentPane().add(difFloorSearchPanel);

		
		//controlPanel
		JPanel difFloorControl = new JPanel();
		difFloorSearchPanel.add(difFloorControl);
		difFloorControl.setBounds(0, 0, 350, 480);
		difFloorControl.setLayout(null);
		
		//buildingLabel
		JLabel building = new JLabel("Building");
		difFloorControl.add(building);
		building.setBounds(25, 0, 300, 20);
		
		//buildingList
		difFloorControl.add(buildingSelection);
		buildingSelection.setBounds(20, 30, 300, 20);
		buildingList = DataManager.getBuildings();
		buildingSelection.addItem(null);
		for(int i=0;i<buildingList.size();i++){
			buildingSelection.addItem(buildingList.get(i));
		}
		
//		floorList = DataManager.getFloorsMapsByBuildingName(buildingSelection.getItemAt(1).toString());
//		for(int i = 0;i<floorList.size();i++){
//			tofloorSelection.addItem(floorList.get(i));
//		}
//		for(int i = 0;i<floorList.size();i++){
//			fromfloorSelection.addItem(floorList.get(i));
//		}
//		
//		sourceList = DataManager.getLocationsByMapID(buildingSelection.getItemAt(1).toString(), fromfloorSelection.getSelectedItem().toString());
//		for(int i=0;i<sourceList.size();i++){
//			sourceSelection.addItem(sourceList.get(i).getName());
//			
//		}
//		
//		destinationList = DataManager.getLocationsByMapID(buildingSelection.getItemAt(1).toString(), tofloorSelection.getSelectedItem().toString());
//		for(int i=0;i<destinationList.size();i++){
//			
//			destinationSelection.addItem(destinationList.get(i).getName());
//		}
		
		buildingSelection.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				tofloorSelection.removeAllItems();
				fromfloorSelection.removeAllItems();
				sourceSelection.removeAllItems();
				destinationSelection.removeAllItems();
				sourceSelection.removeItemListener(sListener);
				destinationSelection.removeItemListener(dListener);
				// TODO Auto-generated method stub
				if(e.getStateChange()==ItemEvent.SELECTED)
				{
					buildingselected = buildingSelection.getSelectedItem().toString();
					floorSelected = DataManager.getFloorsMapsByBuildingName(buildingselected);
					for(int i = 0;i<floorSelected.size();i++){
						tofloorSelection.addItem(floorSelected.get(i));
					}
					for(int i = 0;i<floorSelected.size();i++){
						fromfloorSelection.addItem(floorSelected.get(i));
					}
				}
					System.out.println("You have chosen building"+" "+buildingSelection.getSelectedItem());
			}
		});
		
		//FromfloorLabel
		JLabel fromfloor = new JLabel("From Floor");
		difFloorControl.add(fromfloor);
		fromfloor.setBounds(25, 60, 300, 20);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(350, 180, 640, 480);
		panel_4.setLayout(null);
		
		//FromfloorList
		difFloorControl.add(fromfloorSelection);
		fromfloorSelection.setBounds(20, 90, 300, 20);
		fromfloorSelection.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				sourceSelection.removeAllItems();
				sourceSelection.removeItemListener(sListener);

				if(e.getStateChange()==ItemEvent.SELECTED)
				{
					buildingselected = buildingSelection.getSelectedItem().toString();
					fromfloorselected = fromfloorSelection.getSelectedItem().toString();
					fromlocations = DataManager.getLocationsByMapID(buildingselected, fromfloorselected);
					fromfilename = DataManager.getMapPathByName(buildingselected, fromfloorselected);
					
					for(int i = 0;i<fromlocations.size();i++){
						sourceSelection.addItem(fromlocations.get(i).getName().toString());
						}
					
					fromFloorMap = new ImagePanel(fromfilename, 640, 480);
					fromFloorMap.setBounds(0,0, 640, 480);
					fromFloorMap.repaint();
					
					panel_4.removeAll();
					panel_4.add(fromFloorMap);
					panel_4.repaint();
					
					frame.add(panel_4);
					System.out.println("You have chosen floor"+" "+fromfloorSelection.getSelectedItem());
					}
					}
				});
		
		//TofloorLabel
		JLabel tofloor = new JLabel("ToFloor");
		difFloorControl.add(tofloor);
		tofloor.setBounds(25, 180, 300, 20);


				
		//TofloorList
		difFloorControl.add(tofloorSelection);
		tofloorSelection.setBounds(20, 210, 300, 20);
		tofloorSelection.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				destinationSelection.removeAllItems();
				destinationSelection.removeItemListener(dListener);
				
				if(e.getStateChange()==ItemEvent.SELECTED)
				{
					buildingselected = buildingSelection.getSelectedItem().toString();
					tofloorselected = tofloorSelection.getSelectedItem().toString();
					tolocations = DataManager.getLocationsByMapID(buildingselected, tofloorselected);
					tofilename = DataManager.getMapPathByName(buildingselected, tofloorselected);
					
					tolocations = DataManager.getLocationsByMapID(buildingselected, tofloorselected);

					for(int i = 0;i<tolocations.size();i++){
						destinationSelection.addItem(tolocations.get(i).getName().toString());
						}
					
					toFloorMap = new ImagePanel(tofilename, 640, 480);
					toFloorMap.setBounds(0,0, 640, 480);
					panel_4.removeAll();
					panel_4.add(toFloorMap);
					panel_4.repaint();
					frame.add(panel_4);
					
					System.out.println("You have chosen floor"+" "+tofloorSelection.getSelectedItem());
					}
					}
				});
		
		//sourceLabel
		JLabel source = new JLabel("Source");
		difFloorControl.add(source);
		source.setBounds(25, 120, 300, 20);
		
		//sourceList
		difFloorControl.add(sourceSelection);
		sourceSelection.setBounds(20, 150, 300, 20);
		
		
		sourceSelection.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange()==ItemEvent.SELECTED){
					sourceSelection.addItemListener(sListener);
				}
			}
		});	
		
		sListener = new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange()==ItemEvent.SELECTED){
					fromlocations = DataManager.getLocationsByMapID(buildingselected, fromfloorselected);
//					frombutton.setBackground(Color.gray);
//					tobutton.setForeground(Color.decode("#F1F1F1"));
//					tobutton.setBackground(Color.decode("#AB2A36"));
//					tobutton.setOpaque(true);
//					tobutton.setBorderPainted(false);
					
					if(source1!=null){
						fromFloorMap.removeAll();
						fromFloorMap = new ImagePanel(fromfilename, 640, 480);
						fromFloorMap.setBounds(0,0, 640, 480);
						int i = sourceSelection.getSelectedIndex();
						x1 = fromlocations.get(i).getX();
						y1 = fromlocations.get(i).getY();
						source1 =new SolidPoint(Color.decode("#000000"), x1, y1);
						fromFloorMap.add(source1);
						fromFloorMap.repaint();
						
						panel_4.removeAll();
						panel_4.add(fromFloorMap);
						panel_4.repaint();
						frame.add(panel_4);
					}
					
					else if(source1 == null){
						int i = sourceSelection.getSelectedIndex();
						x1 = fromlocations.get(i).getX();
						y1 = fromlocations.get(i).getY();
						source1 =new SolidPoint(Color.decode("#000000"), x1, y1);
						fromFloorMap.add(source1);
						fromFloorMap.repaint();
						
						panel_4.removeAll();
						panel_4.add(fromFloorMap);
						panel_4.repaint();
						frame.add(panel_4);
					}
				}
			}
		};

		
		
		//destinationLabel
		JLabel destination = new JLabel("Destination");
		difFloorControl.add(destination);
		destination.setBounds(25, 240, 300, 20);
		
		//destinationList
		difFloorControl.add(destinationSelection);
		destinationSelection.setBounds(20, 270, 300, 20);
		
		destinationSelection.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange()==ItemEvent.SELECTED){
					destinationSelection.addItemListener(dListener);
				}
			}
		});
		
		dListener = new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange()==ItemEvent.SELECTED){
					tolocations = DataManager.getLocationsByMapID(buildingselected, tofloorselected);
//					tobutton.setBackground(Color.gray);
//					frombutton.setForeground(Color.decode("#F1F1F1"));
//					frombutton.setBackground(Color.decode("#AB2A36"));
//					frombutton.setOpaque(true);
//					frombutton.setBorderPainted(false);
					
					if(destination1!=null){
						toFloorMap.removeAll();
						toFloorMap = new ImagePanel(tofilename, 640, 480);
						toFloorMap.setBounds(0,0, 640, 480);
						int i = destinationSelection.getSelectedIndex();
						x2 = tolocations.get(i).getX();
						y2 = tolocations.get(i).getY();
						destination1 =new SolidPoint(Color.decode("#009966"), x2,y2);
						toFloorMap.add(destination1);
						toFloorMap.repaint();
						
						panel_4.removeAll();
						panel_4.add(toFloorMap);
						panel_4.repaint();
						frame.add(panel_4);
					}
					
					else if(destination1 ==null){
						int i = destinationSelection.getSelectedIndex();
						x2 = tolocations.get(i).getX();
						y2 = tolocations.get(i).getY();
						destination1 =new SolidPoint(Color.decode("#009966"), x2,y2);
						toFloorMap.add(destination1);
						toFloorMap.repaint();
						
						panel_4.removeAll();
						panel_4.add(toFloorMap);
						panel_4.repaint();
						frame.add(panel_4);
					}
				}
			}
		};

		
		
		//buttonPanel
		JPanel buttonPanel = new JPanel();
		difFloorControl.add(buttonPanel);
		buttonPanel.setBounds(20, 300, 300, 40);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		//From button
		frombutton = new JButton("From");
		buttonPanel.add(frombutton);
		frombutton.setForeground(Color.decode("#F1F1F1"));
		frombutton.setBackground(Color.decode("#AB2A36"));
		frombutton.setOpaque(true);
		frombutton.setBorderPainted(false);
		
		frombutton.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
//				frombutton.setBackground(Color.decode("#AB2A36"));
//				frombutton.setOpaque(true);
//				frombutton.setBorderPainted(false);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
//				frombutton.setBackground(Color.gray);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				frombutton.setBackground(Color.gray);
				tobutton.setForeground(Color.decode("#F1F1F1"));
				tobutton.setBackground(Color.decode("#AB2A36"));
				tobutton.setOpaque(true);
				tobutton.setBorderPainted(false);
			}
		});
		
		frombutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panel_4.removeAll();
				panel_4.add(fromFloorMap);
				panel_4.repaint();
				frame.add(panel_4);
				fromFloorMap.setLayout(null);
				sourceSelection.setEnabled(true);
				fromfloorSelection.setEnabled(true);
				destinationSelection.setEnabled(false);
				tofloorSelection.setEnabled(false);
			}
		});
		
		//to button
		tobutton = new JButton("To");
		buttonPanel.add(tobutton);
		tobutton.setForeground(Color.decode("#F1F1F1"));
		tobutton.setBackground(Color.decode("#AB2A36"));
		tobutton.setOpaque(true);
		tobutton.setBorderPainted(false);
		
		tobutton.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
//				tobutton.setBackground(Color.decode("#AB2A36"));
//				tobutton.setOpaque(true);
//				tobutton.setBorderPainted(false);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
//				tobutton.setBackground(Color.gray);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				tobutton.setBackground(Color.gray);
				frombutton.setForeground(Color.decode("#F1F1F1"));
				frombutton.setBackground(Color.decode("#AB2A36"));
				frombutton.setOpaque(true);
				frombutton.setBorderPainted(false);
			}
		});
		
        tobutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panel_4.removeAll();
				panel_4.add(toFloorMap);
				panel_4.repaint();
				frame.add(panel_4);
				toFloorMap.setLayout(null);
				sourceSelection.setEnabled(false);
				fromfloorSelection.setEnabled(false);
				destinationSelection.setEnabled(true);
				tofloorSelection.setEnabled(true);
			}
		});
		
		//Find route button
		JButton findRoute = new JButton("Find Route");
		buttonPanel.add(findRoute);
		findRoute.setForeground(Color.decode("#F1F1F1"));
		findRoute.setBackground(Color.decode("#AB2A36"));
		findRoute.setOpaque(true);
		findRoute.setBorderPainted(false);
		findRoute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                buildingselected = buildingSelection.getSelectedItem().toString();
                fromfloorselected = fromfloorSelection.getSelectedItem().toString();
                tofloorselected =tofloorSelection.getSelectedItem().toString();
                if(fromfloorselected == tofloorselected){
                	JOptionPane.showMessageDialog(null, "You are choosing the same floor.");
                	
                }
                else{
                	ArrayList<Point> fromlocation = DataManager.getLocationsByMapID(buildingselected, fromfloorselected);
                    ArrayList<Point> tolocation = DataManager.getLocationsByMapID(buildingselected, tofloorselected);
                    
                    Graph tograph = DataManager.getGraphByNameWithDB(buildingselected, tofloorselected);
               
                    int i = sourceSelection.getSelectedIndex();
                    int j = destinationSelection.getSelectedIndex();
                    
                    Point source0 = fromlocation.get(i);
                    Point destination0 = tolocation.get(j);
                    
                    ArrayList<Point> p = RouteFinder.computePaths(source0, tograph, destination0);
                    
                    int to = 0;
                    ArrayList<Point> frompointlist = new ArrayList<>();
                    ArrayList<Point> topointlist = new ArrayList<>();
                    
                    for(int c = 0; c<p.size()-1;c++){
                    	if(p.get(c).getMapId()==p.get(c+1).getMapId()){
                    		frompointlist. add(p.get(c));
                    	}
                    	else{
                    		frompointlist. add(p.get(c));
                    		to = c+1;
                    		break;
                    	}
                    }
                    
                    for(int t = to;t<p.size() ; t++){
                    	if(p.get(t).getMapId()==destination0.getMapId())
                    	{
                    	Point topoint = p.get(t);
                		topointlist.add(topoint);
                    	}
                    }
                    
                    fromFloorMap.removeAll();
                    toFloorMap.removeAll();
                    
                    int i0 = sourceSelection.getSelectedIndex();
                    x1 = fromlocations.get(i0).getX();
					y1 = fromlocations.get(i0).getY();
                    source1 =new SolidPoint(Color.decode("#000000"), x1, y1);
                    fromFloorMap.add(source1);
                    fromFloorMap.repaint();
                    
                    int j0 = destinationSelection.getSelectedIndex();
                    x2 = tolocations.get(j0).getX();
					y2 = tolocations.get(j0).getY();
                    destination1 =new SolidPoint(Color.decode("#009966"), x2,y2);
                    toFloorMap.add(destination1);
                    toFloorMap.repaint();
                    wpi.cs509.ui.util.UtilScreen3.drawPath(fromFloorMap, frompointlist);
                    wpi.cs509.ui.util.UtilScreen3.drawPath2(toFloorMap, topointlist);
                }      
            }
        });

	}
}
