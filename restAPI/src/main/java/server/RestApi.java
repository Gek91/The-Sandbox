package server;

import dto.PostInputDTO;
import dto.ResultDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api")
public interface RestApi {

	@GET
	@Path("/info")
	@Produces(MediaType.APPLICATION_JSON)
	ResultDTO getInfo();

	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	Response postApi(PostInputDTO input);

}
