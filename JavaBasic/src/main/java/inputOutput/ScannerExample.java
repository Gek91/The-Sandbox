package inputOutput;

import java.util.Scanner;

public class ScannerExample {

	public static void main(String[] args) {
		
		sampleDate();
		
	}
	
	public static void sampleDate() {
		String sampleDate ="25 Dec 1988";
		
		try(Scanner sDate = new  Scanner(sampleDate)) {
			
			int dayOfMonth = sDate.nextInt();
			String month = sDate.next();
			int year = sDate.nextInt();
			
			System.out.print("Day: " + dayOfMonth + " month: " + month + " year: " + year);
		}
	}
}
