package java10.localVariable;

import java.time.Instant;

public class Basic {

	public static void main(String[] args) {

		//no need to define data type with variable name
		//compiler infers the type using value provided
		var inferString = "string";
		var inferInt = 1;
		var inferObject = Instant.now();


	}
}
