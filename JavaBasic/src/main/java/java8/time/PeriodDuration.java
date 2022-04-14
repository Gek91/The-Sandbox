package java8.time;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class PeriodDuration {

	
	public static void main(String[] args) {
		
		chronoUnitsEnum();

		periond();
		
		duration();
	}
	
	private static void chronoUnitsEnum() {
		
		LocalDate today = LocalDate.now();
		
		LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
		
		LocalDate nextWeek = today.plus(1, ChronoUnit.WEEKS);
		
		LocalDate nextMonth = today.plus(1, ChronoUnit.MONTHS);
		
		LocalDate nextYear = today.plus(1, ChronoUnit.YEARS);
		
		LocalDate yesteday = today.minus(1, ChronoUnit.DAYS);
		
		System.out.println("today: " + today);
		System.out.println("tomorrow: " + tomorrow);
		System.out.println("nextWeek: " + nextWeek);
		System.out.println("nextMonth: " + nextMonth);
		System.out.println("nextYear: " + nextYear);
		System.out.println("yesterday: "+ yesteday);
	}
		
	//Period used for date
	private static void periond() {
		
		LocalDate today = LocalDate.now();
		LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
		LocalDate nextMonth = today.plus(1, ChronoUnit.MONTHS);
		
		LocalDate past = today.minus(1, ChronoUnit.DAYS);
		past = past.minus(2,ChronoUnit.MONTHS);
		
		Period period1 = Period.between(today, tomorrow);
		Period period2 = Period.between(today, nextMonth);
		Period period3 = Period.between(today, past);
		
		System.out.println("Between " + today + " and " + tomorrow +" there are : " + period1);
		System.out.println("Between " + today + " and " + nextMonth +" there are : " + period2);
		System.out.println("Between " + today + " and " + past +" there are : " + period3);
	}
	
	//Duration used for java8.time
	private static void duration() {
		
		LocalTime now = LocalTime.now();

		Duration twoHours = Duration.ofHours(2);
		Duration oneMinute = Duration.ofMinutes(1);
		
		LocalTime newTime1 = now.plus(twoHours);
		
		LocalTime newTime2 = now.plus(oneMinute);
		
		Duration duration1 = Duration.between(now, newTime1);
		Duration duration2 = Duration.between(now, newTime2);
		
		System.out.println("Period Between " + now + " and " + twoHours +" there are : " + duration1);
		System.out.println("Period Between " + now + " and " + newTime2 +" there are : " + duration2);
	}
	
}
