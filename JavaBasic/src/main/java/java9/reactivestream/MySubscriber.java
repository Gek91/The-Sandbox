package java9.reactivestream;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class MySubscriber implements Subscriber<MyMessage>{

	private Subscription subscription;
	
	@Override
	public void onSubscribe(Subscription subscription) {
		
		this.subscription = subscription;
		subscription.request(1); //needed
		System.out.println("Subscribed");
	}

	@Override
	public void onNext(MyMessage item) {
		System.out.println("Process message");
		this.subscription.request(1);
		System.out.println("Message payload " + item.getPayload());
	}

	@Override
	public void onError(Throwable throwable) {
		System.out.println("Error!!");
	}

	@Override
	public void onComplete() {
		System.out.println("Processing complete");
	}

}
