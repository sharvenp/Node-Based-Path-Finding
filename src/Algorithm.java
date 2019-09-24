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
	
	protected void init() {
		
		this.setPath(new ArrayList<>());
		
		String startString;
		String endString;
	
		startString = String.format("(%s, %s)", start.getX(), start.getY());
		endString = String.format("(%s, %s)", end.getX(), end.getY());
		
		System.out.println(String.format("Finding Path from %s to %s using %s.", startString, endString, toString()));
	
	}
	
	protected void updatePanel() {
		Main.pathFinderPanel.repaint();
		delay(5);
	}
	
	protected void delay(int duration) {
		try {
			Thread.sleep(duration);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Node findNodeWithCoord(int x, int y, ArrayList<Node> list) {
		
		Node foundNode = null;
		
		for (Node node : list) {
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
			
			Node adjacentNode = findNodeWithCoord(x + dx, y + dy, this.getNodes());
			out[i] = adjacentNode;
		}
		return out;
	}
	
	protected Node getClosestNode(Node node) {
		
		Node out = null;
		double dist = Integer.MAX_VALUE;
		
		for (int i = 1; i < this.getNodes().size() - 1; i++) {
			Node n = this.getNodes().get(i);
			double d = getDistance(node, n);
			if (d < dist) {
				dist = d;
				out = n;
			}
		}
		
		return out;
	}
	
	protected double getDistance(Node from, Node to) {
		
		double xpow = Math.pow((from.getX() - to.getX()), 2);
		double ypow = Math.pow((from.getY() - to.getY()), 2);
		
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
