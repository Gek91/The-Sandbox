package designPattern.behavioralPattern;

import java.util.ArrayList;
import java.util.List;

public class Observer {

	public static void main( String[] args) {
		Subject sub = new Subject();
		
		new HexObserver(sub);
		new BinObserver(sub);
		new OctObserver(sub);
		
		sub.setState(12);
		
		sub.setState(24);
	}
}

///////////////////////////////////////
//Subject
interface ISubject {
	public void add(AObserver o);
	
	public void execute();
	
}

//Concrete Subject

class Subject implements ISubject {
	
	private List<AObserver> observers = new ArrayList<>();
	
	private int state;
	
	public void add(AObserver o) {
		observers.add(o);
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
		execute();
	}
	
	public void execute() {
		for(AObserver observer : observers) {
			observer.update();
		}
	}
	
}

///////////////////////////////////
//Observer
abstract class AObserver {
	protected Subject subject;
	
	public abstract void update();
}

//Concrete observer
class HexObserver extends AObserver {
	
	
	public HexObserver(Subject subject) {
		this.subject = subject;
		this.subject.add(this);
	}
	
	public void update() {
		System.out.print(" " + Integer.toOctalString(subject.getState()));
	}
}

class BinObserver extends AObserver {
	
	public BinObserver(Subject subject) {	
		this.subject = subject;
		this.subject.add(this);
	}
	
	public void update() {
		System.out.print(" " + Integer.toBinaryString(subject.getState()));
	}
}

class OctObserver extends AObserver {
	
	public OctObserver(Subject subject) {
		this.subject = subject;
		this.subject.add(this);
	}
	
	public void update() {
		System.out.println(" " + Integer.toOctalString(subject.getState()));
	}
}