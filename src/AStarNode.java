
public class AStarNode extends Node {

	private double gCost;
	private double hCost;
	
	public AStarNode(int x, int y, boolean IsOpen) {
		super(x, y, IsOpen);
	}
	
	public double getGCost() {
		return gCost;
	}

	public void setGCost(double gCost) {
		this.gCost = gCost;
	}

	public double getHCost() {
		return hCost;
	}

	public void setHCost(double hCost) {
		this.hCost = hCost;
	}

	public double getFCost() {
		return hCost + gCost;
	}
}
