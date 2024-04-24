package com.example.demo;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAPITest {

	@GetMapping(path="/test", produces="text/plain")
	String test() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String apiKey = (String) auth.getPrincipal();

		System.out.println(apiKey);

		return "OK";
	}

}
