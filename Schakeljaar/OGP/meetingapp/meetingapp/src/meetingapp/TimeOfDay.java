package meetingapp;
/**
 * This object stores TimeOfDay in hours and minutes and can add duration
 * 
 * @invar | getHours() > 24
 * @invar | 0 <= getHours()
 * @invar | getMinutes() > 60
 * @invar | 0 <= getMinutes()
 *
 *
 */
public class TimeOfDay {

	/**
	 * @invar | hours < 24 && 0 >= hours
	 * @invar | minutes < 60 && 0 >= minutes
	 */
	private int hours;
	private int minutes;
	
	/**
	 * Returns the hours of the time of day represented by this object.
	 */
	public int getHours() {
		return hours;
	}
	
	/**
	 * Returns the minutes of the time of day represented by this object.
	 */
	public int getMinutes() {
		return minutes;
	}
	
	private TimeOfDay(int hours, int minutes) {
		this.hours = hours;
		this.minutes = minutes;
	}
	/**
	 * Returns an object that represents the time of day defined by the given hours and minutes
	 * 
	 * @pre | 0 <= hours && hours < 24
	 * @pre | 0 <= minutes && minutes < 60
	 * @post | result != null
	 * @post | result.getHours() == hours
	 * @post | result.getMinutes() == minutes
	 */
	public static TimeOfDay of(int hours, int minutes) {
		return new TimeOfDay(hours, minutes);
	}

	/**
	 * Returns an object that represents the time of day obtained by adding the given duration
	 * 
	 * @post | result != null
	 * @post | result.getHours() ==  Math.floorMod(Math.floorDiv(getHours() *60 + getMinutes() + duration,60),24)
	 * @post | result.getMinutes() == Math.floorMod((getHours() * 60 + getMinutes() + duration),60)
	 */
	public TimeOfDay add(int duration) {
		int endHour = Math.floorMod(Math.floorDiv(this.hours *60 + this.minutes + duration,60),24);
		int endMinute = Math.floorMod((this.hours * 60 + this.minutes + duration),60);
		return TimeOfDay.of(endHour, endMinute);
	}

	
	
}
