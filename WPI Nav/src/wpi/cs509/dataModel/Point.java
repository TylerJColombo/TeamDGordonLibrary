package wpi.cs509.dataModel;
/**
 * This is the class to describe the basic information of a point 
 * @author Zhiming
 *@version 1.0
 */
public class Point {
  private int id;
	private int x;
	private int y;
	private int mapId;
	private boolean isDestination;
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

}
