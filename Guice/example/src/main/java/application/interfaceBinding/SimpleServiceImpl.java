package application.interfaceBinding;

import application.interfaceBinding.SimpleService;

public class SimpleServiceImpl implements SimpleService {

	private int counter = 0;

	@Override
	public void doSomething() {
		System.out.println("simple impl " + counter++ );
	}
}
