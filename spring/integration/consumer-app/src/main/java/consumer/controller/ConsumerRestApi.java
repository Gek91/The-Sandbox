package consumer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public interface ConsumerRestApi {

	@GetMapping(path  = "/do-get-headers")
	ResponseEntity<Void> doGetHeaders();

	@GetMapping(path = "/do-get")
	ResponseEntity<Void> doGet();

	@GetMapping(path="/do-post")
	ResponseEntity<Void> doPost();

	@GetMapping(path="/do-exchange")
	ResponseEntity<Void> doExchange();

	@GetMapping(path = "/do-get-options")
	ResponseEntity<Void> doGetOptions();

	@GetMapping(path = "/do-get-timeout")
	ResponseEntity<Void> doGetWithCTimeout();
}
