package wpi.cs509.ui.user;

import java.applet.Applet;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import wpi.cs509.ui.components.HelpHeader;

public class HelpScreen extends Applet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private DefaultMutableTreeNode root;
	private DefaultTreeModel treeModel;
	private JTree tree;
	private JPanel pTree,pDoc;
	private JTextArea text1;
	private String s1;
	private String s2;
	private String s3;
	private String s4;
	private String s5;
	private String s6;
	private String s7;

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
	public HelpScreen() {
		initialize();
		this.frame.setVisible(true);
	}

	private void initialize() {
		// TODO Auto-generated method stub
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.decode("#F1F1F1"));
		frame.setBounds(0, 0, 1024, 730);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		////////////
		// Header //
		////////////
		HelpHeader headerPanel = new HelpHeader("WPI Navigation System", true, frame);
		headerPanel.setBounds(0, 0, 1024, 730);
		frame.getContentPane().add(headerPanel);
		
		//controlPanel
		pTree = new JPanel();
		frame.getContentPane().add(pTree);
		pTree.setBounds(20, 180, 350, 480);
		pTree.setLayout(null);
		pTree.setBackground(Color.decode("#F1F1F1"));
		
		//showPanel
		pDoc = new JPanel();
		frame.getContentPane().add(pDoc);
		pDoc.setBounds(400, 180, 550, 480);
		pDoc.setLayout(new GridLayout());
		pDoc.setBackground(Color.decode("#F1F1F1"));
		
		text1 = new JTextArea(100,100);
		text1.setBounds(10, 10, 200, 200);
		text1.setLineWrap(true);
		text1.setWrapStyleWord(true);
		text1.setFont(new Font(null, Font.ITALIC, 16));
		text1.setText("    This system is aimed for the use of new, current, alumni, and faculty of Worcester Polytechnic Institute who need to find their way around campus where they are unfamiliar.");
		pDoc.add(text1);
		
		s1 = "• Building Input"
				+ "\n1.  To begin choosing a location the user must select a building from a drop down menu."
				+ "\n2.  This input is a string provided by the system in a drop down list."
				+ "\n3.  The user can have one map input, but in some cases the user can have up to two."
				+ "\n4.  The purpose of this input is to start the location selection process."
				+ "\n5.  The user must select a building first before selection a location point.";
		s2 = "• Floor Input"
				+ "\n1.  After selecting building, the user can select a floor in that building from a drop down menu."
				+ "\n2.  This input is a string provided by the system in a drop down list."
				+ "\n3.  The user can choose up to two floors depending on if they are going from one floor to another or if they are going to one building floor to another building floor."
				+ "\n4.  The purpose of this input is to let the system know what floor they are on or want to end on.";
		s3 = "• Source Location Input"
				+ "\n1.  After selecting floor or building, the user can select a location in that building or on campus from the drop down menu."
				+ "\n2.  This input is a string provided by the system in a drop down list."
				+ "\n3.  The user can only have one source location."
				+ "\n4.  The purpose of this input is to let the system know where the user wants to start their route.";
		s4 = "• Destination Input"
				+ "\n1.  After selecting a source location, the user can select another location on the floor, campus, in the building, or in another building."
				+ "\n2.  This input is a string provided by the system in a drop down list."
				+ "\n3.  The user can only have one destination location."
				+ "\n4.  The purpose of this input is to let the system know where the user wants to go and the final step before finding the shortest route.";
		s5 = "• Add Map"
				+ "\n1.  Input the building name of the map."
				+ "\n2.  Input the floor number of the map."
				+ "\n3.  Choose the map file by clicking the 'Choose' button"
				+ "\n4.  Input map scale."
				+ "\n5.  Click the 'Add map' button to add a map."
				+ "\n• Delete Map"
				+ "\n1.  Choose the map to delete by selecting from the list"
				+ "\n2.  Click the 'Delete map' button to delete a map.";
		s6 = "• Add Points and Edges"
				+ "\n1.  Select a map from the map list."
				+ "\n2.  Click on the map to select the points."
				+ "\n3.  Input location name for the points."
				+ "\n4.  Check point attributes from the checking box."
				+ "\n5.  Click the 'Add Point and its edge' button to add points and edges.";
		s7 = "• Add Connection Edges"
				+ "\n1.  Select the first map from the list."
				+ "\n2.  Select a point from the first map."
				+ "\n3.  Select the second map from the list."
				+ "\n4.  Select a point from the second map."
				+ "\n5.  Click the 'Add Connection Edge' button to add an edge.";
		
		root = new DefaultMutableTreeNode("Help Manual");
		treeModel = new DefaultTreeModel(root);
		tree = new JTree(treeModel);
		tree.setBounds(0, 0, 350, 480);
		pTree.add(tree);
		pTree.setVisible(true);
		
		DefaultMutableTreeNode anode = new DefaultMutableTreeNode("Admin");
		DefaultMutableTreeNode unode = new DefaultMutableTreeNode("User");
		treeModel.insertNodeInto(anode, root, root.getChildCount());
		treeModel.insertNodeInto(unode, root, root.getChildCount());
		
		DefaultMutableTreeNode anode1 = new DefaultMutableTreeNode("Add/Delete Map");
		DefaultMutableTreeNode anode2 = new DefaultMutableTreeNode("Add Edge/Points");
		DefaultMutableTreeNode anode3 = new DefaultMutableTreeNode("Add Connection Edges");
		treeModel.insertNodeInto(anode1, anode, anode.getChildCount());
		treeModel.insertNodeInto(anode2, anode, anode.getChildCount());
		treeModel.insertNodeInto(anode3, anode, anode.getChildCount());
		
		DefaultMutableTreeNode unode1 = new DefaultMutableTreeNode("Route on campus");
		treeModel.insertNodeInto(unode1, unode, unode.getChildCount());
		
		DefaultMutableTreeNode unode11 = new DefaultMutableTreeNode("Building Input");
		DefaultMutableTreeNode unode21 = new DefaultMutableTreeNode("Floor Input");
		DefaultMutableTreeNode unode31 = new DefaultMutableTreeNode("Source Input");
		DefaultMutableTreeNode unode41 = new DefaultMutableTreeNode("Destination Input");
		treeModel.insertNodeInto(unode11, unode1, unode1.getChildCount());
		treeModel.insertNodeInto(unode21, unode1, unode1.getChildCount());
		treeModel.insertNodeInto(unode31, unode1, unode1.getChildCount());
		treeModel.insertNodeInto(unode41, unode1, unode1.getChildCount());
		
		DefaultTreeCellRenderer cellRenderer = (DefaultTreeCellRenderer) tree.getCellRenderer();
		cellRenderer.setTextNonSelectionColor(Color.black);
		cellRenderer.setTextSelectionColor(Color.blue);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				// TODO Auto-generated method stub
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if(node == null){
					return;
				}
				
				else if(node.isLeaf()){
					if(node.getUserObject().toString()=="Add/Delete Map"){
						text1.setText(node.getUserObject().toString()+"\n"+s5);
					}else if(node.getUserObject().toString()=="Add Edge/Points"){
						text1.setText(node.getUserObject().toString()+"\n"+s6);
					}else if(node.getUserObject().toString()=="Add Connection Edges"){
						text1.setText(node.getUserObject().toString()+"\n"+s7);
					}else if(node.getUserObject().toString()=="Building Input"){
						text1.setText(node.getUserObject().toString()+"\n"+s1);
					}else if(node.getUserObject().toString()=="Floor Input"){
						text1.setText(node.getUserObject().toString()+"\n"+s2);
					}else if(node.getUserObject().toString()=="Source Input"){
						text1.setText(node.getUserObject().toString()+"\n"+s3);
					}else if(node.getUserObject().toString()=="Destination Input"){
						text1.setText(node.getUserObject().toString()+"\n"+s4);
					}
				}
			}
		});
	}
	
}
