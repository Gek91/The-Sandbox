package java11.string;

import java.util.List;

public class Basic {

	public static void main(String[] args) {

		String str = "hello";

		//repeat the string n times
		System.out.println(str.repeat(3));


		str = "      ";
		//check if the string contains only white spaces
		System.out.println(str.isBlank());


		str = "    a  ";
		//remove leading and traling whitespace
		System.out.println(str.strip());
		System.out.println(str.stripLeading());
		System.out.println(str.stripTrailing());


		str = "hello\nworld\nis\nexecuted";
		//help processing lines in string
		str.lines().forEach(line -> System.out.println(line));
	}
}
