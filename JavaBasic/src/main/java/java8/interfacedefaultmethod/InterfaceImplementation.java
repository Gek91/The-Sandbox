package java8.interfacedefaultmethod;

public class InterfaceImplementation implements InterfaceWithMethod {

	@Override
	public Long interfaceMethod() {
		return 3L;
	}
	
	public static void main(String[] args) {

		InterfaceWithMethod obj = new InterfaceImplementation();
		
		obj.interfaceMethod();
		
		obj.defaultMethod();
		
		InterfaceWithMethod.staticMethod();
	}

}
