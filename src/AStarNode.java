import java.awt.Color;

public class AStarNode extends Node {

	private AStarNode parentNode;
	private double gCost;
	private double hCost;
	
	private Color openColor = new Color(20, 230, 20);
	private Color closedColor = new Color(230, 20, 20);
	
	public AStarNode(int x, int y, boolean IsOpen) {
		super(x, y, IsOpen);
		parentNode = null;
	}
	
	public void setOpen() {
		this.nodeColor = openColor;
		this.nodeRadius = 4;
	}
	
	public void setClosed() {
		this.nodeColor = closedColor;
		this.nodeRadius = 2;
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
	
	public AStarNode getParentNode() {
		return parentNode;
	}

	public void setParentNode(AStarNode parentNode) {
		this.parentNode = parentNode;
	}
}
