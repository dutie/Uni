package interval;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

@SuppressWarnings("unused")
class IntervalTest {

	@Test
	void test() {
		Interval interval = new Interval(3, 4, null);

		interval.setUpperBound(8);
		assert interval.getLowerBound() == 3;
	}


}