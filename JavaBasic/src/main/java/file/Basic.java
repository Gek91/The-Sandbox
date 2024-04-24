package file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Basic {

	public static void main(String[] args) {

//		fileWriter();
		//fileReader();
		readLine();
	}

	public static void fileWriter() {

		String input = "File Handling in Java using "+
				" FileWriter and FileReader";

		try (FileWriter writer = new FileWriter("./output.txt")) {

			for (int i = 0; i < input.length(); i++)
				writer.write(input.charAt(i));

			System.out.println("Writing successful");

		} catch (IOException exception ) {
			throw new RuntimeException(exception);
		}
	}

	public static void fileReader() {
		try (FileReader reader = new FileReader("./input")) {
			int character;
			while((character = reader.read()) != -1) {
				System.out.print((char)character);
			}

		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	public static void readLine() {

		try (BufferedReader reader = new BufferedReader(new FileReader("./input")) {}){
			for(String line; (line = reader.readLine()) != null; ) {
				System.out.println(line);
			}
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}
}
