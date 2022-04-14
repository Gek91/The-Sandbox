package codeBat;

public class WarmUp {
	/**
	 *	The parameter weekday is true if it is a weekday, 
	 *	and the parameter vacation is true if we are on vacation. 
	 *	We sleep in if it is not a weekday or we're on vacation
	 */
	public static boolean sleepIn(boolean weekday, boolean vacation){
		return !weekday || vacation;
	}
	
	/**
	 * Given an int n, return the absolute difference between n and 21,
	 * except return double the absolute difference if n is over 21.
	 */
	public static int diff21(int n){
		int result = 21 - n;
		if(result > 0)
			return result;
		return Math.abs(result * 2);
	}
	
	/**
	 * Given an int n, return true if it is within 10 of 100 or 200.
	 *  Note: Math.abs(num) computes the absolute value of a number.
	 */
	public static boolean nearHundred(int n){
		return (Math.abs(100 - n) < 11) || (Math.abs(200 - n) < 11) ? true : false ;
	}
	
	/**
	 * Given a non-empty string and an int n, 
	 * return a new string where the char at index n has been removed. 
	 * The value of n will be a valid index of a char in the original string 
	 * (i.e. n will be in the range 0..str.length()-1 inclusive).
	 */
	public static String missingChar(String str, int n){
		return str.substring(0, n) + str.substring(n+1);
	}
	
	/**
	 * Given a string, take the last char and return a new string with the last char added at the front and back, 
	 * so "cat" yields "tcatt". The original string will be length 1 or more.
	 */
	public static String backAround( String str){
		char lastChar = str.charAt(str.length()-1);
		return lastChar + str + lastChar;
	}
	
	/**
	 * Given a string, return true if the string starts with "hi" and false otherwise.
	 */
	public static boolean starHi(String str){
		if(str.length() > 1)
			return str.substring(0, 2).equals("hi");
		return false;
	}
	
	/**
	 *  We'll say that a number is "teen" if it is in the range 13..19 inclusive. 
	 *  Given 3 int values, return true if 1 or more of them are teen.
	 */
	public static boolean hasTeen(int a, int b, int c){
		if( a>12 && a<20 )
			return true;
		if( b>12 && b<20 )
			return true;
		if( c>12 && c<20 )
			return true;
		return false;
	}
	
	/** 
	 * Return true if the given string begins with "mix", except the 'm' can be anything, so "pix", "9ix" .. all count.
	 */
	public static boolean mixStart(String str) {
		return str.matches("^.ix.*");
	}
	
	/**
	 * Given 2 int values, return whichever value is nearest to the value 10, or return 0 in the event of a tie. 
	 * Note that Math.abs(n) returns the absolute value of a number.
	 */
	public static int close10(int a, int b){
		 int diffA = Math.abs(10 - a);
		 int diffB = Math.abs(10 - b);
		if( diffA < diffB ){
			return a;
		} else if (diffA != diffB){
			return b;
		}
		return 0;
	}
	
	/*
	 * Return true if the given string contains between 1 and 3 'e' chars.
	 */
	public static boolean stringE(String str){
		int count = 0;
		for(int i=0 ; i<str.length() ; i++){
			if(str.charAt(i) == 'e')
				count ++;
		}
		if(count > 0 && count < 4)
			return true;
		return false;
	}
	
	/*
	 * Given a non-empty string and an int N, return the string made starting with char 0,
	 *  and then every Nth char of the string. So if N is 3, use char 0, 3, 6, ... and so on. N is 1 or more.
	 */
	public static String everyNth(String str, int n){
		int i=0;
		String result = "";
		while(i < str.length()){
			result = result + str.charAt(i);
			i = i + n ;
		}
		return result;
	}
	
	/*
	 * We have two monkeys, a and b, and the parameters aSmile and bSmile indicate if each is smiling.
	 * We are in trouble if they are both smiling or if neither of them is smiling. 
	 * Return true if we are in trouble.
	 */
	public static boolean monkeyTrouble(boolean aSmile, boolean bSmile){
		return aSmile == bSmile;
	}
	
	/**
	 * We have a loud talking parrot. The "hour" parameter is the current hour time in the range 0..23.
	 * We are in trouble if the parrot is talking and the hour is before 7 or after 20. 
	 * Return true if we are in trouble.
	 */
	public static boolean parrotTrouble(boolean talking, int hour){
		return talking && (hour < 7  || hour > 20 );
	}

	/**
	 * Given 2 int values, return true if one is negative and one is positive. Except if the parameter "negative" is true, then return true only if both are negative.
	 */
	public static boolean posNeg(int a, int b, boolean negative){
		if(negative){
			return a<0 && b<0;
		}
		return (a*b) < 0;
	}
	
	/**
	 * Given a string, return a new string where the first and last chars have been exchanged.
	 */
	public static String frontBack(String str){
		if(str.length()>0){
			char[] charArray = str.toCharArray();
			charArray[0] = str.charAt(str.length()-1);
			charArray[str.length()-1] = str.charAt(0);
			return new String(charArray);
		}
		return "";
	}
	
