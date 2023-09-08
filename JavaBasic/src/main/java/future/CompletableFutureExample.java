package future;

import java.util.concurrent.*;
import java.util.function.Supplier;

public class CompletableFutureExample {

	//extension of Future to improve the future limits:
	// - no manual completion
	// - no action on future result without blocking
	// - no chaining
	// - no combination
	// - no exception handling
	public static void main(String[] args) throws Exception {

		//basic example
		basicExample();

		callbackExample();

		combinationsExample();

		exceptionHandlingExample();
	}

	public static void basicExample() throws ExecutionException, InterruptedException {

		//manual future completion
		CompletableFuture<String> completableFuture = new CompletableFuture<String>();
		//complete manually
		completableFuture.complete("completed");
		String result = completableFuture.get(); //if not completed stuck forever
		System.out.println(result);


		//run task without result asynchronously
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			// Simulate a long-running Job
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
			System.out.println("I'll run in a separate thread than the main thread.");
		});
		future.get();

		//run task with result asynchronously
		Supplier<String> supplier = () -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
			return "Result of the asynchronous computation";
		};

		completableFuture = CompletableFuture.supplyAsync(supplier);
		result = completableFuture.get();
		System.out.println(result);


		//for now the task was executed on the global thread pool (ForkJoinPool.commonPool())
		//it's possible to pass the  completableFuture to specific thread pool
		Executor executor = Executors.newFixedThreadPool(10);
		completableFuture = CompletableFuture.supplyAsync(supplier, executor);
		result = completableFuture.get();
		System.out.println(result);
	}

	public static void callbackExample() throws ExecutionException, InterruptedException {

		//transformation on completableFuture
		//completableFuture accepts callback to define transformation on future result without blocking
		CompletableFuture<String> whatsYourNameFuture = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
			return "Gino";
		});

		//then apply
		CompletableFuture<String> greetingFuture = whatsYourNameFuture
				.thenApply(name -> "Hello " + name)
				.thenApply(s -> s + " Welcome");
		System.out.println(greetingFuture.get());

		//possibile to run the apply/run computation in a different thread using the async form
		//thenApplyAsync(Function<? super T,? extends U> fn)
		//thenApplyAsync(Function<? super T,? extends U> fn, Executor executor) //can also specify the executor to use


		//then accept
		//take a consumer and return a completableFuture<Void>
		whatsYourNameFuture
				.thenApply(name -> "hello" + name)
				.thenAccept(value -> System.out.println(value))
				.get(); //no value returned

		//thenRun
		//like accept but it doesn't have access to the result of the CompletableFuture
		whatsYourNameFuture
				.thenApply(name -> "hello" + name)
				.thenRun(() -> System.out.println("run"))
				.get();

	}

	public static void combinationsExample() throws ExecutionException, InterruptedException {

		//thenCompose
		//the use of then apply to concatenate two dependent future is wrong, you must use thenCompose
		CompletableFuture<String> result = getFirstFuture(10).thenCompose(value -> getSecondFuture(value));
		System.out.println(result.get());

		//thenCombine
		//to run independently more futures and do something when both are complete use thenCombine
		result = getFirstFuture(10).thenCombine(getSecondFuture(2), (first, second) -> "" + first + second);
		System.out.println(result.get());

		//to combine more than two futures you can use allOf or anyOf function
		//allOf
		//used in scenario similar to thenCombine (indipendent future). return a CompletableFuture<Void>, to retrieve the result occurs a specific mechanism
		CompletableFuture.allOf(getFirstFuture(1), getFirstFuture(2), getFirstFuture(3));

		//anyFuture complete when any of the future passed is completed
		CompletableFuture<Object> anyOfFuture = CompletableFuture.anyOf(getFirstFuture(1), getFirstFuture(2), getFirstFuture(3));
		System.out.println(anyOfFuture.get());
	}

	private static CompletableFuture<Integer> getFirstFuture(Integer value) {
		return CompletableFuture.supplyAsync(() -> value);
	}

	private static CompletableFuture<String> getSecondFuture(Integer value) {
		return CompletableFuture.supplyAsync(() -> "hello".repeat(value) );
	}

	public static void exceptionHandlingExample() throws ExecutionException, InterruptedException {

		int value = 0;

		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
			if(value == 0) {
				throw new IllegalStateException("Illegal state");
			} else {
				return "ok";
			}
		});

		future = future.handle((res, ex) -> {

			if(ex != null) {
				return "exception";
			}

			return res;
		});

		System.out.println(future.get());
	}
}
