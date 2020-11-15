package linkedhashset;


import java.util.Arrays;
import java.util.stream.IntStream;

public class HashSet implements Set {
	
	/**
	 * @invar | buckets != null
	 * @invar | Arrays.stream(buckets).allMatch(b -> b != null)
	 * @invar | IntStream.range(0, buckets.length).allMatch(i ->
	 *        |     buckets[i].stream().allMatch(e ->
	 *        |         Math.floorMod(e.hashCode(), buckets.length) == i))
	 * 
	 * @representationObject
	 * @representationObjects Each bucket is a representation object
	 */
	private Set[] buckets;
	
	private Set getBucket(Object value) {
		return buckets[Math.floorMod(value.hashCode(), buckets.length)];
	}
	
	/**
	 * @pre | 0 < capacity
	 * @post | size() == 0
	 */
	public HashSet(int capacity) {
		buckets = new Set[capacity];
		for (int i = 0; i < buckets.length; i++)
			buckets[i] = new ArraySet();
	}

	public Object[] toArray() {
		Object[] result = new Object[size()];
		int offset = 0;
		for (int i = 0; i < buckets.length; i++) {
			Object[] bucketElements = buckets[i].toArray();
			System.arraycopy(bucketElements, 0,
				result, offset, bucketElements.length);
			offset += bucketElements.length;
		}
		return result;
	}

	public int size() {
		return Arrays.stream(buckets).mapToInt(b -> b.size()).sum();
	}

	public boolean contains(Object value) {
		return getBucket(value).contains(value);
	}

	public void add(Object value) {
		getBucket(value).add(value);
	}

	public void remove(Object value) {
		getBucket(value).remove(value);
	}

}