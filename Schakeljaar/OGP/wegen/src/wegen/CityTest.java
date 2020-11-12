package wegen;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

class CityTest {

	@Test
	void test() {
		City leuven = new City();
		City mechelen = new City();
		City antwerpen = new City();
		assertTrue(leuven.getConnectedCities().isEmpty());
		
		leuven.registerRoad(mechelen);
		antwerpen.registerRoad(mechelen);
		
		assertEquals(Set.of(leuven, antwerpen), mechelen.getConnectedCities());
	}

}
