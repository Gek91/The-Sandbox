package application.simpleBinding;

public class SimpleEagerBind {

	int counter = 0;

	public SimpleEagerBind() {
		System.out.println("bind constructor " + counter++);
	}

	public void doSomething() {
		System.out.println("bind method " + counter++);
	}
}
