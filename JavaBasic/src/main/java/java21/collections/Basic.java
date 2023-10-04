package java21.collections;

import java.util.*;

public class Basic {

	public static void main(String[] args) {

		//new interfaces and method to simplify collection processing

		SequencedCollection<Integer> collection = new ArrayList<Integer>(Arrays.asList(1,2,3));

		collection.getFirst();
		collection.getLast();
		collection.addFirst(10);
		collection.removeFirst();
		collection.addLast(20);
		collection.removeLast();
		collection.reversed();


		//no duplicate
		SequencedSet<Integer> set = new LinkedHashSet<>(Arrays.asList(1,2,3));

		set.getFirst();
		set.getLast();
		set.addFirst(10);
		set.removeFirst();
		set.addLast(20);
		set.removeLast();
		set.reversed();


		SequencedMap<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("1",1);
		map.put("2",2);
		map.put("3",3);

		map.firstEntry();
		map.lastEntry();
		map.putFirst("4",4);
		map.pollFirstEntry();
		map.putLast("5",5);
		map.pollLastEntry();
		map.sequencedValues();
		map.sequencedKeySet();
		map.sequencedEntrySet();

		//unmodfiable builder
		Collections.unmodifiableSequencedCollection(collection);
		Collections.unmodifiableSequencedMap(map);
		Collections.unmodifiableSequencedSet(set);
	}
}
