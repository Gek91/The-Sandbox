package producer.controller.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import producer.controller.ErrorHandlingExampleAPI;

import producer.controller.data.AdviceException;
import producer.controller.data.AnotherException;
import producer.controller.data.ErrorResponse;
import producer.controller.data.MyException;
;import java.time.Instant;

@Slf4j
@RestController
public class ErrorHandlingExampleAPIImpl implements ErrorHandlingExampleAPI {

	@Override
	public ResponseEntity<Void> runMyExceptionExample() {

		if(Instant.now().toEpochMilli() % 2 == 0) {
			//exception annotated with specific HTTP status code
			throw new MyException();
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> runMyExceptionWithExceptionHandlerExample() {

		if(Instant.now().toEpochMilli() % 2 == 0) {
			//exception annotated with specific HTTP status code
			throw new AnotherException();
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> runMyExceptionWithAdviceExample() {

		if(Instant.now().toEpochMilli() % 2 == 0) {
			//exception annotated with specific HTTP status code
			throw new AdviceException();
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}

	//AnotherException handler, in case of the specified exception execute te logic
	public ResponseEntity<ErrorResponse> handlerSpecificException() {

		ErrorResponse response = new ErrorResponse();
		response.setBody("hello");

		//same http error as the declaration
		return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
	}

	/*public ResponseEntity<ErrorResponse> handleAllUncaughtException(Exception exception, WebRequest request) {

		ErrorResponse response = new ErrorResponse();
		response.setBody(request.getContextPath() + " " + exception.getMessage());

		return ResponseEntity.status(HttpStatus.CONFLICT).body(response);

	}*/

}
