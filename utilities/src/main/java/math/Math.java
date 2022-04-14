package math;

import java.math.BigInteger;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Math {


	/**
	 * Returns the set of prime factors of the input number 
	 * @param n
	 * @return
	 */
	public static Set<Long> primeFactors(long n){
		Set<Long> factor = new TreeSet<Long>();
		long d = 2;
		while(n > 1 && d <= n){
			if(n % d == 0){
				n = n / d;
				factor.add(d);
			}
			d++;
			//With this -> (sqrt(n))
			if(d*d == n)
		        if(n > 1){
		        	factor.add(n);
		        	break;
		        }
		}
		return factor;
	}
	
	/**
	 * Builds the map<prime factor, occurences> of the input number
	 * @param n
	 * @return
	 */
	public static Map<Long,Integer> primeFactorsMap(long n){
		Map<Long,Integer> factor = new HashMap<Long,Integer>();
		long d = 2;
		while(n > 1){
			while(n % d == 0){
				n = n / d;
				if(factor.containsKey(d))
					factor.put(d, factor.get(d)+1);
				else
					factor.put(d,1);
			}
			d++;
		}
		return factor;
	}
	
	/**
	 * Calculate the minimum shared multiple of the list 
	 * @param numbers
	 * @return
	 */
	public static int MCM(List<Integer> numbers){
		Map<Long,Integer> maxFactor = new HashMap<Long,Integer>();
		//Calculates the prime factors of every number of the list
		for(int num : numbers){
			Map<Long,Integer> factors = primeFactorsMap(num);
			//Save for every factor the max number of occorence 
			for(Long factor : factors.keySet()){
				if(maxFactor.containsKey(factor)){
					maxFactor.put(factor, java.lang.Math.max(maxFactor.get(factor), factors.get(factor)));
				} else {
					maxFactor.put(factor, factors.get(factor));
				}
			}
		}
		//Calculates the MCM starting from the map of the factor- max number of occurences
		if(maxFactor.size() > 0){
			int result = 1;
			for(long factor: maxFactor.keySet() ){
				result = (result * (int) java.lang.Math.pow(factor, maxFactor.get(factor)));
			}
			return result;
		}
		return 0;
	}
	
	/**
	 * Check if the number is prime, the right way
	 * Primes number >3 can be written in the form 6k+/-1
	 * Any number n can have only one primefactor greater than square(n) .
	 * @param n
	 * @return
	 */
	public static boolean isPrime(int n){
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
			long r = (long) java.lang.Math.floor(java.lang.Math.sqrt(n)); // n rounded to the greatest integer r so that r*r<=n
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
	  * Check if the number is a prime number using a map like a cache
	  * 
	  * @param n
	  * @param cache
	  * @return
	 * @throws Exception 
	  */
	 public static boolean isPrimeWithCache(int n, Map<Integer,Boolean> cache) throws Exception{
		 if(cache != null){
			 Boolean cacheRes = cache.get(n);
			 if(cacheRes == null){
				 cacheRes = isPrime(n);
				 cache.put(n, cacheRes);
				 return cacheRes;
			 } else 
				 return cacheRes;
		 }
		throw new Exception("Cache not initialized");
	 
	 }
	 
		/**
		 * returns the list of prime numbers below n
		 * @param n
		 * @return
		 */
		public static List<Long> primesBelowN(long n){
			List<Long> primesList = new ArrayList<Long>();
			for(int i = 0 ; i < n ; i++){
				if(isPrime(i))
					primesList.add((long)i);
			}
			return primesList;
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
	 * Return a set containing the divisor of the input number
	 * @param n
	 * @return
	 */
	public static Set<Long> divisors(long n) {
		
		Set<Long> factor = new TreeSet<Long>();
		factor.add(1l);
		factor.add(n);
		
		for(long i = 2 ; i < n ; i++){
			
			if(n % i == 0){
				
				if(factor.contains(i)) {
					
					
				}
				
				factor.add(i);
				factor.add((int)n/i);
			}
		}
		
		return factor;
	}
	
	/**
	 * Return a set containing the proper divisor of the input number
	 * @param n
	 * @return
	 */
	public static Set<Long> properDivisors(long n) {
		
		Set<Long> factor = new TreeSet<Long>();
		factor.add(1l);
		
		for(long i = 2 ; i < n ; i++){
			
			if(n % i == 0){
				
				if(factor.contains(i)) {
					
					
				}
				
				factor.add(i);
				factor.add((int)n/i);
			}
		}
		
		return factor;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	///Special sequence
	
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
	 * Calculate triangle number
	 * @param n
	 * @return
	 */
	public static int triangleNumbers(int n){
		return  ( n * (n+1))/2;
	}
	
	/**
	 * Calculate the factorial of the input number
	 * @param n
	 * @return
	 */
	public static BigInteger factorial(int n){
		if(n > 1){
			return factorial(n-1).multiply(BigInteger.valueOf(n));
		} 
		return BigInteger.valueOf(1);
	}

	/**
	 * Create a pythagorean Triplet from the two input value
	 * @param m < n
	 * @param n
	 * @return array of the triplet
	 */
	public static int[] pythagoreanTriplet(int m, int n){
		if(m<n){
			int[] triplet = new int[3];
			triplet[0] = n*n - m*m ;
			triplet[1] = 2 * n * m;
			triplet[2] = n*n + m*m;
			return triplet;	
		}
		throw new InvalidParameterException();
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	//Number String Operation
	
	/**
	 * Multiply the input string for the input integer
	 * @param a, string to multiply
	 * @param b, second factor of the multiplication
	 * @return string containing the result
	 */
	public static String stringMultNumber(String a, int b){
		StringBuilder result = new StringBuilder();
		int report = 0;
		for(int i = a.length()-1 ; i>=0; i--){
			int val=Character.getNumericValue(a.charAt(i)) * b + report;
			report = val / 10;
			result.insert(0, val%10);
		}
		if(report != 0)
			result.insert(0,report);
		return result.toString();
	}
	
	/**
	 * Calculate the factorial of the number, return the result in a string
	 * @param n
	 * @return
	 */
	public static String stringFactorial(int n){
		String result="1";
		for(int i=1; i <= n ; i++){
			result = stringMultNumber(result,i);
		}
		return result;
	}
	
	/**
	 * Generate string containing the result of pow(a,b)
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static String stringPow(int a, int b){
		String result = ""+a;
		for(int i = 1 ; i < b ; i++){
			result = stringMultNumber(result,a);
		}
		return result;
	}
	
	/**
	 * Calculate the sum of two string of numbers
	 * 
	 * @param a
	 * @param b
	 * @return String with the result
	 */
	public static String stringSum(String a, String b){
		StringBuilder result = new StringBuilder();
		int lengthA = a.length()-1;
		int lengthB = b.length()-1;
		int report = 0;
		int maxLength = java.lang.Math.max(lengthA, lengthB);
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
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	//Abbundant number
	
	/**
	 * Calculate the sum of the divisor of the input number
	 * @param n
	 * @return
	 */
	public static long sumDivisors(long n) {
		Set<Long> divisors = divisors(n);
		long sum = 0;
		for(long elem : divisors){
			sum = sum + elem;
		}
		return sum;
	}
	
	public static long sumProperDivisors(long n) {
		Set<Long> divisors = properDivisors(n);
		long sum = 0;
		for(long elem : divisors){
			sum = sum + elem;
		}
		return sum;
	}
	
	/**
	 * Checks if the number is abbundant
	 * @param n
	 * @return
	 */
	public static boolean isAbbundantNumber(int n){
		return n < sumProperDivisors(n);
	}
	
	/**
	 * Check if the number is perfect
	 * @param n
	 * @return
	 */
	public static boolean isPerfectNumber(int n){
		return n == sumProperDivisors(n);
	}
	
	/**
	 * Checks if the number is deficient
	 * @param n
	 * @return
	 */
	public static boolean isDeficientNumber(int n){
		return n > sumProperDivisors(n);
	}
}
