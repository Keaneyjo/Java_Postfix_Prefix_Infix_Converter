import java.util.Stack;

// -------------------------------------------------------------------------
/**
 *  Utility class containing validation/evaluation/conversion operations
 *  for prefix and postfix arithmetic expressions.
 *
 *  @author John Keaney (Student ID: 18328855)
 *  @version 1/12/15 13:03:48
 */

class Arith 
{
	// I  have explained my reasoning why I believe my methods are optimal in the each of the respective methods. Please note that most methods
	// are very similar and as such the explanations are mostly the same.
	
	
	//~ Validation methods ..........................................................
	
	/**
	 * Validation method for prefix notation.
	 * 
	 * validatePrefixOrder 0(n) - one for loop which gives up our n, other operations are lower order and not taken into account
	 * 
	 * I believe my method is optimal as it only uses an integer intialisation which is necessary. I don't believe it would be possible to get a smaller
	 * 0() as it is necessary to look through the entire expression. 
	 * 
	 * @param prefixLiterals : an array containing the string literals hopefully in prefix order.
	 * The method assumes that each of these literals can be one of:
	 * - "+", "-", "*", or "/"
	 * - or a valid string representation of an integer.
	 * 
	 * @return true if the parameter is indeed in prefix notation, and false otherwise.
	 **/
	public static boolean validatePrefixOrder(String prefixLiterals[])
	{
		/*
		 To validate an arithmetic expression in prefix notation we can use a single counter:
			 start the counter at 1
			 read each character of the expression, from left to right
			 if we read an operator (+,-,*,/) then we increment the counter by one
			 if we read a number then we decrement the counter by one.
			 If the counter at the end of the sequence is 0 and it never became negative nor zero during scanning 
			 then the sequence is valid. (If the "nor zero" part was missing then the non-valid prefix string "+ 2 3 - 4" 
			 would be flagged as valid.)
		*/
		int count = 1;
		for(int i = 0; i < prefixLiterals.length; i++) 
		{
			if(prefixLiterals[i].equals("+") || prefixLiterals[i].equals("-") || prefixLiterals[i].equals("*") || prefixLiterals[i].equals("/"))
			{
				count++;
			}
			else
			{
				count--;
			}
			if(i != (prefixLiterals.length-1) && (count <= 0))
			{
				return false;
			}
		}
		if(count == 0) {return true;}
		else {return false;}
		
	}

	/**
	 * Validation method for postfix notation.
	 *
	 * validatePostfixOrder: 0(n) - same as above; one for loop which gives up our n, other operations are lower order and not taken into account
	 *
	 * I used Stacks in this method as it would decrease my running time compared to working with a String []. I don't believe it would be possible to
	 * get a smaller running time as it is necessary to look through the entire array.
	 *
	 * @param postfixLiterals : an array containing the string literals hopefully in postfix order.
	 * The method assumes that each of these literals can be one of:
	 * - "+", "-", "*", or "/"
	 * - or a valid string representation of an integer.
	 *
	 * @return true if the parameter is indeed in postfix notation, and false otherwise.
	 **/
	public static boolean validatePostfixOrder(String postfixLiterals[])
	{
		/*
		To evaluate an arithmetic notation in postfix notation we can use a stack.
			We scan the expression from left to right.
			If we encounter a number we push it to the stack
			If we encounter an operator 'p' we pop two items 'n1' and 'n2' from the stack and perform the 
			calculation 'n2 p n1' and push the result back to the stack.
			we continue until we scan the entire expression
			at the end we pop and return one element from the stack which should be a number and the last element in the stack.
		*/
		Stack<String> stk = new Stack<String>();
		if(postfixLiterals.length == 0) {return false;}
		for(int i = 0; i < postfixLiterals.length; i++)
		{
			if(postfixLiterals[i].equals("+") || postfixLiterals[i].equals("-") || postfixLiterals[i].equals("*") || postfixLiterals[i].equals("/"))
			{
				int popOne = Integer.parseInt(stk.pop());
				int popTwo = Integer.parseInt(stk.pop());
				int result = 0;
				if(postfixLiterals[i].equals("+"))
				{
					result = popTwo + popOne;
				}
				else if(postfixLiterals[i].equals("-"))
				{
					result = popTwo - popOne;
				}
				else if(postfixLiterals[i].equals("*"))
				{
					result = popTwo * popOne;
				}
				if(postfixLiterals[i].equals("/"))
				{
					result = popTwo / popOne;
				}
				stk.push(Integer.toString(result));
			}
			else // its a number
			{
				stk.push(postfixLiterals[i]);
			}
		}
		String lastResult = stk.pop();
		System.out.println("Validating Stack; Final Value: " + lastResult);
		if(stk.isEmpty())
		{
			return true;
		}
		return false;
		
	}


	
	//~ Evaluation methods ..........................................................

