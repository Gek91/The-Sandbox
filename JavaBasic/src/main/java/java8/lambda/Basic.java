package java8.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Basic {

	
	public static void main(String args[]) {
		
//		functionalInterfaceExample();
//		
//		streamExample();
//		
//		optionalExample();
//		
//		methodReferencesExample();
		
//		collectorExample();
		
//		collectionMethods();
				
//		montecarloParallelDiceRoll();
		
		debugLambdaCode();
	}
	
	private static void functionalInterfaceExample() {
		//Lambda with no argument
		Runnable noArguments = () -> System.out.println("Hello World");
		noArguments.run();
		
		//java8.lambda with one argument (get a value, no return)
		Consumer<Integer> oneArgument = x -> System.out.println("Hello World " + x);
		oneArgument.accept(10);
		
		//multi statement
		Runnable multiStatement = () -> {
			System.out.println("Statement 1");
			System.out.println("Statement 2");
		};
		multiStatement.run();
		
		//no argument return a value
		Supplier<String> noArgumentsReturnValue = () -> "Hello World";
		System.out.println("noArgumentsReturnValue :" + noArgumentsReturnValue.get());
		
		//Get two parameter of the same tipe and return a result of the same java8.time
		BinaryOperator<Long> add = (Long x, Long y) -> x + y;
		System.out.println("add: " + add.apply(2l, 3l));
		
		//get an argument a return a result of a different type
		Function<Long, String> function = (Long x) -> "value : " + x; 
		System.out.println("function: " + function.apply(10l));
		
		//get a value and return a boolean
		Predicate<Long> booleanValue = (Long x) -> x > 0;
		System.out.println("booleanValue: " + booleanValue.test(4l));
	}
	
	private static void streamExample () {
		
		List<Integer> list = Arrays.asList(2,5,7,9); //diamond notation
		
		List<Integer> filteredList = list.stream().filter(elem -> elem > 6).collect(Collectors.toList()); //collect return a collection from a stream (stream eager operation)
		System.out.println("filtered List " + filteredList);
		
		long count = list.stream()
			.filter(elem -> elem > 6) //filter get a Predicate<? super Integer> as input and return a stream (stream lazy operation)
			.count(); //count the occurrance (stream eager operation)
		System.out.println("count: " + count);
		
		//map is used for convert a a value of one type into another
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
			.reduce(Integer.MIN_VALUE, (acc, elem) ->elem > acc ? elem : acc); //reduce(accumulatorStartValue,  BinaryOperator(accumulator, element))
		System.out.println("mergedList max: " + min);
		
		//forEach: iterate over the elements
		mergedList.stream()
			.forEach(x -> System.out.println(x)); //forEach take a Consumer(val)
	}
	
	private static void optionalExample() {
		
		Optional<String> value = Optional.of("a");
		System.out.println(value.get().equals("a"));
		System.out.println(value.isPresent());

		
		Optional empty = Optional.empty();
		System.out.println(empty.isPresent());
		
		System.out.println(value.orElse("b"));
		System.out.println(empty.orElse("c"));
		System.out.println(empty.orElseGet(() -> "d")); //wants a Supplier
		
	}
	
	private static void methodReferencesExample() {
		
		String string = "abc";
		
		Function<String, Integer> lambda = lenght -> string.length();
		System.out.println("labda result: " + lambda.apply(string));
		
		//Alternative style
		lambda = String::length; //method referece for lenght
		System.out.println("labda alternative result: " + lambda.apply(string));
		
		String string2 = new String("ABCD");
		System.out.println("string : " +string2);
		
		Function<String, String> lambda2 = String::new; //method reference for constructor
		string2 = lambda2.apply("ABCD");
		System.out.println("string : " + string2);
	}
	
	private static void collectorExample() {
		
		List<Integer> list = Arrays.asList(2,5,7,9); 
		
		 //split the collection grouping by the function
		Map<Integer, List<Integer>> result = list.stream().collect(Collectors.groupingBy(value -> value%2));
		//after grouping count the number of element in each result bucket
		Map<Integer,Long> result2 = list.stream().collect(Collectors.groupingBy(value -> value%2, Collectors.counting()));
		//after grouping get the list of the value converted in string. The second collectors (mapping) collect a subpart of the final result (downstream collector)
		Map<Integer, List<String>> result3 = list.stream().collect(Collectors.groupingBy(value -> value%2, Collectors.mapping(value -> value.toString(), Collectors.toList())));

		System.out.println(result.get(1).toString());
		System.out.println(result2.get(1));
		System.out.println(result3.get(1));
		System.out.println(result.get(0).toString());
		System.out.println(result2.get(0));
		System.out.println(result3.get(0));
		
		//separate every element with | and enclose the entire result with {}
		String stringResult = list.stream().map(x -> x.toString()).collect(Collectors.joining("|", "{", "}"));
		System.out.println(stringResult);
	}
	
	private static void collectionMethods() {
		
		Map<Integer, String> valueStringMap = new HashMap<>();
		
		for(int i = 0 ; i < 10 ; i++) { //execute the function if the value is not in the map
			valueStringMap.computeIfAbsent(i, value -> value.toString());
		}
		
		//iterate on the value of a maps
		valueStringMap.forEach((key, value) -> System.out.println(key + " : " + value));
		
		double[] values = new double[100];
		Arrays.parallelSetAll(values, i -> 1*10); //fill the array's elements with value of the index * 10
				
	}
	
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
	
	private static void debugLambdaCode() {
		
		List<Integer> list = Arrays.asList(2,5,7,9); //diamond notation
		
		List<Integer> mergedList = Stream.of(list, Arrays.asList(1,3,4)) //create a stream of list
				.flatMap(elemt -> elemt.stream()) //get the stream of every list and merge together
				.peek(value -> System.out.println(value))
				.sorted()
				.peek(value -> System.out.println(value))
				.collect(Collectors.toList());
	}
}
