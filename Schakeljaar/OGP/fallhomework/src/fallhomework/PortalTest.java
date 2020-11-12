package fallhomework;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PortalTest {

	@Test
	void test() {
		Portal p1 = new Portal();
		Portal p2 = new ThreeQuartersTransformation();
		
		p1.setPair(p2);
		
		assertEquals(2, p1.apply(3));
		assertEquals(3, p2.apply(4));
		
		
	}

}
