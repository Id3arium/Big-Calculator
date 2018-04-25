package cisc187.bigcalc.operators;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import java.util.Stack;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


/**
 * Unit tests for the Add command
 * Most tests concentrate on the identity value for addition: 0, and
 * handling of large values
 */
public class TestAdd {
  Add command;
  Stack<String> operands;

  private final ByteArrayOutputStream stdout = new ByteArrayOutputStream();

  @Before
  public void setUp() {
    operands = new Stack<>();
    //System.setOut(new PrintStream(stdout));
    command = new Add();
  }
  @After
  public void tearDown() {
    //System.setOut(null);
    command = null;
  }

  @Test 
  public void testIsOperator(){
    assertTrue("Failed to detect addition operator.", command.isOperator("+"));
  }
  @Test 
  public void testIsNotOperator(){
    for (char c: "-0123456789`~!@#$%^&*[   ]".toCharArray()) {
      assertFalse("Incorrectly claimed that " + c + " is an addition operator.", 
                  command.isOperator(Character.toString(c)));
    }
  }

  @Test 
  public void test2plus2(){
    operands.push("2");
    operands.push("2");
    command.execute(operands, null);
    assertEquals("Failed to add 2+2", "4", operands.peek());
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
  public void test0plus2(){
    operands.push("0");
    operands.push("2");
    command.execute(operands, null);
    assertEquals("Failed to add 0+2", "2", operands.peek());
  }
  @Test 
  public void test1plus0(){
    operands.push("1");
    operands.push("0");
    command.execute(operands, null);
    assertEquals("Failed to add 1+0", "1", operands.peek());
  }
  
//  @Test 
//  public void testNeg1plus0(){
//    operands.push("-1");
//    operands.push("-0");
//    command.execute(operands, null);
//    assertEquals("Failed to add -1+-0", "-1", operands.peek());
//  }

  @Test 
  public void test1234567890123456789plus0(){
    operands.push("1234567890123456789");
    operands.push("0");
    command.execute(operands, null);
    assertEquals("Failed to add 1234567890123456789+0", "1234567890123456789", operands.peek());
  }
  @Test 
  public void test0000000890123456789plus9876543210(){
    operands.push("0000000890123456789");
    operands.push("9876543210");
    command.execute(operands, null);
    assertEquals("Failed to add 0000000890123456789+9876543210", "899999999999", operands.peek());
  }
  @Test 
  public void test0000000890123456789plus9876543211(){
    operands.push("9876543211");
    operands.push("0000000890123456789");
    command.execute(operands, null);
    assertEquals("Failed to add 9876543211+0000000890123456789", "900000000000", operands.peek());
  }
}
