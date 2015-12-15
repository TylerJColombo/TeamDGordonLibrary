package wpi.cs509.dataManager;


import java.util.ArrayList;


import wpi.cs509.dataModel.Edge;
import wpi.cs509.dataModel.Map;
import wpi.cs509.dataModel.Point;

public class DataManagerWithMem {
	public static boolean isGraphEmpty()
	{
		DirectorGraph.getInstance();
		if(DirectorGraph.getGraph().getPoints().size()==0)
		{
			return true;
		}
		else return false;
	}
	public static boolean isEdgeIncludeEntrance(int point2id)
	{
		if(!isGraphEmpty())
		{
			
			DirectorGraph.getInstance();
			for(int i=0;i<DirectorGraph.getGraph().getPoints().size();i++)
			{
				DirectorGraph.getInstance();
				Point p = DirectorGraph.getGraph().getPoints().get(i);
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
/*	public static ArrayList<Edge> getEdgesByMapID(int mapID)
	{
		ArrayList<Edge> resultEdges = new ArrayList<Edge>();
		for(int i=0;i<DirectorGraph.g.getEdges().size();i++)
		{
			Edge e =DirectorGraph.g.getEdges().get(i);
			for(int j=0;j<DirectorGraph.g.getPoints().size();j++)
			{
				Point p=DirectorGraph.g.getPoints().get(j);

				if((p.getId() ==e.getsPointId()||p.getId() == e.getePointId())&&p.getMapId()==mapID && p.isMapEntrance()!=true)
				{
					resultEdges.add(DirectorGraph.g.getEdges().get(i));

				}
			}
		}
		return resultEdges;
	}*/
	public static ArrayList<Edge> getEdgesByMapID(int mapID)
    {
        ArrayList<Edge> resultEdges = new ArrayList<Edge>();
        DirectorGraph.getInstance();
		for(int i=0;i<DirectorGraph.getGraph().getEdges().size();i++)
        {
            
            DirectorGraph.getInstance();
			for(int j=0;j<DirectorGraph.getGraph().getPoints().size();j++)
            {
                DirectorGraph.getInstance();
				Point p=DirectorGraph.getGraph().getPoints().get(j);
                if(p.getMapId()==mapID && p.isMapEntrance()!=true)
                {    
                    DirectorGraph.getInstance();
					DirectorGraph.getInstance();
					if(DirectorGraph.getGraph().getEdges().get(i).getePointId()==p.getId()||DirectorGraph.getGraph().getEdges().get(i).getsPointId()==p.getId()){
                        DirectorGraph.getInstance();
						resultEdges.add(DirectorGraph.getGraph().getEdges().get(i));
                    }
                }
            }
        }
        return resultEdges;
    }
	public static Point getPointByID(int pointID)
	{
		Point p = null;
		System.out.println("the point id is "+pointID);
		DirectorGraph.getInstance();
		for(int i=0;i<DirectorGraph.getGraph().getPoints().size();i++)
		{
			DirectorGraph.getInstance();
			Point tp=DirectorGraph.getGraph().getPoints().get(i);
			//System.out.println(tp.getId());
			if(tp.getId() == pointID)
			{
				System.out.println("found it");
				p=tp;
				
			}
			
		}
		
		return p;
	}
	public static ArrayList<Map> getAllMaps()
	{
		ArrayList<Map> resultMap = new ArrayList<Map>();
		
		DirectorGraph.getInstance();
		for(int i=0;i<DirectorGraph.getInstance().getMaps().size();i++)
		{
			DirectorGraph.getInstance();
			resultMap.add(DirectorGraph.getInstance().getMaps().get(i));
		}
		return resultMap;
	}
	
}
