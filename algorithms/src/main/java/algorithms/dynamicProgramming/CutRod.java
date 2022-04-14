package algorithms.dynamicProgramming;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a rod of length n i must find the most profitable way of cutting the rod in pieces maximizing the profit 
 * Rod Cutting problem has an optimal structure: optimal solution of the problem incorporate optimal solutions of subproblems
 * 
 * @author Giacomo Pandini
 */

public class CutRod {

	//Map length-price of cut pieces
	private static final Map<Integer,Integer> mapLengthPrice;
	
	static{
		mapLengthPrice = new HashMap<Integer,Integer>();
		mapLengthPrice.put(1, 1);
		mapLengthPrice.put(2, 5);
		mapLengthPrice.put(3, 8);
		mapLengthPrice.put(4, 9);
		mapLengthPrice.put(5, 10);
		mapLengthPrice.put(6, 17);
		mapLengthPrice.put(7, 17);
		mapLengthPrice.put(8, 20);
	}
		
	/**
	 * Dividi et impera solution
	 * We can see the problem like we cut from the rod a piece of the rod and use the optimal solution of the resulting rod plus the 
	 * optimal solution of the cutted rod to make the optimal solution of the whole rod
	 * Top-Down Solution, running time exponential
	 * @param n, length of the rod
	 * @return optimal solution
	 */
	public static int cutRod(int n){
		
		int q = 0;
		for(int i : mapLengthPrice.keySet()) {
				
			int newN = n - i;
			
			//end recursion case | new recursion call -> get max value of all iteration
			q = Math.max(q, newN < 0 ? 0 : mapLengthPrice.get(i) + cutRod(newN));
		}
		return q;
	}
		
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Dynamic Programming
	// The graph of the subproblem help to calculate the running time of the algorithm. Since we calculate every subproblem 
	// just only once , the running time is the sum of the time needed for every subproblem.
	// The time to compute the solution of subproblem is proportional to the number of outgoing edge in the problem vertex
	// in the subproblem graph.
	
	
	/**
	 * Dynamic programming
	 * Same procedure as cutRod but it uses the memory for saving already computed solutions instead of recompute them
	 * Is like a DFS on the subproblem graph
	 * Running time quadratic
	 * @param n, length of the rod
	 * @return optimal solution
	 */
	public static int memCutRod(int n){
		int[] saveArray = new int[n+1];
		for(int i = 0; i < n+1 ; i++)
			saveArray[i] = -1;
		return memCutRodAux(n, saveArray);
	}
	
	private static int memCutRodAux(int n, int[] saveArray){
		if(saveArray[n] > 0)
			return saveArray[n];
		int q = 0;
			
		for(int i : mapLengthPrice.keySet()){
			
			int newN = n - i;
			
			q = Math.max(q, newN < 0 ? 0 : mapLengthPrice.get(i) + memCutRodAux(newN, saveArray));
		}

		saveArray[n] = q;
		return q;
	}
		
	/**
	 * Dynamic programming
	 * Bottom Up approach, sort the subproblem by size using the concept that the problem of size i is smaller
	 * than a problem of size j with i < j. Resolve the subproblem from size 0 to size n in order so one problem is 
	 * solved after his subproblem has been solved. Is like a reverse topological search on the subproblem graph
	 * Running time quadratic
	 * @param n, length of the rod
	 * @return optimal solution 
	 */
	public static int bottomUpCutRod(int n){
		int[] saveArray = new int[n+1];
		saveArray[0] = 0;
		for(int j = 1 ; j<=n ; j++){
			int q = -1;
			for(int i = 1 ; i<=j ; i++){
				q = Math.max(q, mapLengthPrice.get(i) + cutRod(j-i));
			}
			saveArray[j] = q;
		}
		return saveArray[n];
	}
	
	/**
	 * Calculate the optimal solution, not only the its value
	 * @param n, length of the rod
	 * @return optimal solution 
	 */
	public static int[] extendedBottomUpCutRod(int n){
		int[] saveArray = new int[n+1];
		int[] solutions = new int[n+1];
		saveArray[0] = 0;
		for(int j = 1 ; j<=n ; j++){
			int q = -1;
			for(int i = 1 ; i<=j ; i++){
				int value = mapLengthPrice.get(i) + cutRod(j-i);
				if(q < value){
					q = value;
					solutions[j] = i;
				}
			}
			saveArray[j] = q;
		}
		return solutions;
	}
	

}
