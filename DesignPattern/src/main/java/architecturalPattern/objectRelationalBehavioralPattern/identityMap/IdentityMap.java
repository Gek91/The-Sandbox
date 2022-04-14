package architecturalPattern.objectRelationalBehavioralPattern.identityMap;

import java.util.HashMap;
import java.util.Map;

public class IdentityMap {

	private static Map<Long, Person> persons = new HashMap<>();
	
	private static void addPerson(Person person) {
		persons.put(person.getId(), person);
	}
	
	public static Person getPerson(long key) {
		Person output = persons.get(key);
		
		if(output == null) {
			//search on persistence
			//output = ...
			if(output != null) {
				addPerson(output);
			}
		}
		
		return output;
	}
	
	
}
