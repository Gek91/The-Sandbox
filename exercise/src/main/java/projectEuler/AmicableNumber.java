package projectEuler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class AmicableNumber {

	/**
	 * Return a set containing the divisor of the input number
	 * @param n
	 * @return
	 */
	public static Set<Integer> divisors(long n){
		Set<Integer> factor = new TreeSet<Integer>();
		factor.add(1);
		for(int i = 2 ; i < n ; i++){
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
	 * Calculate the sum of the divisor of the input number
	 * @param n
	 * @return
	 */
	public static int sumDivisor(long n){
		Set<Integer> divisors = divisors(n);
		int sum = 0;
		for(int elem : divisors){
			sum = sum + elem;
		}
		return sum;
	}
	
	/**
	 * Return a list of amicable number that are less than the inmput number
	 * @param n
	 * @return
	 */
	public static List<Integer> amicableNumberListLessThanN(int n){
		List<Integer> resultList = new ArrayList<Integer>();
		Map<Integer,Integer> numbersSumDivisorsMap = new HashMap<Integer,Integer>();
		numbersSumDivisorsMap.put(1, 0);
		for(int i = 2; i<n; i++){
			int sumDivisor;
			//If even, his divisor are the same of its half plus its half
			sumDivisor = sumDivisor(i);
			numbersSumDivisorsMap.put(i, sumDivisor);
			//Check amicable
			if(sumDivisor < i && numbersSumDivisorsMap.get(sumDivisor) == i){
				resultList.add(i);
				resultList.add(sumDivisor);
			}
		}
		return resultList;
	}
	
	public static int sumAmicableNumbersLessthanN(int n){
		List<Integer> amicableNumber = amicableNumberListLessThanN(n);
		int sum = 0;
		for(int number : amicableNumber){
			sum = sum + number;
		}
		return sum;
	}
}
