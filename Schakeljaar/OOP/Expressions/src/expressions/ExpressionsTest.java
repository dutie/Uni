package expressions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class ExpressionsTest {

	@Test
	void test() {
		Expression e1 = new AddExpression(new StringLiteral("Hello"), new StringLiteral(" world"));
		Literal v1 = Expression.evaluate(e1);
		assertTrue(v1 instanceof StringLiteral);
		
		Expression e2 = new AddExpression(new IntLiteral(10), new IntLiteral(20));
		Expression e3 = new AddExpression(e2, new StringLiteral(" = 30?"));
		
		assertTrue(((StringLiteral)Expression.evaluate(e3)).value.equals("30 = 30?"));
	}

}