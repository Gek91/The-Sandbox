package java9.reactivestream;

import java.util.concurrent.Flow.Processor;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;

/*
 * Processor used for translate message object to another object format
 */
public class MyProcessor extends SubmissionPublisher<MyMessageAlternative> implements Processor<MyMessage, MyMessageAlternative>{

	private Subscription subscription;

	
	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		subscription.request(1); //needed
	}

	@Override
	public void onNext(MyMessage item) {
		
		//transformation logic
		MyMessageAlternative newMessage = new MyMessageAlternative();
		newMessage.setPayload(item.getPayload());
		
		submit(newMessage);
	    subscription.request(1);  
	}

	@Override
	public void onError(Throwable throwable) {
		throwable.printStackTrace();
	}

	@Override
	public void onComplete() {
		System.out.println("Done");
	}

}
