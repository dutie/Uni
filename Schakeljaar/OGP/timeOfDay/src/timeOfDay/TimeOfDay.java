package timeOfDay;

/**
 * An instance of this class stores a time of day with 1 minute granularity.
 * 
 * @invar The hours are between 0 and 23
 *      | 0 <= getHours() && getHours() <= 23
 * @invar The minutes are between 0 and 59
 *      | 0 <= getMinutes() && getMinutes() <= 59
 */
public class TimeOfDay {
	
	/**
	 * @invar minutesSinceMidnight is between 0 (inclusive) and 60 * 24 (exclusive)
	 *      | 0 <= minutesSinceMidnight && minutesSinceMidnight < 60 * 24
	 */
	private int minutesSinceMidnight;
	
	/**
	 * Initializes this object with the given hours and minutes.
	 * 
	 * @post This object's hours equal the given hours
	 *    | getHours() == hours
	 * @post This object's minutes equal the given minutes
	 *    | getMinutes() == minutes
	 * @throws IllegalArgumentException
	 *      The given hours are less than 0 or greater than 23
	 *    | !(0 <= hours && hours <= 23)
	 * @throws IllegalArgumentException
	 *      The given minutes are less than 0 or greare than 59
	 *    | !(0 <= minutes && minutes <= 59)
	 */
	public TimeOfDay(int hours, int minutes) {
		if (!(0 <= hours && hours <= 23))
			throw new IllegalArgumentException("hours out of range");
		if (!(0 <= minutes && minutes <= 59))
			throw new IllegalArgumentException("minutes out of range");
		minutesSinceMidnight = 60 * hours + minutes;
	}

	public int getHours() {
		return minutesSinceMidnight / 60;
	}

	public int getMinutes() {
		return minutesSinceMidnight % 60;
	}

}
