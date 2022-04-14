package math;

/**
 * Calculate if a number is divisible for a number < 10 using mental schemes
 * @author giacomo
 *
 */
public class IsDivisible {

	/**
	 * Last digits is a even number
	 * @param n
	 * @return
	 */
	public static boolean isDivisibleBy2(int n){
		int res = n % 10;
		if(res == 0 || res == 2 || res == 4 || res == 6 || res == 8 )
			return true;
		return false;
	}
	
	/**
	 * Sums the digits of the number, if the sum is divisible by 3 the whole number is divisible by 3
	 * @param n
	 * @return
	 */
	public static boolean isDivisibleBy3(int n){
		while(n > 10){
			int val = 0;
			while(n != 0){
				val = val + n%10;
				n = n/10;
			}
			n = val;
		}
		if(n == 3 || n == 6 || n == 9)
			return true;
		return false;
	}
	
	/**
	 * Sees if the last two digits are divisible by 4
	 * @param n
	 * @return
	 */
	public static boolean isDivisibleBy4(int n){
		return (n%100)%4 == 0;
	}
	
	/**
	 * Checks if the last digit is an 0 or an 5
	 * @param n
	 * @return
	 */
	public static boolean isDivisibleBy5(int n){
		int res = n % 10;
		if(res == 0 || res == 5)
			return true;
		return false;
	}
	
	/**
	 * Must be divisible by 2 and by 3
	 * @param n
	 * @return
	 */
	public static boolean isDivisibleBy6(int n){
		return isDivisibleBy2(n) && isDivisibleBy3(n);
	}
	
	/**
	 * 
	 * @param n
	 * @return
	 */
	public static boolean isDivisibleBy7(int n){
		while(n > 10){
			int last = n % 10;
			n = n/10;
			n = java.lang.Math.abs(n - last * 2);
		}
		if(n == 0 || n == 7)
			return true;
		return false;		
	}
	
	/**
	 * If the last 3 digits are divisible by 8, the whole number is divisible by 8
	 * For calculate if the last tree digits are divisible by 8 checks the first digit
	 * If even checks if the last two digits are divisible by 8
	 * If odd subtracts 4 to the last two digits and checks if divisible by 8
	 * @param n
	 * @return
	 */
	public static boolean isDivisibleBy8(int n){
		if(isDivisibleBy2(n)){
			n = n%1000;
			int lastTwo = n%100;
			int first = n/100;
			
			if(isDivisibleBy2(first)){
				return lastTwo % 8 == 0;
			} else {
				return (lastTwo - 4) % 8 == 0;
			}		
		}
		return false;
	}
	
	/**
	 * Sums the digits of the number, if the sum is divisible by 9 the whole number is divisible by 9
	 * @param n
	 * @return
	 */
	public static boolean isDivisibleBy9(int n){
		while(n > 10){
			int val = 0;
			while(n != 0){
				val = val + n%10;
				n = n/10;
			}
			n = val;
		}
		if(n == 9)
			return true;
		return false;
	}
}
