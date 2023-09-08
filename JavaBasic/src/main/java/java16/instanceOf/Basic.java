package java16.instanceOf;

public class Basic {

	public static void main(String[] args) throws Exception {

		Object obj = "string";

		//previously
		if (obj instanceof String) {
			String s = (String) obj;
			System.out.println(s.contains("hello"));
		}

		//now, no need of cast
		if (obj instanceof String s) {
			System.out.println(s.contains("hello"));
		}

	}
}
