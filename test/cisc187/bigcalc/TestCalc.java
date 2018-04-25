package cisc187.bigcalc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import java.util.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


/**
 * Infinite precision, postfix command-line {@link Calculator} Unit tests
 */
public class TestCalc {
  Calc calc;

  private final ByteArrayOutputStream stdout = new ByteArrayOutputStream();

  @Before
  public void setUp() {
    System.setOut(new PrintStream(stdout));
    calc = new Calc();
  }
  @After
  public void tearDown() {
    System.setOut(null);
    calc = null;
  }


  @Test 
  public void testBasicAdd(){
    List<String> tokens = new ArrayList<>();

    tokens.add("2");
    tokens.add("2");
    tokens.add("+");

    assertEquals("Failed to add 2+2", "4", calc.calculate(tokens));
  }

  
  @Test 
  public void testBasicPow(){
    List<String> tokens = new ArrayList<>();

    tokens.add("2");
    tokens.add("3");
    tokens.add("^");

    assertEquals("Failed to calc 2^3", "8", calc.calculate(tokens));
  }


  @Test
  public void testTooFewOperandsAdd(){
    List<String> tokens = new ArrayList<>();

    tokens.add("+");

    assertEquals("Failed to handle exception", "java.lang.IllegalArgumentException: Attempting to add with fewer than 2 operands.", calc.calculate(tokens));
  }

}
