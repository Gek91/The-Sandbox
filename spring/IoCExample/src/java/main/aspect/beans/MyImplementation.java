package aspect.beans;


public class MyImplementation implements MyInterface {

	@Override
	public void doSomething() {
		System.out.println("implementation");
	}

	@Override
	public String doSomethingWithPar(String par) {
		System.out.println("implementation:" +par);

		return par;
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
