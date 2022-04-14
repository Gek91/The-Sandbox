package projectEuler;

public class LexicographicPermutations {

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
