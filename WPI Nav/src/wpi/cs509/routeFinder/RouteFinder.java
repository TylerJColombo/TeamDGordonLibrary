package wpi.cs509.routeFinder;

import wpi.cs509.dataModel.*;
import wpi.cs509.dataManager.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class RouteFinder {

    public static ArrayList<Point> computePaths(Point source, Graph g, Point destination)
    {
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
	return getShortestPathTo(g.getPointById(destination.getId()));
	
    }

    public static ArrayList<Point> getShortestPathTo(Point destination)
    {
        ArrayList<Point> path = new ArrayList<Point>();
        for (Point vertex = destination; vertex != null; vertex = vertex.previous)
        { 
        	path.add(vertex);
        }

        Collections.reverse(path);
        return path;
    }
    
    public static void main(String[] args){
    	Graph g = new Graph();
    	ArrayList<Point> p = new ArrayList<Point>();
    	//g=DataManager.getGraphByNameWithDB("testLab",2);
    	Point source = new Point();
    	source.setId(11);
    	source.setX(111);
    	source.setY(222);
    	source.setBuildingName("testLab");
    	source.setFloorNum(2);
    	source.setMapEntrance(false);
    	source.setDestination(true);
    	source.setName("source");
    	
    	
    	Point end = new Point();
    	end.setId(15);
    	end.setX(161);
    	end.setY(616);
    	end.setBuildingName("testLab");
    	end.setFloorNum(2);
    	end.setMapEntrance(false);
    	end.setDestination(true);
    	end.setName("end");
    	
    	p=computePaths(source,g,end);
    	System.out.println(p.size());
    	for(int i =0;i<p.size();i++)
    	{
    		System.out.println("result is "+p.get(i).getId());
    	}
    }
}
