package projectEuler;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class LargerPrimeFactor {

	/**
	 * Finds larger prime factor of the input number
	 * @param n, input number
	 * @return larger prime factor
	 */
	public static long largerPrimeFactor(long n){
		//Used to save the prime factor
		
		return Collections.max(primeFactors(n));
	}
	
	public static Set<Long> primeFactors(long n){
		Set<Long> factor = new TreeSet<Long>();
		long d = 2;
		while(n > 1){
			if(n % d == 0){
				n = n / d;
				factor.add(d);
			}
			d++;
			//With this -> O(sqrt(n))
			if(d*d > n)
		        if(n > 1){
		        	factor.add(n);
		        	break;
		        }
		}
		return factor;
	}
}
