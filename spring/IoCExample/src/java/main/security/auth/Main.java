package security.auth;

import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

public class Main {

	public static void main(String[] args) {

		//direct setting of securityContextHolder
		SecurityContext context = SecurityContextHolder.createEmptyContext();
		Authentication authentication = new TestingAuthenticationToken("username", "password", "ROLE_USER");
		context.setAuthentication(authentication);
		SecurityContextHolder.setContext(context);

		//access context
		context = SecurityContextHolder.getContext();
		authentication = context.getAuthentication();
		System.out.println(authentication.getName());
		System.out.println(authentication.getPrincipal());
		System.out.println(authentication.getAuthorities());

		//in memory user
		UserDetails user = User.builder()
				.username("user")
				.password(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("password"))
				.roles("USER")
				.build();

		UserDetails user2 = User.withDefaultPasswordEncoder()
				.username("plainUser")
				.password("password")
				.roles("USER")
				.build();

		new InMemoryUserDetailsManager(user, user2);

	}
}
