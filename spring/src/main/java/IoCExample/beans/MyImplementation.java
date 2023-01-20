package IoCExample.beans;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class MyImplementation implements MyInterface {

	private AnotherImplementation anotherImplementation;

	public MyImplementation(AnotherImplementation anotherImpl) {
		this.anotherImplementation = anotherImpl;
	}

	@Override
	public void doSomething() {
		System.out.println("implementation");
		anotherImplementation.doSomethingElse();
	}

//	@PostConstruct
	public void init() {
		System.out.println("init");
	}

//	@PreDestroy
	public void cleanUp() {
		System.out.println("cleanup");
	}
}
