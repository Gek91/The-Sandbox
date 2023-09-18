package pack.code;

import pack.code.MyInterface;
public class Myclass implements MyInterface {

	public static void doSomething() {
		System.out.println("Hello, Modules!");
	}


	@Override
	public void sayHello() {
		System.out.println("Hello!");
	}
}
