package java9.streamAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NewFeatures {

	
	public static void main(String[] args) {
		
		//take while
		Stream<Integer> stream = Stream.of(1,2,3,4,5,6,7);
		stream.takeWhile(x -> x < 4).forEach(elem -> System.out.print(elem + " ")); //1,2,3
		System.out.println("");
		stream = Stream.of(1, 4, 3, 2);
		stream.takeWhile(x -> x < 4).forEach(elem -> System.out.print(elem + " ")); //1
		System.out.println("");
		
		//drop while
		stream = Stream.of(1,2,3,4,5,6);
		stream.dropWhile(x -> x < 4).forEach(elem -> System.out.print(elem + " ")); //4,5,6
		System.out.println("");
		
		stream = Stream.of(1, 4, 3, 2);
		stream.dropWhile(x -> x < 4).forEach(elem -> System.out.print(elem+ " ")); //4,3,2
		System.out.println("");

		
		//iterate. like a for loop (start value, end check, iterator)
		Stream.iterate(1, x -> x < 10, x -> x + 1).forEach(elem ->  System.out.print(elem+ " "));
		System.out.println("");
		Stream.iterate(1, x -> x < 10, x -> x + 1).filter(x -> x% 2 == 0).forEach(elem ->  System.out.print(elem+ " "));
		System.out.println("");
		
		
		//ofNullable
		Stream<Integer> s = Stream.ofNullable(1); //one element stream
		s = Stream.ofNullable(null); //empty stream
		
		
		//optional stream
		List<Optional<String>> listOfOptionals = new ArrayList<>();
		listOfOptionals.add(Optional.of("ciao"));
		listOfOptionals.add(Optional.of("bello"));
		listOfOptionals.add(Optional.empty());

		List<String> filteredList = listOfOptionals.stream()
				  .flatMap(Optional::stream)
				  .collect(Collectors.toList());
		
		filteredList.forEach(elem -> System.out.println(elem));
	}
}
