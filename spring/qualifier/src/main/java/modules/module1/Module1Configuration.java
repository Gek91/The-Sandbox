package modules.module1;

import modules.core.service.QualifierService;
import modules.module1.service.QualifierModule1Service;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import modules.core.CoreModuleConfiguration;

@Import({
		CoreModuleConfiguration.class
})
@Configuration
public class Module1Configuration{

	@Bean(name = "module1Qualifier") //different every bean
	@Qualifier("qual") //same every bean
	public QualifierService qualifierService() {
		return new QualifierModule1Service();
	}
}
