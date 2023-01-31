package algorithms.patternMatching;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PatternMatching {

	public static Optional<Integer> naive(String text, String pattern){

	    int patternLength = pattern.length();
	    int textLength = text.length();

	    for (int i = 0; i < textLength - patternLength +1; i++)  {

			int j = 0;
	        while(j < patternLength && pattern.charAt(j) == text.charAt(i+j)) {
	            j = j+1;
	        }

	        if(j >= patternLength)
	        	return Optional.of(i);
	    }
	    return Optional.empty();
	}

	////////////////////////////////////////////////////////////////////////////////////////////////

	public static List<Integer> knuthMorrisPratt(String text, String pattern) {

		List<Integer> result = new ArrayList<>();

	    int patternLength = pattern.length();
	    int textLength = text.length();

	    //add sentinels
		pattern=pattern+'$';
		text=text+'x';

		int[] deviations = getDeviationsKnuthMorrisPratt(pattern, patternLength);
	    
	    //Search O(n)
	    int patternIndex = 0;
	    int textIndex = patternIndex;

	    while (textIndex <= textLength - patternLength) {
	        while( pattern.charAt(patternIndex) == text.charAt(textIndex + patternIndex) ){
				patternIndex++;
	        }

	        if(patternIndex >= patternLength){
				result.add(textIndex);
	        }

	        textIndex = textIndex + deviations[patternIndex]+1;
			patternIndex = Math.max(0, patternIndex - deviations[patternIndex]-1);
	    }

	    return result;
	}

	private static int[] getDeviationsKnuthMorrisPratt(String pattern, int patternLength) {
		int[] deviations = new int[patternLength+1];

		//Prefix computation O(m)
		int[] prefixes = prefixFunction(pattern);

		for (int j = 0; j <= patternLength; j++){
			deviations[j] = j;
		}
		for (int h = patternLength-1 ; h >= 0; h--) {
			deviations[1 + h + prefixes[1+h] ] = h;
		}

		return deviations;
	}

	private static int[] prefixFunction(String inputString) {
	    int inputStringLength = inputString.length();
	    int[] result = new int[inputStringLength];
	    result[0] = inputStringLength;
	    int h=0;
	    int left,right;

	    while(inputString.charAt(0+h) == inputString.charAt(1+h) ){
	        h=h+1;
	    }
	    result[1] = h;

	    left = 1;
	    right = h;
	    for (int i=2; i < inputStringLength; i++){
	        if(right < i) {

	        	h=0;
	            while ((i+h < inputStringLength) && (inputString.charAt(h) == inputString.charAt(i+h)) ){
	                h = h+1;
	            }

	            result[i] = h;
	            left = i;
	            right = i+h-1;

	        } else if(result[i-left] < right-i+1){
	            result[i] = result[i-left];
	        } else {
	            h = right-i+1;

	            while (i+h<inputStringLength && inputString.charAt(h) == inputString.charAt(i+h)) {
	                h = h+1;
	            }

	            result[i] = h;
	            left = i;
	            right = i+h-1;
	        }
	    }
	    return result;
	}

	////////////////////////////////////////////////////////////////////////////////////////////

	public static List<Integer> matchingPrefix(String text, String pattern) {
		List<Integer> result = new ArrayList<>();

		String input = pattern + '$' + text;

	    int[] res= prefixFunction(input);

	    int patternlenght = pattern.length();
	    for(int i=0; i < input.length() - (patternlenght + 1) ; i++) {
	        if(res[i+patternlenght+1]==patternlenght) {
				result.add(i);
			}
	    }

	    return result;
	}

	////////////////////////////////////////////////////////////////////////////////////////////

	public static List<Integer> boyerMoore(String text, String pattern) {

		List<Integer> result = new ArrayList<>();

		int patternLength = pattern.length();
	    int textLength = text.length();

		int[] deviations = getDeviationsBoyerMoore(pattern, patternLength, text);
	    
		//Search
	    int i=0;
	    while (i <= textLength - patternLength){
	        int j = patternLength -1;
	        while (pattern.charAt(j) == text.charAt(i+j)){
	            j--;
	        }

	        if (j==0){
	            result.add(i);
	        }

	        i = i + deviations[j];
	    }

	    return result;
	}

	private static int[] getDeviationsBoyerMoore(String pattern, int patternLength, String text) {
		int[] deviations = new int[patternLength+1];
		pattern = '$'+ pattern;
		text ='x'+ text;
		String patternReverse = reverse(pattern);

		int[] pref= prefixFunction(patternReverse);

		for (int j = patternLength; j>=0; j--){
			int h = patternLength;
			if(j == patternLength-pref[j])
				h=j;
			deviations[j]=h;
		}

		for (int h = patternLength; h>=1; h--){
			int j = patternLength-pref[h];
			if (h < j) {
				deviations[j]=h;
			}
		}

		return deviations;
	}

	private static String reverse(String S)  {
	    String H = "";
	    for(int i=0; i<S.length(); i++)
	    {
	        H = S.charAt(i) + H;
	    }
	    return H;
	}

	//////////////////////////////////////////////////////////////////////////////////////////

	public static List<Integer> shiftAnd(String text, String pattern) {

		List<Integer> result = new ArrayList<>();

		int patternLength = pattern.length();
	    int textLength = text.length();

		boolean resultVector[] = resultVector(text, pattern);
		boolean[][] booleanMatrix = booleanMatrix(pattern);

	    for(int i=1; i < textLength; i++) {
	        bitShift(resultVector,patternLength);

	        for(int j=0; j < patternLength ; j++) {
				resultVector[j] = booleanMatrix[((int)(text.charAt(i)))-33][j] && resultVector[j];
	        }

	        if(resultVector[patternLength-1]==true)
				result.add(i-patternLength+1);
	    }
		return result;
	}

	private static boolean[] resultVector(String text, String pattern) {

		boolean R[] = new boolean[pattern.length()];

		for(int i=0 ; i < pattern.length() ; i++)
			R[i]=false;

		if(pattern.charAt(0) == text.charAt(0))
			R[0]=true;

		return R;
	}

	private static boolean[][] booleanMatrix(String pattern) {
		boolean U[][] = new boolean [126-33+1][pattern.length()]; //Matrice che contiene i vettori U[j] di lunghezza m, contengono le posizioni nel pattern del catarrete j

		//Creazione vettori U[j]
		for(int j=0; j<=126-33;j++) {
			for(int i=0; i < pattern.length() ;i++)
			{
				if(pattern.charAt(i) == (char)(j+33))
					U[j][i]=true;
				else
					U[j][i]=false;
			}
		}

		return U;
	}

	private static void bitShift(boolean vec[],int n) {
	    boolean h1 = vec[0];
	    boolean h2;

	    for( int i=1; i<n ; i++ ){
	        h2 = vec[i];
	        vec[i] = h1;
	        h1 = h2;
	    }

	    vec[0]= true;
	}
	
}
