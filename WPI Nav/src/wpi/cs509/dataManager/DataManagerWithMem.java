package wpi.cs509.dataManager;

import jdk.internal.org.objectweb.asm.tree.IntInsnNode;
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

}
