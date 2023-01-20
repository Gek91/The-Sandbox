package IoCExample;

import IoCExample.beans.*;
import org.springframework.context.annotation.*;

@Configuration
@Import(SecondaryConfigFile.class)
public class ConfigFile {

	@Bean(initMethod = "init", destroyMethod = "cleanUp")
	public MyInterface myInterface(AnotherImplementation anotherImplementation) {
		return new MyImplementation(anotherImplementation);
	}

	@Bean
	@Lazy
	public LazyBean lazyBean() {
		return new LazyBean();
	}

	@Bean
	@Scope("prototype")
	public PrototypeScopeBean prototypeScopeBean() {
		return new PrototypeScopeBean();
	}

	@Bean
	//no need to declare scope, singleton is default value
	public SingletonScopeBean singletonScopeBean() {
		return new SingletonScopeBean();
	}

	@Bean("profiledBean")
	@Profile("dev")
	public ProfiledBean profiledBeanDev() {
		return new ProfiledBean("dev");
	}

	@Bean("profiledBean")
	@Profile("prod")
	public ProfiledBean profiledBeanProd() {
		return new ProfiledBean("prod");
	}
}
