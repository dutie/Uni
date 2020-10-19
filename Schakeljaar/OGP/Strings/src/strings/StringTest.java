package strings;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StringTest {

	@Test
	void test() {
		char[] chars = new char[] {'H','i', '!'};
		String s = String.valueOf(chars);
				
		assertEquals(3, s.length());
		assertEquals('H', s.charAt(0));
		assertEquals('i', s.charAt(1));
		assertEquals('!', s.charAt(2));
		
		char[] array = s.toCharArray();
		array[0] = 'B';
		assertEquals('H', s.charAt(0));
		
		chars[0] = 'B';
		assertEquals('H', s.charAt(0));
	}

}
