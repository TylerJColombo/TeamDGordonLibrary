package wpi.cs509.dataManager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;
import com.sun.org.apache.regexp.internal.recompile;
import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;

import wpi.cs509.dataModel.Edge;
import wpi.cs509.dataModel.Graph;
import wpi.cs509.dataModel.Map;
import wpi.cs509.dataModel.Point;
public class DataManager {
	private static String  url = "jdbc:mysql://localhost:3306/wpinavi?"+"user=root&password=root&useUnicode=true&characterEncoding=UTF8";
	public static float sqroot(float m)
	{
	     float i=0;
	   float x1,x2=0.0f;
	   while( (i*i) <= m )
	          i+=0.1;
	   x1=i;
	   for(int j=0;j<10;j++)
	   {
	        x2=m;
	      x2/=x1;
	      x2+=x1;
	      x2/=2;
	      x1=x2;
	   }
	   return x2;
	}
	/*return the point id, if add point failed, it should return 0 and suggest failed*/
	public static int addPoint(int mapid, int x, int y, boolean isEntrance,boolean isLocation,String name ){
		
		Connection conn = null;
		String sql="";
		String sql2="";
		int success=0;
		int pointID  = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url);
			
			sql="insert into points (x,y,mapid,isEntrance,attribute,name)values(?,?,?,?,?,?)";
			PreparedStatement ps1 = conn.prepareStatement(sql);
			
				ps1.setInt(1, x);
				ps1.setInt(2, y);
				ps1.setInt(3, mapid);
				ps1.setBoolean(4, isEntrance);
				if(isLocation)
				{
					ps1.setString(5, "Location");
				}
				else ps1.setString(5, "PassageWay");
				ps1.setString(6, name);
					
			
			success  = ps1.executeUpdate();
			
			ps1.close();
			if(success==1)
			{
				System.out.println("add Point successfully.");
			}
			else {
				System.out.println("fail to add the point.");
			}
			conn.close();
			
