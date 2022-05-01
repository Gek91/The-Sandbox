package propertymapping;

import data.*;
import org.modelmapper.*;

//explicit single value mapping with custom logics
public class Main {

	public static void main(String... args) {

		Address address = new Address("street", "city");
		Customer customer = new Customer(new Name("first", "last"));
		Order order = new Order(customer, address);

		// property mapping, permits to define explicit mapping between source and destination
		ModelMapper modelMapper = new ModelMapper();
		TypeMap<Order, OrderDTO> typeMap = modelMapper.typeMap(Order.class, OrderDTO.class);

		typeMap.addMappings(mapper -> mapper.map(
						src -> src.getCustomer().getName().getFirstName(), //source
						(dest, value) -> dest.setCustomerFirstName((String)value) //destination
				));

		OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
		System.out.println(orderDTO.getCustomerFirstName());
		System.out.println(orderDTO.getCustomerLastName());
		System.out.println(orderDTO.getBillingCity());
		System.out.println(orderDTO.getBillingStreet());

		//skip properties
		// property will be skipped in mapping process
		typeMap.addMappings(mapper -> mapper.skip((dest, value) -> dest.setCustomerFirstName((String)value)));

		//apply converter on explicit property mapping
		converter();

		//apply provider on explicit property mapping
		provider();

		//apply condition on explicit property mapping
		condition();
	}

	private static void converter() {

		Address address = new Address("street", "city");
		Customer customer = new Customer(new Name("first", "last"));
		Order order = new Order(customer, address);

		ModelMapper modelMapper = new ModelMapper();
		TypeMap<Order, OrderDTO> typeMap = modelMapper.typeMap(Order.class, OrderDTO.class);

		//converters
		//Allow custom conversion logic in explicit mapping
		Converter<String, String> toUppercaseConverter = val ->
				val.getSource() == null ? null : val.getSource().toUpperCase(); //String to uppercase
		//using keyword
		typeMap.addMappings(mapper -> mapper.using(toUppercaseConverter).map(
				src -> src.getCustomer().getName().getFirstName(),
				(dest, value) -> dest.setCustomerFirstName((String)value)
		));

		OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
		System.out.println(orderDTO.getCustomerFirstName());
		System.out.println(orderDTO.getCustomerLastName());
		System.out.println(orderDTO.getBillingCity());
		System.out.println(orderDTO.getBillingStreet());
	}

	private static void provider() {

		Address address = new Address("street", "city");
		Customer customer = new Customer(new Name("first", "last"));
		Order order = new Order(customer, address);

		ModelMapper modelMapper = new ModelMapper();
		TypeMap<Order, OrderDTO> typeMap = modelMapper.typeMap(Order.class, OrderDTO.class);

		//provider
		//allow to provide instance of destination properties and types prior to mapping in explicit mapping
		Provider<DataDTO> dataProvider = req -> {
			DataDTO data = new DataDTO();
			data.setData("data");
			return data;
		};
		//with keyword
		typeMap.addMappings(mapper -> mapper.with(dataProvider).map(
				src -> src.getCustomer().getName().getFirstName(), //not used, set provider value
				(dest, value) -> dest.setData((DataDTO)value)
		));

		OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
		System.out.println(orderDTO.getCustomerFirstName());
		System.out.println(orderDTO.getCustomerLastName());
		System.out.println(orderDTO.getBillingCity());
		System.out.println(orderDTO.getBillingStreet());
		System.out.println(orderDTO.getData().getData());
	}

	private static void condition() {

		Address address = new Address("street", "city");
		Customer customer = new Customer(new Name("first", null));
		Order order = new Order(customer, address);

		ModelMapper modelMapper = new ModelMapper();
		TypeMap<Order, OrderDTO> typeMap = modelMapper.typeMap(Order.class, OrderDTO.class);

		//conditional mapping
		//mapping destination propery only if a condition is true
		Condition notNull = context -> context.getSource() != null;
		//we can use condition per specify to take place for a property only if the condition is true
		typeMap.addMappings(mapper -> mapper.when(notNull).map(
				src -> src.getCustomer().getName().getLastName(),
				(dest, value) -> dest.setCustomerLastName("value")
		));

		OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
		System.out.println(orderDTO.getCustomerFirstName());
		System.out.println(orderDTO.getCustomerLastName());
		System.out.println(orderDTO.getBillingCity());
		System.out.println(orderDTO.getBillingStreet());
	}
}
