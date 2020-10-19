package interval;

/**
 * An object of this class stores an interval of integers.   
 * 
 * @invar This interval's lower bound is not greater than its upper bound.
 *     | getLowerBound() <= getUpperBound()
 * @invar This interval's width equals the difference between its upper bound
 *        and its lower bound.
 *     | getWidth() == getUpperBound() - getLowerBound()
 */
class Interval {
	
	/**
	 * @invar This interval's lower bound is not greater than its upper bound.
	 *      | lowerBound <= upperBound
	 */
	private int lowerBound;
	private int upperBound;

	int getLowerBound() {
		return lowerBound;
	}
	
	int getUpperBound() {
		return upperBound;
	}
	
	int getWidth() {
		return upperBound - lowerBound;
	}
	
	/**
	 * Initializes this interval with the given lower bound and upper bound.
	 * 
	 * @pre The given lower bound is not greater than the given upper bound.
	 *    | lowerBound <= upperBound
	 * @post This interval's lower bound equals the given lower bound.
	 *    | getLowerBound() == lowerBound
	 * @post This interval's upper bound equals the given upper bound.
	 *    | getUpperBound() == upperBound
	 */
	Interval(int lowerBound, int upperBound) {
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
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
	Interval(int lowerBound, int width, Void dummy) {
		this.lowerBound = lowerBound;
		this.upperBound = lowerBound + width;
	}
	
	/**
	 * Sets this interval's lower bound to the given value.
	 *
	 * @pre The given value is not greater than this interval's upper bound.
	 *    | value <= getUpperBound()
	 * @post This interval's lower bound equals the given value.
	 *    | getLowerBound() == value
	 * @post This interval's upper bound has remained unchanged.
	 *    | getUpperBound() == old(getUpperBound())
	 */
	void setLowerBound(int value) {
		lowerBound = value;
	}
	
	/**
	 * Sets this interval's upper bound to the given value.
	 * 
	 * @pre The given value is not less than this interval's lower bound.
	 *    | getLowerBound() <= value
	 * @post This interval's upper bound equals the given value.
	 *     | getUpperBound() == value
	 * @post This interval's lower bound has remained unchanged.
	 *     | getLowerBound() == old(getLowerBound())  
	 */
	void setUpperBound(int value) {
		upperBound = value;
	}
	
	/**
	 * Sets this interval's width to the given value.
	 * 
	 * @pre The given value is nonnegative.
	 *    | 0 <= value
	 * @post This interval's width equals the given value.
	 *     | getWidth() == value
	 * @post This interval's lower bound has remained unchanged.
	 *     | getLowerBound() == old(getLowerBound())
	 */
	void setWidth(int value) {
		upperBound = lowerBound + value;
	}
	
	/**
	 * Sets this interval's width to the given value.
	 *
	 * @pre The given value is nonnegative.
	 *    | 0 <= value
	 * @post This interval's width equals the given value.
	 *     | getWidth() == value
	 * @post If the caller specified that the lower bound should be updated, the
         *       upper bound has remained unchanged.
	 *     | !updateLowerBound || getUpperBound() == old(getUpperBound())
	 * @post If the caller specified that the lower bound should not be updated,
         *       the lower bound has remained unchanged.
	 *     | updateLowerBound || getLowerBound() == old(getLowerBound())
	 */
	void setWidth(int value, boolean updateLowerBound) {
		if (updateLowerBound)
			lowerBound = upperBound - value;
		else
			upperBound = lowerBound + value;
	}

}