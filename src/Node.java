
public class Node {

	private int x;
	private int y;
	private boolean open;
	
	public Node(int x, int y, boolean IsOpen) {
		this.x = x;
		this.y = y;
		this.open = IsOpen;
	}
	
	public int getX() { 
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean isOpen() {
		return open;
	}
	
}