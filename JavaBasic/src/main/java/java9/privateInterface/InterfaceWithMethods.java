package java9.privateInterface;

public interface InterfaceWithMethods {

	default Long defaultMethod() {
		return privateMethod() + 1;
	}
	
	/*
	 * used to split default method in smaller components
	 */
	private Long privateMethod() {
		
		return 1l;
	}
	
	static Long staticMethod() {
		return privateStaticMethod() + 1;
	}
	
	/*
	 * used to split static method in smaller components 
	 */
	private static Long privateStaticMethod() {
		return 2l;
	}
	
	
}
