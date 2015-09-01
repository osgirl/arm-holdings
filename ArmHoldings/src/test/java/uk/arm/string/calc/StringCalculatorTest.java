package uk.arm.string.calc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StringCalculatorTest {
	
	private StringCalculator stringCalculator = new StringCalculator();
	
	@Test
	public void testAddWithZeroNumbers() {
		assertEquals(0, stringCalculator.add(""));
	}
	
	@Test
	public void testAddWithOneNumbers() {
		assertEquals(3, stringCalculator.add("3"));
	}
	
	@Test
	public void testAddWithTwoNumbers() {
		assertEquals(8, stringCalculator.add("3, 5"));
	}
	
	@Test //Step 2
	public void testAddWithFiveNumbers() {
		assertEquals(28, stringCalculator.add("3, 5, 9, 1, 10"));
	}
	
	@Test //Step 3
	public void testAddWithOnlyNewLineDelimiter() {
		assertEquals(17, stringCalculator.add("3\n 5\n 9"));
	}
	
	@Test //Step 3
	public void testAddWithCommaAndNewLineDelimiter() {
		assertEquals(17, stringCalculator.add("3, 5 \n9"));
	}
	
	@Test //Step 4
	public void testAddWithParameterisedDelimiter() {
		assertEquals(17, stringCalculator.add("//;\n3;5; 9"));
	}
	
	@Test //Step 5
	public void testAddWithNoNegativeNumbersAllowed() {
		boolean thrown = false;
		String exceptionMsg = "";
		
		try {
			stringCalculator.add("//;\n-3;-5; 9");
		} catch (IllegalArgumentException iae) {
			thrown = true;
			exceptionMsg = iae.toString();
		}
		
		assertTrue(thrown);
		assertTrue(exceptionMsg.contains("-3"));
		assertTrue(exceptionMsg.contains("-5"));
		assertFalse(exceptionMsg.contains("9"));
	}
	
	@Test //Step 6
	public void testAddWithNumbersOver1000Ignored() {
		assertEquals(1000, stringCalculator.add("1, 999 \n1001"));
	}

}
