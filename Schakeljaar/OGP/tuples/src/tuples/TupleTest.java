package tuples;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;

import org.junit.jupiter.api.Test;

class TupleTest {

	@Test
	void test() {
		Object[] obj = new Objects[2];
		
		Tuple tuple = new Tuple(obj);
		assertEquals(2, tuple.getLength());
		
		assertEquals(null, tuple.getElement(0));
		assertEquals("tuples.Tuple@3e0", tuple.toString());
	
		Object[] obj2 = new Objects[3];
		Object[] obj3 = new Objects[2];
 		assertEquals(false, tuple.equals(obj2));
		assertEquals(false, tuple.equals(obj3));

		Tuple tuple2 = new Tuple(obj2);
		Tuple tuple3 = new Tuple(obj3);
 		assertEquals(false, tuple.equals(tuple2));
 		assertEquals(true, tuple.equals(tuple3));
	}

}
