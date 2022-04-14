package projectEuler;

import java.util.Map;

public class Prime10001 {

	/**
	 * Checks if the input number is a prime number
	 * @param n
	 * @return true if is prime number, false otherwise
	 */
	public static boolean isPrime(int n){
		Map<Long,Integer> map = SmallestMultiple.primeFactorsMap(n);
		if(map.keySet().size() == 1 && map.containsValue(1))
			return true;
		return false;
	}
	
	/**
	 * Check if the number is prime, the right way
	 * Primes number >3 can be written in the form 6k+/-1
	 * Any number n can have only one primefactor greater than square(n) .
	 * @param n
	 * @return
	 */
	public static boolean isPrime1(int n){
		if(n == 1 || n == 0)
			return false;
		else if(n < 4)
			return true; //2 and 3 are prime
		else if(n % 2 == 0)
			return false;
		else if(n < 9)
			return true; //we have already excluded 4,6 and 8.
		else if(n % 3 == 0)
			return false;
		else{
			long r = (long) Math.floor(Math.sqrt(n)); // n rounded to the greatest integer r so that r*r<=n
			long f = 5L;
			//Check the existence of primefactor <square(n) in the number n
			while(f <= r){
				if(n % f == 0) //6k-1
					return false;
				if(n % (f+2) == 0) //6k+1
					return false;
				f=f+6; //Next value
			}
			return true; //All other cases
		}		
	}
	
	/**
	 * Returns the nth prime number
	 * @param n
	 * @return
	 */
	public static int nthPrime(int n){
		if(n > 0){
			int count = 0;
			int i = 1;
			while(count != n){
				i++;
				if(isPrime(i))
					count++;
			}
			return i;
		}
		return -1;
	}
	
}
