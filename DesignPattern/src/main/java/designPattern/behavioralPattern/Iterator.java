package designPattern.behavioralPattern;

import java.util.ArrayList;
import java.util.List;

public class Iterator {

	public static void main(String[] args) {
		 
		List<String> channels = new ArrayList<String>();
		channels.add("chan1");
		channels.add("chan2");
		channels.add("chan3");
		
		ChannelIterator chanIterator = new ConcreteChannelIterator(channels);
		
		while(chanIterator.hasNext()) {
			System.out.println(chanIterator.currentItem());
			chanIterator.next();
		}
		System.out.println(chanIterator.currentItem());
	}
}


//Iterator interface 
interface ChannelIterator{
	
	public boolean hasNext();
	
	public void next();
	
	public String currentItem();
	
}

//Concrete Iterator 
class ConcreteChannelIterator implements ChannelIterator{
	
	private List<String> channels; 
	
	private int currentPos = 0; 
	
	public ConcreteChannelIterator(List<String> channels){
		this.channels = channels;
	}
	
	public boolean hasNext(){
		
		if(currentPos + 1 < channels.size()){
			return true;
		}return false;
	}
	
	public void next(){
		currentPos++;
	}
	
	public String currentItem(){
		return channels.get(currentPos);
	}
}



//Aggregate interface
interface TV {
	
	public ChannelIterator getIterator();
	
}

//Concrete Aggregator
class ConcreteTV {
	
	private ChannelIterator iterator;
	
	private List<String> channels; 
	
	public ConcreteTV(){
		iterator = new ConcreteChannelIterator(channels);
	}
	
	public ChannelIterator getIterator(){
		return iterator;
	}
	
}
