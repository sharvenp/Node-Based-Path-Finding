import java.awt.Color;

// Nodes superclass
public abstract class Node {

	private int x;
	private int y;
	private boolean open;
	protected Color nodeColor;
	
	public Node(int x, int y, boolean IsOpen) {
		this.x = x;
		this.y = y;
		this.open = IsOpen;
	}
	
	public int getX() { 
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean isOpen() {
		return open;
	}
	
	public String toString() {
		return String.format("Node @ %s %s %s", x, y, open); 
	}
	
	public Color getNodeColor() {
		return nodeColor;
	}
}