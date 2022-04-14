package multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorExample {
	
	public static void main(String args[]) {
		
		ExecutorService es = Executors.newFixedThreadPool(2);
		System.out.println("Starting");
		// Start the threads.
		es.execute(new MyThread("A"));
		es.execute(new MyThread("B"));
		es.execute(new MyThread("C"));
		es.execute(new MyThread("D"));

		es.shutdown();
		System.out.println("Shutdown");

		
		try {
			es.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Done");
	}
	

	static class MyThread implements Runnable {
		String name;

		MyThread(String n) {
			name = n;
			new Thread(this);
		}

		public void run() {
			for (int i = 0; i < 5; i++) {
				System.out.println(name + ": " + i);
			}
			System.out.println("Done " + name);
		}
	}
}

