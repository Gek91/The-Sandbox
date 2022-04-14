package java9.reactivestream;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class MySubscriberAlternative implements Subscriber<MyMessageAlternative> {

	private Subscription subscription;
	
	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		subscription.request(1); //needed
		System.out.println("Subscribed alternative");
	}

	@Override
	public void onNext(MyMessageAlternative item) {
		System.out.println("Process alternative message");
		this.subscription.request(1);
		System.out.println("Alternative message payload " + item.getPayload());
	}

	@Override
	public void onError(Throwable throwable) {
		System.out.println("Error!!!!");
	}

	@Override
	public void onComplete() {
		System.out.println("Processing complete for alternative");
	}

}
