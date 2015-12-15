package wpi.cs509.dataManager;

import java.util.ArrayList;

import sun.security.jca.GetInstance;
import wpi.cs509.dataModel.*;
public class DirectorGraph {
	
		private static DirectorGraph dg = null;
		private static Graph g = null;;
		private static ArrayList<Map> mapList = new ArrayList<Map>();
		
		private  DirectorGraph() {
			
			g = DataManager.getGraphByNameWithDB("123", "123");
			
			mapList = DataManager.getAllMaps();
			
		}
		public static DirectorGraph getInstance()
		{
			if(dg == null)
			{
				dg= new DirectorGraph();
			}
			return dg;
		}
		public static Graph getGraph()
		{
			return g;
		}
		public  ArrayList<Map> getMaps()
		{
			return mapList;
		}
		public int clearGraph()
		{
			for(Point p:DirectorGraph.getInstance().getGraph().getPoints())
			{
				p.setMinDistance(Double.POSITIVE_INFINITY);
				p.setPrevious(null);
			}
			return 1;
		}
}
