package modules.module2;

import modules.core.service.QualifierService;
import modules.module1.Module1Configuration;
import modules.module2.service.QualifierModule2Service;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
		Module1Configuration.class
})
public class Module2Configuration {

	@Bean(name = "module2Qualifier") //different every bean
	@Qualifier("qual") //same every bean
	public QualifierService qualifierService() {
		return new QualifierModule2Service();
	}
}
