package java12.number;

import java.text.NumberFormat;
import java.util.Locale;

public class Basic {

	public static void main(String[] args) {

		// compact number formatting
		NumberFormat formatter = NumberFormat.getCompactNumberInstance(Locale.US,
				NumberFormat.Style.SHORT);

		String formattedString = formatter.format(25000L);
		System.out.println(formattedString);


		formatter = NumberFormat.getCompactNumberInstance(Locale.US,
				NumberFormat.Style.LONG);

		formattedString = formatter.format(25000L);
		System.out.println(formattedString);
	}
}
