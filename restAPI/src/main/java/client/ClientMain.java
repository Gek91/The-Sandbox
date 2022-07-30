package client;

import dto.PostInputDTO;
import dto.ResultDTO;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import server.RestApi;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

public class ClientMain {

	public static void main(String[] args) {

		ResteasyClient client = (ResteasyClient) ClientBuilder.newClient();
		ResteasyWebTarget target = client.target("http://127.0.0.1:8080/rest-API/rest");
		RestApi proxy = target.proxy(RestApi.class);

		ResultDTO response = proxy.getInfo();
		System.out.println(response.getResult());

		PostInputDTO request = new PostInputDTO();
		request.setValue("ciao");
		Response httpResponse = proxy.postApi(request);
		System.out.println(httpResponse.getStatus());
	}
}
