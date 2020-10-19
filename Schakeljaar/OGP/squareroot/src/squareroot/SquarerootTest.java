  
package squareroot;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

@SuppressWarnings("unused")
class SquarerootTest {
	
	@Test
	void test() {
		assert Squareroot.squareRoot(0) == 0;
		assert Squareroot.squareRoot(9) == 3;
		assert Squareroot.squareRoot(10) == 3;
		assert Squareroot.squareRoot(16) == 4;
	}

}