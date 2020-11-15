package portals;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PortalTest {

	@Test
	void test() {
		// set up portals
		Transformation twoThirds = new TwoThirdsTransformation();
		Transformation threeQuarters = new ThreeQuartersTransformation();
		Portal p1 = new Portal(twoThirds);
		Portal p2 = new Portal(threeQuarters);
		Portal p3 = new Portal(twoThirds);
		
		// link up portal 1 and 3
		p1.setPair(p3);
		// test if portals are linked up
		assertEquals(p1, p3.getPair());
		
		// link up portal 1 and 2
		// if overriding is not allowed, first:
		p1.removePair();
		
		p1.setPair(p2);
		
		// test if portals are linked up only with each other
		assertEquals(p2, p1.getPair());
		assertEquals(p1, p2.getPair());
		assertNotEquals(p1,p3.getPair());
		
		
		// set up sizes of objects in cm
		int personSize = 180;
		int doorSize = 210;
		
		// move object through portal
		int transformedPersonSize = p1.transform(personSize);
		int transformedDoorSize = p2.transform(doorSize);
		
		assertEquals(120,transformedPersonSize );
		assertEquals(158, transformedDoorSize);
		
		// remove pairs of portal 1
		p1.removePair();
		
		assertEquals(null, p1.getPair());
		assertEquals(null, p2.getPair());
		
		
	}

}
