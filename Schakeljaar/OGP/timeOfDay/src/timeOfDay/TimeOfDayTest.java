package timeOfDay;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TimeOfDayTest {

	@Test
	void test() {
		int minutes = 15;
		int hours = 12;
		TimeOfDay time = new TimeOfDay(hours, minutes);
		
		assertEquals(time.getHours(), 12);
	
	}

}
