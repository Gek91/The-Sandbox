package consumer.controller.impl;

import consumer.controller.ConsumerRestApi;
import consumer.controller.data.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@Slf4j
@RestController
public class ConsumerRestApiImpl implements ConsumerRestApi {

	@Override
	public ResponseEntity<Void> doGetHeaders() {
		String uri = "http://localhost:8090/get-value";
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders httpHeaders = restTemplate.headForHeaders(uri);
		System.out.println(httpHeaders.toString());

		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> doGet() {
		String uri = "http://localhost:8090/get-value";
		RestTemplate restTemplate = new RestTemplate();

		Message value = restTemplate.getForObject(uri, Message.class);
		System.out.println(value.getLongValue());
		System.out.println(value.getStringValue());

		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> doPost() {
		String uri = "http://localhost:8090/post-value";
		RestTemplate restTemplate = new RestTemplate();

		Message value = new Message();
		value.setLongValue(10l);
		value.setStringValue("hello!");

		restTemplate.postForObject(uri, value, Void.class);

		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	//more generic API method
	@Override
	public ResponseEntity<Void> doExchange() {

		String uri = "http://localhost:8090/post-value";
		RestTemplate restTemplate = new RestTemplate();

		Message value = new Message();
		value.setLongValue(12l);
		value.setStringValue("exchange!");
		HttpEntity<Message> messageEntity = new HttpEntity<>(value);

		ResponseEntity<Void> response = restTemplate.exchange(uri, HttpMethod.POST, messageEntity, Void.class);

		System.out.println(response.getStatusCode());

		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> doGetOptions() {

		String uri = "http://localhost:8090/get-value";
		RestTemplate restTemplate = new RestTemplate();

		Set<HttpMethod> optionsForAllow = restTemplate.optionsForAllow(uri);
		for(HttpMethod method : optionsForAllow) {
			System.out.println(method.toString());
		}

		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> doGetWithCTimeout() {

		RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());

		String uri = "http://localhost:8090/get-value";


		restTemplate.getForObject(uri, Void.class);

		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	private ClientHttpRequestFactory getClientHttpRequestFactory() {

		int timeout = 1;

		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
				= new HttpComponentsClientHttpRequestFactory();
		clientHttpRequestFactory.setConnectTimeout(timeout);

		return clientHttpRequestFactory;
	}

}
