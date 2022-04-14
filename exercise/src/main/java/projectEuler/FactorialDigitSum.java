package projectEuler;

public class FactorialDigitSum {

	/**
	 * Multiply the input string for the input integer
	 * @param a, string to multiply
	 * @param b, second factor of the multiplication
	 * @return string containing the result
	 */
	public static String stringMultNumber(String a, int b){
		StringBuilder result = new StringBuilder();
		int report = 0;
		for(int i = a.length()-1 ; i>=0; i--){
			int val=Character.getNumericValue(a.charAt(i)) * b + report;
			report = val / 10;
			result.insert(0, val%10);
		}
		if(report != 0)
			result.insert(0,report);
		return result.toString();
	}
	
	public static String stringFactorial(int n){
		String result="1";
		for(int i=1; i <= n ; i++){
			result = stringMultNumber(result,i);
		}
		return result;
	}
	
	public static int factorialdigitSum(int n){
		String factorial = stringFactorial(n);
		int sum = 0;
		for(int i = 0 ; i < factorial.length() ; i++ ){
			sum = sum + Character.getNumericValue(factorial.charAt(i));
		}
		return sum;
	}
	
}
