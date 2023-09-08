package java10.optional;

import java.util.Optional;

public class Basic {

	public static void main(String[] args) {

		String stringOne = "Hello world";
		var optionalStringOne =  Optional.ofNullable(stringOne);
		var valueStringOne  = optionalStringOne.orElseThrow();
		System.out.println(valueStringOne);


		String stringTwo = null;
		var optionalStringTwo =  Optional.ofNullable(stringTwo);
		var valueStringTwo  = optionalStringTwo.orElseThrow();
		System.out.println(valueStringTwo);
	}
}
