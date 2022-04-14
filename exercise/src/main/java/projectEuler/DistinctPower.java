package projectEuler;

import java.util.Set;
import java.util.TreeSet;

public class DistinctPower {

	/**
	 * Generate string containing the result of pow(a,b)
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static String StringPow(int a, int b){
		String result = ""+a;
		for(int i = 1 ; i < b ; i++){
			result = FactorialDigitSum.stringMultNumber(result,a);
		}
		return result;
	}
	
	
	public static Set<String> GenerateSequence(int n){
		Set<String> result = new TreeSet<String>();
		for(int i = 2 ; i <= n ; i++){
			for(int j = 2 ; j <= n ; j++){
				result.add(StringPow(i,j));
			}
		}
		return result;
	}
	
	public static int countGeneratedSequence(int n){
		return GenerateSequence(n).size();
	}
}
