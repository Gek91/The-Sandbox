package application.interfaceBinding;

import application.interfaceBinding.SimpleService;

public class MockSimpleServiceImpl implements SimpleService {
	@Override
	public void doSomething() {
		System.out.println("Mock");
	}
}
