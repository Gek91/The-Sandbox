package IoCExample.beans;

import java.time.Instant;

public class PrototypeScopeBean {

	private Instant now = Instant.now();

	public void printCreationTime() {
		System.out.println(now);
	}
}
