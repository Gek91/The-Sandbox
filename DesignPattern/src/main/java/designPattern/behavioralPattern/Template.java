package designPattern.behavioralPattern;

public class Template {

	public static void main(String[] args) {
		CrossCompiler iphone = new IphoneCompiler();
		
		iphone.crossCompiler();
		
		CrossCompiler android = new AndroidCompiler();
		
		android.crossCompiler();
	}
}

//Abstract template
abstract class CrossCompiler {
	
	public final void crossCompiler() {
		collectSource();
		compileToTarget();
	}
	
	protected abstract void collectSource();
	protected abstract void compileToTarget();
}

//Concrete template
class IphoneCompiler extends CrossCompiler {
	
	protected void collectSource() {
		System.out.println("Collect source Iphone");
	}
	
	protected void compileToTarget() {
		System.out.println("Compile to Taget Iphone");
	}
}

class AndroidCompiler extends CrossCompiler {
	
	protected void collectSource() {
		System.out.println("Collect source Android");
	}
	
	protected void compileToTarget() {
		System.out.println("Compile to Taget Android");
	}
}