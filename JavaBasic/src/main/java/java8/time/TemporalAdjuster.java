package java8.time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class TemporalAdjuster {

	public static void main(String[] args) {
		
		adjuster();
	}
	
	public static void adjuster() {
	
		LocalDate today = LocalDate.now();
		
		LocalDate nextMonday = today.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
		
		LocalDate secondSundayOfTheMonth = LocalDate.of(today.getYear(), today.getMonth(), 1)
				.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)).with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
		
		System.out.println("today : " + today);
		System.out.println("secondSundayOfTheMonth : " + secondSundayOfTheMonth);
		System.out.println("nextMonday : " + nextMonday);

	}
}
