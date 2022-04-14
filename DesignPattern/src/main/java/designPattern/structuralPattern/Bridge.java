package designPattern.structuralPattern;

public class Bridge {
	
	public static void main(String[] args) {
		RemoteControl sony = new ConcreteRemote(new Sony());
		RemoteControl philips = new InverseConcreteRemote(new Philips());
		RemoteControl sony2 = new InverseConcreteRemote(new Sony());
		RemoteControl philips2 = new ConcreteRemote(new Philips());
	

		sony.on();
		sony.tuneChannel(1);
		sony.tuneChannel(3);
		sony.tuneChannel(-4);
		sony.off();
			
		philips.off();
		philips.on();
	}
	
}

//Implementor
interface TV {
	
	public void on();
	
	public void off();
	
	public void tuneChannel(int channel);
}

//Concrete implementor
class Sony implements TV {
	
	public void on() { 
		System.out.println("Sony ON");
	}
	
	public void off() {
		System.out.println("Sony OFF");
	}
	
	public void tuneChannel(int channel) {
		System.out.println("Sony tune channel " + channel);
	}
}

class Philips implements TV {
	
	public void on() { 
		System.out.println("Philips ON");
	}
	
	public void off() {
		System.out.println("Philips OFF");
	}
	
	public void tuneChannel(int channel) {
		System.out.println("Philips tune channel " + channel);
	}
}

//Abstraction
abstract class RemoteControl{
	protected TV implementor;
	
	public RemoteControl(TV implementor) {
		this.implementor = implementor;
	}
	
	public void on() {
		implementor.on();
	}
	
	public void off() {
		implementor.off();
	}
	
	public void tuneChannel(int channel) {
		implementor.tuneChannel(channel);
	}
}

//Refined abstraction
class ConcreteRemote extends RemoteControl { 
	
	private int currentChannel;
	
	public ConcreteRemote(TV implementor) {
		super(implementor);
		currentChannel = 0;
	}
	
	private void nextChannel() {
		setChannel(++currentChannel);
	}
	
	private void prevChannel() {
		setChannel(--currentChannel);
	}
	
	public void setChannel(int channel) {
		currentChannel = channel;
	}
	
	public void tuneChannel(int channel) {
		if(channel > 0) {
			nextChannel();
		} else {
			prevChannel();
		}
		implementor.tuneChannel(currentChannel);
	}
	
}

class InverseConcreteRemote extends RemoteControl {

	private int currentChannel;
	
	public InverseConcreteRemote(TV implementor) {
		super(implementor);
		currentChannel = 0;
	}
	
	private void nextChannel() {
		setChannel(--currentChannel);
	}
	
	private void prevChannel() {
		setChannel(++currentChannel);
	}
	
	public void setChannel(int channel) {
		currentChannel = channel;
	}
	
	@Override
	public void on() {
		implementor.off();
	}

	@Override
	public void off() {
		implementor.on();
	}

	public void tuneChannel(int channel) {
		if(channel > 0) {
			nextChannel();
		} else {
			prevChannel();
		}
		implementor.tuneChannel(currentChannel);
	}
}