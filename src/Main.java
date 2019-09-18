
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main {

	public static PathFinder pathFinderPanel;
	
	public static void main(String[] args) {

		pathFinderPanel = new PathFinder(50);
		JFrame frame = generateFrame(pathFinderPanel);
		frame.add(pathFinderPanel);
		
		pathFinderPanel.setAlgorithm(new AStar());
		pathFinderPanel.solve();
	}

	public static JFrame generateFrame(PathFinder p) {
		JFrame frame = new JFrame();
		
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(p.getGrid().length + 40, p.getGrid()[1].length + 14);
        frame.setTitle("Node Based Path Finding");
        frame.setVisible(true);
        return frame;
	}
	
}
