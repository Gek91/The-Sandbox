package IoCExample.beans;

public class ProfiledBean {

	private String value;

	public ProfiledBean(String value) {
		this.value = value;
	}

	public void doSomething() {
		System.out.println(value);
	}
}
