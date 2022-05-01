package converter;

import data.*;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.spi.MappingContext;

//Converter take the place of an implicit or explicit mappings between two types
public class Main {

	public static void main(String... args) {

		//First way to implement a converter, implements functional interface
		Converter<String, String> toUppercaseConverter1 = val ->
				val.getSource() == null ? null : val.getSource().toUpperCase(); //String to uppercase

		//second way to implement a converter, extends AbstractConverter
		Converter<String, String> toUppercaseConverter2 = new AbstractConverter<String, String>() {
			@Override
			public String convert(String source) {
				return source == null ? null : source.toUpperCase();
			}
		};

		//third way to implement converter, implement Converter interface
		Converter<String, String> toUppercaseConverter3 = new Converter<String, String>() {
			@Override
			public String convert(MappingContext<String, String> mappingContext) {
				return mappingContext.getSource() == null ? null : mappingContext.getSource().toUpperCase();
			}
		};

		//add converter to model mapper. NOT WORKING?
		addConverterToModelMapper();

		//add converter to typemap
		addConverterToTypeMap();

		//add converter to property
		addConverterToProperty();

		//preConverter, executed in addiction to normal mapping, before
		preConverterExample();

		//postconverter, executed in addiction to normal mapping, after
		postConverterExample();
	}

	private static void addConverterToModelMapper() {

		Converter<Order, OrderDTO> converter = val -> {
			OrderDTO result = new OrderDTO();
			result.setCustomerFirstName(val.getSource().getCustomer().getName().getFirstName());
			result.setCustomerLastName("lastname");
			return result;
		};

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addConverter(converter);

		Address address = new Address("street", "city");
		Customer customer = new Customer(new Name("first", "last"));
		Order order = new Order(customer, address);
		OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
		System.out.println(orderDTO.getCustomerFirstName());
		System.out.println(orderDTO.getCustomerLastName());
		System.out.println(orderDTO.getBillingCity());
		System.out.println(orderDTO.getBillingStreet());
	}

	private static void addConverterToTypeMap() {

		Converter<Order, OrderDTO> converter = val -> {
			OrderDTO result = new OrderDTO();
			result.setCustomerFirstName(val.getSource().getCustomer().getName().getFirstName());
			result.setCustomerLastName("lastname");
			return result;
		};

		ModelMapper modelMapper = new ModelMapper();
		TypeMap<Order, OrderDTO> typeMap = modelMapper.typeMap(Order.class, OrderDTO.class);
		typeMap.setConverter(converter);

		Address address = new Address("street", "city");
		Customer customer = new Customer(new Name("first", "last"));
		Order order = new Order(customer, address);
		OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
		System.out.println(orderDTO.getCustomerFirstName());
		System.out.println(orderDTO.getCustomerLastName());
		System.out.println(orderDTO.getBillingCity());
		System.out.println(orderDTO.getBillingStreet());
	}

	private static void addConverterToProperty() {

		Converter<String, String> toUppercaseConverter1 = val ->
				val.getSource() == null ? null : val.getSource().toUpperCase();

		ModelMapper modelMapper = new ModelMapper();
		TypeMap<Order, OrderDTO> typeMap = modelMapper.typeMap(Order.class, OrderDTO.class);
		typeMap.setPropertyConverter(toUppercaseConverter1);

		Address address = new Address("street", "city");
		Customer customer = new Customer(new Name("first", "last"));
		Order order = new Order(customer, address);
		OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
		System.out.println(orderDTO.getCustomerFirstName());
		System.out.println(orderDTO.getCustomerLastName());
		System.out.println(orderDTO.getBillingCity());
		System.out.println(orderDTO.getBillingStreet());
	}

	private static void preConverterExample() {

		Converter<Order, OrderDTO> converter = val -> {
			OrderDTO result = new OrderDTO();
			result.setCustomerFirstName(val.getSource().getCustomer().getName().getFirstName());
			result.setCustomerLastName("lastname");
			return result;
		};

		ModelMapper modelMapper = new ModelMapper();
		TypeMap<Order, OrderDTO> typeMap = modelMapper.typeMap(Order.class, OrderDTO.class);
		typeMap.setPreConverter(converter);

		Address address = new Address("street", "city");
		Customer customer = new Customer(new Name("first", "last"));
		Order order = new Order(customer, address);
		OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
		System.out.println(orderDTO.getCustomerFirstName());
		System.out.println(orderDTO.getCustomerLastName());
		System.out.println(orderDTO.getBillingCity());
		System.out.println(orderDTO.getBillingStreet());
	}

	private static void postConverterExample() {

		Converter<Order, OrderDTO> converter = val -> {
			OrderDTO result = new OrderDTO();
			result.setCustomerFirstName(val.getSource().getCustomer().getName().getFirstName());
			result.setCustomerLastName("lastname");
			return result;
		};

		ModelMapper modelMapper = new ModelMapper();
		TypeMap<Order, OrderDTO> typeMap = modelMapper.typeMap(Order.class, OrderDTO.class);
		typeMap.setPostConverter(converter);

		Address address = new Address("street", "city");
		Customer customer = new Customer(new Name("first", "last"));
		Order order = new Order(customer, address);
		OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
		System.out.println(orderDTO.getCustomerFirstName());
		System.out.println(orderDTO.getCustomerLastName());
		System.out.println(orderDTO.getBillingCity());
		System.out.println(orderDTO.getBillingStreet());
	}
}
