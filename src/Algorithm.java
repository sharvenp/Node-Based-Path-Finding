import java.util.ArrayList;

// Algorithm Super Class. All Path-Finding algorithms inherit from this class
public abstract class Algorithm {
	
	private Node start;
	private Node end;
	private ArrayList<Node> nodes;
	private ArrayList<Node> path;

	public Algorithm() {
		path = null;
	}
	
	public abstract void findPath();
	
	public abstract String toString();
	
	public void printMessage() {
		
		String startString;
		String endString;
	
		startString = String.format("(%s, %s)", start.getX(), start.getY());
		endString = String.format("(%s, %s)", end.getX(), end.getY());
		
		System.out.println(String.format("Finding Path from %s to %s using %s", startString, endString, toString()));
	
	}
	
	public void updatePanel() {
		Main.pathFinderPanel.repaint();
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
		
		start = this.nodes.get(0);
		end = this.nodes.get(this.nodes.size() - 1);
	}	
}
