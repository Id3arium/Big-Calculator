package cisc187.bigcalc.operators;

import cisc187.bigcalc.Operator;
import cisc187.bigcalc.BigInt;
import java.util.Stack;

/**
 * Executes the multiplication command on two {@link BigInt}s.
 */
public class Multiply implements Operator {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isOperator(String operator) {
		if (operator.length() != 1) {
			return false;
		}
		return operator.equals("*");
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * This class does not use the token parameter. Two operands will always be popped off the operands stack,
	 * multiplied and the result pushed back onto the stack.
	 * </p>
	 */
	@Override
	public void execute(Stack<String> operands, String token) {
		if (operands.size() < 2) {
			throw new IllegalArgumentException("java.lang.IllegalArgumentException: Attempting to add with fewer than 2 operands.");
		}
		BigInt num1 = BigInt.valueOf(operands.pop());
		BigInt num2 = BigInt.valueOf(operands.pop());
		operands.push(num1.times(num2).toString());
	}

}
