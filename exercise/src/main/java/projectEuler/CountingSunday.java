package projectEuler;

public class CountingSunday {
	
	public enum Day {
	    MONDAY(0), 
	    TUESDAY(1), 
	    WEDNESDAY(2),
	    THURSDAY(3), 
	    FRIDAY(4), 
	    SATURDAY(5),
	    SUNDAY(6);
	    
	    private final int value;
		Day(final int newValue) {
            value = newValue;
        }
        public int getValue() { return value; }
	}
	
	public enum Month {
		January(0,31),
		February(1,28),
		March(2,31),
		April(3,30),
		May(4,31),
		June(5,30),
		July(6,31),
		August(7,31), 
		September(8,30), 
		October(9,31),
		November(10,30),
		December(11,31);

		private final int ord;
		private final int value;
		Month(final int newOrd,final int newValue) {
			ord = newOrd;
			value = newValue;
        }
		public int getOrd() { return ord; }
        public int getValue() { return value; }
	}

	
	/**
	 * Checks if the year is a century and it is multiple of 400
	 * @param year
	 * @return true, the year is leaf, false otherwise
	 */
	public static boolean isCenturyAnd400Mul(int year){
		if(year%100 == 0)
			if(year % 400 != 0)
				return false;
		return true;
	}
	
	/**
	 * Checks if the year is leap
	 * @param year
	 * @return
	 */
	public static boolean isLeapYear(int year){
		if(year % 4 == 0 && isCenturyAnd400Mul(year)){
			return true;
		}
		return false;
	}
	
	/**
	 * Given the first day of the month and the month, return the first day of the next month
	 * @param startDay
	 * @param month
	 * @return
	 */
	public static Day firstDayNextMonth(Day startDay, Month month){
		return Day.values()[(month.value+startDay.value) % 7];
	}
	
	/**
	 * 
	 * @param startDay
	 * @param isLeap
	 * @return
	 */
	public static int monthStartedWithSundayinYear(Day startDay,boolean isLeap){
		Day day = startDay;
		int result = 0;
		for(int i = 0 ; i<12 ; i++){
			day = firstDayNextMonth(day,Month.values()[i]);
			if(day.equals(Day.SUNDAY))
				result++;
		}
		startDay = day;
		System.out.println( day);
		return result;
	}
}
