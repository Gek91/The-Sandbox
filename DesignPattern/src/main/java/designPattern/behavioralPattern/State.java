package designPattern.behavioralPattern;

public class State {

	public static void main(String[] args) {
		MP3PlayerContext mp3 = new MP3PlayerContext(new StandbyState());
		
		mp3.play();
		
		mp3.play();
		
	}
}

//Context
class MP3PlayerContext {
	
	  private IState state;
	  
	  public MP3PlayerContext(IState state) {
	    this.state= state;
	  }
	  
	  public void play() {
	    state.pressPlay(this);
	  }
	  
	  public void setState(IState state) {
	    this.state = state;
	  }
	  
	  public IState getState() {
	    return state;
	  }
}

///////////////////////
//State
interface IState {
  public void pressPlay(MP3PlayerContext context);
}

//Concrete state
class StandbyState implements IState {
	
	public StandbyState() {
		System.out.println("Stand by state");
	}
	
  public void pressPlay(MP3PlayerContext context) {
    context.setState(new PlayingState());
  }
  
}

class PlayingState implements IState {
	
	public PlayingState() {
		System.out.println("Playing state");
	}
	
  public void pressPlay(MP3PlayerContext context) {
    context.setState(new StandbyState());
  }
  
}