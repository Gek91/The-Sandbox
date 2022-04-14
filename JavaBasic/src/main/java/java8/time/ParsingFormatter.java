package java8.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ParsingFormatter {

	public static void main(String[] args) {
		
		format();
		
		parse();

	}

	public static void format() {
		
		LocalDateTime now = LocalDateTime.now();
		
		String nowISO = now.format(DateTimeFormatter.BASIC_ISO_DATE);
		
		String nowISOWeek = now.format(DateTimeFormatter.ISO_WEEK_DATE);
		
		String nowISODateTime = now.format(DateTimeFormatter.ISO_DATE_TIME);
		
		String customPattern = now.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
		String itaDate = now.format(DateTimeFormatter.ofPattern("d. MMMM yyyy", new Locale("it")));
		
		System.out.println("nowISO : " + nowISO);
		System.out.println("nowISOWeek : " + nowISOWeek);
		System.out.println("nowISODateTime : " + nowISODateTime);
		System.out.println("customPattern : " + customPattern);
		System.out.println("itaDate : " + itaDate);

	}
	
	public static void parse() {
		
		LocalDate fromCustomPattern = LocalDate.parse("20.01.2014", DateTimeFormatter.ofPattern("dd.MM.yyyy"));

		System.out.println("fromCustomPattern : " + fromCustomPattern);

	}
}
