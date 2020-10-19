package point;

/**
 * 
 * @author mats
 *
 * Each instance of this class represents a point on the integer 2d coordinate system.
 */
public class Point {
	
	private int x;
	private int y;
	
	/*
	 * returns the instance of X coordinate.
	 */
	public int getX() {
		return x;
	}
	
	/*
	 * returns the instance of Y coordinate.
	 */
	public int getY() {
		return y;
	}
	
	/*
	 * Initializes the instance so that it represents the given point.
	 * @post | getX() == X
	 * @post | getY() == Y
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
		
	}

}
