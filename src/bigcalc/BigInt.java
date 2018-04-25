package cisc187.bigcalc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Simulates Integers of (nearly) unlimited size.
 * <p>
 * A BigInt is a sequence of digits. Each digit is represented by a single Integer. The BigInt class stores the sequence
 * and allows basic arithmetic operations on it's value.
 * </p>
 *
 */
public final class BigInt {

	public static final BigInt ONE = valueOf("1");
	public static final BigInt ZERO = valueOf("0");
	private List<Integer> digits;

	/**
	 * Private constructor is called by the static factory to create a new BigInt.
	 *
	 * @param digits the characters to transform into a BigInt
	 * @throws IllegalArgumentException if a null string is supplied
	 * @throws IllegalArgumentException if a zero length string is supplied
	 */
	private BigInt(String digits) {
		if (digits == null || digits.isEmpty() || !digits.matches("[0-9]+")) {
			throw new NumberFormatException();
		}
		this.digits = new ArrayList<>();
		if (digits.matches("[0]+")) {
			this.digits.add(Integer.valueOf("0"));
			return;
		}
		int start = 0;
		for (int i = 0; i < digits.length(); i++) {
			if (digits.charAt(i) == '0') {
				start++;
			} else {
				break;
			}
		}
		for (int i = start; i < digits.length(); i++) {
			this.digits.add(Integer.valueOf(digits.substring(i, i + 1)));
		}
	}

	public static BigInt valueOf(String digits) {
		return new BigInt(digits);
	}

	/**
	 * @return the number
	 */
	public List<Integer> getDigits() {
		List<Integer> digitsCopy = new ArrayList<Integer>();
		for (Integer i : digits) {
			digitsCopy.add(i);
		}
		return digitsCopy;
	}

	/**
	 *
	 * @param operand
	 * @return
	 */
	public BigInt plus(BigInt operand) {
		List<Integer> n1 = this.getDigits();
		List<Integer> n2 = operand.getDigits();
		List<Integer> sum = new ArrayList<>();
		if (n1.size() >= n2.size()) {
			sum = add(n1, n2, 0, sum);
		} else {
			sum = add(n2, n1, 0, sum);
		}
		return new BigInt(listToString(sum));
	}

	private List<Integer> add(List<Integer> num1, List<Integer> num2, final int carry, List<Integer> result) {
		int sum;
		int c = carry;
		if (num1.size() == 1) {
			sum = getSum(num1, num2, c);
			result.add(0, sum % 10);
			if (sum > 9) {
				result.add(0, 1);
			}
			return result;
		}
		sum = getSum(num1, num2, c);
		result.add(0, sum % 10);
		c = sum / 10;
		return add(num1, num2, c, result);
	}

	private int getSum(List<Integer> num1, List<Integer> num2, int carry) {
		int digit1 = num1.get(num1.size() - 1);
		int digit2;
		num1.remove(num1.size() - 1);
		if (num2.isEmpty()) {
			digit2 = 0;
		} else {
			digit2 = num2.remove(num2.size() - 1);
		}
		return digit1 + digit2 + carry;
	}

	public BigInt times(BigInt operand) {
		List<Integer> n1 = this.getDigits();
		List<Integer> n2 = operand.getDigits();
		List<Integer> result = new ArrayList<>();
		result = multiply(n1, n2, result, 0);
		return new BigInt(listToString(result));
	}

	private List<Integer> multiply(List<Integer> num1, List<Integer> num2, List<Integer> result, int zeros) {
		List<Integer> tempResult;
		if (num2.isEmpty()) {
			return result;
		}
		tempResult = multiplyRow(num1, num2.remove(num2.size() - 1));
		result = updateResult(tempResult, result, zeros);
		return multiply(num1, num2, result, ++zeros);
	}

	private List<Integer> multiplyRow(List<Integer> num1, final int num2) {
		int carry = 0;
		List<Integer> result = new ArrayList<>();
		for (int i = num1.size() - 1; i >= 0; i--) {
			int partialResult = (num1.get(i) * num2) + carry;
			result.add(0, partialResult % 10);
			carry = partialResult / 10;
		}
		if (carry > 0) {
			result.add(0, carry);
		}
		return result;
	}

	private List<Integer> updateResult(List<Integer> tempResult, List<Integer> result, final int zeros) {
		List<Integer> newResult = new ArrayList<>();
		for (int i = 0; i < zeros; i++) {
			tempResult.add(0);
		}
		if (tempResult.size() >= result.size()) {
			add(tempResult, result, 0, newResult);
		} else {
			add(result, tempResult, 0, newResult);
		}
		return newResult;
	}

	public BigInt pow(int exponent) {
		if (exponent == 0) {
			return ONE;
		}
		List<Integer> n1 = this.getDigits();
		List<Integer> result = new ArrayList<>();

		result = exp(n1, exponent, result);
		return new BigInt(listToString(result));
	}

	private List<Integer> exp(List<Integer> base, int exponent, List<Integer> result) {
		if (result.isEmpty()) {
			for (Iterator<Integer> it = base.iterator(); it.hasNext();) {
				result.add(it.next());
			}
		}
		if (exponent == 1) {
			return result;
		}
		List<Integer> newResult = new ArrayList<>();
		result = multiply(base, result, newResult, 0);
		return exp(base, --exponent, result);
	}

	private String listToString(List<Integer> list) {
		StringBuilder sb = new StringBuilder();
		for (Iterator<Integer> it = list.iterator(); it.hasNext();) {
			sb.append(it.next());
		}
		return sb.toString();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Iterator<Integer> i = this.digits.iterator(); i.hasNext();) {
			sb.append(i.next());
		}
		return sb.toString();
	}

	@Override
	public int hashCode() {
		return 445 + Objects.hashCode(this.digits);
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof BigInt)) {
			return false;
		}
		BigInt b = (BigInt) o;
		return b.getDigits().equals(this.getDigits());
	}

}
