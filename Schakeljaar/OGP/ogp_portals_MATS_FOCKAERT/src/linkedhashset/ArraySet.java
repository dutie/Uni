package linkedhashset;

public class ArraySet implements Set {
	
	/**
	 * @invar | elements != null
	 * @invar | elements.stream().distinct().count() == elements.size()
	 * 
	 * @representationObject
	 */
	private ArrayList elements = new ArrayList();
	
	/** @post | size() == 0 */
	public ArraySet() {}

	public Object[] toArray() { return elements.toArray(); }

	public int size() { return elements.size(); }

	public boolean contains(Object value) { return elements.contains(value); }

	public void add(Object value) {
		if (elements.contains(value))
			return;
		elements.add(value);
	}

	public void remove(Object value) { elements.remove(value); }

}