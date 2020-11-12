package collections;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @invar | Arrays.stream(toArray()).allMatch(e -> e != null)
 */
public interface List {
	
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
	 * @pre | 0 <= index
	 * @pre | index < size()
	 * @post | result == toArray()[index]
	 */
	Object get(int index);

	/**
	 * @pre | value != null
	 * @inspects | this
	 * @post | result == Arrays.stream(toArray()).anyMatch(e -> e.equals(value))
	 */
	boolean contains(Object value);
	
	/**
	 * @pre | 0 <= index
	 * @pre | index <= size()
	 * @pre | value != null
	 * @mutates | this
	 * @post | size() == old(size()) + 1
	 * @post | Arrays.equals(toArray(), 0, index, old(toArray()), 0, index)
	 * @post | Arrays.equals(toArray(), index + 1, size(),
	 *       |     old(toArray()), index, old(size()))
	 * @post | get(index).equals(value)
	 */
	void add(int index, Object value);
	
	/**
	 * @pre | value != null
	 * @mutates | this
	 * @post | size() == old(size()) + 1
	 * @post | Arrays.equals(toArray(), 0, old(size()),
	 *       |     old(toArray()), 0, old(size()))
	 * @post | get(old(size())).equals(value)
	 */
	default void add(Object value) { add(size(), value); }
	
	/**
	 * @pre | 0 <= index
	 * @pre | index < size()
	 * @mutates | this
	 * @post | Arrays.equals(toArray(), 0, index, old(toArray()), 0, index)
	 * @post | Arrays.equals(toArray(), index, size(),
	 *       |     old(toArray()), index + 1, old(size()))
	 */
	void remove(int index);

	/**
	 * @pre | value != null
	 * @mutates | this
	 * @post | Arrays.equals(toArray(),
	 *       |     IntStream.range(0, old(size()))
	 *       |         .filter(i -> i == old(IntStream.range(0, size())
	 *       |             .filter(i -> get(i).equals(value))
	 *       |             .findFirst().orElse(-1)))
	 *       |         .mapToObj(i -> old(toArray())[i]).toArray())
	 */
	void remove(Object value);
	
}