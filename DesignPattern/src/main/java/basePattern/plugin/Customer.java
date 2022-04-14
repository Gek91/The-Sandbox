package basePattern.plugin;

public class Customer extends DomainObject {

	private Long id;
	private String name;
	
	private Customer(String name, Long id) {
		
		this.name = name;
		this.id = id;
	}
	
	public Customer create(String name) {
		Long customerId = IdGenerator.INSTANCE.nextId();
		Customer customer = new Customer(name, customerId);
		return customer;
	}
}
