package cisc187.bigcalc.operators;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import java.util.Stack;


/**
 * Unit tests for the Multiply command
 * Most tests concentrate on the identity value for mulitplication: 1, and
 * handling of large values
 */
public class TestMultiply {
  Multiply command;
  Stack<String> operands;

  @Before
  public void setUp() {
    operands = new Stack<>();
    command = new Multiply();
  }
  @After
  public void tearDown() {
    command = null;
  }

  @Test 
  public void testIsOperator(){
    assertTrue("Failed to detect multiplication operator.", command.isOperator("*"));
  }
  @Test 
  public void testIsNotOperator(){
    for (char c: "+-0123456789`~!@#$%^&[   ]".toCharArray()) {
      assertFalse("Incorrectly claimed that " + c + " is a multiplication operator.", 
                  command.isOperator(Character.toString(c)));
    }
  }

  @Test 
  public void test2times2(){
    operands.push("2");
    operands.push("2");
    command.execute(operands, null);
    assertEquals("Failed to multiply 2*2", "4", operands.peek());
  }
  @Test(expected=NumberFormatException.class)
  public void testnullBigInt(){
    operands.push("");
    operands.push("2");
    command.execute(operands, null);
  }
  @Test(expected=IllegalArgumentException.class)
  public void testTooFewOperands(){
    operands.push("2");
    operands.push("2");
    operands.pop();
    command.execute(operands, null);
  }
  @Test 
  public void test1times2(){
    operands.push("1");
    operands.push("2");
    command.execute(operands, null);
    assertEquals("Failed to multiply 1*2", "2", operands.peek());
  }
//  @Test 
//  public void testNeg1times1(){
//    operands.push("-1");
//    operands.push("-1");
//    command.execute(operands, null);
//    assertEquals("Failed to multiply -1*-1", "1", operands.peek());
//  }
  @Test 
  public void test1times0(){
    operands.push("0");
    operands.push("1");
    command.execute(operands, null);
    assertEquals("Failed to multiply 1*0", "0", operands.peek());
  }

  @Test 
  public void test1234567890123456789times1(){
    operands.push("12345678901234567891234567890123456789");
    operands.push("1");
    command.execute(operands, null);
    assertEquals("Failed to multiply 12345678901234567891234567890123456789*1", "12345678901234567891234567890123456789", operands.peek());
  }
//  @Test 
//  public void test1234567890123456789timesNeg1(){
//    operands.push("12345678901234567891234567890123456789");
//    operands.push("-1");
//    command.execute(operands, null);
//    assertEquals("Failed to multiply 12345678901234567891234567890123456789*1", "-12345678901234567891234567890123456789", operands.peek());
//  }

  @Test 
  public void test0000000890123456789times9876543210(){
    operands.push("000000089012345678912345678901234567891234567890123456789");
    operands.push("9876543210");
    command.execute(operands, null);
    assertEquals("Failed to multiply 000000089012345678912345678901234567891234567890123456789*9876543210", "879134278321234567900124828532123456790012482853211126352690", operands.peek());
  }
  @Test 
  public void LongValTimes9876543210NewOrder(){
    operands.push("9876543210");
    operands.push("000000089012345678912345678901234567891234567890123456789");
    command.execute(operands, null);
    assertEquals("Failed to multiply 9876543210 * 000000089012345678912345678901234567891234567890123456789", "879134278321234567900124828532123456790012482853211126352690", operands.peek());
  }
}
