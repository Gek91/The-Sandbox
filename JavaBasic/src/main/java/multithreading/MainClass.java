package multithreading;

public class MainClass {
	
	public static void main(String [ ] args){
		Value value= new Value(10);
		//Runnable
		MyJob j1 = new MyJob("1",value);
		MyJob j2 = new MyJob("2",value);
		Thread t1 = new Thread(j1,"Job1");
		Thread t2 = new Thread(j2,"Job2");
		//Thread
		Thread t3 = new MyJobThread("3",value);
		Thread t4 = new MyJobThread("4",value);
		
		t1.run();
		t2.run();
		t3.run();
		t4.run();
		
		System.out.println(value.getValue());
	}
}
