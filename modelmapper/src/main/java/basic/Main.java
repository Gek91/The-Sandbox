package basic;

import data.*;
import org.modelmapper.ModelMapper;

public class Main {

	public static void main(String... args) {
		ModelMapper modelMapper = new ModelMapper();

		//validate matching
		modelMapper.createTypeMap(Order.class, OrderDTO.class);
		modelMapper.validate(); //throw exception if a destination property is unmatched

		//basic mapping
		Address address = new Address("street", "city");
		Customer customer = new Customer(new Name("first", "last"));
		Order order = new Order(customer, address);

		OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
		System.out.println(order.getCustomer().getName().getFirstName());
		System.out.println(orderDTO.getCustomerFirstName());
		System.out.println(order.getCustomer().getName().getLastName());
		System.out.println(orderDTO.getCustomerLastName());
		System.out.println(order.getBillingAddress().getStreet());
		System.out.println(orderDTO.getBillingStreet());
		System.out.println(order.getBillingAddress().getCity());
		System.out.println(orderDTO.getBillingCity());

		//define explicit mapping -> reverse address street and city
		modelMapper.typeMap(Order.class, OrderDTO.class).addMappings(mapper -> {
			mapper.map(src -> src.getBillingAddress().getStreet(),
					(dest, value) -> dest.setBillingCity((String)value));
			mapper.map(src -> src.getBillingAddress().getCity(),
					(dest, value) -> dest.setBillingStreet((String)value));
		});

		orderDTO = modelMapper.map(order, OrderDTO.class);
		System.out.println(order.getBillingAddress().getStreet());
		System.out.println(orderDTO.getBillingStreet());
		System.out.println(order.getBillingAddress().getCity());
		System.out.println(orderDTO.getBillingCity());
	}
}
