package wpi.cs509.dataModel;
/**
 * This is the class to describe the basic information of a point 
 * @author Zhiming
 *@version 1.0
 */
public class Point implements Comparable<Point> {
  private int id;
	private int x;
	private int y;
	private int mapId;
	private String buildingName;
	private int floorNum;
	private boolean isDestination;
	public double minDistance = Double.POSITIVE_INFINITY; //modified by Tyler
	public Point previous;// Modified by Tyler
	private boolean isMapEntrance;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getMapId() {
		return mapId;
	}
	public void setMapId(int mapId) {
		this.mapId = mapId;
	}
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	public int getFloorNum() {
		return floorNum;
	}
	public void setFloorNum(int floorNum) {
		this.floorNum = floorNum;
	}
	public boolean isMapEntrance() {
		return isMapEntrance;
	}
	public void setMapEntrance(boolean isMapEntrance) {
		this.isMapEntrance = isMapEntrance;
	}
	public boolean isDestination() {
		return isDestination;
	}
	public void setDestination(boolean isDestination) {
		this.isDestination = isDestination;
	}
	public boolean isMapConnector() {
		return isMapEntrance;
	}
	public void setMapConnector(boolean isMapEntrance) {
		this.isMapEntrance = isMapEntrance;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int compareTo(Point other)
	{
	    return Double.compare(minDistance, other.minDistance);
	}
	
	@Override
	public String toString() {
		return getName();
	}
}
