package projectEuler;

public class PowerDigitSum {

	/**
	 * Having a string of number, duplicate its value and return the String containing the result
	 * @param n
	 * @return
	 */
	public static String doubleNumberStrig(String n){
		StringBuilder result = new StringBuilder();
		int report = 0;
		for(int i = n.length()-1 ; i>=0; i--){
			int val=Character.getNumericValue(n.charAt(i)) * 2 + report;
			report = val / 10;
			result.append(val%10);
		}
		if(report != 0)
			result.append(report);
		return result.reverse().toString();
	}
	
	/**
	 * Return the String cotaining the nth pow of 2
	 * @param n
	 * @return
	 */
	public static String twoPowN(int n){
		String result = "1";
		for(int i = 1 ; i<=n ; i++){
			result = doubleNumberStrig(result);
		}
		return result;
	}
	
	/**
	 * Calculate the sum of the digit of 2 pow n
	 * @param n
	 * @return
	 */
	public static long powDigitSum(int n){
		String powString = twoPowN(n);
		long sumDigit = 0;
		for(int i = 0 ; i< powString.length() ; i++){
			sumDigit = sumDigit +  Character.getNumericValue(powString.charAt(i));
		}
		return sumDigit;
	}
}
