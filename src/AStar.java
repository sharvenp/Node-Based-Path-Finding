
import java.util.ArrayList;

// A* Pathfinding Algorithm Implementation
public class AStar extends Algorithm {

	private ArrayList<AStarNode> openNodes;
 	private ArrayList<AStarNode> closedNodes;
 	
 	private AStarNode closeStart;
 	private AStarNode closeEnd;
 	
	public AStar() {
		super();
	}
	
	private void evaluateAllNodes() {
		
		for (Node node : this.getNodes()) {
			evaluateNode((AStarNode) node);
		}
	}
	
	private void evaluateNode(AStarNode node) {
		
		node.setGCost(super.getDistance(closeStart, node));
		node.setHCost(super.getDistance(closeEnd, node));
	}
	
	public AStarNode getLowestFCost() {
		
		AStarNode minNode = openNodes.get(0);
		
		for (AStarNode node : openNodes) {
			if (node.getFCost() < minNode.getFCost() || node.getFCost() == minNode.getFCost() && node.getHCost() < minNode.getHCost()) {
				minNode = node;
			}
		}
		
		return minNode;
	}
	
	public void solve () {
		
		super.init();
		
		openNodes = new ArrayList<>();
		closedNodes = new ArrayList<>();
		closeStart = (AStarNode) super.getClosestNode(this.start);
		closeEnd = (AStarNode) super.getClosestNode(this.end);
		
		openNodes.add(closeStart);
		closeStart.setOpen();
		
		evaluateAllNodes();

		while (openNodes.size() > 0) {
			
			AStarNode currentNode = getLowestFCost();
			openNodes.remove(currentNode);
			
			closedNodes.add(currentNode);
			currentNode.setClosed();
			
			super.updatePanel();
			
			if (currentNode == closeEnd) {				
				
				AStarNode pathCurr = currentNode;
				this.getPath().add(this.end);

				while (pathCurr != null) {
				
					this.getPath().add(pathCurr);
					pathCurr = pathCurr.getParentNode();
				}
				
				this.getPath().add(this.start);
				
				System.out.println("Done.");
				return;
			} 
				
			AStarNode[] adjacentNodes = new AStarNode[8];
			Node[] adjNodes = super.getAdjacentNodes(currentNode);
			for (int i = 0; i < adjacentNodes.length; i++) {
				adjacentNodes[i] = (AStarNode) adjNodes[i];
			}
			
			for (AStarNode node : adjacentNodes) {
			
				if (node == null || !node.isOpen() || closedNodes.contains(node))
					continue;
				
				double g = currentNode.getGCost() + getDistance(currentNode, node);
				
				if (g < node.getGCost() || !openNodes.contains(node)) {
					node.setGCost(g);
					node.setHCost(getDistance(node, closeEnd));
					node.setParentNode(currentNode);

					if (!openNodes.contains(node))
					{
						openNodes.add(node);
						node.setOpen();
						
						super.updatePanel();
					}
				}	
			}	
		}
		
		System.out.println("No Path Found.");
	}
	
	public String toString() {
		return "A*";
	}
	
}
