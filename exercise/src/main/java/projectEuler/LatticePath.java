package projectEuler;

import java.math.BigInteger;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class LatticePath {

	/**
	 * Calculate the number of permutation of n element with repetition 
	 * @param n, number of element
	 * @param types, list containing the number of object per type
	 * @return
	 */
	public static long permutationWithRepeat(int n, List<Integer> types){
		int sumCheck = 0;
		for(int type : types){
			sumCheck = sumCheck + type;
		}
		if(n == sumCheck){
			BigInteger permMultType = BigInteger.valueOf(1);
			for(int type : types){
				permMultType = permMultType.multiply(factorial(type)) ;
			}
			return factorial(n).divide(permMultType).longValue();
		} else 
			throw new InvalidParameterException(); 
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
	 * Return the number of different path in a square lattice of n side
	 * @param n
	 * @return
	 */
	public static long countLatticePath(int n){
		int numberOfMoves = n * 2;
		List<Integer> types = new ArrayList<Integer>();
		types.add(n);
		types.add(n);
		return permutationWithRepeat(numberOfMoves,types);
	}
}
