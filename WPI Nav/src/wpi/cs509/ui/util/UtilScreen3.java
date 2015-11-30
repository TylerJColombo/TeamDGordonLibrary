package wpi.cs509.ui.util;

import java.awt.Color;

import java.util.ArrayList;

import wpi.cs509.dataModel.Point;
import wpi.cs509.ui.components.ImagePanel;
import wpi.cs509.ui.components.RouteLine;
import wpi.cs509.ui.components.SolidPoint;

public class UtilScreen3 {
	
	public  static void drawPath( ImagePanel a, ArrayList <Point> P){
		if(P.size()!=1){
			for(int i = 1; i < P.size(); i++){
				SolidPoint pathofSolid = new SolidPoint(Color.decode("#D55E00"), P.get(i).getX(), P.get(i).getY()); 
				a.add(pathofSolid);
				a.repaint();
			}
			for(int i=0;i<=P.size()-2;i++){
				Point p1 =P.get(i);
				Point p2 =P.get(i+1);
				RouteLine path = new RouteLine(Color.decode("#D55E00"), p1.getX(), p1.getY(), p2.getX(), p2.getY());
				a.add(path, new Integer(1), 0);
				a.repaint();	
			}
		}
	}
	
	public  static void drawPath2( ImagePanel a, ArrayList <Point> P){
		 if(P.size()!=1){
			for(int i = 0; i < P.size()-1; i++){
				SolidPoint pathofSolid = new SolidPoint(Color.decode("#D55E00"), P.get(i).getX(), P.get(i).getY()); 
				a.add(pathofSolid);
				a.repaint();
			}
			
			for(int i=0;i<=P.size()-2;i++){
				Point p1 =P.get(i);
				Point p2 =P.get(i+1);
				RouteLine path = new RouteLine(Color.decode("#D55E00"), p1.getX(), p1.getY(), p2.getX(), p2.getY());
				a.add(path, new Integer(1), 0);
				a.repaint();	
			}
		}
	}
}
