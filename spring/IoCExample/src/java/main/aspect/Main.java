package aspect;


import aspect.beans.MyImplementation;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(ConfigFile.class);

		context.refresh();

		//needed to call the pre destroy lifecycle call back of beans
		context.registerShutdownHook();

		MyImplementation bean = context.getBean(MyImplementation.class);
		bean.doSomething();


		bean.doSomethingWithPar("ciao");
	}
}
