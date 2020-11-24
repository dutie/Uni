package examroom;

import java.util.HashSet;
import java.util.Set;

import logicalcollections.*;
/**
 * 
 * @invar | getRooms().stream().allMatch(m -> m != null)
 * @invar | getRooms() != null
 * @invar | getRooms().stream().allMatch(m -> m.getExams().contains(this))
 *
 */
public class Exam {
	/**
	 * 
	 * @invar | rooms.stream().allMatch(m -> m != null)
	 * @invar | rooms != null
	 * @invar | rooms.stream().allMatch(m -> m.exams.contains(this))
	 *
	 * @representationObject
	 * @peerObjects
	 */
	Set<Room> rooms = new HashSet<>();
	
	/**
	 * @peerObjects
	 * 
	 * @post | result != null
	 * @basic
	 * @creates | result
	 */
	public Set<Room> getRooms(){
		return Set.copyOf(rooms);
	}
	/**
	 * @post | getRooms().size() == 0
	 */
	public Exam() {}
	
	/**
	 * @throws IllegalArgumentException | room == null
	 * 
	 * @mutates_properties | this.getRooms(), room.getExams()
	 * 
	 * @post | getRooms().contains(room)
	 * @post | room.getExams().contains(this)
	 * @post | getRooms().equals(LogicalSet.plus(old(getRooms()),room))
	 * @post | room.getExams().equals(LogicalSet.plus(old(room.getExams()),this))
	 *
	 */
	public void addRoom(Room room) {
		if(room == null) {
			throw new IllegalArgumentException();
		}
		rooms.add(room);
		room.exams.add(this);
	}
	
	/**
	 * @throws IllegalArgumentException | room == null
	 * @throws IllegalStateException | !room.getExams().contains(this)
	 * 
	 *  @mutates_properties | this.getRooms(), room.getExams()
	 *  
     * @post | getRooms().equals(LogicalSet.minus(old(getRooms()),room))
	 * @post | old(room).getExams().equals(LogicalSet.minus(old(room.getExams()),this))
	 *
	 */
	public void removeRoom(Room room) {
		if(room == null) {
			throw new IllegalArgumentException();
		}
		if(!room.exams.contains(this)) {
			throw new IllegalStateException();
		}
		this.rooms.remove(room);
		room.exams.remove(this);
	}
	
	
}
