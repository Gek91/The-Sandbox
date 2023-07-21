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

//		streamExample();

//		methodReferencesExample();
		
//		collectorExample();
		
//		collectionMethods();

	}
	
	private static void functionalInterfaceExample() {
		//Lambda with no argument
		Runnable noArguments = () -> System.out.println("Hello World");
		noArguments.run();

		//multi statement
		Runnable multiStatement = () -> {
			System.out.println("Statement 1");
			System.out.println("Statement 2");
		};
		multiStatement.run();

		//Consumer:
		// java8.lambda with one argument (get a value, no return)
		Consumer<Integer> oneArgument = x -> System.out.println("Hello World " + x);
		oneArgument.accept(10);
		
		//Supplier:
		// no argument return a value
		Supplier<String> noArgumentsReturnValue = () -> "Hello World";
		System.out.println("noArgumentsReturnValue :" + noArgumentsReturnValue.get());
		
		//BynaryOperator:
		// Get two parameter of the same tipe and return a result of the same java8.time
		BinaryOperator<Long> add = (Long x, Long y) -> x + y;
		System.out.println("add: " + add.apply(2l, 3l));
		
		//Function:
		// get an argument a return a result of a different type
		Function<Long, String> function = (Long x) -> "value : " + x; 
		System.out.println("function: " + function.apply(10l));
		
		//Predicate:
		// get a value and return a boolean
		Predicate<Long> booleanValue = (Long x) -> x > 0;
		System.out.println("booleanValue: " + booleanValue.test(4l));
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

}
