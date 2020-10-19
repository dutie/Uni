package interval2;


/**
 * An object of this class stores an interval of integers.   
 * 
 * @invar This interval's lower bound is not greater than its upper bound.
 *     | getLowerBound() <= getUpperBound()
 * @invar This interval's width equals the difference between its upper bound
 *        and its lower bound.
 *     | getWidth() == getUpperBound() - getLowerBound()
 */
public class Interval {
	
/**
 * @invar This interval's lower bound is not greater than its upper bound.
 *    | lowerBound <= upperBound
 */
	
	private int lowerBound;
	private int upperBound;
	
	public int getLowerBound() {
		return lowerBound;
	}
	
	public int getWidth() {
		return upperBound - lowerBound;
	}
	
	public int getUpperBound() {
		return upperBound;
	}
	
	/**
	 * Initializes this interval with the given lower bound and width.
	 * 
	 * @pre The given width is nonnegative. 
	 *    | 0 <= width
	 * @post This interval's lower bound equals the given lower bound.
	 *    | getLowerBound() == lowerBound
	 * @post This interval's width equals the given width.
	 *    | getWidth() == width
	 */
	public Interval(int lowerBound, int width) {
		this.lowerBound = lowerBound;
		this.upperBound = lowerBound + width;
	}
	
	/**
	 * 
	 * @pre The given upper bound is not smaller than the lower bound
	 *    | lowerBound <= upperBound
	 * @post This interval's lower bound is the given lower bound
	 *    | getLowerBound() == lowerBound
	 * @post This interval's upper bound is the given upper bound
	 *    | getUpperBound() == upperBound
	 *
	 * @param lowerBound
	 * @param upperBound
	 * @param dummy
	 */
	public Interval(int lowerBound, int upperBound, Void dummy) {
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
	}
	
	public void setWidth(int value) {
		this.upperBound = lowerBound + value;
	}
	
	public void setUpperBound(int value) {
		this.upperBound = lowerBound + value;
	}
	
	public void setLowerBound(int value) {
		this.lowerBound = value;
	}
	
}
