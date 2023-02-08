package security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.*;

import java.util.HashMap;
import java.util.Map;

public class Password {

	public static void main(String[] args) {

		String password = "security";
		PasswordEncoder passwordEncoder;
		String encryptedPassword;


		//default delegating password encoder
		 passwordEncoder =
				PasswordEncoderFactories.createDelegatingPasswordEncoder();
		encryptedPassword = passwordEncoder.encode(password); //encode
		System.out.println(password + " default-> " + encryptedPassword);
		System.out.println(passwordEncoder.matches(password, encryptedPassword)); //decode


		//custom delegating passoerd encoder
		Map encoders = new HashMap();
		encoders.put("bcrypt", new BCryptPasswordEncoder());
		encoders.put("noop", NoOpPasswordEncoder.getInstance());
//		encoders.put("pbkdf2", Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_5());
//		encoders.put("pbkdf2@SpringSecurity_v5_8", Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8());
//		encoders.put("scrypt", SCryptPasswordEncoder.defaultsForSpringSecurity_v4_1());
//		encoders.put("scrypt@SpringSecurity_v5_8", SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8());
//		encoders.put("argon2", Argon2PasswordEncoder.defaultsForSpringSecurity_v5_2());
//		encoders.put("argon2@SpringSecurity_v5_8", Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8());
		encoders.put("sha256", new StandardPasswordEncoder());

		passwordEncoder =
				new DelegatingPasswordEncoder("bcrypt", encoders);
		encryptedPassword = passwordEncoder.encode(password);
		System.out.println(password + " bcrypt-> " + encryptedPassword);
		System.out.println(passwordEncoder.matches(password, encryptedPassword));

		passwordEncoder =
				new DelegatingPasswordEncoder("noop", encoders);
		encryptedPassword = passwordEncoder.encode(password);
		System.out.println(password + " noop-> " + encryptedPassword);
		System.out.println(passwordEncoder.matches(password, encryptedPassword));

		passwordEncoder =
				new DelegatingPasswordEncoder("sha256", encoders);
		encryptedPassword = passwordEncoder.encode(password);
		System.out.println(password + " sha256-> " + encryptedPassword);
		System.out.println(passwordEncoder.matches(password, encryptedPassword));

		/////

		//hashing utilities
		User.UserBuilder users = User.withDefaultPasswordEncoder();
		UserDetails user = users
				.username("gianni")
				.password("security")
				.roles("user")
				.build();

		//return hashed password
		System.out.println(user.getPassword());

		/////


	}
}
