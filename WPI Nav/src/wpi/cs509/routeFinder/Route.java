package wpi.cs509.routeFinder;

import wpi.cs509.dataModel.*;
import wpi.cs509.dataManager.*;
import wpi.cs509.routeFinder.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public interface Route {
	   public ArrayList<Point> getPath();
	}

	public DikstraRoute implements Route {
	   public DikstraRoute(Point start, Graph g, Point destination) {
	   		if(source.getId()==destination.getId()){
				ArrayList<Point> point=new ArrayList<Point>();
				point.add(source);
				return point;
			}

    		source.minDistance = 0;
    		PriorityQueue<Point> pointQueue = new PriorityQueue<Point>();
    		pointQueue.add(source);

		while (!pointQueue.isEmpty()) {
    		Point u = pointQueue.poll();

        		// Visit each edge exiting u
        		for (Edge e : g.getNeighbors(u))
        		{
        			try
        			{

            		Point v = g.getPointById(e.getePointId()!=u.getId()?e.getePointId():e.getsPointId());

            		float weight = e.getWeight();
            		double distanceThroughU = u.minDistance + weight;
			if (distanceThroughU < v.minDistance) {
	    		pointQueue.remove(v);
	    		v.minDistance = distanceThroughU ;
	    		v.previous = u;
	    		pointQueue.add(v);
	  //  System.out.println("u.id is"+u.getId());
					}
        			}catch(NullPointerException e1)
        			{
        		System.out.println(e.getId()+"**********"+u.getName());
        			}
        		}
    		}
	   }

	   @Override
	   public ArrayList<Point> getPath() {
	        ArrayList<Point> path = new ArrayList<Point>();
    		for (Point vertex = destination; vertex != null; vertex = vertex.previous){
    			path.add(vertex);
    		}

    		Collections.reverse(path);
    		return path;
	   }
	}

	public PrimsRoute implements Route {
		   public PrimsRoute(Point start, Graph g, Point destination) {
	    	if(source.getId()==destination.getId()){
				ArrayList<Point> point=new ArrayList<Point>();
				point.add(source);
				return point;
	    	}

	    	source.minDistance = 0;
	    	pointQueue.insert(source, source.minDistance);
	    	while (!pointQueue.isEmpty()) {
	    		int v = pointQueue.remove();
	    		scan(Graph G, v);
	        }
	   }

	    @Override
	    public ArrayList<Point> getPath() {
	        ArrayList<Point> path = new ArrayList<Point>();
    		for (Point vertex = destination; vertex != null; vertex = vertex.previous){
    			path.add(vertex);
    		}

    		Collections.reverse(path);
    		return path;
	   }
	}
