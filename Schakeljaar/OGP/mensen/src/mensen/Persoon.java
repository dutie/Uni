package mensen;

import java.util.HashSet;
import java.util.Set;
import logicalcollections.*;
/**
 * @invar | getKinderen().stream().allMatch(c -> c != null)

 */
public class Persoon {
	/**
	 * @invar | kinderen.stream().allMatch(c -> c != null)
	 * @representationObject
	 * @peerObjects
	 */
	private Persoon vader;
	private final Set<Persoon> kinderen = new HashSet<>();
	
	/**
	 * @post | getVader() == null
	 * @post | getKinderen().isEmpty()
	 */
	public Persoon () {}
	
	/**
	 * @peerObject
	 */
	public Persoon getVader() {
		return vader;
	}
	/**
	 * @peerObjects
	 */
	public Set<Persoon> getKinderen() {
		return Set.copyOf(kinderen);
	}
	/**
	 * @throws IllegalArgumentException | other == null
	 * @throws IllegalArgumentException | getVader() != null
	 * 
	 * @mutates this, other
	 * 
	 * @post | getVader() == other
	 */
	public void setVader(Persoon other) {
		if(other == null) {
			throw new IllegalArgumentException();
		}
		if(this.vader != null) {
			throw new IllegalArgumentException();		
			}

		vader = other;
		other.kinderen.add(this);
	}
	/**
	 * @throws IllegalArgumentException other == null
	 * @throws IllegalArgumentException other.getVader() != null
	 * 
	 * @mutates other, this
	 * 
	 * @post | getKinderen().stream().anyMatch(c -> c == other)
	 */
	public void addKind(Persoon other) {
		if(other == null) {
			throw new IllegalArgumentException();
		}
		if (other.vader != null) {
			throw new IllegalArgumentException();
		}
		
		kinderen.add(other);
		other.vader = this;
	}
	/**
	 * @throws IllegalArgumentException getVader() == null
	 * 
	 * @mutates this
	 * 
	 * @post | getVader() == null
	 * @post | old(getVader()).getKinderen().equals(LogicalSet.minus(old(getVader().getKinderen()),(this)))
	 */
	public void clearVader() {
		if(vader == null) {
			throw new IllegalArgumentException();
		}
		vader.kinderen.remove(this);
		vader = null;
	}
	/**
	 * @throws IllegalArgumentException getKinderen().isEmpty()
	 */
	public void clearKinderen() {
		if(kinderen.isEmpty()) {
			throw new IllegalArgumentException();
		}
		for(Persoon kind : kinderen) {
			kind.vader = null;
			kind = null;
			
		}
	}
	/**
	 * @throws IllegalArgumentException | getKinderen().isEmpty()
	 * @throws IllegalArgumentException | getKinderen().contains(other) == false
	 * @param other
	 */
	public void removeKind(Persoon other) {
		if(kinderen.isEmpty()) {
			throw new IllegalArgumentException();
		}
		if(!kinderen.contains(other)) {
			throw new IllegalArgumentException();
		}
		
		kinderen.remove(other);
	}
	
	
	
}
