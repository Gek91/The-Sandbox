package event.events;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

public class MyAnnotatedListener {

	@EventListener
	public void processMyEvent(MyEvent event) {

		System.out.println("annotated event listener:" + event.getField());
	}

	@EventListener
	@Async
	public void processMyEventAsync(MyEvent event) {

		System.out.println("Async event listener:" + event.getField());

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
