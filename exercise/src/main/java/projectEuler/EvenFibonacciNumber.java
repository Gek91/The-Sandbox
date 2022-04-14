package projectEuler;

import java.security.InvalidParameterException;

public class EvenFibonacciNumber {

	/**
	 * Returns last fibonacci number 
	 * @param n, upper limit
	 * @return
	 */
	public static int fibonacci(int n){
		if(n<1)
			throw new InvalidParameterException(); 
		if(n < 3)
			return 1;
		return fibonacci(n-1) + fibonacci(n-2);
	}
	
	/**
	 * Returns if the fibonacci element is even
	 * @param n
	 * @return
	 */
	public static boolean fibonacciIsEven(int n){
		return fibonacci(n) % 2 == 0;
	}
	
	/**
	 * Returns the sum of the numbers in fibonacci sequence less than n
	 * @param n
	 * @return
	 */
	public static int sumFibonacciEven(int n){
		int result = 0;
		int fibonacci = 1;
		int fibonacci_1 = 1;
		int fibonacci_2 = 1;
		while(fibonacci < n){
			if(fibonacci % 2 == 0)
				result = result + fibonacci;
			fibonacci = fibonacci_1 + fibonacci_2;
			int help = fibonacci;
			fibonacci_2 = fibonacci_1;
			fibonacci_1 = help;
		} 
		return result;
	}
}
