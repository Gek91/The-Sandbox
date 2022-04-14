package multithreading;

/**
 * Runnable implementation
 * 
 * @author Gek
 *
 */

public class MyJob implements Runnable{

	private String name;
	private Value value;
	
	public MyJob(String name,Value value){
		this.name = name;
		this.value = value;
	}
	
	@Override
	public void run() {
		System.out.println(name);
		value.setValue(value.getValue() + 1);
	}

}
