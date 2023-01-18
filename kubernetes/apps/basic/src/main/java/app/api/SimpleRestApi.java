package app.api;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Properties;


@RestController
public class SimpleRestApi {

	private Instant timer = Instant.now();

	@GetMapping(path="/hello", produces="application/json")
	public String hello() {
		return "hello";
	}

	@GetMapping(path="/health-timer-fail")
	public ResponseEntity<Void> failAfter30Seconds() {

		if(Instant.now().isAfter(timer.plus(30, ChronoUnit.SECONDS))) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path="/config-map")
	public String returnConfigMapValues() {

		String hello = System.getenv().getOrDefault("HELLO", "not found");
		String singleHello = System.getenv().getOrDefault("HELLO_SINGLE", "not found");

		String propertyValue = null;

		try {
			Properties prop = new Properties();

			File file = new File("/application.properties");
			prop.load(new FileInputStream(file));

			propertyValue = prop.getProperty("key1");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "HELLO : " + hello + " | SINGLE_HELLO: " + singleHello + " | file property key1: " + propertyValue;
	}
}
