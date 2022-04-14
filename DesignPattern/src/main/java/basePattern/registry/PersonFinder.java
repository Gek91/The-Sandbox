package basePattern.registry;

public class PersonFinder {

	public Person find(long id) {
		
		//find logid
		
		//stub
		if(id == 1) {
			return new Person("Mario", "Rossi")
;		}
		
		throw new IllegalArgumentException();
	}
}
