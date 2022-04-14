package designPattern.creationalPattern;

public class FactoryMethod {

	public static void main(String[] args){
	
		Client client = new Client();

		AbstractLoggerCreator creator = null;

		creator = new XMLLoggerCreator();
		client.someMethodThatLogs(creator);

		creator = new OtherLoggerCreator();
		client.someMethodThatLogs(creator);
	}
}

///////////////////////////////////////////////////////////
//Abstract Product
interface Logger {
	public void log(String message);
}

//Concrete products
class XMLLogger implements Logger {
	public void log(String message) {
		System.out.println("XMLLogger :" + message);
	}
}

class OtherLogger implements Logger {
	public void log(String message) {
		System.out.println("Other Logger:" + message);
	}
}

///////////////////////////////////////////////////////////
//Abstract Creator
abstract class AbstractLoggerCreator { 
	
	public abstract Logger createLogger();
	
	public Logger getLogger() {
		Logger logger = createLogger();
		
		return logger;
	}
	
}

//Concrete Creator
class XMLLoggerCreator extends AbstractLoggerCreator {
	
	public Logger createLogger() {
		return new XMLLogger();
	}
}

class OtherLoggerCreator extends AbstractLoggerCreator{

	public Logger createLogger() {
		return new OtherLogger();
	}
}

/////////////////////////////////////////
//Client
class Client {
	
	public void someMethodThatLogs(AbstractLoggerCreator logCreator){
		Logger logger = logCreator.createLogger();
		logger.log("message");
	}
	
}