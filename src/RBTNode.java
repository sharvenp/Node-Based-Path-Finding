import java.awt.Color;

public class RBTNode extends Node {

	private boolean visited;
	
	private Color visitedColor = new Color(20, 200, 20);
	private Color defaultColor = new Color(20, 20, 200);
	
	public RBTNode(int x, int y, boolean IsOpen) {
		super(x, y, IsOpen);
		this.visited = false;
		this.nodeColor = defaultColor;
		this.nodeRadius = 2;
	}
	
	public boolean getVisited() {
		return this.visited;
	}
	
	public void setVisited(boolean visited) {
		this.visited = visited;
		if (visited)
		{
			this.nodeColor = visitedColor;
			this.nodeRadius = 4;
		}
	}
}
