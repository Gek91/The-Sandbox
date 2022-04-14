package designPattern.creationalPattern;

public class Singleton {

	public static void main(String[] args) {
		MySingle single = MySingle.getMySingle();
		MySingle other = MySingle.getMySingle();
		if(single != other)
			throw new RuntimeException();
	}
}


class MySingle {
	private static MySingle instance = null;
	
	private MySingle() { }
	
	public static synchronized MySingle getMySingle() {
		if(instance == null)
			instance = new MySingle();
		return instance;
	}
}