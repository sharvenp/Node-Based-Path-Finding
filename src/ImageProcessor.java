import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageProcessor {

	public MapData processImage(String dir) {
		
		System.out.println("Processing Image");
		
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new FileInputStream(dir));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		MapData data = new MapData();
		generateMap(img, data);
		System.out.println("Processing Done.");
		
		return data;
	}
	
	private void generateMap(BufferedImage img, MapData data) {
		
		int[][] grid = new int[img.getHeight()][img.getWidth()];
		Node start = null;
		Node end = null;
		
		for (int row = 0; row < img.getHeight(); row++) {
			for (int col = 0; col < img.getWidth(); col++) {
				
				int p = img.getRGB(col, row);
				int red = (p >> 16) & 0xff;
			    int green = (p >> 8) & 0xff;
			    int blue = (p) & 0xff;
				
				if (red == 255 && blue == 0 && green == 0) { // Start
					grid[row][col] = 2;
					start = new Node(col, row, true);
				} else if (red == 0 && blue == 0 && green == 255) { // End
					grid[row][col] = 3;
					end = new Node(col, row, true);
				} else if (red == 0 && blue == 0 && green == 0) { // Traverseable
					grid[row][col] = 0;
				} else { // Obstacle
					grid[row][col] = 1;
				}
			}
		}
		
		data.setGrid(grid);
		data.setStart(start);
		data.setEnd(end);
		
	}
	
}
