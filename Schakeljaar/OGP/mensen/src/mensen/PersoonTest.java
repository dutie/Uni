package mensen;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class PersoonTest {

	@Test
	void test() {
		Persoon Marie = new Persoon();
		Persoon Caro = new Persoon();
		
		Persoon Mats = new Persoon();
		Persoon Milan = new Persoon();
		
		Persoon Werner = new Persoon();
		Persoon Hans = new Persoon();
		
		Persoon Jos = new Persoon();
		Set<Persoon> t = new HashSet<Persoon>();
		t.add(Marie);
		
		Marie.setVader(Hans);
		assertEquals(Set.of(Marie), Hans.getKinderen());
		Hans.addKind(Caro);
		assertEquals(Hans, Caro.getVader());
		
		Milan.setVader(Werner);
		assertEquals(Set.of(Milan), Werner.getKinderen());
		Werner.setVader(Jos);
		assertEquals(Jos, Werner.getVader());
		Werner.addKind(Mats);
		
		assertEquals(Set.of(Mats,Milan), Werner.getKinderen());
	}

}
