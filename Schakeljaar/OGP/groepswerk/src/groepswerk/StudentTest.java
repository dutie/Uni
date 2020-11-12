package groepswerk;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StudentTest {

	@Test
	void test() {
		Student an = new Student();
		Student bert = new Student();
		// an will not encap bert
		an.setTeammate(bert);
		assertEquals(bert,  an.getTeammate());
		assertEquals(an, bert.getTeammate());
		
		bert.clearTeammate();
		assertEquals(null, bert.getTeammate());
		assertEquals(null, an.getTeammate());
	}

}
