
import java.util.ArrayList;

// A* Pathfinding Algorithm Implementation
public class AStar extends Algorithm {

	private ArrayList<AStarNode> openNodes;
 	private ArrayList<AStarNode> closedNodes;
	
 	private boolean terminated;
 	
	public AStar() {
		super();
		terminated = false;
	}
	
	private void evaluateAllNodes() {
		
		for (Node node : this.getNodes()) {
			evaluateNode((AStarNode) node);
		}
	}
	
	private void evaluateNode(AStarNode node) {
		
		int startx = this.start.getX();
		int starty = this.start.getY();
		
		int endx = this.end.getX();
		int endy = this.end.getY();
		
		int nodex = node.getX();
		int nodey = node.getY();
		
		node.setGCost(super.getDistance(startx, starty, nodex, nodey));
		node.setHCost(super.getDistance(endx, endy, nodex, nodey));
	}
	
	public AStarNode getLowestFCost() {
		
		double min = -1;
		AStarNode minNode = null;
		
		for (AStarNode node : openNodes) {
			if (min < node.getFCost()) {
				minNode = node;
				min = node.getFCost();
			}
		}
		
		return minNode;
	}
	
	public void solve () {
		
		super.printMessage();
		this.setPath(new ArrayList<>());
		
		openNodes = new ArrayList<>();
		closedNodes = new ArrayList<>();
		
		openNodes.add((AStarNode) this.getNodes().get(0));
		evaluateAllNodes();
		
		AStarNode currentNode = getLowestFCost();
		
		openNodes.remove(currentNode);
		closedNodes.add(currentNode);
	
		findPath(currentNode);
		
		System.out.println("Done");
		
	}
	
	private void findPath (AStarNode currentNode) {
				
		super.delay(5);
		super.updatePanel();
		
		// Reached End Node
		if (currentNode == this.getNodes().get(this.getNodes().size() - 1)) {
			
			terminated = true;
			this.getPath().add(currentNode);
			return;
			
		} else {
			
			AStarNode[] adjacentNodes = new AStarNode[8];
			for (int i = 0; i < adjacentNodes.length; i++) {
				adjacentNodes[i] = (AStarNode) super.getAdjacentNodes(currentNode)[i];
			}
			
			for (AStarNode node : adjacentNodes) {
			
				if (node == null || !node.isOpen() || closedNodes.contains(node)) {
					continue;
				}
				
				if (!openNodes.contains(node) || node.getFCost() <= currentNode.getFCost()) {
				
					evaluateNode(node);
					findPath(node);
					
					
					if (terminated) {
						this.getPath().add(currentNode);
						return;
					}
					
					if (!openNodes.contains(node)) {
						openNodes.add(node);
					}
				}	
			}	
		}
	}
	
	public String toString() {
		return "A*";
	}
	
}
