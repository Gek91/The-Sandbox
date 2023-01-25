package event.events;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

public class MyEventPublisher implements ApplicationEventPublisherAware {

	private ApplicationEventPublisher publisher;

	public MyEventPublisher(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.publisher = publisher;
	}

	public void doPublish(String value) {
		System.out.println("publishing event:" + value);
		this.publisher.publishEvent(new MyEvent(this, value));
		System.out.println("published event:" + value);

	}
}
