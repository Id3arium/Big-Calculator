package cisc187.bigcalc.operators;

import cisc187.bigcalc.Operator;
import java.util.Stack;

/**
 * Pushes non-operator items (operands) onto the stack.
 * <p>
 * This is currently the only command that pushes anything onto
 * the operands stack in {@link #execute execute}
 * </p>
 */
public class OperandPusher implements Operator {

  
  /*
   * A regular expression to test for 1 or more digits (0-9) and an optional leading minus.
   */
  private static final String MY_OPERATOR = "-?[0-9]+";

  /**
   * {@inheritDoc}
   * <p>
   * A leading unary minus (-) is optional.
   * </p>
   */
  @Override
  public boolean isOperator(String operand) {
    return operand.matches(MY_OPERATOR);
  }

  /**
   * {@inheritDoc}
   *
   * <p>
   * This executor pushes the operand stored in the parameter
   * token  onto the stack.
   * i.e. if it looks like a number, then push it onto the
   * operands stack.
   * </p>
   */
	@Override
	public void execute(Stack<String> operands, String token) {
		operands.push(token);
	}

}
