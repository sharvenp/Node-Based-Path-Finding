
public class AStarNode extends Node {

	private int gCost;
	private int fCost;
	
	public AStarNode(int x, int y, boolean IsOpen) {
		super(x, y, IsOpen);
	}
	
	public int getGCost() {
		return gCost;
	}

	public void setGCost(int gCost) {
		this.gCost = gCost;
	}

	public int getFCost() {
		return fCost;
	}

	public void setFCost(int fCost) {
		this.fCost = fCost;
	}

	public int getHCost() {
		return fCost + gCost;
	}
}
