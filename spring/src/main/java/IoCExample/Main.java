package IoCExample;

import IoCExample.beans.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Lazy;

public class Main {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(ConfigFile.class);

		context.getEnvironment().setActiveProfiles("prod");
//		context.getEnvironment().setDefaultProfiles("prod"); //setdefault profile

		context.refresh();

		//needed to call the pre destroy lifecycle call back of beans
		context.registerShutdownHook();

		MyImplementation bean = context.getBean(MyImplementation.class);
		bean.doSomething();

		//bean created only when used
		context.getBean(LazyBean.class).doSomething();

		//always same instance
		context.getBean(SingletonScopeBean.class).printCreationTime();
		context.getBean(SingletonScopeBean.class).printCreationTime();

		//new instance every invocation
		context.getBean(PrototypeScopeBean.class).printCreationTime();
		context.getBean(PrototypeScopeBean.class).printCreationTime();

		//profiled bean
		((ProfiledBean) context.getBean("profiledBean")).doSomething();
	}
}