	/*
	 * Return true if the given non-negative number is a multiple of 3 or a multiple of 5. Use the % "mod" operator
	 */
	public static boolean or35(int n){
		if(n%3==0 || n%5==0)
			return true;
		return false;
	}
	
	/**
	 * Given two temperatures, return true if one is less than 0 and the other is greater than 100.
	 */
	public static boolean icyHot(int temp1, int temp2){
		if(temp1 < 0 && temp2 > 100)
			return true;
		if(temp2 < 0 && temp1 > 100)
			return true;
		return false;
	}
	

	/**
	 * We'll say that a number is "teen" if it is in the range 13..19 inclusive. 
	 * Given 2 int values, return true if one or the other is teen, but not both.
	 */
	public static boolean loneTeen(int a, int b){
		if(isTeen(a) && !isTeen(b))
			return true;
		if(isTeen(b) && !isTeen(a))
			return true;
		return false;		
	}
	
	private static boolean isTeen(int n){
		if(n>=13 && n<=19 )
			return true;
		return false;
	}
	
	/**
	 * Given a string, return a string made of the first 2 chars (if present),
	 *  however include first char only if it is 'o' and include the second only if it is 'z',
	 *   so "ozymandias" yields "oz"
	 */
	public static String startOz(String str){
		String result = "";
		if(str.length() > 0 && str.charAt(0) == 'o')
			result = "o";
		if(str.length() > 1 && str.charAt(1) == 'z')
			result = result + 'z';
		return result;
	}
	
	/**
	 * Given 2 int values, return true if they are both in the range 30..40 inclusive,
	 *  or they are both in the range 40..50 inclusive.
	 */
	public static boolean in3050(int a, int b){
		if (a >= 30 && a <= 40 && b >= 30 && b <= 40) {
			return true;
		}
		if (a >= 40 && a <= 50 && b >= 40 && b <= 50) {
			return true;
		}
		return false;
	}
	
	/**
	 * Given two non-negative int values, return true if they have the same last digit,
	 *  such as with 27 and 57.
	 */
	public static boolean lastDigit(int a, int b){
		if( a%10 == b%10 )
			return true;
		return false;
	}
	
	/**
	 * Given two int values, return their sum. Unless the two values are the same, then return double their sum.
	 */
	public static int sumDouble(int a, int b){
		if(a!=b)
			return a+b;
		return (a+b)*2;
	}
	
	/**
	 * Given 2 ints, a and b, return true if one if them is 10 or if their sum is 10.
	 */
	public static boolean makes10(int a, int b) {
		return (a == 10 || b == 10 || a+b == 10);
	}
	
	/**
	 * Given a string, return a new string where "not " has been added to the front. 
	 * However, if the string already begins with "not", return the string unchanged.
	 */
	public static String notString(String str){
		if(str.length() >= 3 && str.substring(0, 3).equals("not"))
			return str;
		return "not " + str;
	}
	
	/**
	 * Given a string, we'll say that the front is the first 3 chars of the string. 
	 * If the string length is less than 3, the front is whatever is there. 
	 * Return a new string which is 3 copies of the front.
	 */
	public static String front3(String str){
		String front = str.substring(0,(str.length() < 3 ? str.length() : 3));
		return front + front + front;
	}
	
	/**
	 * Given a string, take the first 2 chars and return the string with the 2 chars added at both the front and back,
	 *  so "kitten" yields"kikittenki". If the string length is less than 2, use whatever chars are there.
	 */
	public static String front22(String str){
		String front = str.substring(0, (str.length() < 2 ? str.length() : 2));
		return front + str + front;
	}
	
	/**
	 * Given 2 int values, return true if either of them is in the range 10..20 inclusive.
	 */
	public static boolean in1020(int a, int b){
		return ( a >= 10 && a <= 20 || b >= 10 && b <= 20);
	}
	
	/**
	 * Given a string, if the string "del" appears starting at index 1, return a string where that "del" has been deleted.
	 * Otherwise, return the string unchanged.
	 */
	public static String delDel(String str){
		if(str.length() >= 4 && str.substring(1, 4).equals("del"))
			return str.charAt(0) + str.substring(4);
		return str;
	}
	
	/**
	 * Given three int values, a b c, return the largest.
	 */
	public static int intMax(int a, int b, int c){
		return Math.max(a, Math.max(b, c));
	}
	
	/**
	 * Given 2 positive int values, return the larger value that is in the range 10..20 inclusive, or return 0 if neither is in that range.
	 */
	public static int max1020(int a, int b){
		int max,min;
		if(a > b){
			max = a;
			min = b;
		} else {
			max = b;
			min = a;
		}
		if(max >= 10 && max <=20) return max;
		if(min >=10 && min <=20) return min;
		return 0;
	}
	
	/**
	 * Given a string, return a new string where the last 3 chars are now in upper case. 
	 * If the string has less than 3 chars, uppercase whatever is there. Note that str.toUpperCase() returns the uppercase version of a string.
	 */
	public static String endUp(String str){
		int lenght = str.length();
		if(lenght > 3)
			return str.substring(0, lenght-3) + str.substring(lenght-3, lenght).toUpperCase();
		return str.toUpperCase();
	}
}
