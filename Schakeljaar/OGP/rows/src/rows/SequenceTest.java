package rows;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SequenceTest {

	@Test
	void test() {
		// Minimal test suite that tests all statements
		EmptySequence empty = new EmptySequence();
		assertEquals(0, empty.getLength());
		assertEquals(empty, new EmptySequence());
		
		NonEmptySequence c = new NonEmptySequence("c", empty);
		assertEquals("c", c.getHead());
		assertEquals(empty, c.getTail());
		assertEquals(1, c.getLength());
		assertEquals(c, new NonEmptySequence("c", new EmptySequence()));
		assertNotEquals(empty, c);
		assertNotEquals(c, empty);
		assertNotEquals(c, new NonEmptySequence("b", new EmptySequence()));
		assertNotEquals(c, new NonEmptySequence("c", new NonEmptySequence("d", new EmptySequence())));
	}

}
