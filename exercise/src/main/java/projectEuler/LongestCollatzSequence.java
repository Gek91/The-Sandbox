package projectEuler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestCollatzSequence {
	
	/**
	 * Generate collaz sequence of the number
	 * @param n
	 * @param cSequence
	 */
	public static void collatzSequence(long n,List<Long> cSequence){
		cSequence.add(n);
		if(n != 1)
			if(n % 2 == 0)
				collatzSequence(n/2, cSequence);
			else
				collatzSequence(3*n + 1, cSequence);
	}
	
	/**
	 * Generates map of the collatz sequences length of n. Uses the pre builded collatz sequences to build the n collatz sequence length 
	 * @param n
	 * @param collatzMap
	 * @return
	 */
	public static long generateCollatzSqueneceMap(long n, Map<Long,Long> collatzMap){
		//If the sequence length is already builded uses it
		if(collatzMap.containsKey(n)){
			return collatzMap.get(n);
		} else {
			//First number of the sequences
			if(n == 1L){
				collatzMap.put(1L,1L);
				return 1;
			}				
			else { //If the sequence doesn't exist in the map, recursive calculation
				long value;
				if(n % 2 == 0){
					value = generateCollatzSqueneceMap(n/2,collatzMap);
				}
				else{
					value = generateCollatzSqueneceMap(3*n + 1,collatzMap);
				}
				//Save the value of length of the sequence 
				collatzMap.put(n, value + 1);
				return value + 1;
			}
		}
	}
	
	/**
	 * Returns the starting number of the collatz sequence that have the max length sequence less than input n
	 * @param n
	 * @return
	 */
	public static long maxLenghtCollatzSequence(long n){
		Map<Long,Long> collatzMap = new HashMap<Long,Long>();
		//Using the map generate the collatz sequence of every number under n
		for(int i=1 ; i < n ; i++){
			generateCollatzSqueneceMap(i,collatzMap);
		}
		long maxKey = -1;
		long maxValue = 0;
		//find the max length sequence
		for(long key : collatzMap.keySet()){
			long value = collatzMap.get(key);
			if(maxValue < value){
				maxKey = key;
				maxValue = value;
			}
		}
		return maxKey;
			
	}
}
