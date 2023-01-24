package aspect;

import aspect.aspects.MyAspect;
import aspect.beans.MyImplementation;
import aspect.beans.MyInterface;
import org.springframework.context.annotation.*;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass=true) //needed for aspect annotation scanning
public class ConfigFile {

	@Bean(initMethod = "init", destroyMethod = "cleanUp")
	public MyInterface myInterface() {
		return new MyImplementation();
	}

	@Bean
	public MyAspect myAspect() {
		return new MyAspect();
	}
}