	/**
	 * Evaluation method for prefix notation.
	 *
	 * evaluatePrefixOrder: 0(n) - same as above; one for loop which gives up our n, other operations are lower order and not taken into account
	 *
	 * This method is virtually the same as above and as such it has the same argument. I used Stacks in this method as it would decrease my 
	 * running time compared to working with a String []. I don't believe it would be possible to
	 * get a smaller running time as it is necessary to look through the entire array.
	 *
	 * @param prefixLiterals : an array containing the string literals in prefix order.
	 * The method assumes that each of these literals can be one of:
	 * - "+", "-", "*", or "/"
	 * - or a valid string representation of an integer.
	 *
	 * @return the integer result of evaluating the expression
	 **/
	public static int evaluatePrefixOrder(String prefixLiterals[])
	{
		if(prefixLiterals.length == 0) {return 0;}
		Stack<String> stk = new Stack<String>();
		for(int i = (prefixLiterals.length-1); i >= 0 ;i--)
		{
			if(prefixLiterals[i].equals("+") || prefixLiterals[i].equals("-") || prefixLiterals[i].equals("*") || prefixLiterals[i].equals("/"))
			{
				int popOne = Integer.parseInt(stk.pop());
				int popTwo = Integer.parseInt(stk.pop());
				int result = 0;
				if(prefixLiterals[i].equals("+"))
				{
					result = popOne + popTwo;
				}
				else if(prefixLiterals[i].equals("-"))
				{
					result = popOne - popTwo;
				}
				else if(prefixLiterals[i].equals("*"))
				{
					result = popOne * popTwo;
				}
				if(prefixLiterals[i].equals("/"))
				{
					result = popOne / popTwo;
				}
				stk.push(Integer.toString(result));
			}
			else // its a number
			{
				stk.push(prefixLiterals[i]);
			}
		}
		return Integer.parseInt(stk.pop());
	}

	/**
	 * Evaluation method for postfix notation.
	 *
	 * evaluatePostfixOrder: 0(n) - same as above; one for loop which gives up our n, other operations are lower order and not taken into account
	 *
	 * This method is virtually the same as above and as such it has the same argument. I used Stacks in this method as it would decrease my 
	 * running time compared to working with a String []. I don't believe it would be possible to
	 * get a smaller running time as it is necessary to look through the entire array.
	 *
	 * @param postfixLiterals : an array containing the string literals in postfix order.
	 * The method assumes that each of these literals can be one of:
	 * - "+", "-", "*", or "/"
	 * - or a valid string representation of an integer.
	 *
	 * @return the integer result of evaluating the expression
	 **/
	public static int evaluatePostfixOrder(String postfixLiterals[])
	{
		if(postfixLiterals.length == 0) {return 0;}
		Stack<String> stk = new Stack<String>();
		for(int i = 0; i < postfixLiterals.length; i++)
		{
			if(postfixLiterals[i].equals("+") || postfixLiterals[i].equals("-") || postfixLiterals[i].equals("*") || postfixLiterals[i].equals("/"))
			{
				int popOne = Integer.parseInt(stk.pop());
				int popTwo = Integer.parseInt(stk.pop());
				int result = 0;
				if(postfixLiterals[i].equals("+"))
				{
					result = popTwo + popOne;
				}
				else if(postfixLiterals[i].equals("-"))
				{
					result = popTwo - popOne;
				}
				else if(postfixLiterals[i].equals("*"))
				{
					result = popTwo * popOne;
				}
				if(postfixLiterals[i].equals("/"))
				{
					result = popTwo / popOne;
				}
				stk.push(Integer.toString(result));
			}
			else // its a number
			{
				stk.push(postfixLiterals[i]);
			}
		}
		return Integer.parseInt(stk.pop());
	}


	
	//~ Conversion  methods ..........................................................

