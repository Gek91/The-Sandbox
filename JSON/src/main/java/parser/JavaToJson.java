package parser;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import parser.classes.Car;


public class JavaToJson {

	public static void main(String[] args) throws JsonProcessingException {
		
		writeJSONString();
		
		parseDateReadable();
	}
	
	public static void writeJSONString() {
	
		Car car = new Car();
		car.setBrand("BMW");
		car.setDoors(4);
		
		ObjectMapper om = new ObjectMapper();

		try {
			String json = om.writeValueAsString(car);
			System.out.println(json);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void parseDateReadable() {
		
		Date date = new Date();
		
		ObjectMapper om = new ObjectMapper();

		try {
			String longTranslate = om.writeValueAsString(date);
			
			System.out.println("Print date as a long: " + longTranslate);
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			om.setDateFormat(dateFormat);
			
			String dateTranslate = om.writeValueAsString(date);
			
			System.out.println("Print date formatted: " + dateTranslate);			
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
