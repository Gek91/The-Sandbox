package projectEuler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class NonAbbundantSum {

	/**
	 * Checks if the number is abbundant
	 * @param n
	 * @return
	 */
	public static boolean isAbbundantNumber(int n){
		return n < AmicableNumber.sumDivisor(n);
	}
	
	/**
	 * Check if the number is perfect
	 * @param n
	 * @return
	 */
	public static boolean isPerfectNumber(int n){
		return n == AmicableNumber.sumDivisor(n);
	}
	
	/**
	 * Checks if the number is deficient
	 * @param n
	 * @return
	 */
	public static boolean isDeficientNumber(int n){
		return n > AmicableNumber.sumDivisor(n);
	}
	
	/**
	 * Returns if the input number is sum of two abundant number
	 * @param n
	 * @return
	 */
	public static boolean isSumOfAbbundant(int n){
		for(int i = 1; i < (n%2 == 0 ? n : n/2+1) ; i++){
			if(isAbbundantNumber(i) && isAbbundantNumber(n-i))
				return true;
		}
		return false;
	}
	
	/**
	 * Returns if the input number is sum of two abundant number. Get in input the set of abbundant number
	 * @param abbundantNumber, set of abbundant number
	 * @param n
	 * @return
	 */
	public static boolean isSumOfAbbundant(int n,Set<Integer> abbundantNumber){
		for(int i = 1; i < (n%2 == 0 ? n : n/2+1) ; i++){
			if(abbundantNumber.contains(i) && abbundantNumber.contains(n-i))
				return true;
		}
		return false;
	}
	
	/**
	 * Returns the set of abbundant number less than n
	 * @param n
	 * @return
	 */
	public static Set<Integer> setOfAbbundantLessThanN(int n){
		Set<Integer> resultSet = new TreeSet<Integer>();
		for(int i=1 ; i<n ; i++){
			if(isAbbundantNumber(i))
				resultSet.add(i);
		}
		return resultSet;
	}	
	
	/**
	 * Returns the list of numbers,less than n, that is not composed of a sum of two abbundant number
	 * @param n
	 * @return
	 */
	public static List<Integer> nonAbbundantNumberList(int n){
		Set<Integer> abbundantNumbers = setOfAbbundantLessThanN(n);
		List<Integer> resultList = new ArrayList<Integer>();
		for(int i = 1; i<=n ; i++){
			if(!isSumOfAbbundant(i,abbundantNumbers))
				resultList.add(i);
		}
		return resultList;
	}
	
	/**
	 * Returns the sum of all numbers, less than n, that is not composed of a sum of two abbundant number
	 * @return
	 */
	public static long sumNonAbbundantNumberList(){
		long sum = 0;
		for(int elem : nonAbbundantNumberList(28123)){
			sum = sum + elem;
		}
		return sum;
	}
	
}
