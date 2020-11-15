package linkedhashset;


import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @invar The set has no null elements.
 *     | Arrays.stream(toArray()).allMatch(e -> e != null)
 * @invar {@code toArray()} does not contain duplicates.
 *     | Arrays.stream(toArray()).distinct().count() == size()
 */
public interface Set {

	/**
	 * @inspects | this
	 * @creates | result
	 * @post | result != null
	 */
	Object[] toArray();
	
	default Stream<Object> stream() { return Arrays.stream(toArray()); } 

	/**
	 * @inspects | this
	 * @post | result == toArray().length
	 */
	int size();

	/**
	 * @pre | value != null
	 * @inspects | this
	 * @post | result == Arrays.stream(toArray()).anyMatch(e -> e.equals(value))
	 */
	boolean contains(Object value);
	
	/**
	 * @pre | value != null
	 * @mutates | this
	 * @post The given value is in the set.
	 *       | Arrays.stream(toArray()).anyMatch(e -> e.equals(value))
	 * @post No elements have disappeared from the set.
	 *       | Arrays.stream(old(toArray())).allMatch(eo ->
	 *       |     Arrays.stream(toArray()).anyMatch(e -> e.equals(eo)))
	 * @post No elements, other than the given value, have been added.
	 *       | Arrays.stream(toArray()).allMatch(e -> e.equals(value) ||
	 *       |     Arrays.stream(old(toArray())).anyMatch(eo -> e.equals(eo)))
	 */
	void add(Object value);

	/**
	 * @pre | value != null
	 * @mutates | this
	 * @post The given value is no longer in the set.
	 *       | Arrays.stream(toArray()).noneMatch(e -> e.equals(value))
	 * @post No elements, other than the given value, have disappeared
	 *       from the set.
	 *       | Arrays.stream(old(toArray())).allMatch(eo -> eo.equals(value) ||
	 *       |     Arrays.stream(toArray()).anyMatch(e -> e.equals(eo)))
	 * @post No elements have been added to the set.
	 *       | Arrays.stream(toArray()).allMatch(e ->
	 *       |     Arrays.stream(old(toArray())).anyMatch(eo -> e.equals(eo)))
	 */
	void remove(Object value);
	
}