package java14.newswtich;

import java.time.DayOfWeek;
import java.time.Instant;

public class Basic {

	public static void main(String[] args) {

		//switch now has value
		Boolean result = switch(DayOfWeek.from(Instant.now())) {
			case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> { yield true; }
			case SATURDAY, SUNDAY -> { yield false; }
		};

		System.out.println(result);
	}
}
