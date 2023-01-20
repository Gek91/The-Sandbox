package IoCExample;

import IoCExample.beans.AnotherImplementation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecondaryConfigFile {

	@Bean
	public AnotherImplementation anotherImplementation() {
		return new AnotherImplementation();
	}
}
