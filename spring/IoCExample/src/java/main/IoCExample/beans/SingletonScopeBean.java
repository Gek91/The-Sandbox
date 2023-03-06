package IoCExample.beans;

import java.time.Instant;

public class SingletonScopeBean {

	private Instant now = Instant.now();

	public void printCreationTime() {
		System.out.println(now);
	}
}
