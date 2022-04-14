package projectEuler;

import java.util.ArrayList;
import java.util.List;

public class SumOfPrimes {

	/**
	 * returns the list of prime numbers below n
	 * @param n
	 * @return
	 */
	public static List<Long> primesBelowN(long n){
		List<Long> primesList = new ArrayList<Long>();
		for(int i = 0 ; i < n ; i++){
			if(Prime10001.isPrime1(i))
				primesList.add((long)i);
		}
		return primesList;
	}
	
	public static long primesBelowNSum(long n){
		long sum = 0;
		for(long prime : primesBelowN(n)){
			sum = sum + prime;
		}
		return sum;
	}
}