			conn = DriverManager.getConnection(url);
			sql2 = "select id from points p where p.x = ? and p.y = ? and p.mapid = ?";
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			ps2.setInt(1, x);
			ps2.setInt(2, y);
			ps2.setInt(3, mapid);
			ResultSet idSet =  ps2.executeQuery();
			while(idSet.next())
			{
				pointID = idSet.getInt(1);
			}
			ps2.close();
			conn.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pointID;
	

	}
	public static boolean addEdge(int point1ID,int point2ID){
		
		
		Connection conn=null;
		String sql="";
		int success=0;
		String sql1="";
		String sql2="";
		Point point1= new Point();
		Point point2 = new Point();
		float EdgeweightS = 0.0f;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url);
			
			sql1 = "select * from points where id =?";
			PreparedStatement ps2 = conn.prepareStatement(sql1);
			ps2.setInt(1, point1ID);
			ResultSet rs = ps2.executeQuery();
			
			while(rs.next())
			{
				point1.setId(rs.getInt(1));
				point1.setX(rs.getInt(2));
				point1.setY(rs.getInt(3));
				point1.setMapId(rs.getInt(4));
				if(rs.getInt(5)==1)
				{point1.setMapEntrance(true);}
				else point1.setMapEntrance(false);
			}
			ps2.setInt(1, point2ID);
			rs = ps2.executeQuery();
			while(rs.next())
			{
				point2.setId(rs.getInt(1));
				point2.setX(rs.getInt(2));
				point2.setY(rs.getInt(3));
				point2.setMapId(rs.getInt(4));
				if(rs.getInt(5)==1)
				{point2.setMapEntrance(true);}
				else point2.setMapEntrance(false);
			}
			
			if(point1.getMapId()!=point2.getMapId())
			{
				if(point1.isMapEntrance()&&point2.isMapEntrance())
				{
					EdgeweightS = 1.0f;
				
				sql="insert into edge (point1id,point2id,weight)values(?,?,?)";
				PreparedStatement ps1 = conn.prepareStatement(sql);
							
				ps1.setInt(1, point1ID);
				ps1.setInt(2, point2ID);
				ps1.setFloat(3,EdgeweightS);
				System.out.println(EdgeweightS);
				success  = ps1.executeUpdate();
				
				ps1.close();
				conn.close();
				}
				else 
				{
					System.out.println("data input error");
				}
			}
			
			
			else{
			EdgeweightS = (point1.getX()-point2.getX())*(point1.getX()-point2.getX())+(point1.getY()-point2.getY())*(point1.getY()-point2.getY());
			
			
			sql="insert into edge (point1id,point2id,weight)values(?,?,?)";
			PreparedStatement ps1 = conn.prepareStatement(sql);
						
			ps1.setInt(1, point1ID);
			ps1.setInt(2, point2ID);
			ps1.setFloat(3,sqroot(EdgeweightS));
			
			System.out.println("the weight is "+sqroot(EdgeweightS));

			
			
			success  = ps1.executeUpdate();
			
			ps1.close();
			conn.close();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		if(success!=0)
		{
			return true;
		}
		else return false;
		

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
				
				Point p = new Point();
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
				p.setId(Integer.parseInt(resultsPoint[0]));
				
				System.out.println("X:"+resultsPoint[1]);
				p.setX(Integer.parseInt(resultsPoint[1]));
				
				System.out.println("Y:"+resultsPoint[2]);
				p.setY(Integer.parseInt(resultsPoint[2]));
				
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
	
	public static Graph getGraphByNameWithDB(String buildingName , String floorName)
	{
		Graph graph = new Graph();
		int floorNum=0;
		ArrayList<Point> pointsArray= new ArrayList<Point>();
		ArrayList<Edge> edgesArray= new ArrayList<Edge>();
		
		//for points and edges buffer.
		Connection conn =null;
		String sqlMap = "";
		String sqlPoint="";
		String sqlEdge="";
		//Point p = new Point();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("successfully load the driver");
			
			
			conn = DriverManager.getConnection(url);
			
//			sqlMap = "select mapid from map where buildingName like ? and floorNum = ?";
			//sqlPoint = "select id,x,y,p.mapid,isEntrance,attribute,p.name from points p, map m where p.mapid = m.mapid and m.buildingName like ? and m.floorNum = ?";
			sqlPoint = "select * from points ";		
//			sqlPoint = "select * from points p, map m where p.mapid == m.mapid";
			
//			sqlPoint = "select * from points where buildingName like ? and floorNum = ?";
			PreparedStatement ps1 = conn.prepareStatement(sqlPoint);
			
			
			//ps1.setString(1, buildingName+"%");
			
		/*	switch(floorName)
			{
			case "SubBasement":
				floorNum = -1;break;
			case "Basement":
				floorNum = 0;break;
			case "First Floor":
				floorNum = 1;break;
			case "Second Floor":
				floorNum = 2;break;
			case "Third Floor":
				floorNum = 3;break;
			case "Fourth Floor":
				floorNum = 4;break;
			case "Fifth Floor":
				floorNum = 5;break;
				default:
					floorNum=88;break;
			}
			*/
			//ps1.setInt(2, floorNum);
			sqlEdge = "select * from edge where point1id = ?";
			PreparedStatement ps2 = conn.prepareStatement(sqlEdge);
			
			
			ResultSet resultPoints = ps1.executeQuery();
			ResultSet resultEdges;
			while(resultPoints.next())
			{
				Point p = new Point();
//				resultPoints.getString(0);
				p.setId(resultPoints.getInt(1));
				p.setX(resultPoints.getInt(2));
				p.setY(resultPoints.getInt(3));
				p.setMapId(resultPoints.getInt(4));
				int isEntrance = resultPoints.getInt(5);
				if(isEntrance!=0)
				{
					p.setMapEntrance(true);
				}
				else p.setMapEntrance(false);
				String attribute = resultPoints.getString(6);
				p.setName(resultPoints.getString(7));
				System.out.println("the id is .."+resultPoints.getString(1));
				pointsArray.add(p);
				
				ps2.setInt(1, resultPoints.getInt(1));
				
				resultEdges = ps2.executeQuery();
				while(resultEdges.next())
				{
					Edge edge = new Edge();
					edge.setId(resultEdges.getInt(1));
					edge.setsPointId(resultEdges.getInt(2));
					edge.setePointId(resultEdges.getInt(3));
					edge.setWeight(resultEdges.getFloat(4));
					System.out.println(edge);
			/*		if(!(isEdgeIncludeEntrance(edge.getePointId())&&isEdgeIncludeEntrance(edge.getsPointId())))
					{
						edgesArray.add(edge);
					}*/
					edgesArray.add(edge);
				}
				 resultEdges.close();
				
			}
			
			
			
		    resultPoints.close(); 
		   
		    ps1.close();
		    ps2.close();
		    
		   
		    
		    
		    conn.close(); 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		graph.setPoints(pointsArray);
		graph.setEdges(edgesArray);
		
		//System.out.println(pointsArray.get(0).getId());
		//System.out.println(pointsArray.get(0).getId());
		//System.out.println("points size is "+graph.getPoints().get(0).getId());  //testing
		//System.out.println("points size is "+graph.getPoints().get(1).getId());  //testing
		//System.out.println("weight is "+graph.getEdges().get(0).getWeight()); //testing
		return graph;
	}
	public static boolean isEdgeIncludeEntrance(int point2id)
	{
		
		Connection conn = null;
		String sql="";
		int result = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("successfully load the driver");
			
			conn = DriverManager.getConnection(url);
			
			sql="select isEntrance from points where id=? ";
			PreparedStatement ps1 = conn.prepareStatement(sql);
			
			ps1.setInt(1, point2id);
			ResultSet rs = ps1.executeQuery();
			while(rs.next())
			{
				result = rs.getInt(1);	
			}
			rs.close();
			ps1.close();
			conn.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result==1? true : false;
	}
	public static Graph getGraphOfCampus()
	{
		Graph graph = new Graph();
		graph = getGraphByNameWithDB("Campus","Basement");
		return graph;
	}
	
	public static ArrayList<String> getFloorsMapsByBuildingName(String buildingName)
	{
		ArrayList<String> floorName = new ArrayList<String>();
		
		Connection conn =null;
		String sql="";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("successfully load the driver");
			
			conn = DriverManager.getConnection(url);
			
			sql="select distinct floorNum from map where buildingName like ? ";
			PreparedStatement ps1 = conn.prepareStatement(sql);
			
			ps1.setString(1, buildingName+"%");
			ResultSet rs = ps1.executeQuery();
			while(rs.next())
			{
				int floorNum =0;
				String floor="";
				floorNum = rs.getInt(1);
				switch(floorNum)
				{
				case -1:
					floor="SubBasement";break;
				case 0:
					floor = "Basement";break;
				case 1:
					floor = "First Floor";break;
				case 2:
					floor = "Second Floor";break;
				case 3:
					floor = "Third Floor";break;
				case 4:
					floor="Fourth Floor";break;
				case 5:
					floor="Fifth Floor";break;
				default:
						floor="";
				}
				
				floorName.add(floor);
				
			}
			rs.close();
			ps1.close();
			conn.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return floorName;
	}
	
	public static ArrayList<String> getBuildings(){
		
		ArrayList<String> buildingsName = new ArrayList<String>();
		
		Connection conn =null;
		String sql="";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url);
			
			sql="select distinct buildingName from map where buildingName<> ?";
			PreparedStatement ps1 = conn.prepareStatement(sql);
			
			ps1.setString(1, "Campus");
			ResultSet rs = ps1.executeQuery();
			while(rs.next())
			{
				String building="";
				building = rs.getString(1);
				
				buildingsName.add(building);

				
			}
			rs.close();
			ps1.close();
			conn.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return buildingsName;
		
	}

	public static ArrayList<Point> getBuildingOnCampus()
	{
		ArrayList<Point> buildingsName = new ArrayList<Point>();
		
		Connection conn =null;
		String sql="";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url);
			
			sql="select * from points p, map m where p.mapid = m.mapid and m.buildingName = ? and p.attribute<> ?";
			PreparedStatement ps1 = conn.prepareStatement(sql);
			
			ps1.setString(1, "Campus");
			ps1.setString(2, "PassageWay");
			ResultSet rs = ps1.executeQuery();
			while(rs.next())
			{
				Point p = new Point();

				p.setId(rs.getInt(1));
				p.setX(rs.getInt(2));
				p.setY(rs.getInt(3));
				p.setMapId(rs.getInt(4));
				int isEntrance = rs.getInt(5);
				if(isEntrance!=0)
				{
					p.setMapEntrance(true);
				}
				else p.setMapEntrance(false);
				String attribute = rs.getString(6);
				
				p.setDestination(attribute=="PassageWay"? false:true);
				
				
				p.setName(rs.getString(7));
				System.out.println("the id is .."+rs.getString(1));
				buildingsName.add(p);

				
			}
			rs.close();
			ps1.close();
			conn.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return buildingsName;
	}
	public static ArrayList<Point> getLocationsByMapID(String buildingName,String floorName)
	{
		ArrayList<Point> locations = new ArrayList<Point>();
		int floorNum=0;
		Connection conn =null;
		String sql="";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url);
			
			sql="select * from points p,map m where m.buildingName like ? and m.floorNum=? and p.attribute<>'PassageWay' and m.mapid = p.mapid order by p.name ";
			PreparedStatement ps1 = conn.prepareStatement(sql);
			
			ps1.setString(1,buildingName+"%");
			switch(floorName)
			{
			case "SubBasement":
				floorNum = -1;break;
			case "Basement":
				floorNum = 0;break;
			case "First Floor":
				floorNum = 1;break;
			case "Second Floor":
				floorNum = 2;break;
			case "Third Floor":
				floorNum = 3;break;
			case "Fourth Floor":
				floorNum = 4;break;
			case "Fifth Floor":
				floorNum = 5;break;
				default:
					floorNum=88;break;
			}
			ps1.setInt(2, floorNum);
			ResultSet rs = ps1.executeQuery();
			while(rs.next())
			{
				Point p = new Point();
//				resultPoints.getString(0);
				p.setId(rs.getInt(1));
				p.setX(rs.getInt(2));
				p.setY(rs.getInt(3));
				p.setMapId(rs.getInt(4));
				int isEntrance = rs.getInt(5);
				if(isEntrance!=0)
				{
					p.setMapEntrance(true);
				}
				else p.setMapEntrance(false);
				String attribute = rs.getString(6);
				if(attribute!="PassageWay")
				{
					p.setDestination(true);
				}
				
				p.setName(rs.getString(7));
				System.out.println("the id is .."+rs.getString(1));
				locations.add(p);
				
				
			}
			rs.close();
			ps1.close();
			conn.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return locations;
	}

	public static boolean saveMap(String buildingName,int floorNum, String path,float scale)
	{
		Connection conn =null;
		int success=0;
		String sql="";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url);
			
			sql="insert into map (scale,buildingName,floorNum,path)values(?,?,?,?)";
			PreparedStatement ps1 = conn.prepareStatement(sql);
			
		
			ps1.setFloat(1, scale);
			ps1.setString(2, buildingName);
			ps1.setInt(3, floorNum);
			ps1.setString(4, path);
			
			
			success  = ps1.executeUpdate();
			
			ps1.close();
			conn.close();
			
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(success!=0)
		return true;
		else return false;
	}
	
	public static ArrayList<Map> getAllMaps()
	{
		ArrayList<Map> mapList = new ArrayList<Map>();
		
		Connection conn = null;
		String sql="";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url);
			
			sql="select * from map";
			PreparedStatement ps1 = conn.prepareStatement(sql);
				
			ResultSet resultMap = ps1.executeQuery();
			while(resultMap.next())
			{
				Map map = new Map();
				map.setId(resultMap.getInt(1));
				map.setScale(resultMap.getFloat(2));
				//map.setName(resultMap.getString(3));
				map.setFileLocation(resultMap.getString(4));
				map.setBuildingName(resultMap.getString(5));
				map.setFloorNum(resultMap.getInt(6));
				mapList.add(map);
			}
			resultMap.close();
			ps1.close();
			conn.close();
			
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(mapList.size());
		
		return mapList;
	}
	
	public static ArrayList<Point>getAllPoints()
	{
		ArrayList<Point> pointsArray = new ArrayList<Point>();
		
		Connection conn = null;
		String sql="";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url);
			
			sql="select * from points order by name";
			PreparedStatement ps1 = conn.prepareStatement(sql);
				
			ResultSet resultPoints = ps1.executeQuery();
			while(resultPoints.next())
			{
				Point p = new Point();
//				resultPoints.getString(0);
				p.setId(resultPoints.getInt(1));
				p.setX(resultPoints.getInt(2));
				p.setY(resultPoints.getInt(3));
				p.setMapId(resultPoints.getInt(4));
				
				int isEntrance = resultPoints.getInt(5);
				if(isEntrance!=0)
				{
					p.setMapEntrance(true);
				}
				else p.setMapEntrance(false);
				String attribute = resultPoints.getString(6);
				p.setName(resultPoints.getString(7));
				System.out.println("the id is .."+resultPoints.getString(1));
				pointsArray.add(p);
			}
			resultPoints.close();
			ps1.close();
			conn.close();
			
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(pointsArray.size());
		
		return pointsArray;
	}
	public static ArrayList<Edge>getAllEdges()
	{
		ArrayList<Edge> edgesArray = new ArrayList<Edge>();
		
		Connection conn = null;
		String sql="";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url);
			
			sql="select * from edge";
			PreparedStatement ps1 = conn.prepareStatement(sql);
				
			ResultSet resultEdges = ps1.executeQuery();
			while(resultEdges.next())
			{
				Edge edge = new Edge();
				edge.setId(resultEdges.getInt(1));
				edge.setsPointId(resultEdges.getInt(2));
				edge.setePointId(resultEdges.getInt(3));
				edge.setWeight(resultEdges.getFloat(4));
				System.out.println(edge);
				edgesArray.add(edge);
			}
			 resultEdges.close();
			ps1.close();
			conn.close();
			
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(edgesArray.size());
		
		return edgesArray;
		
	}
	/*--this function is for finding two points that forms an edge--*/
	public static ArrayList<Point> getPointsByEdge(int edgeID)
	{
		ArrayList<Point> pointsArray = new ArrayList<Point>();
		
		Connection conn = null;
		String sql="";
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url);
			
				sql="select * from points p,edges e where e.id=? and (e.point1id=p.id or e.point2id = p.id)";
				
				PreparedStatement ps1 = conn.prepareStatement(sql);
			
				ps1.setInt(1, edgeID);
				
				ResultSet rs = ps1.executeQuery();
				
				while(rs.next())
				{
					Point p = new Point();				
					p.setId(rs.getInt(1));
					p.setX(rs.getInt(2));
					p.setY(rs.getInt(3));
					p.setMapId(rs.getInt(4));

					int isEntrance = rs.getInt(5);
					if(isEntrance!=0)
					{
						p.setMapEntrance(true);
					}
					else p.setMapEntrance(false);
					String attribute = rs.getString(6);
					
					if(attribute == "PassageWay")
					{
						p.setDestination(false);
					}
					p.setName(rs.getString(7));
					System.out.println("the id is .."+rs.getString(1));
					pointsArray.add(p);
				}
				rs.close();
				ps1.close();
				


			conn.close();
			
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pointsArray;
	}
	
	public static String getMapPathByName(String BuildingName,String FloorName)
	{
		String path="";
		int floorNum =0;
		Connection conn = null;
		String sql="";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url);
		
			sql="select * from map where buildingName like ? and floorNum = ?";
				
			PreparedStatement ps1 = conn.prepareStatement(sql);
			
			ps1.setString(1, BuildingName+"%");
			
			switch(FloorName)
			{
			case "SubBasement":
				floorNum = -1;break;
			case "Basement":
				floorNum = 0;break;
			case "First Floor":
				floorNum = 1;break;
			case "Second Floor":
				floorNum = 2;break;
			case "Third Floor":
				floorNum = 3;break;
			case "Fourth Floor":
				floorNum = 4;break;
			case "Fifth Floor":
				floorNum = 5;break;
				default:
					floorNum=88;break;
			}
		
			ps1.setInt(2, floorNum);
			ResultSet rs = ps1.executeQuery();
				
				while(rs.next())
				{
					path = rs.getString(4);
				}
				rs.close();
				ps1.close();
				conn.close();
			
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
	
	public static Point getPointByBuildingName(String BuildingName)
	{
		Point building = new Point();
		Connection conn = null;
		
		String sql="";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url);
		
			sql="select * from points p,map m where m.buildingName= ? and p.attribute<>'PassageWay' and p.name like ? order by p.name";
				
			PreparedStatement ps1 = conn.prepareStatement(sql);
			
			ps1.setString(1, "Campus");
			ps1.setString(2, BuildingName);
				
			ResultSet rs = ps1.executeQuery();
				
				while(rs.next())
				{
					Point p = new Point();
					p.setId(rs.getInt(1));
					p.setX(rs.getInt(2));
					p.setY(rs.getInt(3));
					p.setMapId(rs.getInt(4));
					int isEntrance = rs.getInt(5);
					if(isEntrance!=0)
					{
						p.setMapEntrance(true);
					}
					else p.setMapEntrance(false);
					String attribute = rs.getString(6);
					if(attribute!="PassageWay")
					{
						p.setDestination(true);
					}
					
					p.setName(rs.getString(7));
					System.out.println("the id is .."+rs.getString(1));
					building = p;
				}
				rs.close();
				ps1.close();
				conn.close();
			
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return building;
	}
	public static ArrayList<Point> getEntranceByMapID(int mapid)
	{
		ArrayList<Point> entrancesList = new ArrayList<Point>();
		Connection conn = null;
		
		String sql="";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url);
		
			sql="select * from points where mapid = ? and isEntrance = 1 order by name";
				
			PreparedStatement ps1 = conn.prepareStatement(sql);
			ps1.setInt(1, mapid);
	
				
			ResultSet rs = ps1.executeQuery();
			while(rs.next())
			{
				Point p = new Point();
				p.setId(rs.getInt(1));
				p.setX(rs.getInt(2));
				p.setY(rs.getInt(3));
				p.setMapId(rs.getInt(4));
				p.setMapEntrance(rs.getInt(5)==1?true:false);
				String attribute = rs.getString(6);
				p.setName(rs.getString(7));
				
				entrancesList.add(p);
			}
				
				rs.close();
				ps1.close();
				conn.close();
			
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entrancesList;
		
	}
	public static Point findClosestPoint(int mapid,int x ,int y)
	{
		ArrayList<Point> resPoint = new ArrayList<Point>();
		Connection conn = null;
		
		String sql="";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url);
		
			sql="select * from points where x>?-10 and x<?+10 and y>?-10 and y<?+10 and mapid=? ";
				
			PreparedStatement ps1 = conn.prepareStatement(sql);
			ps1.setInt(1, x);
			ps1.setInt(2, x);
			ps1.setInt(3, y);
			ps1.setInt(4, y);
			ps1.setInt(5, mapid);
	
				
			ResultSet rs = ps1.executeQuery();
			while(rs.next())
			{
				Point p = new Point();
				p.setId(rs.getInt(1));
				p.setX(rs.getInt(2));
				p.setY(rs.getInt(3));
				p.setMapId(rs.getInt(4));
				p.setMapEntrance(rs.getInt(5)==1?true:false);
				String attribute = rs.getString(6);
				p.setName(rs.getString(7));
				
				resPoint.add(p);
			}
				
				rs.close();
				ps1.close();
				conn.close();
			
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(resPoint.size()==0)
		{
			return null;
		}
		else return resPoint.get(0);
		
	}
	public static boolean removeMap(int mapid)
	{
		Connection conn=null;
		String sql="";
		int res=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url);
		
			sql="delete from map where mapid= ?";
				
			PreparedStatement ps1 = conn.prepareStatement(sql);
			ps1.setInt(1, mapid);
			//ResultSet rs = ps1.executeQuery();
			res= ps1.executeUpdate();
			
				
				
				ps1.close();
				conn.close();
			
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res==1?true : false;
	}
	public static Point getPointByID(int pointID)
	{
		Point p = new Point();
		Connection conn = null;
		String sql="";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url);
		
			sql="select * from points where id = ?";
				
			PreparedStatement ps1 = conn.prepareStatement(sql);
			ps1.setInt(1, pointID);
			ResultSet rs = ps1.executeQuery();
			while(rs.next())
			{
				p.setId(rs.getInt(1));
				p.setX(rs.getInt(2));
				p.setY(rs.getInt(3));
				p.setMapId(rs.getInt(4));
				p.setMapEntrance(rs.getInt(5)==1?true:false);
				String attribute = rs.getString(6);
				p.setName(rs.getString(7));
			}
				rs.close();
				ps1.close();
				conn.close();
			
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return p;
	}
	public static ArrayList<Edge> getEdgesByMapID(int mapID)
	{
		ArrayList<Edge> edgeList = new ArrayList<Edge>();
		Connection conn = null;
		String sql="";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url);
			
			sql="select * from edge e, points p1,points p2 where e.point1id = p1.id and p1.mapid = ? and e.point2id= p2.id and p2.mapid=?";
			PreparedStatement ps1 = conn.prepareStatement(sql);
			ps1.setInt(1, mapID);
			ps1.setInt(2, mapID);
			ResultSet resultEdges = ps1.executeQuery();
			while(resultEdges.next())
			{
				Edge edge = new Edge();
				edge.setId(resultEdges.getInt(1));
				edge.setsPointId(resultEdges.getInt(2));
				edge.setePointId(resultEdges.getInt(3));
				edge.setWeight(resultEdges.getFloat(4));
				System.out.println(edge);
				edgeList.add(edge);
			}
			 resultEdges.close();
			ps1.close();
			conn.close();
			
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return edgeList;
	}
	public static void main(String[] args){
		Graph graph1= new Graph();
		Graph graph2= new Graph();

//		getGraph(graph,"src/HF1.txt");
		
		//graph1 =getGraphByNameWithDB("FullerLab","First Floor");
		graph1 = getGraphByNameWithDB("GordonLibrary","Basement");
		//System.out.println(graph1.getEdges().size());
		//graph2 =getGraphByNameWithDB("FullerLab","Second Floor");
		//System.out.println(graph1.getPoints().size());
//		System.out.println(graph2.getPoints().size());
		//graph1.addGraph(graph2); 
		float testScale = 15.5f;
		float testWeight = 3.2f;
		float testSq = 400f;
		ArrayList<Integer> testEdges = new ArrayList<Integer>();
		testEdges.add(1);
		testEdges.add(11);
		testEdges.add(13);
		
		//getFloorsMapsByBuildingName("Higgins");
		//System.out.println(getFloorsMapsByBuildingName("GordonLibrary").get(0));
		
		//System.out.println(getBuildings().get(0));
		//System.out.println(getBuildings().get(1));
		//System.out.println(getLocationsByMapID("GordonLibrary","SubBasement").size());
		//System.out.println(saveMap("HigginsLab","c://testForIMG",testScale));
		//System.out.println(addPoint("FullerLab","Third Floor", 600, 480, false,true,"Room333"));
		//System.out.println(addEdge(1,2,testWeight));
		//System.out.println(getAllMaps().get(1).getBuildingName());
		/*System.out.println(getAllPoints());
		System.out.println(getAllEdges());*/
		//System.out.println(getPointsByEdges(testEdges).get(0).getX());
		//System.out.println(getPointsByEdges(testEdges).get(1).getX());
		//System.out.println(getMapPathByName("FullerLab"));
		//System.out.println(getPointByBuildingName("FullerLab").getId());
		//System.out.println(graph.getPoints().size());
/*		System.out.println(graph1.getPoints().get(0).getId());
		System.out.println(graph1.getPoints().get(1).getId());
		System.out.println(graph1.getPoints().get(2).getId());
*/
		//System.out.println(graph2.getPoints().size());
		//System.out.println(sqroot(testSq));
		System.out.println(findClosestPoint(1,236, 74).getId());
	}
}
