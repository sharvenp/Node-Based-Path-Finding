
import java.util.ArrayList;

public class AStar extends Algorithm {

	/* Classic Dijkstra's Algorithm
	 */
	
	public AStar() {
		super();
	}
	
	public void findPath () {
		
		super.printMessage();
		this.setPath(new ArrayList<>());
		
		
		super.updatePanel();
	}
	
	public String toString() {
		return "A* Algorithm";
	}
	
}
