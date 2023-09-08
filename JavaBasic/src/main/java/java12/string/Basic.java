package java12.string;

public class Basic {

	public static void main(String[] args) {

		//indent
		//indent string on spaces
		String string = "foo\nbar\nciao";
		System.out.println(string.indent(4));


		//transform
		//apply a function to the string characters
		string = string.transform(String::toUpperCase);
		System.out.println(string);
	}
}
