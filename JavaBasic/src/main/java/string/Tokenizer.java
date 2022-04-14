package string;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.StringReader;

public class Tokenizer {

	public static void main(String[] args) {
		
		Reader reader = new StringReader("Stringa di prova");
		StreamTokenizer tokenizer = new StreamTokenizer(reader);
		try {
			
			while(tokenizer.nextToken() != StreamTokenizer.TT_EOF) {
				System.out.println(tokenizer.sval);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
