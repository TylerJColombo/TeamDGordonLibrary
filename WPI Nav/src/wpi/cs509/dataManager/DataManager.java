package wpi.cs509.dataManager;

import wpi.cs509.dataModel.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DataManager {
	public static boolean addPoint(Point p,String fileName){
		return false;
		
	}
	public static boolean addEdge(Edge e,String fileName){
		return false;
		
	}
	public static Graph getGraph(Graph g,String fileName){
		
		FileReader fr = null;
		try {
			fr = new FileReader(fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("file does not exist...");
		}
		BufferedReader br = new BufferedReader(fr);
		
		String valueString = null;
		try {
			while((valueString=br.readLine())!=null)
			{
				if(valueString.equals("MapName"))
				{
					System.out.println(br.readLine());
					continue;
				}
				
				if(valueString.equals("Points"))
				{
					System.out.println("Points begin...");
					//here begins the point information
					continue;
				}
				if(valueString.equals("Edges"))
				{
					System.out.println("Edges begin...");
					//here begins the point information
					break;
				}
				if(valueString.equals(""))
				{
					System.out.println("there is a enter charater...");
					continue;
				}
				if(valueString.substring(0, 2).equals("ID"))
				{
					System.out.println("here begins the introduction...");
					continue;
				}
				//if(valueString)
				//System.out.println(valueString);
				String[] resultsPoint=valueString.split("\t{1,}");
			if(resultsPoint.length==7)   // the complete information of points.
			{
				System.out.println("ID:"+resultsPoint[0]);
				System.out.println("X:"+resultsPoint[1]);
				System.out.println("Y:"+resultsPoint[2]);
				System.out.println("BuildingName:"+resultsPoint[3]);
				System.out.println("FloorNumber:"+resultsPoint[4]);
				System.out.println("EntranceID:"+resultsPoint[5]);
				System.out.println("PointAttribute:"+resultsPoint[6]);
//				System.out.println("RoomName:"+resultsPoint[7]);
			}
			else System.out.println("data error");
				/*for(int i=0;i<resultsPoint.length;i++)
				{
					System.out.println(resultsPoint[i]+"\n");
				}*/
			}
			
			while((valueString=br.readLine())!=null)
			{
				if(valueString.equals("Edges"))
				{
					System.out.println("Edges begin...");
					continue;
				}
				if(valueString.substring(0, 2).equals("ID"))
				{
					System.out.println("here begins the introduction...");
					continue;
				}
				String[] resultsEdge=valueString.split("\t{1,}");
				if(resultsEdge.length==8)   // the complete information of points.
				{
					System.out.println("ID:"+resultsEdge[0]);
					System.out.println("Point1ID:"+resultsEdge[1]);
					System.out.println("BuildingName:"+resultsEdge[2]);
					System.out.println("FloorNumer:"+resultsEdge[3]);
					System.out.println("Point2ID:"+resultsEdge[4]);
					System.out.println("BuildingName:"+resultsEdge[5]);
					System.out.println("FloorNumer:"+resultsEdge[6]);
					System.out.println("Weight:"+resultsEdge[7]);
				}
				else System.out.println("data error");
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("failed to readline()...");
		}
		return g;
		
		
	}
	public static Graph getGraphByNameWithDB(String fileName)
	{
		Graph graph = new Graph();
		Connection conn =null;
		String sql="";
		String url = "jdbc:mysql://localhost:3306/wpinavi?"+"user=root&password=root&useUnicode=true&characterEncoding=UTF8";
		Point p = new Point();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("successfully load the driver");
			
			
			conn = DriverManager.getConnection(url);
			
			sql = "select * from points";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet resultPoints = ps.executeQuery();
			while(resultPoints.next())
			{
				
//				resultPoints.getString(0);
				p.setId(resultPoints.getInt(1));
				p.setX(resultPoints.getInt(2));
				p.setY(resultPoints.getInt(3));
				p.setBuildingName(resultPoints.getString(4));
				p.setFloorNum(resultPoints.getInt(5));
				int isEntrance = resultPoints.getInt(6);
				if(isEntrance!=0)
				{
					p.setMapEntrance(true);
				}
				else p.setMapEntrance(false);
				String attribute = resultPoints.getString(7);
				p.setName(resultPoints.getString(8));
				System.out.println("the id is .."+resultPoints.getString(1));
			}
			
			
			
		    resultPoints.close(); 
		    ps.close();
		    
		    
		    sql="select * from edges";
		    ps=conn.prepareStatement(sql);
		    ResultSet resultEdges = ps.executeQuery();
		    
		    while(resultEdges.next())
		    {
		    	
		    }
		    
		    
		    
		    conn.close(); 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return graph;
	}
	public static void getListOfMaps(){
		
	}
	public static void addMap(){
		
	}
	public static void main(String[] args){
		Graph graph= new Graph();
//		getGraph(graph,"src/HF1.txt");
		getGraphByNameWithDB("src/HF1/txt");
	}
	}
}
