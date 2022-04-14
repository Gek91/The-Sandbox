package architecturalPattern.domainLogicPattern.domainModel;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;


public class Main {

	public static void main(String[] args) {
		
		
		Product word = Product.newWordProcessor("Word Processor");
		Product calc = Product.newSpreadsheet("Spreadsheet");
		Product db = Product.newDatabase("Database");
		
		Instant now = Instant.now();
		
		Contract wordContrac = new Contract(word, 3, now);
		wordContrac.calculateRecognitions();
		Contract calcContrac = new Contract(calc, 3, now);
		calcContrac.calculateRecognitions();
		Contract dbContrac = new Contract(db, 3, now);
		dbContrac.calculateRecognitions();
		
		System.out.println("Now");
		System.out.println("Word Processor revenue: " + wordContrac.recognizedRevenue(now));
		System.out.println("Spreadsheet revenue: " + calcContrac.recognizedRevenue(now));
		System.out.println("Database revenue: " + dbContrac.recognizedRevenue(now));

		now = now.plus(30, ChronoUnit.DAYS);
		
		System.out.println("30 Days");
		System.out.println("Word Processor revenue: " + wordContrac.recognizedRevenue(now));
		System.out.println("Spreadsheet revenue: " + calcContrac.recognizedRevenue(now));
		System.out.println("Database revenue: " + dbContrac.recognizedRevenue(now));

		now = now.plus(30, ChronoUnit.DAYS);

		System.out.println("60 Days");
		System.out.println("Word Processor revenue: " + wordContrac.recognizedRevenue(now));
		System.out.println("Spreadsheet revenue: " + calcContrac.recognizedRevenue(now));
		System.out.println("Database revenue: " + dbContrac.recognizedRevenue(now));

		now = now.plus(30, ChronoUnit.DAYS);
		
		System.out.println("90 Days");
		System.out.println("Word Processor revenue: " + wordContrac.recognizedRevenue(now));
		System.out.println("Spreadsheet revenue: " + calcContrac.recognizedRevenue(now));
		System.out.println("Database revenue: " + dbContrac.recognizedRevenue(now));
	}
}
