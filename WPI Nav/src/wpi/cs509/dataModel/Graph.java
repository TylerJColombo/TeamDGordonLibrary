package wpi.cs509.dataModel;

import java.util.ArrayList;
import java.util.Iterator;

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
	public ArrayList<Edge> getNeighbors(Point s){
		ArrayList<Edge> es=new ArrayList<Edge>();
		for (int i = 0; i < edges.size(); i++) {
	           if(edges.get(i).getsPointId()==s.getId() || edges.get(i).getePointId()==s.getId()){
	        	   es.add(edges.get(i));
	           }
	        }
		return es;
	}
	public Point getPointById(int id){
		for (int i = 0; i < points.size(); i++) {
	           if(points.get(i).getId()==id){
	        	   return points.get(i);
	           }
	        }
		return null;
	}
	public Graph addGraph(ArrayList<Graph> graph2)
	{
		
		
		for(int i =0;i<graph2.size();i++)
		{
			this.addGraph(graph2.get(i));
		}
		
		
		return this;
	}
	public Graph addGraph(Graph graph2)
	{
		
		
		ArrayList<Point> pointsArray = this.getPoints();
		
		pointsArray.addAll(graph2.getPoints());
		
		this.setPoints(pointsArray);
		
		ArrayList<Edge> edgesArray = this.getEdges();
		
		edgesArray.addAll(graph2.getEdges());
		
		this.setEdges(edgesArray);
		
		return this;
		
	}
}
