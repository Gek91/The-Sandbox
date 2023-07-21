package java8.stream;

import java.util.*;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Basic {

	public static void main(String[] args) {

		List<Integer> list = Arrays.asList(2,5,7,9); //diamond notation

		List<Integer> filteredList = list.stream().filter(elem -> elem > 6).collect(Collectors.toList()); //collect return a collection from a stream (stream eager operation)
		System.out.println("filtered List " + filteredList);

		long count = list.stream()
				.filter(elem -> elem > 6) //filter get a Predicate<? super Integer> as input and return a stream (stream lazy operation)
				.count(); //count the occurrance (stream eager operation)
		System.out.println("count: " + count);

		//map is used for convert a value of one type into another
		List<String> newList = list.stream()
				.map(value -> "!" + value + "!") //map take a Function<? super T, ? extends R> and return a stream (stream lazy operation)
				.collect(Collectors.toList());
		System.out.println("newList: " + newList);

		//flatmap concatenate stream together
		List<Integer> mergedList = Stream.of(list, Arrays.asList(1,3,4)) //create a stream of list
				.flatMap(elemt -> elemt.stream()) //get the stream of every list and merge together
				.sorted()
				.collect(Collectors.toList());
		System.out.println("mergedList: " + mergedList);

		//return the min element
		Integer min = mergedList.stream()
				.min(Comparator.naturalOrder()) //min require a comparator. return an Optional object
//			.min(Comparator.comparing(x -> x)) //custom comparator
				.get();	//java8.optional is a value that may not exist

		System.out.println("mergedList min: " + min);

		//reduce: generate a single result from a collection
		min = mergedList.stream() //create the max function with reduce
				.reduce(Integer.MIN_VALUE, (acc, elem) -> elem > acc ? elem : acc); //reduce(accumulatorStartValue,  BinaryOperator(accumulator, element))
		System.out.println("mergedList max: " + min);

		//forEach: iterate over the elements
		mergedList.stream()
				.forEach(x -> System.out.println(x)); //forEach take a Consumer(val)



		//parallel stream
		montecarloParallelDiceRoll();

		//stream debugging
		debugStreamCode();
	}


	//Parallel stream
	private static void montecarloParallelDiceRoll() {
		double fraction = 1.0 / 100;
		Map<Integer, Double> result = IntStream.range(0,100) //create a stream of size specified
				.parallel() //parallel version of the stream framework
				.mapToObj(twoDiceThrows()) //create a stream from the calculated result
				.collect(Collectors.groupingBy(side -> side , Collectors.summingDouble( n -> fraction))); //create map value/probability

		result.forEach( (key, value) -> System.out.println(key + " : " + value));
	}

	private static IntFunction<Integer> twoDiceThrows() {

		IntFunction<Integer> result = (value) -> {
			Random r = new Random();
			return r.nextInt(6) + 1 + r.nextInt(6) + 1;
		};

		return result;
	}

	private static void debugStreamCode() {

		List<Integer> list = Arrays.asList(2,5,7,9); //diamond notation

		List<Integer> mergedList = Stream.of(list, Arrays.asList(1,3,4)) //create a stream of list
				.flatMap(elemt -> elemt.stream()) //get the stream of every list and merge together
				.peek(value -> System.out.println(value))
				.sorted()
				.peek(value -> System.out.println(value))
				.collect(Collectors.toList());
	}

}
