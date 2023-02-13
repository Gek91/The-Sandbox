package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.context.NullSecurityContextRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance()).withUser("user").password("user").roles("USER");
		auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance()).withUser("admin").password("admin").roles("ADMIN");
	}

	//enable password authentication
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests(
						(authorize) -> authorize.anyRequest().access(new CustomAuthorizationManager()))
				.authorizeHttpRequests()
				.and()
				.httpBasic(Customizer.withDefaults());

		//no user session enabled
		http.securityContext().securityContextRepository(new NullSecurityContextRepository());
	}
}
