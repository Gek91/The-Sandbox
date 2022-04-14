package algorithms.multiThreading.problems.philosophers;
/*
 * Philospher class
 * Eat, think or acquire fork for eating
 * 
 */
public class Philosopher implements Runnable{

	private Object leftFork;
	private Object rightFork;
	
	public Philosopher(Object leftFork, Object rightFork ) {
		this.leftFork = leftFork;
		this.rightFork = rightFork;
	}
	
	private void doAction(String action) throws InterruptedException {
		System.out.println(
				Thread.currentThread().getName() + " " + action
				);
		Thread.sleep( ( (int) (Math.random() * 100) ) );
	}
	
	//Main of the thread philosopher
	@Override
	public void run() {
		try {
			 while(true) {
				 
				 //thinking
				 doAction(System.nanoTime() + ": Thinking");
				 //Left pick up
				 synchronized(leftFork) {
					 doAction( System.nanoTime() + ": Picked up left fork");
					 //right pick up - eat
					 synchronized (rightFork) {
						 doAction(System.nanoTime() + ": Picked up right fork - eating");
						 //righ put down
						 doAction(System.nanoTime() + ": Put Down right fork");
					}
					 //left put down
					doAction(System.nanoTime() + ":  Put Down left fork");
				 }
			 }
		} catch(InterruptedException e) {
			Thread.currentThread().interrupt();
			return;
		}
	}
}
