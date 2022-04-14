package designPattern.creationalPattern;

//Main
public class Builder {

	public static void main(String[] args) {
		
		Cook cook = new Cook();
		
		Pizza pizza = cook.constructPizza(new HawaiianPizzaBuilder());
		pizza.myPizza();
		
		pizza = cook.constructPizzaWithoutSauce(new SpicyPizzaBuilder());
		pizza.myPizza();
	}
}

/////////////////////////////////////////////
//Product
class Pizza {
	private String dough;
	private String sauce;
	private String topping;
	
	public void myPizza() { 
		System.out.println("My pizza: Dough "+ this.getDough() + " sauce " + this.getSauce() + " topping " + this.getTopping());
	}
	
	public String getDough() {
		return dough;
	}
	public String getSauce() {
		return sauce;
	}
	public String getTopping() {
		return topping;
	}
	public void setDough(String dough) {
		this.dough = dough;
	}
	public void setSauce(String sauce) {
		this.sauce = sauce;
	}
	public void setTopping(String topping) {
		this.topping = topping;
	}
	
}

////////////////////////////////////////////////
//Abstract builder
abstract class PizzaBuilder {
	
	public Pizza createNewPizza() {
		
		Pizza pizza = new Pizza();
				
		return pizza;
	}
	
	protected abstract void buildDough(Pizza pizza);
	protected abstract void buildSauce(Pizza pizza);
	protected abstract void buildTopping(Pizza pizza);
}


//Concrete builder
class HawaiianPizzaBuilder extends PizzaBuilder {
	
	protected void buildDough(Pizza pizza) { pizza.setDough("cross"); }
	protected void buildSauce(Pizza pizza) { pizza.setSauce("mild"); }
	protected void buildTopping(Pizza pizza) { pizza.setTopping("pineapple"); }
	
}

class SpicyPizzaBuilder extends PizzaBuilder {
	
	protected void buildDough(Pizza pizza) { pizza.setDough("pan baked"); }
	protected void buildSauce(Pizza pizza) { pizza.setSauce("hot"); }
	protected void buildTopping(Pizza pizza) { pizza.setTopping("salami"); }
}

////////////////////////////////////////////////
//Director
class Cook {
	
	//contain construction logic
	public Pizza constructPizza(PizzaBuilder builder) {
		
		Pizza pizza = builder.createNewPizza();

		//other operations ...
		builder.buildDough(pizza);
		//other operations ...
		builder.buildSauce(pizza);
		//other operations ...
		builder.buildTopping(pizza);
		//other operations ...

		return pizza;
	}

	public Pizza constructPizzaWithoutSauce(PizzaBuilder builder) {
		Pizza pizza = builder.createNewPizza();
		builder.buildDough(pizza);
		builder.buildTopping(pizza);
		
		return pizza;
	}
}