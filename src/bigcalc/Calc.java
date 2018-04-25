package cisc187.bigcalc;

import cisc187.bigcalc.operators.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Calc {

	private final Stack<String> operands = new Stack<>();
	private final List<String> tokens = new ArrayList<>();
	private List<Operator> operators;
	private final String defaultResult = "Error: could not produce a result.";

	/**
	 * Create a new post-fix notation based BigInt calculator.
	 */
	public Calc() {
		initOperators();
	}

	private void initOperators() {
		operators = new ArrayList<Operator>();
		operators.add(new OperandPusher());
		operators.add(new Add());
		operators.add(new Multiply());
		operators.add(new Power());
	}

	public String calculate(List<String> tokens) {
		String result = defaultResult;
		System.out.println("Printing the file passed in:");
		for (String token : tokens) {
			///if its an operator dont push it
			operands.push(token);
			System.out.println("analizing token: " + token);
			for (Operator o : operators) {
				if (o.isOperator(token)) {
					try {
						o.execute(operands, token);
					} catch (IllegalArgumentException e) {
						return e.getMessage();
					}
				}
			}
		}
		return tokens.toString();
	}

	/**
	 * Main entrance point for Calc. Reads from standard input and sends complete postfix sequences to the calculator
	 * for evaluation. Results are written to standard output.
	 *
	 * @param args not used
	 */
	public static void main(String[] args) {
		Calc calc = new Calc();
		try (Scanner input = new Scanner(System.in)) {
			System.out.println("calc created");
			while (input.hasNextLine()) {
				if (input.hasNext()) {
					calc.operands.add(input.next());
					calc.calculate(calc.operands);
				}
				calc.operands.clear();
			}
		}
	}

	public Stack<String> getOperands() {
		return operands;
	}
}
