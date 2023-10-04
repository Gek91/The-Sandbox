package java21.virtualthread;

import java.lang.reflect.Executable;
import java.time.Duration;
import java.util.concurrent.*;

public class Basic {

	//virutal thread, new thread resource not linked with OS thread concept. Virtual thread are chap and plentiful, they should never be pooled.
	//Virtual thread  are short-lived with shallow call stacks. Virtual thread preserve the programming style used in web serve application (thread-per-request)
	// while utilizing the available resources optimally.
	//virtual thread are executed on the OS thread without capturing it for the entiere code lifetime. This mean taht many virtual threads can run  on the same OS Thread.
	//Virtual thread are a lightweight implementation of threads provided by the JDK.
	public static void main(String[] args) throws InterruptedException, ExecutionException {

		Runnable toExecute = () -> {
			try {
				System.out.println("Running");
				Thread.sleep(Duration.ofSeconds(1L));
				System.out.println("End");
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		};

		Callable<Integer> toCall = () -> {
			try {
				System.out.println("Running");
				Thread.sleep(Duration.ofSeconds(1L));
				System.out.println("End");
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}

			return 0;
		};

		////////////
		//Thread
		////////////

		//Old thread example
		Thread oldThread = new Thread(toExecute);
		oldThread.start();

		//virtual thread
		Thread vritualThred = Thread.ofVirtual().name("my virtual thread").start(toExecute);

		//wait for termination
		oldThread.join();
		vritualThred.join();


		////////////
		//executors
		////////////

		//old executor example
		ExecutorService oldExecutor = Executors.newSingleThreadExecutor();
		Future<Integer> oldExecutorFuture = oldExecutor.submit(toCall);

		//virtual thread executor
		ExecutorService virtualExecutor = Executors.newVirtualThreadPerTaskExecutor();
		Future<Integer> virutalExecutorFuture = virtualExecutor.submit(toCall);

		oldExecutorFuture.get();
		virutalExecutorFuture.get();


		////////////
		//Thread factory
		////////////

		ThreadFactory oldThreadFactory = Thread.ofPlatform().factory();
		oldExecutor = Executors.newThreadPerTaskExecutor(oldThreadFactory);
		var old = oldExecutor.submit(toExecute);
		old.get();


		ThreadFactory virtualThreadFactory = Thread.ofVirtual().factory();
		virtualExecutor = Executors.newThreadPerTaskExecutor(virtualThreadFactory);
		var virtual = virtualExecutor.submit(toExecute);
		virtual.get();
	}
}
