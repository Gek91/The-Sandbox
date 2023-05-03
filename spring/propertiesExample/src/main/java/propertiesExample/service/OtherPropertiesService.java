package propertiesExample.service;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
@Data
public class OtherPropertiesService {

	private String field1;
	private String field2;
}
