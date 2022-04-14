package designPattern.behavioralPattern;

public class Command {

	public static void main(String[] args)    {
	    RemoteControl control = new RemoteControl(); //invoker
	    Light light = new Light(); //receiver
	    Icommand lightsOn = new LightOnCommand(light); //command
	    Icommand lightsOff = new LightOffCommand(light); //command
	    //switch on
	    control.setCommand(lightsOn);
	    control.pressButton();
	    //switch off
	    control.setCommand(lightsOff);
	    control.pressButton();
	  }
	
}

//Command
interface Icommand{ 
	public void execute();
}

//Concrete command
class LightOnCommand implements Icommand {
	
	Light light;
	
	public LightOnCommand(Light light) {
		 this.light = light;
	}
	
	public void execute() {
		light.switchOn();
	}
	
}

class LightOffCommand implements Icommand{

	Light light;
	
	 public LightOffCommand(Light light){
	    this.light = light;
	 }
	  
	 public void execute(){
	    light.switchOff();
	  }
	 
}

//Receiver
class Light{
	
	private boolean on;
	
	public void switchOn() {
		this.on = true;
	}
	
	public void switchOff() {
		this.on = false;
	}
}

//Invoker
class RemoteControl {
	
	private Icommand command;
	
	public void setCommand(Icommand command) {
		this.command = command;
	}
	
	public void pressButton() {
		command.execute();
	}
}