package propertiesExample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class EnvironmentFieldService {

	@Autowired
	private Environment env;

	public String getEnvironmentField() {
		return env.getProperty("environment.field1");
	}
}
