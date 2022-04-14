import java.util.ArrayList;
import java.util.List;

public class GenericUtils {

	/**
	 * Calculate the number of digits of a number
	 * @param n
	 * @return
	 */
	 public static int numlength(int n){
	    if (n == 0) 
	    	return 1;
	    int l;
	    n=Math.abs(n);
	    for(l = 0 ; n > 0 ; ++l)
	        n /= 10;
	    return l;           
	}
	 
	/**
	 * Check if the input number is palindrome
	 * @param n
	 * @return True if is palindrome, False otherwise
	 */
	public static boolean isPalindrome(int n){
		List<Integer> digits = getDigits(n);
		int size = digits.size();
		for(int i=0 ; i<(size/2) ; i++){
			if(digits.get(i) != digits.get(size-1-i))
				return false;
		}
		return true;
	}
	
	/**
	 * Creates an ordered list of the digits of the number
	 * @param n
	 * @return
	 */
	public static List<Integer> getDigits(int n){
		List<Integer> digits = new ArrayList<Integer>();
		int factor = 10;
		while(n != 0){
			int digit = n%factor;
			n = (n - digit)/factor;;
			digits.add(digit);
		}
		return digits;
	}
	
	/**
	 * Calculate the next lexicographc permutation of the input array
	 * @param array
	 */
	public static void nextLexicographicPermutation(int[] array){
		// Find the index of last character of the longest non-increasing suffix
	    int i = array.length - 1;
	    while (i > 0 && array[i - 1] >= array[i])
	        i--;
	    
	    //not last permutation
	    if (i > 0){
		    // Find last element in the array that exceeds array[i - 1] (pivot)
		    int j = array.length - 1;
		    while (array[j] <= array[i - 1])
		        j--;
		    
		    // Swap i-1 with j
		    int temp = array[i - 1];
		    array[i - 1] = array[j];
		    array[j] = temp;
		    
		    // Reverse the suffix
		    j = array.length - 1;
		    while (i < j) {
		        temp = array[i];
		        array[i] = array[j];
		        array[j] = temp;
		        i++;
		        j--;
		    }
	    }
	}
}
