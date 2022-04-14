package java9.reactivestream;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.SubmissionPublisher;

public class ReactiveMain {

	public static void main(String[] args) throws InterruptedException {
		
		//Create publisher
		SubmissionPublisher<MyMessage> publisher = new SubmissionPublisher<>();
		
		//register subscriber
		MySubscriber subscriber = new MySubscriber();
		publisher.subscribe(subscriber);
		
		//alternative subscriber with different format of message
		MySubscriberAlternative alternativeSubscriber = new MySubscriberAlternative();
		
		//create processor
		MyProcessor processor = new MyProcessor();
		publisher.subscribe(processor); //chain o publisher - processor - subscriber
		processor.subscribe(alternativeSubscriber);
		
		List<MyMessage> messages = buildMessages();
		
		messages.forEach( message -> publisher.submit(message));
		
		Thread.sleep(10000);
		
		//close publisher
		publisher.close();
		
	}
	
	private static List<MyMessage> buildMessages() {
		
		List<MyMessage> messages = new ArrayList<>();
		
		MyMessage message = new MyMessage();
		message.setPayload("hello");
		messages.add(message);
		
		message = new MyMessage();
		message.setPayload("world");
		messages.add(message);

		message = new MyMessage();
		message.setPayload("!!!");
		messages.add(message);

		return messages;
	}
}
