package wpi.cs509.dataManager;


import java.util.ArrayList;

import com.sun.org.apache.xpath.internal.operations.And;

import wpi.cs509.dataModel.Edge;
import wpi.cs509.dataModel.Point;

public class DataManagerWithMem {
	public static boolean isGraphEmpty()
	{
		if(DirectorGraph.g.getPoints().size()==0)
		{
			return true;
		}
		else return false;
	}
	public static boolean isEdgeIncludeEntrance(int point2id)
	{
		if(!isGraphEmpty())
		{
			
			for(int i=0;i<DirectorGraph.g.getPoints().size();i++)
			{
				Point p = DirectorGraph.g.getPoints().get(i);
				if(p.getId() == point2id)
				{
					if(p.isMapEntrance() == true)
					{
						return true;
					}
				}
			}
			
			
			
			return false;
		}
		return false;
		
	}
	public static ArrayList<Edge> getEdgesByMapID(int mapID)
	{
		ArrayList<Edge> resultEdges = new ArrayList<Edge>();
		for(int i=0;i<DirectorGraph.g.getEdges().size();i++)
		{
			
			for(int j=0;j<DirectorGraph.g.getPoints().size();j++)
			{
				Point p=DirectorGraph.g.getPoints().get(j);
				if(p.getMapId()==mapID && p.isMapEntrance()!=true)
				{	
					if(DirectorGraph.g.getEdges().get(i).getePointId()==p.getId()||DirectorGraph.g.getEdges().get(i).getsPointId()==p.getId()){
						resultEdges.add(DirectorGraph.g.getEdges().get(i));
					}
				}
			}
		}
		return resultEdges;
	}
}
