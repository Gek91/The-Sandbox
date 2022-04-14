package java9.trycatch;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class TryCatchMain {

	public static void main(String[] args) throws FileNotFoundException {
		
		//java 8
		BufferedReader reader1 = new BufferedReader(new FileReader("journaldev.txt"));
		try (BufferedReader reader2 = reader1){ //need a new variable
			//logic
		} catch (Exception e) {
			//error logic
		}
		
		//java 9
		BufferedReader reader3 = new BufferedReader(new FileReader("journaldev.txt"));
		try(reader3) { //no need for new variable
			//logic
		}  catch (Exception e) {
			//error logic
		}
		
	}
}
