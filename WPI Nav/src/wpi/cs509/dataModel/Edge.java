package wpi.cs509.dataModel;
/**
 * This is the class to describe the edge between a pair of points.
 * @author Zhiming
 *@version 1.0
 */
public class Edge {
  private int id;
	private int weight;
	private String description;
	private int sPointId;
	private int ePointId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getsPointId() {
		return sPointId;
	}
	public void setsPointId(int sPointId) {
		this.sPointId = sPointId;
	}
	public int getePointId() {
		return ePointId;
	}
	public void setePointId(int ePointId) {
		this.ePointId = ePointId;
	}
}
