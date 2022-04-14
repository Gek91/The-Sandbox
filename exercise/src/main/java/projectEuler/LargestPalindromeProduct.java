package projectEuler;

import java.util.ArrayList;
import java.util.List;

public class LargestPalindromeProduct {

	/**
	 * Check if the input number is palindrome
	 * @param n
	 * @return True if is palindrome, False otherwise
	 */
	public static boolean isPalindrome(int n){
		List<Integer> digits = getDigits(n);
		int size = digits.size();
		for(int i=0 ; i<(size/2) ; i++){
			if(digits.get(i) != digits.get(size-1-i))
				return false;
		}
		return true;
	}
	
	/**
	 * Creates an ordered list of the digits of the number
	 * @param n
	 * @return
	 */
	public static List<Integer> getDigits(int n){
		List<Integer> digits = new ArrayList<Integer>();
		int factor = 10;
		while(n != 0){
			int digit = n%factor;
			n = (n - digit)/factor;;
			digits.add(digit);
		}
		return digits;
	}
	
	/**
	 * Calculates largest palindrome product of 2 number of digits length
	 * @param digits
	 * @return
	 */
	public static long calculateLargestPalindromeProduct(int digits){
		//Upper limit for the length of the numbers
		int uppeLimit = (int) Math.pow(10, digits);
		//starts from the numbers with the maximum number of digits
		int a = (int) Math.pow(10, digits-1);
		long result = 0;
		//Fucking quadratic
		while(a < uppeLimit){
			int b = (int) Math.pow(10, digits-1);
			while(b < uppeLimit){
				int product = a*b;
				//largest product
				if(isPalindrome(product)){
					if(result < product)
						result = product;
				}
				b++;
			}
			a++;
		}
		return result;
	}
}
