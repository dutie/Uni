package interval2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IntervalTest {

	@Test
	void test() {
		int lb = 5;
		int ub = 12;
		int w = 7;
		Interval interval = new Interval(lb, ub, null);
		assertEquals(interval.getWidth(),w );
	}

}
