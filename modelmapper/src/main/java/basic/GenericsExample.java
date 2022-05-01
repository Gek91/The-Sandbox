package basic;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericsExample {

	public static void main(String... args) {

		List<Integer> numbers = Arrays.asList(1,2,3);
		List<String> characters = new ArrayList<String>();

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.map(numbers, characters);
		System.out.println(characters); //empty
		//ModelMapper doesn't kwno that the result is supposed to contain String instances
		//generic information are erased at runtime

		//workaround, use typetoken
		//create anonymous subclass of typeToken passing List<String> as a parameter
		//this allow the resulting listType to contain the captured type information
		Type listType = new TypeToken<List<String>>() {}.getType();
		characters = modelMapper.map(numbers, listType);
		System.out.println(characters);

	}
}
