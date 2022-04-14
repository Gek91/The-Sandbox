package projectEuler;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class EvenFibonacciNumerTest {
	@Test
	public void fibonacci(){
		int result = EvenFibonacciNumber.fibonacci(1);
		assertEquals(1,result);
		result = EvenFibonacciNumber.fibonacci(2);
		assertEquals(1,result);
		result = EvenFibonacciNumber.fibonacci(3);
		assertEquals(2,result);
		result = EvenFibonacciNumber.fibonacci(4);
		assertEquals(3,result);
		result = EvenFibonacciNumber.fibonacci(5);
		assertEquals(5,result);
		result = EvenFibonacciNumber.fibonacci(6);
		assertEquals(8,result);
	}
	
	@Test
	public void fibonacciEven(){
		assertEquals(false,EvenFibonacciNumber.fibonacciIsEven(1));
		assertEquals(false,EvenFibonacciNumber.fibonacciIsEven(2));
		assertEquals(true,EvenFibonacciNumber.fibonacciIsEven(3));
	}
	
	@Test
	public void fibonacciEvenSum(){
		int result = EvenFibonacciNumber.sumFibonacciEven(3);
		assertEquals(2,result);
		result = EvenFibonacciNumber.sumFibonacciEven(10);
		assertEquals(10,result);
	}
	
	@Test
	public void fibonacciEvenSum4Milion(){
		int result = EvenFibonacciNumber.sumFibonacciEven(4000000);
		System.out.print(result);
	}
}
