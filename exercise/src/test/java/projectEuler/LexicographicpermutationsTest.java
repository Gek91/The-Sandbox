package projectEuler;

import org.junit.Test;


public class LexicographicpermutationsTest {

	@Test
	public void nextLexicographicPermutation(){
		int[] sequence = {0,1,2};
		System.out.println(sequence[0] + "," + sequence[1] + "," + sequence[2]);
		LexicographicPermutations.nextLexicographicPermutation(sequence);
		System.out.println(sequence[0] + "," + sequence[1] + "," + sequence[2]);
		LexicographicPermutations.nextLexicographicPermutation(sequence);
		System.out.println(sequence[0] + "," + sequence[1] + "," + sequence[2]);
		LexicographicPermutations.nextLexicographicPermutation(sequence);
		System.out.println(sequence[0] + "," + sequence[1] + "," + sequence[2]);
		LexicographicPermutations.nextLexicographicPermutation(sequence);
		System.out.println(sequence[0] + "," + sequence[1] + "," + sequence[2]);
		LexicographicPermutations.nextLexicographicPermutation(sequence);
		System.out.println(sequence[0] + "," + sequence[1] + "," + sequence[2]);	
	}
	
	@Test
	public void permutation(){
		int[] sequence = {0, 1, 2, 3, 4, 5, 6, 7, 8 , 9};
		for(int i=0 ; i < 1000000-1; i++){
			LexicographicPermutations.nextLexicographicPermutation(sequence);
		}
		System.out.println("" + sequence[0] + sequence[1] + sequence[2] + sequence[3] + sequence[4]
				 + sequence[5] + sequence[6] + sequence[7] + sequence[8] + sequence[9]);
	}
}
