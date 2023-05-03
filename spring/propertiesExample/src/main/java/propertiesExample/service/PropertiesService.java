package propertiesExample.service;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "properties")
@Data
public class PropertiesService {

	private String field1;
	private String field2;
	private NestedProperties nested;
}
