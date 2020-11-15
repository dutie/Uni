package portals;

public interface Transformation {
	
	/**
	 * Returns a size of an object scaled by the portal's transformation
	 * The size will be scaled by some fraction between 1/2 and 2.
	 * 
	 * @pre  | 0 <= objSize
	 * @creates | result
	 * @post | result <= objSize * 2 || result >= objSize * 0.5
	 */
	
	int apply(int objSize);
}