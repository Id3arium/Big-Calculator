package cisc187.bigcalc;

import java.util.Stack;

/**
 * A command interface for arithmetic operations.
 */
public interface Operator {
	
	/**
	 * Check if the provided string can be used as an operator
   * @param op A string that may be a math operation
	 * @return {@code true} if the parameter is a valid operator 
	 */
	boolean isOperator(String op);
	
	/**
	 * Execute the arithmetic command using the provided operator and operands.
	 * @param operands the subjects of the arithmetic operation
	 * @param token Either an operand or operator applied to the operands
   *
   * @throws IllegalArgumentException if fewer than two operands are on the stack
   *         when execute is called.
	 */
	void execute(Stack<String> operands, String token) throws IllegalArgumentException ;
}
