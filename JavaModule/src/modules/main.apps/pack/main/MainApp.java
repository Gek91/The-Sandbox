package pack.main;

import pack.code.Myclass;
import pack.code.MyInterface;

import java.util.ServiceLoader;

public class MainApp {

	public static void main(String[] args) {

		Myclass.doSomething();


		Iterable<MyInterface> services = ServiceLoader.load(MyInterface.class);
		MyInterface service = services.iterator().next();
		service.sayHello();
	}
}
