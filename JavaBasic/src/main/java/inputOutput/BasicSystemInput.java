package inputOutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BasicSystemInput {

	public static void main(String[] args) {
		
//		basic();
		
//		readerAndWriter();
		
		OneCharAtTimeReader();
	}
	
	public static void basic( ) {
		
		int b = 0;
		try {
			//reads from system input (console)
			b = System.in.read(); 
			
			//output in system output (console)
	        System.out.println("Read this data: " + (char)b);
			//output in system error (console)
	        System.err.println("Read this data: " + (char)b);

		} catch (Exception e) {
			
			System.out.println("Caught " + e);
			
	   }
	}
	
	
	public static void readerAndWriter() {
		try {
			
			//convert the inputstream in a reader and pass it to a bufferedReader
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
			//Constructor take a stream
			PrintWriter printer = new PrintWriter(System.out);

			String inputLine;
			//Continue read the input line for line
			while( (inputLine = reader.readLine()) != null) {
				printer.println(inputLine);
				printer.flush(); //force printing
			}
			
		} catch(IOException e) {
            System.out.println("IOException: " + e);
		}
	}
	
	public static void OneCharAtTimeReader() {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		int c;
		
		try {
			
			while( (c = reader.read() ) != -1) {
				System.out.println((char) c);
			}
			
		} catch (IOException e) {
			
			 System.out.println("IOException: " + e);
			 
		}
		
	}
}
