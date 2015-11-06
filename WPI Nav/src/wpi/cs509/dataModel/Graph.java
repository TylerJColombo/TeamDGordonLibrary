package wpi.cs509.dataModel;

import java.util.ArrayList;

public class Graph {
  	private ArrayList<Point> points;
	private ArrayList<Edge> edges;
	public ArrayList<Point> getPoints() {
		return points;
	}
	public void setPoints(ArrayList<Point> points) {
		this.points = points;
	}
	public ArrayList<Edge> getEdges() {
		return edges;
	}
	public void setEdges(ArrayList<Edge> edges) {
		this.edges = edges;
	}
	public float getWeight(Point s1, Point s2){
		for (int i = 0; i < edges.size(); i++) {
           if(edges.get(i).getsPointId()==s1.getId()&&edges.get(i).getePointId()==s2.getId()){
        	   return edges.get(i).getWeight();
           }
        }
		return (Float) null;
	}

}
