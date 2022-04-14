package java8.time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class BackWardCompatibility {

	
	public static void main(String[] args) {
		
		backward();
		
		instant();
	}
	

	public static void backward() {
		
		Date now = new Date();
		
		//take instant an zone of date
		Instant nowInstant = now.toInstant();
		ZoneId currentZone = ZoneId.systemDefault();
		
		//generate java 8 date object
		LocalDateTime nowJava8 = LocalDateTime.ofInstant(nowInstant, currentZone);
		
		ZonedDateTime zonedNowJava8 = ZonedDateTime.ofInstant(nowInstant, currentZone);
		
		
		System.out.println("now old way: " + now);
		System.out.println("now new way: " + nowJava8);
		System.out.println("zoned now new way: " + zonedNowJava8);
	}
	
	public static void instant() {
		
		Instant now = Instant.now();
		
		Instant epoch = Instant.ofEpochSecond(1262347200);
		
		Instant fromIso8601 = Instant.parse("2010-01-01T12:00:00Z");
		
		long toUnixTimestamp = now.getEpochSecond();

		System.out.println("now instant: " + now);
		System.out.println("epoch instant " + epoch);
		System.out.println("fromIso8601 instant : " + fromIso8601);
		System.out.println("now epoch: " + toUnixTimestamp);
	}
}
