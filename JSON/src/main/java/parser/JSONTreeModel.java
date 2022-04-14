package parser;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JSONTreeModel {


	public static void main(String[] args) {
		
		base();

		update();
	}
	
	public static void base() {
	
		String json = "{ \"brand\" : \"Merceds\", \"doors\" : 5, "
				+ "\"owners\" : [\"John\", \"Jack\", \"Jill\"], "
				+ " \"obj\" : { \"field\" : \"value\"} }";

		ObjectMapper om = new ObjectMapper();
		
		try {
			
			JsonNode node = om.readValue(json, JsonNode.class);
			
			JsonNode brand = node.get("brand");
			System.out.println("brand = " + brand.asText());
			
			JsonNode doors = node.get("doors");
			System.out.println("doors = " + doors.asInt());
			
			JsonNode owners = node.get("owners");
			JsonNode firstOwner = owners.get(0);
			System.out.println("first owner = " + firstOwner.asText());
			
			JsonNode obj = node.get("obj");
			JsonNode childField = obj.get("field");
			System.out.println("child field = " + childField.asText());
			
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	public static void update() {
	
		String json = "{ \"brand\" : \"Merceds\", \"doors\" : 5 }";
		
		ObjectMapper om = new ObjectMapper();
		
		try {
			JsonNode node = om.readValue(json, JsonNode.class);
			
			ObjectNode objNode = (ObjectNode) node;
			objNode.put("brand", "Fiat");
			
			objNode.remove("doors");
			
			System.out.println("JSON: " + node);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
}


