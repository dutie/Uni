package stringLists;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class StringListTest {

	@Test
	void test() {
		String[] strings = {"Hello", "Bye"};
		StringList list = new StringList(strings);
		
		assertEquals(8,list.getSumOfLengths());
		
		strings[0] = null;
	}

}
