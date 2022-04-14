package java8.interfacedefaultmethod;

public interface InterfaceWithMethod {

	/* This is a default method so we need not
     * to implement this method in the implementation 
     * classes  
     */
	default Long defaultMethod() {
		return 1l;
	}
	
	/* This is a static method. Static method in interface is
     * similar to default method except that we cannot override 
     * them in the implementation classes.
     * Similar to default methods, we need to implement these methods
     * in implementation classes so we can safely add them to the 
     * existing interfaces.
     */
	static Long staticMethod() {
		return 2l;
	}
	
	Long interfaceMethod();
}
