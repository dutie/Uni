package linkedhashset;

import java.util.Arrays;
import java.util.stream.IntStream;

public class HashMap implements Map {
/**
 * @invar | buckets != null
 * @invar | Arrays.stream(buckets).allMatch(b -> b != null)
 * @invar | IntStream.range(0, buckets.length).allMatch(i ->
 *        |     buckets[i].entrySet().stream().allMatch(e ->
 *        |         Math.floorMod(((Entry)e).getKey().hashCode(),
 *        |             buckets.length) == i))
 * 
 * @representationObject
 * @representationObjects
 */
private Map[] buckets;

private Map getBucket(Object key) {
	return buckets[Math.floorMod(key.hashCode(), buckets.length)];
}

public Set entrySet() {
	ArraySet result = new ArraySet();
	for (int i = 0; i < buckets.length; i++)
		for (Object entry : buckets[i].entrySet().toArray())
			result.add(entry);
	return result;
}

public Object get(Object key) {
	return getBucket(key).get(key);
}

public boolean contains(Object object) {
	return getBucket(object).contains(object);
}

/**
 * @pre | 0 < capacity
 * @post | entrySet().size() == 0
 */
public HashMap(int capacity) {
	buckets = new Map[capacity];
	for (int i = 0; i < capacity; i++)
		buckets[i] = new ArrayMap();
}

public void put(Object key, Object value) {
	getBucket(key).put(key, value);
}

public void remove(Object key) {
	getBucket(key).remove(key);
}

}