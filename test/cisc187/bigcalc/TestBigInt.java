package cisc187.bigcalc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * {@link Queues} Unit tests
 */
public class TestBigInt {

	@Test
	public void testSimple() {
		BigInt b = BigInt.valueOf("12345");

		assertEquals("Failed to init BIGInt 12345", "12345", b.toString());
	}

	@Test
	public void testOnlyZeros() {
		BigInt b = BigInt.valueOf("00000");
		assertEquals("Failed to init BIGInt 00000", "0", b.toString());
	}

	@Test
	public void test999101plus199100() {
		BigInt a = BigInt.valueOf("00000999101");
		BigInt b = BigInt.valueOf("0199100");

		assertEquals("Failed to add 999101+199100 ", BigInt.valueOf("01198201"), a.plus(b));
	}

	@Test(expected = NumberFormatException.class)
	public void testNegativeSigninBadLocation() {
		BigInt b = BigInt.valueOf("12345-");
	}

	@Test(expected = NumberFormatException.class)
	public void testEmptyStringInConstructor() {
		BigInt b = BigInt.valueOf("");
	}

	@Test
	public void testAdd2and2() {
		BigInt a = BigInt.valueOf("2");
		BigInt b = BigInt.valueOf("2");

		assertEquals("Failed to add 2+2 ", BigInt.valueOf("4"), a.plus(b));
	}

	@Test
	public void testAdd0and2() {
		BigInt a = BigInt.valueOf("0");
		BigInt b = BigInt.valueOf("1");

		assertEquals("Failed to add 0+1 ", BigInt.ONE, a.plus(b));
	}

	@Test
	public void testAdd2and0() {
		BigInt a = BigInt.valueOf("2");
		BigInt b = BigInt.ZERO;

		assertEquals("Failed to add 0+2 ", BigInt.valueOf("2"), a.plus(b));
	}

	@Test
	public void testAddTwoNumsZeroPadded() {
		BigInt a = BigInt.valueOf("1234567890123456789");
		BigInt b = BigInt.ZERO;

		assertEquals("Failed to add 0+1234567890123456789 ", BigInt.valueOf("1234567890123456789"), a.plus(b));
	}

	@Test
	public void testAdd890123456789plus9876543210() {
		BigInt a = BigInt.valueOf("0000000890123456789");
		BigInt b = BigInt.valueOf("9876543210");

		assertEquals("Failed to add 0000000890123456789+9876543210", BigInt.valueOf("899999999999"), a.plus(b));
	}

	@Test
	public void multiplySmall() {
		BigInt a = BigInt.valueOf("326");
		BigInt b = BigInt.valueOf("48");

		assertEquals("Failed to multiply small numbers ", BigInt.valueOf("15648"), a.times(b));
	}
	
	@Test
	public void testZeroTimes2() {
		BigInt a = BigInt.ZERO;
		BigInt b = BigInt.valueOf("2");

		assertEquals("Failed to multiply 0*2 ", BigInt.ZERO, a.times(b));
	}
	
	@Test
	public void test2power3() {
		BigInt a = BigInt.valueOf("2");
		int b = 9999;
		assertEquals("Failed to raise 9999^99 ", BigInt.valueOf("990148353526723487602263124753282625570559528895791057324326529121794837894053513464422176826916433932586924386677766244032001623756821400432975051208820204980098735552703841362304669970510691243800218202840374329378800694920309791954185117798434329591212159106298699938669908067573374724331208942425544893910910073205049031656789220889560732962926226305865706593594917896276756396848514900989999"), a.pow(b));
	}

	@Test
	public void test1234567890123456789times1() {
		BigInt a = BigInt.ONE;
		BigInt b = BigInt.valueOf("12345678901234567891234567890123456789");

		assertEquals("Failed to multiply 1*123456789012345678912345678901234567892 ", BigInt.valueOf("12345678901234567891234567890123456789"), a.times(b));
	}

	@Test
	public void multiplyBig() {
		BigInt a = BigInt.valueOf("000000089012345678912345678901234567891234567890123456789");
		BigInt b = BigInt.valueOf("9876543210");

		assertEquals("Failed to multiply big numbers ", BigInt.valueOf("879134278321234567900124828532123456790012482853211126352690"), a.times(b));
	}

	@Test
	public void multiplyBigReverseOperands() {
		BigInt a = BigInt.valueOf("9876543210");
		BigInt b = BigInt.valueOf("000000089012345678912345678901234567891234567890123456789");

		assertEquals("Failed to multiply big numbers ", BigInt.valueOf("879134278321234567900124828532123456790012482853211126352690"), a.times(b));
	}

}
