package event;


import event.events.MyEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(ConfigFile.class);

		context.refresh();

		//needed to call the pre destroy lifecycle call back of beans
		context.registerShutdownHook();

		MyEventPublisher bean = context.getBean(MyEventPublisher.class);
		bean.doPublish("yee");

		bean.doPublish("yaa");

	}
}
