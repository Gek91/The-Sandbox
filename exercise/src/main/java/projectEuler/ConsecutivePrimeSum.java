package projectEuler;

import java.util.LinkedList;
import java.util.List;

public class ConsecutivePrimeSum {

	
	/**
	 * Generate a list containing the sequence of prime number
	 * @param maxNumber
	 * @return
	 */
	public static List<Integer> primeNumberSequence(int maxNumber){
		List<Integer> result = new LinkedList<Integer>();
		for(int i = 2 ; i <= maxNumber ; i++)
			if(Prime10001.isPrime(i))
				result.add(i);
		return result;
	}
	
	/**
	 * Find the prime number with the maximum number of prime numbers that their sum give the number
	 * @param maxNumber
	 * @return
	 */
	//TODO: TOOO SLOOOOWW THI THIS:
	//Keep finding primes and store them into array until the cumulative sum of primes is just > 1000000.
	//Also store the latest cumulative sum everytime you find a new prime no. When the sum is just > 1 mil. 
	//you have the latest sum stored (initial sum) , now subtract the terms from right( starting from the biggest
	//prime in the array) from the initial sum value until you obtain a prime number ( to check whether the obtained sum is 
	//a prime number , you just need to check the remainder after dividing that number by all the preceeding prime numbers 
	//upto its square root. ). Store this prime number(right) alongwith the number(right) of terms added to get it. 
	//Now take the initial sum and start subtracting terms from left ( starting from smallest prime no. in the array)
	//from the initial sum and until you  obtain a prime number. Store this prime number(left) along with number(left) of 
	//terms added to get to it. Now check whether number of terms (left) is greater or number of terms(right) is greater.
	//The greater number of terms is the answer.
	public static int consecutivePrimeSum(int maxNumber){
		List<Integer> primeNumbers = primeNumberSequence(maxNumber);
		int numElem = 0; //Number of prime number added to compose the number
		int value = -1; //prime number composed by the max number of consecutive prime number
		for(int i = 0 ; i < primeNumbers.size() ; i++){ //Cicle on the stating point
			int j = i;
			int sum = primeNumbers.get(i); 
			while(sum < maxNumber && j < primeNumbers.size()-1){ //end cicle condition
				if(primeNumbers.contains(sum) && j-i+1 > numElem){
					numElem = j-i+1;
					value = sum;
				}
				j++;
				sum = sum + primeNumbers.get(j);
			}
		}
		System.out.println(value);
		return numElem;
	}
}
