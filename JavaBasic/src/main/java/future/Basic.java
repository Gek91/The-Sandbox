package future;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;

public class Basic {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		//ExecutorService simplifies running tasks in asynchronous mode
//		executorServiceExample();

		//Future represents a future result of an asynchronous computation
		//allows us to get the result of a task’s execution or to check the task’s status
//		futureExample();

		//SchedulerExecutorService runs tasks after some predefined delay and/or periodically
//		schedulerExecutorServiceExample();

		//ForkJoinTask is an abstract class which implements Future, and is capable of running a large number of tasks
		// hosted by a small number of actual threads
		forkJoinExample();

	}

	private static void executorServiceExample() throws InterruptedException {

//		ExecutorService executor = Executors.newFixedThreadPool(10); //pool of thread
		ExecutorService executor = Executors.newSingleThreadExecutor();

		//ExecutorService can execute Runnable and Callable tasks

		Runnable runnableTask = () -> {
			try {
				TimeUnit.MILLISECONDS.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};

		//execute return void, ignore result
		executor.execute(runnableTask);


		Callable<String> callableTask = () -> {
			TimeUnit.MILLISECONDS.sleep(300);
			return "Task's execution";
		};

		List<Callable<String>> callableTasks = new ArrayList<>();
		callableTasks.add(callableTask);
		callableTasks.add(callableTask);
		callableTasks.add(callableTask);

		//submit return a future as a result
		Future<String> future =
				executor.submit(callableTask);

		//execute all the task a return a list of futures
		List<Future<String>> futures = executor.invokeAll(callableTasks);

		//executor service needs to be shutdown
		//Example of correct shutdown
		executor.shutdown(); //wait threads termination
		try {
			if (!executor.awaitTermination(800, TimeUnit.MILLISECONDS)) {
				executor.shutdownNow(); //forced shutdown
			}
		} catch (InterruptedException e) {
			executor.shutdownNow();
		}
	}

	private static void futureExample() throws ExecutionException, InterruptedException {

		ExecutorService executor = Executors.newSingleThreadExecutor();

		Callable<String> callableTask = () -> {
			TimeUnit.MILLISECONDS.sleep(300);
			return "Task's execution";
		};

		List<Callable<String>> callableTasks = new ArrayList<>();
		callableTasks.add(callableTask);
		callableTasks.add(callableTask);
		callableTasks.add(callableTask);

		Future<String> future =
				executor.submit(callableTask);

		String result = null;
		try {
			System.out.println(future.isDone()); //check if task is ended
//			future.cancel(true); //cancel task execution
			result = future.get(); //blocking command, wait for future completion and result
			System.out.println(result);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

	}

	private static void schedulerExecutorServiceExample() {

		ScheduledExecutorService executorService = Executors
				.newSingleThreadScheduledExecutor();

		Callable<String> callableTask = () -> {
			TimeUnit.MILLISECONDS.sleep(300);
			return "Task's execution";
		};

		//execute after a fixed delay
		Future<String> resultFuture =
				executorService.schedule(callableTask, 1, TimeUnit.SECONDS);

		Runnable runnable = () -> {
			try {
				callableTask.call();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		};

		//execute at fixed rate after a fixed delay
		executorService.scheduleAtFixedRate(runnable, 100, 450, TimeUnit.MILLISECONDS);

		//execute at fixed rate after a fixed delay, not waiting for previous completion
		executorService.scheduleWithFixedDelay(runnable, 100, 150, TimeUnit.MILLISECONDS);

	}

	private static void forkJoinExample() throws ExecutionException, InterruptedException {

		//RecursiveAction represent a task without result
		RecursiveAction action = new RecursiveAction() {
			@Override
			protected void compute() {
				System.out.println("action");
			}
		};

		//RecursiveTask represent a task with result
		RecursiveTask<Long> task = new RecursiveTask<Long>() {
			@Override
			protected Long compute() {
				action.fork(); //non-blocking execution
				System.out.println("task");
				action.join(); //wait for async execution to end
				return Instant.now().toEpochMilli();
			}
		};

		System.out.println("start");

		ForkJoinPool forkJoinPool = new ForkJoinPool();

		forkJoinPool.execute(task);

		System.out.println("end");

		//real execution here?
		System.out.println(task.get());

	}

}
