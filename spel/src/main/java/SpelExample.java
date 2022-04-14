import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SpelExample {

	/**
	 * @param args
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	/**
	 * @param args
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		
		ExpressionParser expressionParser = new SpelExpressionParser();

		
		Car car = new Car();
		car.setMake("Good manufacturer");
		car.setModel("Model 3");
		car.setYearOfProduction(2014);
		car.setPassengers(Arrays.asList("Qui", "Quo", "Qua"));
		car.setEngine(new Engine(1000, "Diesel"));
		car.setProperties(Collections.singletonMap("Where", "Torino"));
		EvaluationContext context = new StandardEvaluationContext(car);

		//getter
		Expression expression = expressionParser.parseExpression("model");
		String result = (String) expression.getValue(context);
		System.out.println(result);
		
		//setter
		expressionParser.parseExpression("model").setValue(context, "Panda 4x4 Fire");
		System.out.println(car.getModel());
		
		//array
		String passenger = (String) expressionParser.parseExpression("passengers[2]").getValue(context);
		System.out.println(passenger);
		int passengerSize = (Integer) expressionParser.parseExpression("passengers.size()").getValue(context); //method size, not a field
		System.out.println(passengerSize);
		boolean contains = (Boolean) expressionParser.parseExpression("passengers.contains('Paperino')").getValue(context);
		System.out.println(contains);
		contains = (Boolean) expressionParser.parseExpression("passengers.contains('Quo')").getValue(context);
		System.out.println(contains);

		
		//inner object
		String fuel = (String) expressionParser.parseExpression("engine.fuel").getValue(context);
		System.out.println(fuel);
		
		//map
		String where = (String) expressionParser.parseExpression("properties['where']").getValue(context);
		System.out.println(where);
		
		//relational operator
		expression = expressionParser.parseExpression("yearOfProduction > 2005"); //> or gt
		boolean booleanResult = expression.getValue(car, Boolean.class);
		System.out.println(booleanResult);
		//instance of
		boolean falseValue = expressionParser.parseExpression(
		        "'xyz' instanceof T(Integer)").getValue(Boolean.class);  //don't use primive type. T operator -> specify java class instance
		System.out.println(falseValue);
		
		// matches
		boolean trueValue = expressionParser.parseExpression(
		        "'5.00' matches '^-?\\d+(\\.\\d{2})?$'").getValue(Boolean.class);
		System.out.println(trueValue);
		
		//logical operator
		contains = (Boolean) expressionParser.parseExpression("passengers.contains('Qui') and passengers.contains('Qua')").getValue(context);
		System.out.println(contains);

		contains = (Boolean) expressionParser.parseExpression("!passengers.contains('Paperino')").getValue(context);
		System.out.println(contains);
		
		//mathematical
		int doubleCC = (Integer) expressionParser.parseExpression("engine.cc * 2").getValue(context);
		System.out.println(doubleCC);
		
		//constructor
		Car newCar = expressionParser.parseExpression("new Car()").getValue(Car.class);
		
		//variable
		context.setVariable("var1", "ciao");
		context.setVariable("var2", "belli");
		String variables = (String) expressionParser.parseExpression("#var1").getValue(context);
		System.out.println(variables);


		//method
		context.setVariable("reverseString", SpelExample.class.getDeclaredMethod("reverseString", String.class));
		
		String functionResult = (String) expressionParser.parseExpression("#reverseString('hello')").getValue(context);
		System.out.println(functionResult);

		//safe navigator operator, avoid nullPOinterException
		Integer ccValue = expressionParser.parseExpression("engine?.cc").getValue(context, newCar, Integer.class);
		System.out.println(ccValue); // null
		
		//collection selection (list and map)
		List<String> passegers = (List<String>) expressionParser.parseExpression("passengers.?[#this.length() == 3]").getValue(context); //#this special variable
		System.out.println(passegers); 
		
		Map newMap = (Map) expressionParser.parseExpression("properties.?[key == 'Where']").getValue(context); //filter by key
		System.out.println(newMap); 
		newMap = (Map) expressionParser.parseExpression("properties.?[value == 'Torino']").getValue(context); //filter by value
		System.out.println(newMap); 
		
		//collextion projection (list and map). With list works on list object fields
		List<String> projectionValue = (List<String>) expressionParser.parseExpression("properties.![value]").getValue(context); //takes all the map values
		System.out.println(projectionValue); 

		//template parsing -> allow mixing literal text with one or more evaluation block
		
		String stringTemplate = expressionParser.parseExpression(
		        "random number is #{T(java.lang.Math).random()} and #{T(java.lang.Math).random() + 1} and #{T(java.lang.Math).random() - 1}" ,
		        new TemplateParserContext()).getValue(String.class); //evaluating expression ${ expression }
		System.out.println(stringTemplate); 

	}
	
	public static String reverseString(String input) {
        StringBuilder backwards = new StringBuilder(input.length());
        for (int i = 0; i < input.length(); i++) {
            backwards.append(input.charAt(input.length() - 1 - i));
        }
        return backwards.toString();
    }
}


class Car {
	private String make;
	private String model;
	private Integer yearOfProduction;
	private List<String> passengers;
	private Engine engine;
	private Map<String, String> properties;
	
	public Car() { }  //needed for constructor example
	
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Integer getYearOfProduction() {
		return yearOfProduction;
	}
	public void setYearOfProduction(Integer yearOfProduction) {
		this.yearOfProduction = yearOfProduction;
	}
	public List<String> getPassengers() {
		return passengers;
	}
	public void setPassengers(List<String> passengers) {
		this.passengers = passengers;
	}
	public Engine getEngine() {
		return engine;
	}
	public void setEngine(Engine engine) {
		this.engine = engine;
	}
	public Map<String, String> getProperties() {
		return properties;
	}
	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}
	
}

class Engine {
	private Integer cc;
	private String fuel;
	
	public Engine(Integer cc, String fuel) {
		this.cc = cc;
		this.fuel = fuel;
	}

	public Integer getCc() {
		return cc;
	}

	public void setCc(Integer cc) {
		this.cc = cc;
	}

	public String getFuel() {
		return fuel;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}
	
}