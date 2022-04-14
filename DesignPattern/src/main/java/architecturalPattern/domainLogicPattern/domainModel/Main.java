package architecturalPattern.domainLogicPattern.domainModel;

import java.util.Date;

import util.DateUtil;

public class Main {

	public static void main(String[] args) {
		
		
		Product word = Product.newWordProcessor("Word Processor");
		Product calc = Product.newSpreadsheet("Spreadsheet");
		Product db = Product.newDatabase("Database");
		
		Date now = new Date();
		
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

		now = DateUtil.addDays(now, 30);
		
		System.out.println("30 Days");
		System.out.println("Word Processor revenue: " + wordContrac.recognizedRevenue(now));
		System.out.println("Spreadsheet revenue: " + calcContrac.recognizedRevenue(now));
		System.out.println("Database revenue: " + dbContrac.recognizedRevenue(now));
		
		now = DateUtil.addDays(now, 30);

		System.out.println("60 Days");
		System.out.println("Word Processor revenue: " + wordContrac.recognizedRevenue(now));
		System.out.println("Spreadsheet revenue: " + calcContrac.recognizedRevenue(now));
		System.out.println("Database revenue: " + dbContrac.recognizedRevenue(now));
		
		now = DateUtil.addDays(now, 30);
		
		System.out.println("90 Days");
		System.out.println("Word Processor revenue: " + wordContrac.recognizedRevenue(now));
		System.out.println("Spreadsheet revenue: " + calcContrac.recognizedRevenue(now));
		System.out.println("Database revenue: " + dbContrac.recognizedRevenue(now));
	}
}
