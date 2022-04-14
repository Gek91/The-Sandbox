package projectEuler;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class ThousandDigitFibonacciNumberTest {

	@Test
	public void stringSum(){
		assertEquals("2",ThousandDigitFibonacciNumber.stringSum("1","1"));
		assertEquals("10",ThousandDigitFibonacciNumber.stringSum("7","3"));
		assertEquals("20",ThousandDigitFibonacciNumber.stringSum("17","3"));
		assertEquals("120",ThousandDigitFibonacciNumber.stringSum("17","103"));
		assertEquals("1110",ThousandDigitFibonacciNumber.stringSum("111","999"));
	}
	
	@Test
	public void stringfibonacci(){
		assertEquals("1",ThousandDigitFibonacciNumber.stringFibonacci(2));
		assertEquals("2",ThousandDigitFibonacciNumber.stringFibonacci(3));
		assertEquals("3",ThousandDigitFibonacciNumber.stringFibonacci(4));
		assertEquals("5",ThousandDigitFibonacciNumber.stringFibonacci(5));
	}
	
	@Test
	public void fibonacciStringLegth(){
		assertEquals(7,ThousandDigitFibonacciNumber.fibonacciStringLegth(2));
		assertEquals(12,ThousandDigitFibonacciNumber.fibonacciStringLegth(3));
		System.out.println(ThousandDigitFibonacciNumber.fibonacciStringLegth(1000));
	}
	
	
}
