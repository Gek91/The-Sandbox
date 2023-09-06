package producer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import producer.controller.data.AdviceException;
import producer.controller.data.ErrorResponse;

@ControllerAdvice
//@ControllerAdvice("producer.controller") //apply advice only at controller in the package
//@ControllerAdvice(annotations = Advised.class) //apply advice only at the controller annotated
public class ControllerAdviceExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(AdviceException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ResponseEntity<ErrorResponse> handleAdviceException(AdviceException exception, WebRequest request) {

		ErrorResponse response = new ErrorResponse();
		response.setBody("advice " + request.getContextPath() + " " + exception.getMessage());

		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
	}
}
