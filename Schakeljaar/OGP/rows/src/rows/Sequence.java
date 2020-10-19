package rows;

import java.util.Objects;

public abstract class Sequence {

	public abstract int getLength();
}

class NonEmptySequence extends Sequence {
	private Sequence tail;
	private Object head;

	public Sequence getTail() {
		return tail;
	}

	public Object getHead() {
		return head;
	}

	@Override
	public int getLength() {
		return 1 + tail.getLength();
	}

	public NonEmptySequence(Object head, Sequence tail) {
		if (tail == null)
			throw new IllegalArgumentException("Tail is null!");
		this.head = head;
		this.tail = tail;
	}

	public Sequence of(Object head, Sequence tail) {
		return new NonEmptySequence(head, tail);
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof NonEmptySequence))
			return false;
		NonEmptySequence otherSequence = (NonEmptySequence) other;
		return Objects.equals(head, otherSequence.head) && tail.equals(otherSequence.tail);
	}
}

class EmptySequence extends Sequence {

	@Override
	public int getLength() {
		return 0;
	}

	@Override
	public boolean equals(Object other) {
		return other instanceof EmptySequence;
	}
}