	/**
	 * Converts prefix to postfix.
	 * 
	 * convertPrefixToPostfix: 0(n) - One for loop is implemented which is run in n-1 time which we then shorten to n time.
	 * 
	 * This method is the mostly the same as above with the exception being that the Stack is now of type "String []". In most typical prefixToPostfix
	 * calculators the programmer will usually concatenate the string together and then push this back onto the Stack. This is fine until the very end
	 * when we need to divide our expression into the String []. Say we had the Integer 10, which we would then concatenate into a statement. At the
	 * end how would we know whether this is 1 or 10 or 0. There really is no way without implementing some sort of complicated counting system that
	 * is overly difficult and hard to read. We can prevent this by using String [] so that the 10 is stored in and index and rather than having the 
	 * result concatenated we instead store the result in the respective ordered indexes of an array.
	 * 
	 * The worst case space complexity of this implementation is at most 0(2n - 1). Which thus returns to 0(n).
	 * This is because we will need a new array to store our result (n) and we also need our two components popped from the stack (n) minus the operator.
	 *   
	 * 
	 * @param prefixLiterals : an array containing the string literals in prefix order.
	 * The method assumes that each of these literals can be one of:
	 * - "+", "-", "*", or "/"
	 * - or a valid string representation of an integer.
	 *
	 * @return the expression in postfix order.
	 **/
	public static String[] convertPrefixToPostfix(String prefixLiterals[])
	{
		/*
		Algorithm for Prefix to Postfix:
			Read the Prefix expression in reverse order (from right to left)
			If the symbol is an operand, then push it onto the Stack
			If the symbol is an operator, then pop two operands from the Stack
			Create a string by concatenating the two operands and the operator after them.
			string = operand1 + operand2 + operator
			And push the resultant string back to Stack
			Repeat the above steps until end of Prefix expression.
			*/
		Stack<String []> stk = new Stack<String []>();
		if(prefixLiterals.length == 0) {return prefixLiterals;}
		for(int i = (prefixLiterals.length-1); i >= 0 ;i--)
		{
			if(prefixLiterals[i].equals("+") || prefixLiterals[i].equals("-") || prefixLiterals[i].equals("*") || prefixLiterals[i].equals("/"))
			{
				String [] popOne = stk.pop();
				String [] popTwo = stk.pop();
				String [] result = new String [(popOne.length + popTwo.length + 1)]; // 1 is for the size of our operator.
				System.arraycopy(popOne, 0, result, 0, popOne.length);
				System.arraycopy(popTwo, 0, result, popOne.length, popTwo.length);
				result[result.length-1] = prefixLiterals[i];
				stk.push(result);
				// popOne + popTwo + prefixLiterals[i]
			}
			else
			{
				stk.push(new String [] {prefixLiterals[i]});
			}
		}
		return stk.pop();
	}

	/**
	 * Converts postfix to prefix.
	 *
	 * convertPostfixToPrefix: 0(n) - Same as above;
	 *
	 * My explanation for this method is the same as that of the one above. The space complexity and time complexity are the same also.
	 * 
	 * @param prefixLiterals : an array containing the string literals in postfix order.
	 * The method assumes that each of these literals can be one of:
	 * - "+", "-", "*", or "/"
	 * - or a valid string representation of an integer.
	 *
	 * @return the expression in prefix order.
	 **/
	public static String[] convertPostfixToPrefix(String postfixLiterals[])
	{
		/*
		 	Read the Postfix expression from left to right
			If the symbol is an operand, then push it onto the Stack
			If the symbol is an operator, then pop two operands from the Stack
			Create a string by concatenating the two operands and the operator before them.
			string = operator + operand2 + operand1
			And push the resultant string back to Stack
			Repeat the above steps until end of Prefix expression.
		 */
		Stack<String[]> stk = new Stack<String[]>();
		if(postfixLiterals.length == 0) {return postfixLiterals;}
		for(int i = 0; i < postfixLiterals.length; i++)
		{
			if(postfixLiterals[i].equals("+") || postfixLiterals[i].equals("-") || postfixLiterals[i].equals("*") || postfixLiterals[i].equals("/"))
			{
				String [] popOne = stk.pop();
				String [] popTwo = stk.pop();
				String [] result = new String [(popOne.length + popTwo.length + 1)]; // 1 is for the size of our operator.
				System.arraycopy(popTwo, 0, result, 1, popTwo.length);
				System.arraycopy(popOne, 0, result, popTwo.length+1, popOne.length);
				result[0] = postfixLiterals[i];
				stk.push(result);
				//postfixLiterals[i] + popTwo + popOne);
			}
			else
			{
				stk.push(new String [] {postfixLiterals[i]});
			}
		}
		return stk.pop();
		
	}

