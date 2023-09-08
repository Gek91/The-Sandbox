package java11.file;

import java.nio.file.Files;
import java.nio.file.Path;

public class Basic {

	public static void main(String[] args) throws Exception {

		//help reducing boilerplate code to write string to file or read file content as a string
		Path path = Files.writeString(Files.createTempFile("helloworld", ".txt"), "Hi, my name is!");

		String str = Files.readString(path);
		System.out.println(str);

	}
}
