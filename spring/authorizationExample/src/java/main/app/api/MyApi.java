package app.api;

import app.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyApi {

	@Autowired
	private MyService myService;

	@GetMapping(path = "/all", produces = "application/json")
	public ResponseEntity<Void> getAll() {
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

	@GetMapping(path = "/admin", produces = "application/json")
	public ResponseEntity<Void> getAdmin() {
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

	@GetMapping(path = "/user", produces = "application/json")
	public ResponseEntity<Void> getUser() {
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

	@GetMapping(path = "/method-admin", produces = "application/json")
	public ResponseEntity<Void> getAdminMethodSecurity() {

		myService.onlyAdminLogic();

		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

	@GetMapping(path = "/method-user", produces = "application/json")
	public ResponseEntity<Void> getUserMethodSecurity() {

		myService.userLogic();

		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}
}
