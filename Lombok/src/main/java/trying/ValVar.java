package trying;

import lombok.val;
import lombok.var;

import java.util.ArrayList;
import java.util.HashMap;

public class ValVar {

	public void valExample() {
		//type inferction on local variable
		val foo = "Hello, World!";
		//foo = "value"; //can't is final

		//work on loops local variables
		val list = new ArrayList<String>();
		list.add("zero");
		list.add("five");
		for (val value : list) {
			System.out.printf("%s\n", value);
			//value = "hello"; //can't is final
		}
	}

	public void varExample() {

		var foo = "Hello, World!";
		foo = "value"; //is not final

		//work on loops local variables
		var list = new ArrayList<String>();
		list.add("zero");
		list.add("five");
		for (var value : list) {
			System.out.printf("%s\n", value);
			value = "hello";
		}
	}
}
