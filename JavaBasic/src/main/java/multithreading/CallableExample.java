package multithreading;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableExample {

	public static void main(String args[]) {
		
		ExecutorService es = Executors.newFixedThreadPool(3);
		
		System.out.println("Starting");
		
		//Callable is a task that returns a result
		//submit returns a Future object, represent the value that will be returned by the Callable 
		Future<Integer> f = es.submit(new Sum(10));
		Future<Double> f2 = es.submit(new Hypot(3, 4));
		Future<Integer> f3 = es.submit(new Factorial(5));
		
		//computation occurs simultaneously
		try {
			//get waits the result indefinitely
			System.out.println(f.get());
			System.out.println(f2.get());
			System.out.println(f3.get());
		} catch (InterruptedException exc) {
			System.out.println(exc);
		} catch (ExecutionException exc) {
			System.out.println(exc);
		}
		
		es.shutdown();
		System.out.println("Done");
	}

	// Following are three computational threads.
	static class Sum implements Callable<Integer> {
		int stop;

		Sum(int v) {
			stop = v;
		}

		public Integer call() {
			int sum = 0;
			for (int i = 1; i <= stop; i++) {
				sum += i;
			}
			return sum;
		}
	}

	static class Hypot implements Callable<Double> {
		double side1, side2;

		Hypot(double s1, double s2) {
			side1 = s1;
			side2 = s2;
		}

		public Double call() {
			return Math.sqrt((side1 * side1) + (side2 * side2));
		}
	}

	static class Factorial implements Callable<Integer> {
		int stop;

		Factorial(int v) {
			stop = v;
		}

		public Integer call() {
			int fact = 1;
			for (int i = 2; i <= stop; i++) {
				fact *= i;
			}
			return fact;
		}
	}
}
