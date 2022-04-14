package basePattern.registry;

/*
 * singleton
 */
public class Registry {

	private static Registry soleInstance = new Registry();
	
	private static Registry getInstance() {
		return soleInstance;
	}
	
	
	protected PersonFinder personFinder = new PersonFinder();
	
	public static PersonFinder personFinder() {
		return getInstance().personFinder;
	}
}
