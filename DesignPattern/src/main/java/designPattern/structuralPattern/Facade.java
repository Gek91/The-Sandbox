package designPattern.structuralPattern;


public class Facade {

	public static void main(String[] args) {
		ComputerFacade computer = new ComputerFacade();
		computer.start();
	}
}


class CPU {
	public void freeze() { System.out.println("CPU freeze"); }
	public void jump( long position ) { System.out.println("CPU jump to position " + position); }
	public void execute() { System.out.println("CPU execute"); }
}

class Memory {
	public void load(long position) {System.out.println("Memory load position " + position ); }
}

class HardDrive { 
	public void read(long position)  { System.out.println("HardDrive read position " + position); }
}

//Facade
class ComputerFacade {
	
	private CPU processor;
	private Memory ram;
	private HardDrive hd;
	
	public ComputerFacade() {
		this.processor = new CPU();
		this.ram = new Memory();
		this.hd = new HardDrive();
	}
	
	public void start() {
		processor.freeze();
		ram.load(1);
		hd.read(1);
		processor.jump(1);
		processor.execute();
	}
	
}

	