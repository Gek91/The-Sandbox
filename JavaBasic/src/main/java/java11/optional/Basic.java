package java11.optional;

import java.util.Optional;

public class Basic {

	public static void main(String[] args) {

		//added isEmpty, reverse of isPresent

		String currentTime = null;

		System.out.println(Optional.ofNullable(currentTime).isPresent());
		System.out.println(Optional.ofNullable(currentTime).isEmpty());

		currentTime = "12:00 PM";

		System.out.println(Optional.ofNullable(currentTime).isPresent());
		System.out.println(Optional.ofNullable(currentTime).isEmpty());
	}
}
