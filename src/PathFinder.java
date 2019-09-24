import java.util.ArrayList;
import java.lang.Math;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;

@SuppressWarnings("serial")
public class PathFinder extends JPanel {

	public static Algorithm currentAlgorithm;
	
	private int[][] grid;
	private MapData data;
	private int nodeDensity;
	
	private int[] start;
	private int[] end;
	
	private final Color NODE_NON_TRAVERSEABLE_COLOR = new Color (255, 255, 0);
	private final Color OBSTACLE_COLOR = new Color (255, 255, 255);
	private final Color START_COLOR = new Color (255, 0, 0);
	private final Color END_COLOR = new Color (0, 255, 0);
	private final Color FREE_COLOR = new Color (0, 0, 0);
	private final Color LINE_COLOR = new Color (37, 240, 255);
	private final int offset = 20;
	
	public int[][] getGrid() {
		return grid;
	}
	
	public void setAlgorithm(Algorithm algorithm) {
		currentAlgorithm = algorithm;
		generateNodes();
	}

	public PathFinder(int nodeDensity) {
					
		ImageProcessor processor = new ImageProcessor();
		data = processor.processImage("resources/img.png");
		grid = data.getGrid();
		start = data.getStart();
		end = data.getEnd();
		
		this.nodeDensity = nodeDensity;		
	}
	
	private void generateNodes() {
		
		ArrayList<Node> nodes = new ArrayList<>();
		int[] delta = new int[2];
		

		Class<? extends Algorithm> algorithmType = currentAlgorithm.getClass();
		
		// Update After new Algorithm Implemented
		if (algorithmType == AStar.class) {
			nodes.add(new AStarNode(start[0], start[1], true));
		} else if (algorithmType == RBT.class) {
			nodes.add(new RBTNode(start[0], start[1], true));
		}
		
		int xInterval = Math.floorDiv(grid.length, nodeDensity);
		int yInterval = Math.floorDiv(grid[1].length, nodeDensity);
		
		// delta used to get surrounding nodes by algorithms
		delta[0] = xInterval; 
		delta[1] = yInterval;
		
		for (int y = 0; y < grid.length; y++) {
			for (int x = 0; x < grid[y].length; x++) {
				if (x % xInterval == 0 && y % yInterval == 0) {
					
					boolean IsOpen = (grid[y][x] != 1);
				
					// Update After new Algorithm Implemented
					if (algorithmType == AStar.class) {
						nodes.add(new AStarNode(x, y, IsOpen));
					} else if (algorithmType == RBT.class) {
						nodes.add(new RBTNode(x, y, IsOpen));
					}
				
				}
			}
		}
		
		// Update After new Algorithm Implemented
		if (algorithmType == AStar.class) {
			nodes.add(new AStarNode(end[0], end[1], true));
		} else if (algorithmType == RBT.class) {
			nodes.add(new RBTNode(end[0], end[1], true));
		}
		
		currentAlgorithm.setDelta(delta);
		currentAlgorithm.setNodes(nodes);
	}
	
	public void runAlgorithm() {
		currentAlgorithm.solve();
		currentAlgorithm.updatePanel();
	}
	
	@Override
    public void paintComponent(Graphics g) {
        
		super.paintComponent(g);

        for (int y = 0; y < grid.length; y++) {
			for (int x = 0; x < grid[y].length; x++) {
				switch (grid[y][x]) {
					case 0:
						g.setColor(FREE_COLOR);
						break;
					case 1:
						g.setColor(OBSTACLE_COLOR);
						break;
					case 2:
						g.setColor(START_COLOR);
						break;
					case 3:
						g.setColor(END_COLOR);
						break;
				}				
				g.fillRect(x + offset, y + offset, 1, 1);
			}
		}
        
        
        for (int i = 0; i < currentAlgorithm.getNodes().size(); i++) {
        	
        	g.setColor(currentAlgorithm.getNodes().get(i).getNodeColor());
        	int radius = 2;
        	
        	if (!currentAlgorithm.getNodes().get(i).isOpen()) {
        		g.setColor(NODE_NON_TRAVERSEABLE_COLOR);
        	}
        	
        	if (i == 0) {
        		g.setColor(START_COLOR);
        		radius = 6;
        	} else if (i == currentAlgorithm.getNodes().size() - 1) {
        		g.setColor(END_COLOR);
        		radius = 6;
        	}
        	
        	g.fillOval(currentAlgorithm.getNodes().get(i).getX() + offset - (int)(radius/2), 
        			   currentAlgorithm.getNodes().get(i).getY() + offset - (int)(radius/2), 
        			   radius, radius);
        	
        }

        if (currentAlgorithm.getPath() != null) {
        	
        	g.setColor(LINE_COLOR);
        	
        	for (int i = 0; i < currentAlgorithm.getPath().size() - 1; i++) {

        		Node fromNode = currentAlgorithm.getPath().get(i);
        		Node toNode = currentAlgorithm.getPath().get(i + 1);

        		g.drawLine(fromNode.getX() + offset, fromNode.getY() + offset, toNode.getX() + offset, toNode.getY() + offset);
        	}
        }
        
    }
	
}
