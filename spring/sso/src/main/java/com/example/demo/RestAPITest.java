package com.example.demo;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAPITest {

	@GetMapping(path="/test", produces="text/plain")
	String test() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Jwt jwt = (Jwt) auth.getPrincipal();

		System.out.println(jwt.getClaims().get("email"));

		System.out.println(jwt.getTokenValue());

		return "OK";
	}

}
