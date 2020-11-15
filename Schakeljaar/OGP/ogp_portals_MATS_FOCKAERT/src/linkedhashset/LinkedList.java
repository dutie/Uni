package linkedhashset;


public class LinkedList implements List {
	
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
	
	/**
	 * @post | size() == 0
	 */
	public LinkedList() {
		sentinel = new Node();
		sentinel.previous = sentinel;
		sentinel.next = sentinel;
	}

	public Object[] toArray() {
		Object[] result = new Object[size];
		int i = 0;
		for (Node node = sentinel.next; node != sentinel; node = node.next)
			result[i++] = node.element;
		return result;
	}

	public int size() {
		return size;
	}

	public Object get(int index) {
		return getNode(index).element;
	}

	public boolean contains(Object value) {
		for (Node node = sentinel.next; node != sentinel; node = node.next)
			if (node.element.equals(value))
				return true;
		return false;
	}

	public void add(int index, Object value) {
		Node next = getNode(index);
		Node node = new Node();
		node.element = value;
		node.next = next;
		node.previous = next.previous;
		node.next.previous = node;
		node.previous.next = node;
		size++;
	}

	public void add(Object value) {
		add(size, value);
	}

	public void remove(int index) {
		Node node = getNode(index);
		node.next.previous = node.previous;
		node.previous.next = node.next;
		size--;
	}

	public void remove(Object value) {
		Node node = sentinel.next;
		for (;;) {
			if (node == sentinel)
				return;
			if (node.element.equals(value)) {
				node.next.previous = node.previous;
				node.previous.next = node.next;
				size--;
				return;
			}
			node = node.next;
		}
	}

}

