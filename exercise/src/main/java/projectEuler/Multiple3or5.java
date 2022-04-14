package projectEuler;

import java.util.Set;
import java.util.TreeSet;

public class Multiple3or5 {
	
	/**
	 * Calculates the sum of the natural multiple of 3 or 5 < n
	 * @param n, upper limit
	 * @return sum of the multiple
	 */
	public static int sumOfmultiple(int n){
		//Contains alla the multiple
		Set<Integer> multiples = new TreeSet<Integer>();
		int number = 3;
		while(number < n){
			multiples.add(number);
			number = number + 3;
		}
		
		number = 5;
		while(number < n){
			multiples.add(number);
			number = number +5;
		}
		
		int result = 0;
		for(int elem : multiples){
			result = result + elem;
		}
		return result;
	}
	
	/**
	 * Short implementation
	 * @param n
	 * @return
	 */
	public static int sumOfmultiple2(int n){
		int result = 0;
		for(int i = 0; i < n; i++){
			if(i%3 == 0 || i%5 == 0)
				result = result + i;
		}
		return result;
	}

}
