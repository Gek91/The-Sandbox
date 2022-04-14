package algorithms.multiThreading.problems.philosophers;

public class DiningPhilosophers {

	public static void main(String[] args) throws Exception {
		
		Philosopher[] philosophers = new Philosopher[5];
		Object[] forks = new Object[philosophers.length];
		
		for(int i = 0 ; i < forks.length ; i ++) {
			forks[i] = new Object();
		}
		
		for(int i = 0 ; i < philosophers.length ; i++) {
			Object leftFork = forks[i];
			Object rightFork = forks[(i + 1) % forks.length ];
			
			//To avoid deadlock -> al the philosopher take the fork in index order 
			if(i == philosophers.length -1 ) {
				Object temp = leftFork;
				leftFork = rightFork;
				rightFork = temp;
			}
			
			philosophers[i] = new Philosopher(leftFork, rightFork);
			
			Thread t = new Thread(philosophers[i], "Philosopher " + (i+1));
			t.start();
		}
	}
}
