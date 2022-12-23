package webapp.logicModule.rest;

import com.google.inject.Inject;
import webapp.logicModule.service.MyService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/")
public class MyRest {

	@Inject
	private MyService myService;

	@GET
	@Path("/test")
	public Response test() {

		myService.doSomething();

		return Response.ok().build();
	}
}
