package portals;

public class TwoThirdsTransformation implements Transformation{
	/**
	 * @invar | fraction >= (1.0/2.0) 
	 * @invar | fraction <= 2.0
	 * @immutable
	 */
	public final double fraction = (2.0/3.0);
	
	/**
	 * @throws IllegalArgumentException if objSize is negative or zero
	 *     | 0 >= objSize
	 * @post | result == Math.round(objSize * fraction)
	 */
	@Override
	public int apply(int objSize) {
		if(0>objSize)
			throw new IllegalArgumentException("Size of object must be larger than 0");
		return (int) Math.round(objSize * fraction);
	}
	
}


