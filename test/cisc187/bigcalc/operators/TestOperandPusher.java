package cisc187.bigcalc.operators;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import java.util.Stack;



/**
 * Unit tests for the OperandPusher command
 */
public class TestOperandPusher {
  OperandPusher command;
  Stack<String> operands;


  @Before
  public void setUp() {
    command = new OperandPusher();
    operands = new Stack<>();
  }
  @After
  public void tearDown() {
    command = null;
  }

  @Test 
  public void testIsOperator(){
    for (char c: "0123456789".toCharArray()) {
      assertTrue("Failed to detect basic integer " + c + " is an operand.", 
                  command.isOperator(Character.toString(c)));
    }
  }
  @Test 
  public void testIsNotOperator(){
    for (char c: "-`~!@#$%^&*+[   ]".toCharArray()) {
      assertFalse("Incorrectly claimed that " + c + " is an addition operator.", 
                  command.isOperator(Character.toString(c)));
    }
  }
  @Test 
  public void testIsNegativeOperator(){
    for (char c: "0123456789".toCharArray()) {
      assertTrue("Failed to detect negative integer -" + c + " is an operand.", 
                  command.isOperator("-" + Character.toString(c)));
    }
  }

  @Test 
  public void testPush0(){
    command.execute(operands, "0");
    assertEquals("Failed to add 0", "0", operands.peek());
  }
}
