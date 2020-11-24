package examroomtest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

import examroom.Exam;
import examroom.Room;

class ExamRoomTest {

	@Test
	void test() {
		Exam ogp = new Exam();
		Exam bri = new Exam();
		Exam ai = new Exam();
		
		Room a1 = new Room();
		Room a2 = new Room();
		Room b1 = new Room();
		
		ogp.addRoom(a1);
		bri.addRoom(a1);
		bri.addRoom(a2);
		ai.addRoom(b1);
		ai.addRoom(a1);
		
		assertEquals(Set.of(a1, a2), bri.getRooms());
		assertEquals(Set.of(ogp,bri,ai), a1.getExams());
		
		bri.removeRoom(a1);
		
		assertEquals(Set.of(ogp,ai), a1.getExams());
	}

}
