package linkedhashset;

import java.util.Arrays;
import java.util.stream.IntStream;

public class LinkedHashSet implements Set {
	
	private class Node {
		/**
		 * @invar | (element == null) == (this == sentinel)  
		 * @invar | previous != null
		 * @invar | next != null
		 * @invar | next.previous == this
		 * @invar | previous.next == this
		 * 
		 * @peerObject
		 */
		private Node previous;
		private Object element;
		/** @peerObject */
		private Node next;
		
		private int getLength() { return this == sentinel ? 0 : 1 + next.getLength(); }
	}
	
	/**
	 * @invar | sentinel != null
	 * @invar | size == sentinel.next.getLength()
	 */
	private int size;
	/** @representationObject */
	private Node sentinel;
	
	private Node getNode(int index) {
		Node node = sentinel;
		if (index <= size / 2)
			for (; index >= 0; index--)
				node = node.next;
		else
			for (; index < size; index++)
				node = node.previous;
		return node;
	}
	
	// HashMap
	HashMap map = new HashMap(20);
	
	@Override
	// not possible in constant time
	public Object[] toArray() {
		Object[] result = new Object[size];
		int i = 0;
		for (Node node = sentinel.next; node != sentinel; node = node.next)
			result[i++] = node.element;
		return result;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean contains(Object value) {
		map.contains(value);
	}

	@Override
	public void add(Object value) {
		Node node = new Node();
		node.element = value;
		node.next = null;
		node.previous = sentinel.previous;
		sentinel.previous = node;
		node.previous.next = node;
		size++;
		map.put(value, node);
	}


	@Override
	public void remove(Object object) {
		Node node = map.get(object);

		if (node.previous == null) {
			node.next.previous = null;
			sentinel.next = node.next;
		} else if (node.next == null) {
			node.previous.next = null;
			sentinel.previous = node.previous;
		} else {
			node.previous.next = node.next;
			node.next.previous = node.previous;
			// node will be deleted by garbage collector
		}
		size--;
		map.remove(object);
	}


}
