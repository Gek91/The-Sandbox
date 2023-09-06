package producer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import producer.controller.data.Message;

import java.util.List;

@RestController
public interface ProducerRestApi {

	@GetMapping(path="/get-value", produces="application/json")
	ResponseEntity<Message> getExpenseById();

	@PostMapping(path="/post-value", consumes="application/json")
	ResponseEntity<Void> createExpense(@RequestBody Message message);
}
