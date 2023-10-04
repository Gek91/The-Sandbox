package java21.switchpatternmatching;

public class Basic {

	public static void main(String[] args) {


		//priori jdk 21
		Object obj = Integer.valueOf(1);

		String result = "unknown";
		if(obj != null) { //old null management
			if (obj instanceof Integer i) {
				result = String.format("int %d", i);
			} else if (obj instanceof Long l) {
				result = String.format("long %d", l);
			} else if (obj instanceof Double d) {
				result = String.format("double %f", d);
			} else if (obj instanceof String s) {
				result = String.format("String %s", s);
			}
		} else {
			result = "is null"; //old null management
		}

		System.out.println(result);


		//jdk 21
		result = switch (obj) {
			case null -> "is null"; //null management
			case Integer i -> String.format("int %d", i);
			case Long l    -> String.format("long %d", l);
			case Double d  -> String.format("double %f", d);
			case String s  -> String.format("String %s", s);
			default        -> obj.toString();
		};

		System.out.println(result);
	}
}
