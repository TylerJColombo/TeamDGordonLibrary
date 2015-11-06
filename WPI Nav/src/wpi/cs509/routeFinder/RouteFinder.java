package wpi.cs509.routeFinder;

import wpi.cs509.dataModel.*;
import wpi.cs509.dataManager.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class RouteFinder {

    public static List<Point> computePaths(Point source, Graph g, Point destination)
    {
        source.minDistance = 0.;
        PriorityQueue<Point> pointQueue = new PriorityQueue<Point>();
        pointQueue.add(source);

	while (!pointQueue.isEmpty()) {
	    Point u = pointQueue.poll();

            // Visit each edge exiting u
            for (Edge e : g.getNeighbors(u))
            {
                Point v = g.getPointById(e.getePointId());
                float weight = e.getWeight();
                double distanceThroughU = u.minDistance + weight;
		if (distanceThroughU < v.minDistance) {
		    pointQueue.remove(v);

		    v.minDistance = distanceThroughU ;
		    v.previous = u;
		    pointQueue.add(v);
				}
            }
        }
	return getShortestPathTo(destination);
	
    }

    public static List<Point> getShortestPathTo(Point destination)
    {
        List<Point> path = new ArrayList<Point>();
        for (Point vertex = destination; vertex != null; vertex = vertex.previous)
            path.add(vertex);

        Collections.reverse(path);
        return path;
    }
    
    public static void main(String[] args){
    	Graph g = new Graph();
    	
    }
}
	



