import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ArithTest {

	@Test
	public void validatePrefixOrderTest() {
		String [] arr = {};
		assertEquals("validatePrefixOrderTest; Empty Case", Arith.validatePrefixOrder(arr), false);
		arr = new String [] {"+", "+", "4", "*", "5", "4", "2"};
		assertEquals("validatePrefixOrderTest; Correct Case", Arith.validatePrefixOrder(arr), true);
		arr[6] = "+"; // {"+", "+", "A", "*", "B", "C", "+"};
		assertEquals("validatePrefixOrderTest; Correct Case", Arith.validatePrefixOrder(arr), false);
	}
	
	@Test
	public void validatePostfixOrderTest() {
		String [] arr = {};
		assertEquals("validatePostfixOrder; Empty Case", Arith.validatePostfixOrder(arr), false);
		arr = new String [] {"3", "4", "5", "*", "+", "2", "+"};
		assertEquals("validatePostfixOrder; Correct Case", Arith.validatePostfixOrder(arr), true);
		arr[6] = "6"; // {"+", "+", "A", "*", "B", "C", "+"};
		assertEquals("validatePostfixOrder; Correct Case", Arith.validatePostfixOrder(arr), false);
	}
	
	@Test
	public void evaluatePrefixOrderTest() {
		String [] arr = {};
		assertEquals("evaluatePrefixOrder; Empty Case", Arith.evaluatePrefixOrder(arr), 0);
		arr = new String [] { "+", "*", "-", "1", "2", "3", "-", "10", "+", "3", "/", "6", "3" };
		assertEquals("evaluatePrefixOrder; Correct Case", Arith.evaluatePrefixOrder(arr), 2);
		arr = new String [] { "-", "+", "7", "*", "4", "5", "+", "2", "0" };
		assertEquals("evaluatePrefixOrder; Correct Case", Arith.evaluatePrefixOrder(arr), 25);
	}
	
	@Test
	public void evaluatePostfixOrderTest() {
		String [] arr = {};
		assertEquals("evaluatePrefixOrder; Empty Case", Arith.evaluatePostfixOrder(arr), 0);
		arr = new String [] { "4", "55", "+", "62", "23", "-", "*" };
		assertEquals("evaluatePrefixOrder; Correct Case", Arith.evaluatePostfixOrder(arr), 2301);
	}
	
	@Test
	public void convertPrefixToPostfixTest() {
		String [] arr = {};
		assertEquals("convertPrefixToPostfix; Empty Case", Arith.convertPrefixToPostfix(arr).toString(), arr.toString());
		arr = new String [] { "*", "-", "A", "/", "B", "C", "-", "/", "A", "K", "L" };
		String test = "";
		arr = Arith.convertPrefixToPostfix(arr);
		for(int i = 0; i < arr.length; i++)
		{
			test = test + arr[i];
		}
		assertEquals("convertPrefixToPostfix; Correct Case", test, "ABC/-AK/L-*");
	}
	
	@Test
	public void convertPostfixToPrefixTest() {
		String [] arr = {};
		assertEquals("convertPostfixToPrefix; Empty Case", Arith.convertPostfixToPrefix(arr).toString(), arr.toString());
		arr = new String [] { "A", "B", "C", "/", "-", "A", "K", "/", "L", "-", "*" };
		String test = "";
		arr = Arith.convertPostfixToPrefix(arr);
		for(int i = 0; i < arr.length; i++)
		{
			test = test + arr[i];
		}
		assertEquals("convertPostfixToPrefix; Correct Case", test, "*-A/BC-/AKL");
	}
	
	@Test
	public void convertPrefixToInfixTest() {
		String [] arr = {};
		assertEquals("convertPrefixToInfix; Empty Case", Arith.convertPrefixToInfix(arr).toString(), arr.toString());
		arr = new String [] { "*", "-", "A", "/", "B", "C", "-", "/", "A", "K", "L" };
		String test = "";
		arr = Arith.convertPrefixToInfix(arr);
		for(int i = 0; i < arr.length; i++)
		{
			test = test + arr[i];
		}
		assertEquals("convertPostfixToPrefix; Correct Case", test, "((A-(B/C))*((A/K)-L))");
	}
	
	@Test
	public void convertPostfixToInfixTest() {
		String [] arr = {};
		assertEquals("convertPrefixToInfix; Empty Case", Arith.convertPostfixToInfix(arr).toString(), arr.toString());
		arr = new String [] { "A", "B", "C", "/", "-", "A", "K", "/", "L", "-", "*" };
		String test = "";
		arr = Arith.convertPostfixToInfix(arr);
		for(int i = 0; i < arr.length; i++)
		{
			test = test + arr[i];
		}
		assertEquals("convertPostfixToPrefix; Correct Case", test, "((A-(B/C))*((A/K)-L))");
	}
}
