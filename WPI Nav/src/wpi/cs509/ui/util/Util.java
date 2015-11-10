package wpi.cs509.ui.util;

import java.awt.Color;

import java.util.ArrayList;

import javax.swing.JOptionPane;

//import apple.laf.JRSUIConstants.Size;
import sun.net.www.content.text.plain;
import wpi.cs509.dataModel.Edge;
import wpi.cs509.dataModel.Point;
import wpi.cs509.ui.components.ImagePanel;
import wpi.cs509.ui.components.Line;
import wpi.cs509.ui.components.SolidPoint;

public class Util {
	public  static boolean drawPath( ImagePanel a, ArrayList <Point> P){
	 if(P.size()!=1){
		for(Point path:P){
			SolidPoint pathofSolid = new SolidPoint(Color.green, path.getX(), path.getY()); 
			a.add(pathofSolid);
			a.repaint();
			
		}
		for(int i=0;i<=P.size()-2;i++){
			Point p1 =P.get(i);
			Point p2 =P.get(i+1);
//			Line path = new Line(Color.decode("#929292"), p1.getX(), p1.getY(), p2.getX(), p2.getY());
			Line path = new Line(Color.blue, p1.getX(), p1.getY(), p2.getX(), p2.getY());
			a.add(path, new Integer(1), 0);
			a.repaint();
			
		}
		}else {
			SolidPoint ssolid = new SolidPoint(Color.red, P.get(0).getX(), P.get(0).getY());
			a.add(ssolid);
			a.repaint();
			JOptionPane.showMessageDialog(null, "source point and destination point shouldnt be same");
		}
	
		return true;
	}

}
