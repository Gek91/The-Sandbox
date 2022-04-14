package projectEuler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmallestMultiple {

	/**
	 * Builds the map<prime factor, occurence> of the input number
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
	
	public static int MCM(List<Integer> numbers){
		Map<Long,Integer> maxFactor = new HashMap<Long,Integer>();
		//Calculates the prime factors of every number of the list
		for(int num : numbers){
			Map<Long,Integer> factors = primeFactorsMap(num);
			//Save for every factor the max number of occorence 
			for(Long factor : factors.keySet()){
				if(maxFactor.containsKey(factor)){
					maxFactor.put(factor, Math.max(maxFactor.get(factor), factors.get(factor)));
				} else {
					maxFactor.put(factor, factors.get(factor));
				}
			}
		}
		//Calculates the MCM starting from the map of the factor- max number of occurences
		if(maxFactor.size() > 0){
			int result = 1;
			for(long factor: maxFactor.keySet() ){
				result = (result * (int) Math.pow(factor, maxFactor.get(factor)));
			}
			return result;
		}
		return 0;
	}
}
