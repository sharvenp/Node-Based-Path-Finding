import java.util.ArrayList;
import java.lang.Math;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;

public class PathFinder extends JPanel {

	private ArrayList<Node> nodes;
	private int[][] grid;
	
	private final Color NODE_TRAVERSEABLE_COLOR = new Color (255, 255, 0);
	private final Color NODE_NON_TRAVERSEABLE_COLOR = new Color (255, 0, 255);
	private final Color OBSTACLE_COLOR = new Color (255, 255, 255);
	private final Color START_COLOR = new Color(255, 0, 0);
	private final Color END_COLOR = new Color (0, 255, 0);
	private final Color FREE_COLOR = new Color (0, 0, 0);
	
	public int[][] getGrid() {
		return grid;
	}

	public PathFinder(int nodeDensity) {
			
		nodes = new ArrayList<Node>();
		
		ImageProcessor processor = new ImageProcessor();
		MapData data = processor.processImage("resources/img.png");
		
		grid = data.getGrid();
		nodes.add(data.getStart());
		
		int xInterval = Math.floorDiv(grid.length, nodeDensity);
		int yInterval = Math.floorDiv(grid[1].length, nodeDensity);
		
		for (int y = 0; y < grid.length; y++) {
			for (int x = 0; x < grid[y].length; x++) {
				if (x % xInterval == 0 && y % yInterval == 0) {
					boolean IsOpen = (grid[y][x] != 1);
					nodes.add(new Node(x, y, IsOpen));
				}
			}
		}
		
		nodes.add(data.getEnd());
		
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
				g.fillRect(x, y, 1, 1);
			}
		}
        
        
        for (int i = 0; i < nodes.size(); i++) {
        	
        	g.setColor(NODE_TRAVERSEABLE_COLOR);
        	int radius = 2;
        	
        	if (!nodes.get(i).isOpen()) {
        		g.setColor(NODE_NON_TRAVERSEABLE_COLOR);
        	}
        	
        	if (i == 0) {
        		g.setColor(START_COLOR);
        		radius = 4;
        	} else if (i == nodes.size() - 1) {
        		g.setColor(END_COLOR);
        		radius = 4;
        	}
        	
        	g.fillOval(nodes.get(i).getX() - (int)(radius/2), nodes.get(i).getY() - (int)(radius/2), radius, radius);
        	
        }
    }
	
}
