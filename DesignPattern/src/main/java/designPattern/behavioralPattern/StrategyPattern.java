package designPattern.behavioralPattern;

public class StrategyPattern {

	public static void main(String[] args) {
		
		CompressionContext ctx = new CompressionContext( new ZipCompressionStrategy());
		
		ctx.compress(2);
		
		ctx = new CompressionContext(new RarCompressionStrategy());
		
		ctx.compress(3);
	}
}

//Context
class CompressionContext {
	
	private CompressionStrategy strategy;
	
	public CompressionContext(CompressionStrategy strategy) {
		this.strategy = strategy;
	}
	
	public void compress(int file) {
		this.strategy.compressFile(file);
	}
}

//////////////////////////////
//Strategy
interface CompressionStrategy {
	
	public void compressFile(int file);
}

//Concrete Strategy
class ZipCompressionStrategy implements CompressionStrategy {
	 
	public void compressFile(int file) {
		System.out.println("Zip " + file);
	}
}

class RarCompressionStrategy implements CompressionStrategy {
	
	public void compressFile(int file) {
		System.out.println("Rar " + file);
	}
}

