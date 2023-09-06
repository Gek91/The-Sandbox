package producer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import producer.controller.data.AnotherException;
import producer.controller.data.ErrorResponse;
import producer.controller.data.MyException;

@RestController
public interface ErrorHandlingExampleAPI {

	@GetMapping(path="/my-exception")
	ResponseEntity<Void> runMyExceptionExample();

	@GetMapping(path="/my-exception-exception-handler")
	ResponseEntity<Void> runMyExceptionWithExceptionHandlerExample();

	@GetMapping(path="/my-exception-advice-handler")
	ResponseEntity<Void> runMyExceptionWithAdviceExample();

	//exception handler
	@ExceptionHandler(AnotherException.class) //exception to manage
	@ResponseStatus(HttpStatus.CONFLICT) //http response in case of exception
	ResponseEntity<ErrorResponse> handlerSpecificException(); //it's possible to add as argument the request and the exception

	//fallback exception handler
	/*@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	ResponseEntity<ErrorResponse> handleAllUncaughtException(Exception exception, WebRequest request);*/
}
