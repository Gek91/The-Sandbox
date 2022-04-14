package multithreading;

/**
 * Thread extension implementation 
 * 
 * @author Gek
 *
 */

public class MyJobThread extends Thread {

	private String name;
	private Value value;
	
	public MyJobThread(String name,Value value){
		this.name = name;
		this.value = value;
	}
	public void run(){
		System.out.println("Thread " + name);
		value.setValue(value.getValue() - 5);
	}
}
