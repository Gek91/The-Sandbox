package projectEuler;

import java.util.Map;
import java.util.TreeMap;

public class CircularPrimes {

	/**
	 * Calculate the circular value of the input number
	 * @param n
	 * @return
	 */
	public static int[] circularValues(int n){
		int length = numlength(n);
		int[] result = new int[length-1];
		for(int i = 0 ; i < length-1 ; i++){
			n = n/10 + n%10 * (int)(Math.pow(10, length-1));
			result[i] = n;
		}
		return result;
	}
	
	/**
	 * Calculate the number of digits of a number
	 * @param n
	 * @return
	 */
	 public static int numlength(int n){
	    if (n == 0) 
	    	return 1;
	    int l;
	    n=Math.abs(n);
	    for(l = 0 ; n > 0 ; ++l)
	        n /= 10;
	    return l;           
	}
	
	 /**
	  * Calculate the number of prime number that is circular with value less than maxNumber
	  * 
	  * @param maxNumber
	  * @return
	  */
	 public static int numberOfPrimeCircular(int maxNumber){
		 int count = 0;
		 Map<Integer,Boolean> cache = new TreeMap<Integer,Boolean>();
		 for(int i = 2 ; i <= maxNumber ; i++){
			 if(isPrimeWithCache(i,cache)){
				 boolean circularPrime = true;
				for(int val : circularValues(i)){
					if(!Prime10001.isPrime1(val)){
						circularPrime = false;
						break;
					}
				}
				if(circularPrime)
					count++;
			 }
		 }
		 return count;
	 }
	 
	 /**
	  * Check if the number is a prime number using a map like a cache
	  * 
	  * @param n
	  * @param cache
	  * @return
	  */
	 public static boolean isPrimeWithCache(int n, Map<Integer,Boolean> cache){
		 Boolean cacheRes = cache.get(n);
		 if(cacheRes == null){
			 cacheRes = Prime10001.isPrime1(n);
			 cache.put(n, cacheRes);
			 return cacheRes;
		 } else 
			 return cacheRes;
	 
	 }
}
