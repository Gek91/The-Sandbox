package java12.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Basic {



	public static void main(String[] args) throws IOException {
		Path path1 = Files.writeString(Files.createTempFile("helloworl1", ".txt"), "Hi, my name is");

		Path path2 = Files.writeString(Files.createTempFile("helloworld2", ".txt"), "Hi, my name is!");

		Long result = Files.mismatch(path1, path2);
		System.out.println(result); //-1 if the files are equal
	}
}
