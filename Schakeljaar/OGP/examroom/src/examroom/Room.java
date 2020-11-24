package examroom;

import java.util.HashSet;
import java.util.Set;
/**
 * 
 * @invar | getExams().stream().allMatch(m -> m != null)
 * @invar | getExams() != null
 * @invar | getExams().stream().allMatch(m -> m.getRooms().contains(this))
 *
 */
public class Room {
	/**
	 * 
	 * @invar | exams.stream().allMatch(m -> m != null)
	 * @invar | exams != null
	 * @invar | exams.stream().allMatch(m -> m.rooms.contains(this))
	 *
	 * @representationObject
	 * @peerObjects
	 */
	Set<Exam> exams = new HashSet<>();
	
	/**
	 * @peerobjects
	 * 
	 * @post | result != null
	 * @basic
	 * @creates | result
	 */
	public Set<Exam> getExams() {
		return Set.copyOf(exams);
	}
	
	/**
	 * @post | getExams().size() == 0
	 */
	public Room() {}

}
