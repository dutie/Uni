package linkedhashset;

public class ArrayMap implements Map {

	/**
	 * @invar | entries != null
	 * @invar | entries.stream().allMatch(e -> e instanceof Entry)
	 * @invar | entries.stream().map(e -> ((Entry)e).getKey()).distinct().count()
	 *        | == entries.size()
	 * 
	 * @representationObject
	 */
	private ArrayList entries = new ArrayList();
	
	private int indexOf(Object key) {
		for (int i = 0; i < entries.size(); i++) {
			Entry entry = (Entry)entries.get(i);
			if (entry.getKey().equals(key))
				return i;
		}
		return -1;
	}
	
	public Set entrySet() {
		Set result = new ArraySet();
		for (int i = 0; i < entries.size(); i++)
			result.add(entries.get(i));
		return result;
	}

	public Object get(Object key) {
		int index = indexOf(key);
		return index == -1 ? null : ((Entry)entries.get(index)).getValue();
	}
	
	/**
	 * @post | entrySet().size() == 0
	 */
	public ArrayMap() {}

	public void put(Object key, Object value) {
		int index = indexOf(key);
		if (index != -1)
			entries.remove(index);
		entries.add(new Entry(key, value));
	}
	
	public void remove(Object key) {
		int index = indexOf(key);
		if (index != -1)
			entries.remove(index);
	}

	// self written
	public boolean contains(Object object) {
		entries.contains(object);
	}
	

}
