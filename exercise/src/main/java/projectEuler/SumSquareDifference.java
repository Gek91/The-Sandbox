package projectEuler;

import java.util.List;

public class SumSquareDifference {
	
	/**
	 * Calculates the sum of square of the numbers in the list
	 * @param elements, list of integers
	 * @return sum of square
	 */
	public static int sumOfSquare(List<Integer> elements){
		int result = 0;
		for(int element : elements){
			result = result + element*element;
		}
		return result;
	}
	
	/**
	 * Calculates the square of the sum of the number in the list
	 * @param elements, list of integers
	 * @return the square of the sum
	 */
	public static int squareOfSum(List<Integer> elements){
		int result = 0;
		for(int element : elements){
			result = result + element;
		}
		return result*result;
	}
	
	/**
	 * Returns the difference between the sum of the square of the element in a list and the square of the sum
	 * @param elements, list of integers
	 * @return
	 */
	public static int differenceSumOfSquareSquareOfSum(List<Integer> elements){
		return Math.abs(sumOfSquare(elements) - squareOfSum(elements));
	}
}
