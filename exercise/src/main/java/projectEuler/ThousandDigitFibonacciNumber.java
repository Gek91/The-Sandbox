package projectEuler;

import java.security.InvalidParameterException;

public class ThousandDigitFibonacciNumber {

	
	/**
	 * Calculate the sum of two string of numbers
	 * @param a
	 * @param b
	 * @return String with the result
	 */
	public static String stringSum(String a, String b){
		StringBuilder result = new StringBuilder();
		int lengthA = a.length()-1;
		int lengthB = b.length()-1;
		int report = 0;
		int maxLength = Math.max(lengthA, lengthB);
		for(int i = 0 ; i <= maxLength ; i++){
			int valA = (i<=lengthA ? Character.getNumericValue(a.charAt(lengthA-i)) : 0);
			int valB = (i<=lengthB ? Character.getNumericValue(b.charAt(lengthB-i)) : 0);
			int sum = valA + valB + report;
			report = sum/10;
			result.insert(0, sum%10);
		}
		if(report != 0)
			result.insert(0,report);
		return result.toString();
	}
	
	/**
	 * Returns last fibonacci number 
	 * @param n, upper limit
	 * @return
	 */
	public static String stringFibonacci(int n){
		if(n<1)
			throw new InvalidParameterException(); 
		if(n < 3)
			return "1";
		return stringSum(stringFibonacci(n-1),stringFibonacci(n-2));
	}
	
	/**
	 * Return the index of fibonacci sequence that is n digits
	 * @param n
	 * @return
	 */
	public static int fibonacciStringLegth(int n){
		String fibonacci = "1";
		String fibonacci_1 = "1";
		String fibonacci_2 = "";
		int i = 2;
		while(fibonacci.length() < n){
			fibonacci_2 = fibonacci_1;
			fibonacci_1 = fibonacci;
			i++;
			fibonacci = stringSum(fibonacci_1,fibonacci_2);
		}
		return i;
	}
}
