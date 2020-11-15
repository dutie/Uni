package linkedhashset;

import java.util.Arrays;

public class ArrayList implements List {

	/**
	 * @invar | elements != null
	 * @invar | 0 <= size
	 * @invar | size <= elements.length
	 * @invar | Arrays.stream(elements, 0, size).allMatch(e -> e != null)
	 * @invar | Arrays.stream(elements, size, elements.length)
	 *        |     .allMatch(e -> e == null)
	 * 
	 * @representationObject
	 */
	private Object[] elements = new Object[10];
	private int size;
	
	/**
	 * @post | size() == 0
	 */
	public ArrayList() {}
	
	public Object[] toArray() {
		return Arrays.copyOf(elements, size);
	}

	public int size() {
		return size;
	}

	public Object get(int index) {
		return elements[index];
	}

	private int indexOf(Object value) {
		for (int i = 0; i < size; i++)
			if (elements[i].equals(value))
				return i;
		return -1;
	}

	public boolean contains(Object value) {
		return indexOf(value) != -1;
	}

	public void add(int index, Object value) {
		if (elements.length == size) {
			Object[] newElements = new Object[elements.length * 2];
			System.arraycopy(elements, 0, newElements, 0, size);
			elements = newElements;
		}
		System.arraycopy(elements, index, elements, index + 1, size - index);
		elements[index] = value;
		size++;
	}

	public void remove(int index) {
		size--;
		System.arraycopy(elements, index + 1, elements, index, size - index);
		elements[size] = null;
	}

	public void remove(Object value) {
		int index = indexOf(value);
		if (index != -1)
			remove(index);
	}

}
