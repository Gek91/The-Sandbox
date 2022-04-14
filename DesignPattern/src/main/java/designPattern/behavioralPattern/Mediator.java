package designPattern.behavioralPattern;

import java.util.ArrayList;

public class Mediator {
	public static void main(String[] args) {
		ApplicationMediator mediator = new ApplicationMediator();
		Colleague desktop = new ConcreteColleague(mediator);
		Colleague mobile = new MobileColleague(mediator);
	    mediator.addColleague(desktop);
	    mediator.addColleague(mobile);
	    desktop.send("Hello World");
	    mobile.send("Hello");
	  }
}

//////////////////////
//Mediator interface
interface IMediator {
	  public void send(String message, Colleague colleague);
}

//Concrete Mediator
class ApplicationMediator implements IMediator {
	
	  private ArrayList<Colleague> colleagues;
	 
	  public ApplicationMediator() {
		  colleagues = new ArrayList<Colleague>();
	  }
	  
	  public void addColleague(Colleague colleague) {
		  colleagues.add(colleague);
	  }
	  
	  public void send(String message, Colleague originator) {
		  
		  for(Colleague colleague: colleagues) {
			  if(colleague != originator) {
				  colleague.receive(message);
			  }
		  }
	  }
	  
}

//////////////////////////////
//Colleage interface
abstract class Colleague{
	
	private IMediator mediator;
	
	public Colleague(IMediator m) {
		mediator = m;
	}

	public void send(String message) {
		mediator.send(message, this);
	}
	
	public IMediator getMediator() {
		return mediator;
	}
	
	public abstract void receive(String message);
}

//Concrete colleague
class ConcreteColleague extends Colleague {
	
	public ConcreteColleague(IMediator m) {
		super(m);
	}

	public void receive(String message) {
		  System.out.println("Colleague Received: " + message);
	}
}


class MobileColleague extends Colleague {
	
  public MobileColleague(IMediator m) {
		super(m);
	}

  public void receive(String message) {
	  System.out.println("Mobile Received: " + message);
  }
}