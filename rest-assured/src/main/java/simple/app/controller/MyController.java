package simple.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import simple.app.model.Example;
import simple.app.repository.ExampleRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class MyController {

	@Autowired
	private ExampleRepository exampleRepository;

	@GetMapping(path="/examples/{name}", produces="application/json")
	ResponseEntity<Example> getExampleByName(@PathVariable("name") String name) {
		Optional<Example> exampleOptional = exampleRepository.getExampleByName(name);

		if(exampleOptional.isPresent()) {
			return new ResponseEntity<>(exampleOptional.get(), HttpStatus.OK);
		}

		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@GetMapping(path="/examples", produces="application/json")
	List<Example> getExamples() {
		return exampleRepository.getExamples();
	}

	@PostMapping(path="/examples", produces="application/json", consumes="application/json")
	ResponseEntity<Void> createExample(@RequestBody Example example) {
		exampleRepository.addExample(example);

		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

	@PutMapping(path="/examples/{name}", produces="application/json", consumes="application/json")
	ResponseEntity<Void> updateExample(@RequestBody Example example) {
		exampleRepository.updateExample(example);

		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

	@DeleteMapping(path="/examples/{name}", produces="application/json")
	ResponseEntity<Void> deleteExample(@PathVariable("name") String name) {
		exampleRepository.deleteExample(name);

		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}
}
