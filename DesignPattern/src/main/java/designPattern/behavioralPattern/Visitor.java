package designPattern.behavioralPattern;

public class Visitor {

	public static void main(String[] args) {
		final Car car = new Car();
		
		car.accept(new CarElementPrintVisitor());
		
		car.accept(new CarElementDoVisitor());
		
	}
	
}


//Object structure
class Car implements CarElement {
	
	private CarElement[] elements;
	
	public Car() {
		this.elements = new CarElement[] {
			new Wheel("Front Left"), 
			new Wheel("Front Right"),
			new Wheel("Back Left"),
			new Wheel("Back Right"),
			new Body(),
			new Engine()
		};
	}
	
	public void accept(final CarElementVisitor visitor) {
		for(CarElement elem : elements) {
			elem.accept(visitor);
		}
		visitor.visit(this);
	}
	
}

/////////////////////////////////////

//Element
interface CarElement {
	void accept(CarElementVisitor visitor);
}

//Concrete Element
class Body implements CarElement {
	public void accept(final CarElementVisitor visitor) {
		visitor.visit(this);
	}
}

class Engine implements CarElement {
	public void accept(final CarElementVisitor visitor) {
		visitor.visit(this);
	}
}

class Wheel implements CarElement {
	private String name;
	
	public Wheel(final String name) {
		 this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void accept(final CarElementVisitor visitor) {
		visitor.visit(this);
	}
}

/////////////////////////////////
//Visitor
interface CarElementVisitor {
	void visit(Body body);
	void visit(Car car);
	void visit(Engine engine);
	void visit(Wheel wheel);
}

//Concrete Visitor
class CarElementDoVisitor implements CarElementVisitor {
	public void visit(final Body body) {
		System.out.println("Moving my body");
	}
	
	public void visit(final Car car) {
		System.out.println("Starting my car");
	}
	
	public void visit(final Wheel wheel) {
		System.out.println("Kicking my " + wheel.getName() + " wheel");
	}
	
	public void visit(final Engine engine) {
		System.out.println("Starting my engine");
	}
}

class CarElementPrintVisitor implements CarElementVisitor {
	public void visit(final Body body) {
		System.out.println("Visit body");
	}
	
	public void visit(final Car car) {
		System.out.println("Visit car");
	}
	
	public void visit(final Wheel wheel) {
		System.out.println("Visit " + wheel.getName() + " wheel");
	}
	
	public void visit(final Engine engine) {
		System.out.println("Visit engine");
	}
}
