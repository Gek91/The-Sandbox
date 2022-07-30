package server;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class RestEasyServer extends Application {

	private Set<Object> singletons = new HashSet<>();

	public RestEasyServer() {
		singletons.add(new RestApiImpl());
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}
