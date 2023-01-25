package event;


import event.events.MyAnnotatedListener;
import event.events.MyEventListener;
import event.events.MyEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass=true) //needed for aspect annotation scanning
public class ConfigFile {

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@Bean
	public MyEventListener myEventListener() {
		return new MyEventListener();
	}

	@Bean
	public MyAnnotatedListener myAnnotatedListener() {
		return new MyAnnotatedListener();
	}

	@Bean
	public MyEventPublisher myEventPublisher() {
		return new MyEventPublisher(eventPublisher);
	}
}
