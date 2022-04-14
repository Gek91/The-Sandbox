package projectEuler;

public class SelfPower {

	public static String selfPower(int n){
		return DistinctPower.StringPow(n, n);
	}
	
	public static String selfPowerSerie(int n){
		String result = "0";
		for(int i = 1 ; i <= n ; i++){
			result = ThousandDigitFibonacciNumber.stringSum(result,selfPower(i));
		}
		return result;
	}
}
