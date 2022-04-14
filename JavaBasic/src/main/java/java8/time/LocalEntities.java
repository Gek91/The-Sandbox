package java8.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class LocalEntities {

	
	public static void main(String[] args) {
		
		currentDateTimeBasic();
		
		currentTimeTimeZone();
		
		customDateTime();
		
		parseString();
	}
	
	private static void currentDateTimeBasic() {
		
		//date java8.time of the current local, no timezone specified
		LocalDateTime currentDateTime = LocalDateTime.now();
		
		LocalDate currentDate = currentDateTime.toLocalDate();
		
		LocalTime currentTime = currentDateTime.toLocalTime();
				
		System.out.println("Current datetime: " + currentDateTime );
		System.out.println("Current date: " + currentDate);
		System.out.println("Current java8.time: " + currentTime);
		System.out.println(currentDateTime.getDayOfMonth() + "/" +  currentDateTime.getMonth() + "/" + currentDateTime.getYear() + " " 
		+ currentDateTime.getHour() + ":"+ currentDateTime.getMinute() + ":" +	currentDateTime.getSecond() + ":" + currentDateTime.getNano());
		
		currentDate = LocalDate.now();
		
		currentTime = LocalTime.now();
		
		System.out.println("Current date: " + currentDate);
		System.out.println("Current java8.time: " + currentTime);
	}
	
	private static void currentTimeTimeZone() {
		
		//Time in Rome
		ZoneId zone1 = ZoneId.of("Europe/Rome");
		LocalTime time1 = LocalTime.now(zone1);
				
		//Time in Londonmain
		ZoneId zone2 = ZoneId.of("Europe/London");
		LocalTime time2 = LocalTime.now(zone2);
		
		//Convert java8.time in Rome in java8.time in London
		LocalTime time4 = LocalDateTime.of(LocalDate.now(), time1).atZone(zone1)
				.withZoneSameInstant(zone2).toLocalTime();
		//Convert java8.time in London in java8.time in Rome
		LocalTime time5 = LocalDateTime.of(LocalDate.now(), time2).atZone(zone2)
				.withZoneSameInstant(zone1).toLocalTime();
				
		System.out.println(time1);
		System.out.println(time2);
		System.out.println(time4);
		System.out.println(time5);
		
		System.out.println(ChronoUnit.HOURS.between(time1, time4));
		System.out.println(ChronoUnit.MINUTES.between(time1, time4));
		
	}
	
	private static void customDateTime() {
		
		LocalDate currentDate = LocalDate.of(2012, 12, 24);
		
		LocalTime currentTime = LocalTime.of(12, 24);
		
		LocalDateTime currentDateTime = LocalDateTime.of(currentDate, currentTime);

		System.out.println("Current datetime: " + currentDateTime );
		System.out.println("Current date: " + currentDate);
		System.out.println("Current java8.time: " + currentTime);
	}
	
	private static void parseString() {
		
		LocalTime currentTime = LocalTime.parse("20:15:20");
		
		System.out.println("Hours:" + currentTime.getHour());
		System.out.println("Minutes:" + currentTime.getMinute());
		System.out.println("Seconds:" + currentTime.getSecond());
		
		LocalDate currentDate = LocalDate.parse("2007-12-03");
		
		System.out.println("Year:" + currentDate.getYear());
		System.out.println("Month:" + currentDate.getMonth());
		System.out.println("Day:" + currentDate.getDayOfMonth());
		
		LocalDateTime currentDateTime = LocalDateTime.parse("2007-12-03T10:15:30");
		
		System.out.println(currentDateTime.getDayOfMonth() + "/" +  currentDateTime.getMonth() + "/" + currentDateTime.getYear() + " " 
				+ currentDateTime.getHour() + ":"+ currentDateTime.getMinute() + ":" +	currentDateTime.getSecond() + ":" + currentDateTime.getNano());

	}
}
