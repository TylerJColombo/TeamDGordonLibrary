package wpi.cs509.dataModel;

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

}
