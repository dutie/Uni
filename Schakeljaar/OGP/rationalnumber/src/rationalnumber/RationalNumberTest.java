package rationalnumber;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RationalNumberTest {

	@Test
	void test() {
		int d = 10;
		int n = 5;
		
		RationalNumber rn = new RationalNumber(d, n);
		
		assertEquals(rn.lowest(), "1/2");
	}

}
