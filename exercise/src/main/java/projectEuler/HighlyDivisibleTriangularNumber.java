package projectEuler;

import java.security.InvalidParameterException;
import java.util.Set;
import java.util.TreeSet;

public class HighlyDivisibleTriangularNumber {


	/**
	 * Calculate the triangular number of the input number 
	 * (sum of all the natural number until n)
	 * @param n
	 * @return
	 */
	public static long triangularNumber(long n){
		if(n == 1)
			return n;
		return triangularNumber(n-1) + n;
	}
	
	/**
	 * Counts the number of factor of the input number
	 * @param n
	 * @return
	 */
	public static int countFactorNumber(long n){
		return factorNumbers(n).size();
	}
	
	/**
	 * Builds a set containing the factor number of the input number
	 * @param n
	 * @return
	 */
	public static Set<Integer> factorNumbers(long n){
		Set<Integer> factor = new TreeSet<Integer>();
		for(int i = 1 ; i < n ; i++){
			if(n % i == 0){
				if(factor.contains(i))
					return factor;
				factor.add(i);
				factor.add((int)n/i);
			}
		}
		return factor;
	}
	
	/**
	 * returns the first trinagular number with more than n factor 
	 * @param n, number of factor
	 * @return 
	 */
	public static long triangularNumberWithMoreThanNFactor(int n){
		if(n < 1)
				throw new InvalidParameterException();
		if(n == 1)
			return 1;
		int factor = 2;
		int i = 1;
		int val = 0;
		while(factor <= n){
			val = val + i;
			factor = countFactorNumber(val);
			i++;
		}
		return val;
	}
}
