package fallhomework;

public interface Transformation {
	
	/**
	 * Returns a size of an object scaled by the portal's transformation
	 * The size will be scaled by some fraction between 1/2 and 2.
	 * 
	 * @pre  | 0 <= objSize
	 */
	
	int apply(int objSize);
}