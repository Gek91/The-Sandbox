import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Single;
import rx.observables.ConnectableObservable;
import rx.subjects.PublishSubject;

public class MainRx {

	public static void main(String[] args) {
		
		final StringBuffer result = new StringBuffer("");
		
		String[] letters = {"a", "b", "c", "d", "e", "f", "g"};
		
		
		Observable<String> observable = Observable.from(letters);
		
		observable.subscribe( //start emitting event when subscribe
		  i -> result.append(i),  //OnNext
		  Throwable::printStackTrace, //OnError
		  () -> result.append("_Completed") //OnCompleted
		);
		
		System.out.println(result.toString()); //abcdefg_Completed
		
		
		// MAP
		
		final StringBuffer result2 = new StringBuffer("");
		
		Observable.from(letters)
			.map(String::toUpperCase) //transformation on element
			.subscribe(
					letter -> result2.append(letter) //OnNext
					);
		
		System.out.println(result2.toString()); //ABCDEFG
		

		// FLAT MAP
		
		final StringBuffer result3 = new StringBuffer("");

		Observable.just("book1", "book2")
		  .flatMap(s -> getTitle(s)) //function return Observable
		  .subscribe(
				  l -> result3.append(l) //onNext
				  );
		
		System.out.println(result3.toString()); //book1book2
		
		//SCAN
		
		final StringBuffer result4 = new StringBuffer("");
		
		Observable.from(letters)
		  .scan(new StringBuilder(), StringBuilder::append) //generate an obsevable containing element builded from a forwarded state (first, first+second, first+second+third ...)
		  .subscribe(total -> result4.append(total.toString())); //on next
		
		System.out.println(result4.toString()); //aababcabcdabcdeabcdefabcdefg

		
		//GROUP BY
		
		List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8);
		
		List<Integer> even = new ArrayList<>();
		List<Integer> odd = new ArrayList<>();
		
		Observable.from(numbers)
		  .groupBy(i -> 0 == (i % 2) ? "EVEN" : "ODD") //classify into group categories
		  .subscribe(group ->
		    group.subscribe((number) -> { //on next
		        if (group.getKey().toString().equals("EVEN")) { //key selector
		        	even.add(number);
		        } else {
		        	odd.add(number);
		        }
		    })
		  );
		
		System.out.println(even);
		System.out.println(odd);
		
		//FILTER
		
		final StringBuffer result5 = new StringBuffer("");
		
		Observable.from(numbers)
			.filter(i -> (i % 2 == 1)) //filter
			.subscribe(i -> result5.append(i)); //on next
		
		System.out.println(result5.toString());
		
		//conditional operator
		
		final StringBuffer result6 = new StringBuffer("");
		
		Observable.empty()
		  .defaultIfEmpty("Observable is empty") 
		  .subscribe(s -> result6.append(s)); //on next

		System.out.println(result6.toString());

		final StringBuffer result7 = new StringBuffer("");

		Observable.from(numbers)
		  .defaultIfEmpty(0)
		  .first() //first element
		  .subscribe(s -> result7.append(s)); //on next
		
		System.out.println(result7.toString());

		Integer[] sum = {1};
		
		Observable.from(numbers)
			.takeWhile(i -> i < 5) //stream event while
			.subscribe(s -> sum[0] += s );
		
		System.out.println(sum[0]);
		
		//connectable obsevables
		
		String[] resultString = {""};
		ConnectableObservable<Long> connectable = Observable.interval(200, TimeUnit.MILLISECONDS).publish(); //emit incremental value every 200 milliseconds
		
		connectable.subscribe(i -> resultString[0] += i); //doesn't emit

		connectable.connect(); //start emitting
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(resultString[0]); //01, first 2 event emitted
		
		//single
		
		String[] resultString1 = {""};
		Single<String> single = Observable.just("Hello") //emit a single event or an error notification
		  .toSingle()
		  .doOnSuccess(i -> resultString1[0] += i)
		  .doOnError(error -> {
		      throw new RuntimeException(error.getMessage());
		  });
		single.subscribe();
		
		System.out.println(resultString1[0]); //Hello
		
		//Subject
		Integer[] subscriber = {0,0};
		
		Observer<Integer> firstObjserver = new Observer<Integer>() {
			
			 	@Override
		        public void onNext(Integer value) {
		           subscriber[0] += value;
		        }

		        @Override
		        public void onError(Throwable e) {
		            System.out.println("error");
		        }

		        @Override
		        public void onCompleted() {
		            System.out.println("Subscriber1 completed");
		        }
		};
		
		Observer<Integer> secondObjserver = new Observer<Integer>() {
			
		 	@Override
	        public void onNext(Integer value) {
		 		subscriber[1] += value;
	        }

	        @Override
	        public void onError(Throwable e) {
	            System.out.println("error");
	        }

	        @Override
	        public void onCompleted() {
	            System.out.println("Subscriber2 completed");
	        }
	};
		
		PublishSubject<Integer> subject = PublishSubject.create(); // can be user to publish event coming from more than one osservable and this event can be reemitted to subscribing observer.
		
		subject.subscribe(firstObjserver);
		subject.onNext(1);
		subject.onNext(2);
		subject.onNext(3);
		subject.subscribe(secondObjserver);
		subject.onNext(4);
		subject.onCompleted();

		System.out.println(subscriber[0]);
		System.out.println(subscriber[1]);
		
		//resource manager
		
		String[] result10 = {""};
		
		Observable<Character> values = Observable.using(
		  () -> "MyResource", //resource factory
		  r -> { //observable factory
		      return Observable.create(o -> {
		          for (Character c : r.toCharArray()) {
		              o.onNext(c);
		          }
		          o.onCompleted();
		      });
		  },
		  r -> System.out.println("Disposed: " + r) //disposed function
		);
		values.subscribe(
		  v -> result10[0] += v, //on next
		  e -> result10[0] += e //on error
		);
		
		System.out.println(result10[0]);
	}
	
	private static Observable<String> getTitle(String string) {
	    return Observable.from(Arrays.asList(string));
	} 
}
