import java.util.ArrayList;

public class RBT extends Algorithm {

	private ArrayList<RBTNode> allNodes;
	
	private RBTNode closeStart;
 	private RBTNode closeEnd;
 	
 	private boolean terminated;
	
	public RBT() {
		super();
		terminated = false;
	}
	
	public void solve() {
		
		super.init();
		allNodes = new ArrayList<>();

		for (Node node : this.getNodes()) {
			allNodes.add((RBTNode) node);
		}
		
		closeStart = (RBTNode) getClosestNode(this.start);
		closeEnd = (RBTNode) getClosestNode(this.end);
		
		findNextNode(closeStart);
		System.out.println("Done.");
	}
	
	private void findNextNode(RBTNode currentNode) {
		
		while (true) {
			
			
			if (terminated) {
				this.getPath().add(currentNode);
				super.updatePanel();
				break;
			}
			
			
			RBTNode[] adjacentNodes = new RBTNode[8];
			Node[] adjNodes = this.getAdjacentNodes(currentNode);
			for (int i = 0; i < adjacentNodes.length; i++) {
				adjacentNodes[i] = (RBTNode) adjNodes[i];
				if (adjacentNodes[i] != null && (adjacentNodes[i].getVisited() || !adjacentNodes[i].isOpen())) {
					adjacentNodes[i] = null;
				}
			}

			boolean checkDeadEnd = true;
			for (int i = 0; i < adjacentNodes.length; i++) {
				if (adjacentNodes[i] != null) {
					checkDeadEnd = false;
					break;
				}
			}
			

			if (!checkDeadEnd) {
				
				for (RBTNode n : adjacentNodes) {
					if (n == closeEnd) {
						terminated = true;
						this.getPath().clear();
						break;
					}
				}
				
				RBTNode chosenNode = null;
				
				for (int i = 0; i < adjacentNodes.length; i++) {
					if (adjacentNodes[i] != null) {
						chosenNode = adjacentNodes[i];
						break;
					}
				}
				
				chosenNode.setVisited(true);
				
				super.updatePanel();
				
				findNextNode(chosenNode);
				
			} else {
				break;
			}
		}
	}

	public String toString() {
		return "Recursive Back-Tracker (RBT)";
	}

}
