package server;

import dto.PostInputDTO;
import dto.ResultDTO;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RestApiImpl implements RestApi{

	@Override
	public ResultDTO getInfo() {

		ResultDTO result = new ResultDTO();

		result.setResult("OK");

		return result;
	}

	@Override
	public Response postApi(PostInputDTO input) {

		System.out.println(input.getValue());

		return Response.ok().build();
	}

}