	/**
	 * Converts prefix to infix.
	 * 
	 * convertPrefixToInfix: 0(n)
	 * 
	 * My explanation for this method is the same as that of the convertPrefixToPostfix method. The time complexity of both these methods is the same.
	 * However the space complexity is slightly different. This is because now we must factor in brackets. Assuming that every statement within the expression
	 * needs two brackets for the worst case space complexity we would need 2 brackets for every statement divided by two. So, considering out space complexity
	 * for the method above we would need 0(2n - 1 + n) which is equal to 3n-1. However we know that we should shorten this down to 0(n) for worst
	 * case running time.
	 * 
	 * @param infixLiterals : an array containing the string literals in prefix order.
	 * The method assumes that each of these literals can be one of:
	 * - "+", "-", "*", or "/"
	 * - or a valid string representation of an integer.
	 *
	 * @return the expression in infix order.
	 **/
	public static String[] convertPrefixToInfix(String prefixLiterals[])
	{
		/*
		Algorithm for Prefix to Infix:
			Read the Prefix expression in reverse order (from right to left)
			If the symbol is an operand, then push it onto the Stack
			If the symbol is an operator, then pop two operands from the Stack
			Create a string by concatenating the two operands and the operator between them.
			string = (operand1 + operator + operand2)
			And push the resultant string back to Stack
			Repeat the above steps until end of Prefix expression.
		*/
		Stack<String[]> stk = new Stack<String[]>();
		if(prefixLiterals.length == 0) {return prefixLiterals;}
		for(int i = (prefixLiterals.length-1); i >= 0 ;i--)
		{
			if(prefixLiterals[i].equals("+") || prefixLiterals[i].equals("-") || prefixLiterals[i].equals("*") || prefixLiterals[i].equals("/"))
			{
				String [] popOne = stk.pop();
				String [] popTwo = stk.pop();
				String [] result = new String [(popOne.length + popTwo.length + 3)]; // 1 is for the size of our operator. // 2 for brackets
				System.arraycopy(popOne, 0, result, 1, popOne.length);
				result[popOne.length+1] = prefixLiterals[i];
				System.arraycopy(popTwo, 0, result, popOne.length+2, popTwo.length);
				result[0] = "(";
				result[result.length-1] = ")";
				stk.push(result);
				//stk.push("(" + popOne + prefixLiterals[i]  + popTwo + ")");
			}
			else
			{
				stk.push(new String [] {prefixLiterals[i]});
			}
		}
		return stk.pop();
		
	}

	/**
	 * Converts postfix to infix.
	 * 
	 * convertPostfixToInfix: 0(n)
	 * 
	 * My explanation for this method is the same as that of the convertPrefixToPostfix method. The space complexity explanation is also the same.
	 * 
	 * @param infixLiterals : an array containing the string literals in postfix order.
	 * The method assumes that each of these literals can be one of:
	 * - "+", "-", "*", or "/"
	 * - or a valid string representation of an integer.
	 *
	 * @return the expression in infix order.
	 **/
	public static String[] convertPostfixToInfix(String postfixLiterals[])
	{
		Stack<String[]> stk = new Stack<String[]>();
		if(postfixLiterals.length == 0) {return postfixLiterals;}
		for(int i = 0; i < postfixLiterals.length; i++)
		{
			if(postfixLiterals[i].equals("+") || postfixLiterals[i].equals("-") || postfixLiterals[i].equals("*") || postfixLiterals[i].equals("/"))
			{
				String [] popOne = stk.pop();
				String [] popTwo = stk.pop();
				String [] result = new String [(popOne.length + popTwo.length + 3)]; // 1 is for the size of our operator. // 2 for brackets
				System.arraycopy(popTwo, 0, result, 1, popTwo.length);
				result[popTwo.length+1] = postfixLiterals[i];
				System.arraycopy(popOne, 0, result, popTwo.length+2, popOne.length);
				result[0] = "(";
				result[result.length-1] = ")";
				stk.push(result);
				//stk.push("(" + popTwo + postfixLiterals[i]  + popOne + ")");
			}
			else
			{
				stk.push(new String [] {postfixLiterals[i]});
			}
		}
		return stk.pop();
	
	}

	
	
	
	//~ Data Structures Used ..........................................................
	/*
	public class Stack<E>
	Constructor new Stack<E>();		Space Complexity = 0(n * <E>) thus it is just 0(n).
	
	Time Complexity of a Stack:
	void .push(x)		: insert a new element onto the top of the stack			: 0(1) : i.e place last index
	<E> .pop()			: remove and return the element from the top of the stack	: 0(1) : i.e remove last index
	boolean isEmpty()   : is the stack empty?										: 0(1) : i.e peek on stack
	
	Resources for Time Complexities Found: Dr.Koutavas' Lecture Notes
	
	*/

}