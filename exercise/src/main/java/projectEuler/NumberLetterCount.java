package projectEuler;

public class NumberLetterCount {

	public static String first20(int n){
		switch(n){
		case 1:
			return "one";
		case 2:
			return "two";
		case 3:
			return "three";
		case 4:
			return "four";
		case 5:
			return "five";
		case 6:
			return "six";
		case 7:
			return "seven";
		case 8:
			return "eight";
		case 9: 
			return "nine";
		case 10:
			return "ten";
		case 11:
			return "eleven";
		case 12:
			return "twelve";
		case 13:
			return "thirteen";
		case 14:
			return "fourteen";
		case 15:
			return "fifteen";
		case 16:
			return "sixteen";
		case 17:
			return "seventeen";
		case 18:
			return "eighteen";
		case 19: 
			return "nineteen";
		}
		return "";
	}
	
	public static String from20To99(int n){
		int unit = n%10;
		int decimal = n/10;
		StringBuilder resultString = new StringBuilder("");
		switch(decimal){
			case 2:
				resultString.append("twenty");
				break;
			case 3:
				resultString.append("thirty");
				break;
			case 4:
				resultString.append("forty");
				break;
			case 5: 
				resultString.append("fifty");
				break;
			case 6:
				resultString.append("sixty");
				break;
			case 7:
				resultString.append("seventy");
				break;
			case 8: 
				resultString.append("eighty");
				break;
			case 9:
				resultString.append("ninety");
				break;
		}
		resultString.append(first20(unit));
		return resultString.toString();
	}
	
	public static String hundreds(int n){
		int hundred = n/100;
		int ten = n % 100;
		StringBuilder resultString = new StringBuilder("");
		if(hundred > 0){
			resultString.append(first20(hundred));
			resultString.append("hundred");
			if(ten > 0 )
				resultString.append("and");
		}
		if(ten<20)
			resultString.append(first20(ten));
		else
			resultString.append(from20To99(ten));
		return resultString.toString();
	}
	
	public static int countLetterFromOne(int n){
		int result = 0;
		for(int i = 1 ; i <= n ; i++){
			result = result + hundreds(i).length();
		}
		return result;
	}
}
