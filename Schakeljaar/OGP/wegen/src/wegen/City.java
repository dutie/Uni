package wegen;

import java.util.HashSet;
import java.util.Set;
import logicalcollections.*;
/**
 * @invar | getConnectedCities() != null
 * @invar | getConnectedCities().stream().allMatch(c -> c != null)
 * @invar | getConnectedCities().stream().allMatch(c ->
 *  	  |	c.getConnectedCities().contains(this))
 */
public class City {
	/**
	 * @invar | connectedCities != null
	 * @invar | connectedCities.stream().allMatch(c -> c != null)
	 * @invar | connectedCities.stream().allMatch(c ->
	 *  	  |	c.connectedCities.contains(this))
	 *  
	 *  @representationObject
	 *  @peerObjects
	 */
	// object stays same but content will vary
	private final Set<City> connectedCities = new HashSet<>();
	
	/**
	 * @peerObjects
	 */
	// hashset object is repr. object of city object and cannot be leaked
	public Set<City> getConnectedCities(){
		return Set.copyOf(connectedCities);}
	
	/**
	 * @post | getConnectedCities().isEmpty()
	 */
	public City() {}
	
	/**
	 * @pre | other != null
	 * @mutates | this, other
	 * @post | getConnectedCities().equals(LogicalSet.plus(old(getConnectedCities()),other))
	 * @post | other.getConnectedCities().equals(LogicalSet.plus(old(other.getConnectedCities()),this))
	 */
	public void registerRoad(City other) {
		connectedCities.add(other);
		other.connectedCities.add(this);
	}
	/**
	 * @pre | getConnectedCities().contains(other)
	 * @mutates | this
	 * @post | getConnectedCities().equals(LogicalSet.minus(old(other.getConnectedCities()), this))
	 * @post | other.getConnectedCities().equals(LogicalSet.minus(old(getConnectedCities()), other))
	 * @param other
	 */
	public void registerRoadRemoved(City other) {
		connectedCities.remove(other);
		other.connectedCities.remove(this);
	}
}
