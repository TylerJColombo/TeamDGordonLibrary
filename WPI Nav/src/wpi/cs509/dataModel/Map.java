package wpi.cs509.dataModel;
/**
 * This is the class to describe a map
 * @author Zhiming
 *@version 1.0
 */
public class Map {
  private int id;
	private String name;
	private String fileLocation;
	private float scale;
	public int getId() {
		return id;
	}
	/**
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFileLocation() {
		return fileLocation;
	}
	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}
	public float getScale() {
		return scale;
	}
	public void setScale(float scale) {
		this.scale = scale;
	}

}
