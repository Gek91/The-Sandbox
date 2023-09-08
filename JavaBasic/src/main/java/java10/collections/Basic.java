package java10.collections;

import java.util.List;
import java.util.stream.Collectors;

public class Basic {

	public static void main(String[] args) {

		var integerList = List.of(11, 22, 33, 44, 55);

		try {
			// Get an unmodifiable list by use of copyOf()
			List<Integer> copyOfIds = List.copyOf(integerList);

			// Try to add element in unmodifiable list
			copyOfIds.add(66);
		} catch(UnsupportedOperationException e) {
			System.out.println("Collection can't modify.");
		}

		try {
			// Get an unmodifiable list by use Collector
			List<Integer> listOfEvenNumbers = integerList.stream()
					.filter(i -> i % 2 == 0)
					.collect(Collectors.toUnmodifiableList());

			listOfEvenNumbers.add(66);
		} catch(UnsupportedOperationException e) {
			System.out.println("Collection can't modify.");
		}
	}
}
