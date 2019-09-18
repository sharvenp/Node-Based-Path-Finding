import java.awt.image.BufferedImage;

import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

import com.sun.imageio.spi.RAFImageInputStreamSpi;

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
		int startx = -1;
		int starty = -1;
		int endx = -1;
		int endy = -1;
		
		for (int row = 0; row < img.getHeight(); row++) {
			for (int col = 0; col < img.getWidth(); col++) {
				
				int p = img.getRGB(col, row);
				int red = (p >> 16) & 0xff;
			    int green = (p >> 8) & 0xff;
			    int blue = (p) & 0xff;
				
				if (red == 255 && blue == 0 && green == 0) { // Start
					grid[row][col] = 2;
					startx = col;
					starty = row;
				} else if (red == 0 && blue == 0 && green == 255) { // End
					grid[row][col] = 3;
					endx = col;
					endy = row;
				} else if (red == 0 && blue == 0 && green == 0) { // Traverseable
					grid[row][col] = 0;
				} else { // Obstacle
					grid[row][col] = 1;
				}
			}
		}
		
		if (startx == -1 || starty == -1 || endx == -1 || endy == -1) {
			throw new IllegalArgumentException();
		}
		
		int[] start = {startx, starty}; 
		int[] end = {endx, endy}; 
		
		data.setGrid(grid);
		data.setStart(start);
		data.setEnd(end);
		
	}
	
}
