package parser;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import parser.classes.Car;


public class JsonToJava {

	
	
	
	public static void main(String[] args) throws URISyntaxException {

		parseString();
		
		parseFile();
		
		parseArray();
	}
	
	
	private static void parseString() {
		
		String json = "{ \"brand\" : \"Merceds\", \"doors\" : 5 }";
		
		ObjectMapper om = new ObjectMapper();
		
		try {
			
			//maps names of the JSON fields to Class getters and setters (standard serializer/deserializer)
			Car car = om.readValue(json, Car.class);
			
			System.out.println("Car - Brand: " + car.getBrand() + " Doors: " + car.getDoors());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private static void parseFile() throws URISyntaxException {
		
		File file = new File(JsonToJava.class.getClassLoader().getResource("car.json").toURI());

		ObjectMapper om = new ObjectMapper();
		
		try {
			
			Car car = om.readValue(file, Car.class);
			
			System.out.println("Car - Brand: " + car.getBrand() + " Doors: " + car.getDoors());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private static void parseArray() {
		
		String json = "[{ \"brand\" : \"Merceds\", \"doors\" : 5 }, {\"brand\" : \"Fiat\", \"doors\" : 4  } ]";
		
		ObjectMapper om = new ObjectMapper();
		
		try {
			
			//parse as array
			Car[] cars = om.readValue(json, Car[].class);
			
			for(Car car : cars) {
				System.out.println("Car - Brand: " + car.getBrand() + " Doors: " + car.getDoors());
			}
			
			//Parse as a list
			List<Car> carsList = om.readValue(json, new TypeReference<List<Car>>() {});
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//TODO CUSTOM PARSER
}
