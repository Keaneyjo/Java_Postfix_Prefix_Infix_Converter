import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ArithTest {

	@Test	  	
     	public void testArith()	  	
     	{	  	
       		new Arith();	  	
     	}
	@Test
	public void validatePrefixOrderTest() {
		String [] arr = {};
		assertEquals("validatePrefixOrderTest; Empty Case", Arith.validatePrefixOrder(arr), false);
		arr = new String [] {"+", "+", "4", "*", "5", "4", "2"};
		assertEquals("validatePrefixOrderTest; Correct Case", Arith.validatePrefixOrder(arr), true);
		arr[6] = "+"; // {"+", "+", "A", "*", "B", "C", "+"};
		assertEquals("validatePrefixOrderTest; Correct Case", Arith.validatePrefixOrder(arr), false);
		arr = new String [] { "+", "+", "-", "4", "4", "2", "*", "/", "6", "3", "2" }; // + + - 4 4 2 * / 6 3 2 = 6
		assertEquals("evaluatePrefixOrder; Correct Case", Arith.validatePrefixOrder(arr), true);
		arr = new String [] { "+", "3", "4", "3" }; // + + - 4 4 2 * / 6 3 2 = 6
		assertEquals("evaluatePrefixOrder; Correct Case", Arith.validatePrefixOrder(arr), false);
		arr = new String [] { "3", "3", "-", "3" }; // + + - 4 4 2 * / 6 3 2 = 6
		assertEquals("evaluatePrefixOrder; Correct Case", Arith.validatePrefixOrder(arr), false);
		
	}
	
	@Test
	public void validatePostfixOrderTest() {
		String [] arr = {};
		assertEquals("validatePostfixOrder; Empty Case", Arith.validatePostfixOrder(arr), false);
		arr = new String [] {"3", "4", "5", "*", "+", "2", "+"};
		assertEquals("validatePostfixOrder; Correct Case", Arith.validatePostfixOrder(arr), true);
		arr[6] = "6"; // {"+", "+", "A", "*", "B", "C", "+"};
		assertEquals("validatePostfixOrder; Correct Case", Arith.validatePostfixOrder(arr), false);
		arr = new String [] { "5", "5", "/", "10", "2", "-", "*" }; // 5 5 / 10 2 - * = 8
		assertEquals("evaluatePrefixOrder; Divide Case", Arith.validatePostfixOrder(arr), true);
	}
	
	@Test
	public void evaluatePrefixOrderTest() {
		String [] arr = {};
		assertEquals("evaluatePrefixOrder; Empty Case", Arith.evaluatePrefixOrder(arr), 0);
		arr = new String [] { "+", "*", "-", "1", "2", "3", "-", "10", "+", "3", "/", "6", "3" };
		assertEquals("evaluatePrefixOrder; Correct Case", Arith.evaluatePrefixOrder(arr), 2);
		arr = new String [] { "-", "+", "7", "*", "4", "5", "+", "2", "0" };
		assertEquals("evaluatePrefixOrder; Correct Case", Arith.evaluatePrefixOrder(arr), 25);
		arr = new String [] { "+", "+", "-", "4", "4", "2", "*", "/", "6", "3", "2" }; // + + - 4 4 2 * / 6 3 2 = 6
		assertEquals("evaluatePrefixOrder; Correct Case", Arith.evaluatePrefixOrder(arr), 6);
	}
	
	@Test
	public void evaluatePostfixOrderTest() {
		String [] arr = {};
		assertEquals("evaluatePrefixOrder; Empty Case", Arith.evaluatePostfixOrder(arr), 0);
		arr = new String [] { "4", "55", "+", "62", "23", "-", "*" };
		assertEquals("evaluatePrefixOrder; Correct Case", Arith.evaluatePostfixOrder(arr), 2301);
		arr = new String [] { "5", "5", "/", "10", "2", "-", "*" }; // 5 5 / 10 2 - * = 8
		assertEquals("evaluatePrefixOrder; Divide Case", Arith.evaluatePostfixOrder(arr), 8);
		
	}
	
	@Test
	public void convertPrefixToPostfixTest() {
		String [] arr = {};
		assertEquals("convertPrefixToPostfix; Empty Case", Arith.convertPrefixToPostfix(arr).toString(), arr.toString());
		arr = new String [] { "*", "-", "A", "/", "B", "C", "-", "/", "A", "K", "L" };
		String [] test = new String [] { "A", "B", "C", "/", "-", "A", "K", "/", "L", "-", "*" };
		arr = Arith.convertPrefixToPostfix(arr);
		assertArrayEquals("convertPrefixToPostfix; Correct Case", test, arr);
		// "ABC/-AK/L-*"
		arr = new String [] { "+", "A", "/", "B", "*", "C", "D" }; // Prefix
		test = new String [] { "A", "B", "C", "D", "*", "/", "+" }; // Postfix
		arr = Arith.convertPrefixToPostfix(arr);
		assertArrayEquals("convertPostfixToPrefix; Plus and Multiplied Case", test, arr);
	}
	
	@Test
	public void convertPostfixToPrefixTest() {
		String [] arr = {};
		assertEquals("convertPostfixToPrefix; Empty Case", Arith.convertPostfixToPrefix(arr).toString(), arr.toString());
		arr = new String [] { "A", "B", "C", "/", "-", "A", "K", "/", "L", "-", "*" };
		String [] test = new String [] {"*", "-", "A", "/", "B", "C", "-", "/", "A", "K", "L"};
		arr = Arith.convertPostfixToPrefix(arr);
		assertArrayEquals("convertPostfixToPrefix; Correct Case", test, arr);
		arr = new String [] { "A", "B", "C", "D", "*", "/", "+" }; // Postfix
		test = new String [] { "+", "A", "/", "B", "*", "C", "D" }; // Prefix
		arr = Arith.convertPostfixToPrefix(arr);
		assertArrayEquals("convertPostfixToPrefix; Plus and Multiplied Case", test, arr);
		
	}
	
	@Test
	public void convertPrefixToInfixTest() {
		String [] arr = {};
		assertEquals("convertPrefixToInfix; Empty Case", Arith.convertPrefixToInfix(arr).toString(), arr.toString());
		arr = new String [] { "*", "-", "A", "/", "B", "C", "-", "/", "A", "K", "L" };
		String [] test = new String [] { "(", "(", "A", "-", "(", "B", "/", "C", ")", ")", "*", "(", "(", "A", "/", "K", ")", "-", "L", ")", ")" };
		arr = Arith.convertPrefixToInfix(arr);
		assertArrayEquals("convertPostfixToPrefix; Correct Case", test, arr);
		//"((A-(B/C))*((A/K)-L))"
		arr = new String [] { "+", "A", "/", "B", "*", "C", "D" };
		test = new String [] { "(", "A", "+", "(", "B", "/", "(", "C", "*", "D", ")", ")", ")" };
		arr = Arith.convertPrefixToInfix(arr);
		assertArrayEquals("convertPostfixToPrefix; Plus and Multiplied Case", test, arr);
	}
	
	@Test
	public void convertPostfixToInfixTest() {
		String [] arr = {};
		assertEquals("convertPrefixToInfix; Empty Case", Arith.convertPostfixToInfix(arr).toString(), arr.toString());
		arr = new String [] { "A", "B", "C", "/", "-", "A", "K", "/", "L", "-", "*" };
		String [] test = new String [] { "(", "(", "A", "-", "(", "B", "/", "C", ")", ")", "*", "(", "(", "A", "/", "K", ")", "-", "L", ")", ")" };
		arr = Arith.convertPostfixToInfix(arr);
		assertArrayEquals("convertPostfixToPrefix; Correct Case", test, arr);
		// PO: a b c d * / +
		// IN: (A + (B / (C * D)))
		arr = new String [] { "A", "B", "C", "D", "*", "/", "+" };
		test = new String [] { "(", "A", "+", "(", "B", "/", "(", "C", "*", "D", ")", ")", ")" };
		arr = Arith.convertPostfixToInfix(arr);
		assertArrayEquals("convertPostfixToPrefix; Plus and Multiplied Case", test, arr);
		
	}
}
