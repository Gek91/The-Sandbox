 package algorithms.dynamicProgramming;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
  * Given  weights and values of n items put these items in 
  * knapsack of capacity W to get the maximum total value in the knapsack
  * 
  * @author Giacomo Pandini
  */
public class Knapsack {

	private static final List<Integer> weights;
	private static final List<Integer> values;
	
	private static final Map<BigDecimal, Integer> cache;
	
	static {
		weights = new ArrayList<>();
		weights.add(10);
		weights.add(20);
		weights.add(30);
		values = new ArrayList<>();
		values.add(60);
		values.add(100);
		values.add(120);
		
		cache = new HashMap<BigDecimal, Integer>();
	}
	
	/**
	 * Dividi et impera
	 * @param n, length of the rod
	 * @return optimal solution
	 */
	
	public static int knapsack(int n, List<Boolean> used) {

		int q = 0;
		for(int i = 0 ; i < used.size() ; i++) {
			if(!used.get(i)) {
				
				int newN = n - weights.get(i);
					
				//keep track of used items
				List<Boolean> newUsed = new ArrayList<>(used);
				newUsed.set(i, true);
				
				//end recursion case | new recursion call -> get max value of all iteration
				q = Math.max(q, newN < 0 ? 0 : values.get(i) + knapsack(newN, newUsed));
			}
		}
		return q;
	}
	
	/*
	 * Dynamic programming
	 * Same dividi et impera procedure but it uses memory for save already computed solutions
	 */
	public static int memKnapsack(int n, List<Boolean> used) {
		BigDecimal cacheKey = buildCacheKey(n, used);
		Integer cacheValue = cache.get(cacheKey);
		if(cacheValue != null) {
			return cacheValue;
		}
		
		int q = 0;
		for(int i = 0 ; i < used.size() ; i++) {
			if(!used.get(i)) {
				
				int newN = n - weights.get(i);
					
				//keep track of used items
				List<Boolean> newUsed = new ArrayList<>(used);
				newUsed.set(i, true);
				
				//end recursion case | new recursion call -> get max value of all iteration
				q = Math.max(q, newN < 0 ? 0 : values.get(i) + memKnapsack(newN, newUsed));
			}
		}
		cache.put(cacheKey, q);
		return q;
	}
	
	/*
	 * Build cache map key 
	 * Use a decimal
	 *  - integer part is n
	 *  - decimal part is used values ordered
	 *  ES: (40, [1,0,1]) -> 40,101 
	 */
	private static BigDecimal buildCacheKey(int n, List<Boolean> used) {
		BigDecimal result = new BigDecimal(n).setScale(used.size());
		
		StringBuilder decimal = new StringBuilder("0.");
		for(int i = 0 ; i < used.size(); i++) {
			if(used.get(i)) {
				decimal.append("1");
			} else {
				decimal.append("0");
			}
		}
		
		return new BigDecimal(decimal.toString()).add(result);
	}
	
}
