package simple.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class MyController {

	Logger logger = LoggerFactory.getLogger(MyController.class);

	@GetMapping(path="/")
	String log() {
		//default configuration not showing
		logger.trace("A TRACE Message");
		logger.debug("A DEBUG Message");
		//showing in default configuration (default level INFO)
		logger.info("An INFO Message");
		logger.warn("A WARN Message");
		logger.error("An ERROR Message");

		return "i'm logging";
	}
}
