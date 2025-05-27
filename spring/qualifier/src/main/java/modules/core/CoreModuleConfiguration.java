package modules.core;

import modules.core.service.CoreService;
import modules.core.service.QualifierService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CoreModuleConfiguration {

	@Bean
	public CoreService coreService(@Qualifier("qual") List<QualifierService> qualifierService) {
		return new CoreService(qualifierService);
	}


	@Bean(name = "coreQualifier") //different every bean
	@Qualifier("qual") //same every bean
	public QualifierService qualifierService() {
		return new QualifierService();
	}
}