package projectEuler;

import java.security.InvalidParameterException;

public class PythagoreanTriplet {

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
	
	/**
	 * Retrieve the phytagoran triplet with the summatory a + b + c equals to sum input parameter
	 * @param sum, sum of the triplect factor
	 * @return array of the triplet
	 */
	public static int[] pythagoreanTripletSum(int sum){
		int m=1;
		while(1 < sum){
			int n = m+1;
			int sumCalc = 0;
			while(sumCalc < sum){
				int[] tripletCalc = new int[3];
				tripletCalc[0] = n*n - m*m ;
				tripletCalc[1] = 2 * n * m;
				tripletCalc[2] = n*n + m*m;
				sumCalc = tripletCalc[0] + tripletCalc[1] + tripletCalc[2];
				if(sumCalc == sum)
					return tripletCalc;
				n++;
			} 
			m++;
		}
		return null;
	}
	
	public static long pythagoreanTripletSumProduct(int sum){
		int[] result = pythagoreanTripletSum(sum);
		if(result != null){
			return result[0] * result[1] * result[2];
		}
		return 0;
	}
}
