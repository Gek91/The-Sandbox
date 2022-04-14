package java9.collectionFactory;

import java.util.List;
import java.util.Map;
import java.util.Set;


public class Basic {

	
	public static void main(String[] args) {
		
		//immutable list
		List immutableList = List.of();
		immutableList = List.of("one", "two", "three");
		
		Set immutableSet = Set.of();
		immutableSet = Set.of("one", "two", "three");
		
		Map immutableMap = Map.of();
		immutableMap = Map.of(1, "one", 2, "two", 3, "three");
		
	}
}
