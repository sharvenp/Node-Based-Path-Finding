import java.util.ArrayList;
import java.lang.Math;

// Algorithm Super Class. All Path-Finding algorithms inherit from this class
public abstract class Algorithm {
	
	protected Node start;
	protected Node end;
	private ArrayList<Node> nodes;
	private ArrayList<Node> path;
	
	private int[] delta;

	public Algorithm() {
		path = null;
	}
	
	public abstract void solve();
	
	public abstract String toString();
	
	protected void printMessage() {
		
		String startString;
		String endString;
	
		startString = String.format("(%s, %s)", start.getX(), start.getY());
		endString = String.format("(%s, %s)", end.getX(), end.getY());
		
		System.out.println(String.format("Finding Path from %s to %s using %s.", startString, endString, toString()));
	
	}
	
	protected void updatePanel() {
		Main.pathFinderPanel.repaint();
	}
	
	protected void delay(int duration) {
		try {
			Thread.sleep(duration);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Node findNodeWithCoord(int x, int y) {
		
		Node foundNode = null;
		
		for (Node node : this.getNodes()) {
			if (node.getX() == x && node.getY() == y) {
				foundNode = node;
				break;
			}
		}
		
		return foundNode;
	}
	
	protected Node[] getAdjacentNodes(Node node) {
		
		Node[] out = new Node[8];

		int[][] directions = {{-delta[0], -delta[1]}, {-delta[0], 0}, {-delta[0], delta[1]}, {0, -delta[1]},
							  { 0,  delta[1]}, {delta[0], -delta[1]}, { delta[0], 0}, {delta[0],  delta[1]}};
		
		int x = node.getX();
		int y = node.getY();
		
		for (int i = 0; i < directions.length; i++) {
			
			int dx = directions[i][0];
			int dy = directions[i][1];
			
			Node adjacentNode = findNodeWithCoord(x + dx, y + dy);
			out[i] = adjacentNode;
		}
		return out;
	}
	
	protected double getDistance(int x1, int y1, int x2, int y2) {
		
		double xpow = Math.pow((x1 - x2), 2);
		double ypow = Math.pow((y1 - y2), 2);
		
		return Math.sqrt(xpow + ypow);
		
	}
	
	public ArrayList<Node> getPath() {
		return path;
	}

	public void setPath(ArrayList<Node> path) {
		this.path = path;
	}
	
	public ArrayList<Node> getNodes() {
		return nodes;
	}

	public void setNodes(ArrayList<Node> nodes) {
		this.nodes = nodes;
		
		this.start = this.nodes.get(0);
		this.end = this.nodes.get(this.nodes.size() - 1);
	}
	
	public void setDelta(int[] delta) {
		this.delta = delta;
	}
	
	public int[] getDelta() {
		return this.delta;
	}
	
}
