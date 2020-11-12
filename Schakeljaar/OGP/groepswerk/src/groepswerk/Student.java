package groepswerk;
/**
 * @invar | getTeammate() == null || getTeammate().getTeammate() == this
 */
public class Student {
/**
 * @invar | teammate == null || teammate.teammate == this //meaning we can break repr. invar. of this by changing teammate cfr. *1
 * @peerObject //*1
 */
	private Student teammate;
	
	/**
	 * @peerObject //*1
	 */
	// again no use to copy this see setTeammate
	public Student getTeammate() {
		return teammate;
	}
	
	/**
	 * @post | getTeammate() == null
	 */
	public Student() {}
	
	/**
	 * @throws IllegalArgumentException | other == null
	 * @throws IllegalArgumentException | getTeammate() != null
	 * @throws IllegalArgumentException | other.getTeammate() != null
	 * @mutates | this, other // allows peer objects to be changed
	 * @post | getTeammate() == other
	 * //@post | other.getTeammate() == this // not needed since peerobject specification
	 */
	// other will not be encapsulated by other
	// objects will remain themselves
	// we are only laying an association
	// every object can only be itself since no copies in graph
	public void setTeammate(Student other) {
		if(other == null) {
			throw new IllegalArgumentException();
		}
		if(this.teammate != null) {
			throw new IllegalArgumentException();		
			}
		if (other.teammate != null) {
			throw new IllegalArgumentException();
		}
		// bidirectional graph, so this needs teammate and other needs teammate
		teammate = other;
		other.teammate = this; //*1
	}
	/**
	 * @throws IllegalArgumentException | getTeammate() == null
	 * @mutates | this
	 * @post | getTeammate() == null
	 * @post | old(getTeammate()).getTeammate() == null
	 */
	public void clearTeammate() {
		if(teammate == null) {
			throw new IllegalArgumentException();
		}
		// bidirectional graph, so this clears teammate and other clear teammate
		teammate.teammate = null; //*1
		teammate = null;
	}
}
