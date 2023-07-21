package java8.optional;

import java.util.Optional;

public class Basic {

	/*
	 * Optional is used to represent a value that is present or absent
	 * - No null check required
	 * - No NullPointerException
	 */
	
	public static void main(String[] args) {
			
		//Create an empty java8.optional object
		Optional<String> stringOptional = Optional.empty();
		System.out.println(stringOptional.isPresent());
		
		stringOptional = Optional.of("value");
		System.out.println(stringOptional.isPresent());
		System.out.println(stringOptional.get());

		//NullPointerException
//		stringOptional = Optional.of("value");
		
		stringOptional = Optional.ofNullable("value");
		System.out.println(stringOptional.isPresent());
		
		//Return a non empty Optional
		stringOptional = Optional.ofNullable("value");
		System.out.println(stringOptional.isPresent());

		//return an empty java8.optional object
		stringOptional = Optional.ofNullable(null);
		System.out.println(stringOptional.isPresent());
		
		stringOptional = Optional.of("value");
		stringOptional.ifPresent(x -> System.out.println("Not Empty"));
		System.out.println(stringOptional.orElse("Is Empty")); //value
		
		stringOptional = Optional.empty();
		stringOptional.ifPresent(x -> System.out.println("Not empty")); // no print
		System.out.println(stringOptional.orElse("Is Empty")); //Is empty

	}
}
