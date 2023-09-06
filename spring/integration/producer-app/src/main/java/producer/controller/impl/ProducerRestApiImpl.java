package producer.controller.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import producer.controller.ProducerRestApi;

import producer.controller.data.Message;
;

@Slf4j
@RestController
public class ProducerRestApiImpl implements ProducerRestApi {

	@Override
	public ResponseEntity<Message> getExpenseById() {

		Message message = new Message();
		message.setStringValue("value");
		message.setLongValue(1l);

		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> createExpense(@RequestBody Message message) {

		System.out.println("Producer received message");
		System.out.println(message.getLongValue());
		System.out.println(message.getStringValue());

		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}
}
