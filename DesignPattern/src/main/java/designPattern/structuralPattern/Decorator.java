package designPattern.structuralPattern;

public class Decorator {
	
	 public static void printInfo(Coffee c) {
	        System.out.println("Cost: " + c.getCost() + "; Ingredients: " + c.getIngredients());
	    }

	  public static void main(String[] args) {
	        Coffee c = new SimpleCoffee();
	        printInfo(c);

	        c = new WithMilk(c);
	        printInfo(c);

	        c = new WithSprinkles(c);
	        printInfo(c);
	    }
}

//Component
interface Coffee { 
	
	 public double getCost(); 
	 
	 public String getIngredients();
}

//Concrete component
class SimpleCoffee  implements Coffee {
	
	 public double getCost() { return 1; }
	 
	 public String getIngredients() { return "Coffee"; }
}

//Decorator
abstract class CoffeeDecorator implements Coffee {
	
	protected final Coffee decoratedCoffee;
	
	public CoffeeDecorator(Coffee c) {
		this.decoratedCoffee = c;
	}
	
	 public double getCost() { return this.decoratedCoffee.getCost(); }
	 
	 public String getIngredients() { return this.decoratedCoffee.getIngredients(); }
}

//Concrete decorator
class WithMilk extends CoffeeDecorator {
	
    public WithMilk(Coffee c) { super(c); }

    public double getCost() { return super.getCost() + 0.5; }

    public String getIngredients() { return super.getIngredients() + ", Milk"; }
}


class WithSprinkles extends CoffeeDecorator {
	
	public WithSprinkles(Coffee c) { super(c); }

    public double getCost() { return super.getCost() + 0.2; }

    public String getIngredients() { return super.getIngredients() + ", Sprinkles"; }
}
