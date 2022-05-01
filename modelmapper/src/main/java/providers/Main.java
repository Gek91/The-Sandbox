package providers;

import data.*;
import org.modelmapper.*;

public class Main {

	public static void main(String... args) {

		//First provider implementation
		Provider<Customer> customerProvider1 = new AbstractProvider<Customer>() {
			public Customer get() {
				return new Customer(new Name("name", "surname"));
			}
		};

		//Second provider implementation
		Provider<Customer> customerProvider2 = new Provider<Customer>() {
			public Customer get(ProvisionRequest<Customer> request) {
				return new Customer(new Name("name", "surname"));
			}
		};

		//third provider implementation
		Provider<Customer> customerProvider3 = req -> new Customer(new Name("name", "surname"));


		//add provider to modelMapper
		//addProviderToModelMapper();

		//add provider to typemap
		//addProviderToTypeMap();

		//add provider to property
		addProviderToProperty();
	}

	private static void addProviderToModelMapper() {

		Provider<OrderDTO> orderDTOProvider = new AbstractProvider<OrderDTO>() {
			public OrderDTO get() {
				OrderDTO order = new OrderDTO();
				order.setCustomerLastName("name");
				order.setCustomerLastName("surname");
				return order;
			}
		};

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setProvider(orderDTOProvider);

		Address address = new Address("street", "city");
		Customer customer = new Customer(new Name("first", "last"));
		Order order = new Order(customer, address);
		OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
		System.out.println(orderDTO.getCustomerFirstName());
		System.out.println(orderDTO.getCustomerLastName());
		System.out.println(orderDTO.getBillingCity());
		System.out.println(orderDTO.getBillingStreet());
	}

	private static void addProviderToTypeMap() {
		Provider<OrderDTO> orderDTOProvider = new AbstractProvider<OrderDTO>() {
			public OrderDTO get() {
				OrderDTO order = new OrderDTO();
				order.setCustomerLastName("name");
				order.setCustomerLastName("surname");
				return order;
			}
		};

		ModelMapper modelMapper = new ModelMapper();
		TypeMap<Order, OrderDTO> typeMap = modelMapper.typeMap(Order.class, OrderDTO.class);
		typeMap.setProvider(orderDTOProvider);

		Address address = new Address("street", "city");
		Customer customer = new Customer(new Name("first", "last"));
		Order order = new Order(customer, address);
		OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
		System.out.println(orderDTO.getCustomerFirstName());
		System.out.println(orderDTO.getCustomerLastName());
		System.out.println(orderDTO.getBillingCity());
		System.out.println(orderDTO.getBillingStreet());
	}

	private static void addProviderToProperty() {

		Provider<Customer> customerProvider1 = new AbstractProvider<Customer>() {
			public Customer get() {
				return new Customer(new Name("name", "surname"));
			}
		};

		ModelMapper modelMapper = new ModelMapper();
		TypeMap<Order, OrderDTO> typeMap = modelMapper.typeMap(Order.class, OrderDTO.class);
		typeMap.setPropertyProvider(customerProvider1);

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
