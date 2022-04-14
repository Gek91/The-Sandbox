package date;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Basic {

	public static void main(String[] args) {

		parsing();
	}

	public static void parsing() {

		Date now = new Date();

		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss z");

		dt.setTimeZone(TimeZone.getTimeZone("Europe/Rome"));
		System.out.println(dt.format(now));

		dt.setTimeZone(TimeZone.getTimeZone("UTC"));
		System.out.println(dt.format(now));

		dt.setTimeZone(TimeZone.getTimeZone("Europe/London"));
		System.out.println(dt.format(now));
	}
}