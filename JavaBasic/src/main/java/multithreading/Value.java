package multithreading;

/**
 * Value Class
 * 
 * @author Gek
 *
 */
public class Value {

	private int value;

	public Value(int val){
		value = val;
	}
	
	public synchronized int getValue(){
		notify();
		return value;
		
	}
	
	public synchronized void setValue(int val){

		value = val;
	}
}
