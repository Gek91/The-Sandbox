package IoCExample.beans;

public class LazyBean {

	public LazyBean() {
		System.out.println("Init lazy");
	}

	public void doSomething() {
		System.out.println("do something lazy");
	}
}
