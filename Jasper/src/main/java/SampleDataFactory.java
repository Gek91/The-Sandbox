import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SampleDataFactory {

	public static Collection<Company> createBeanCollection() {
		List<Company> result = new ArrayList<Company>();
		
		Company c = new Company();
		result.add(c);
		c.setName("Injenia");
		List<Employee> employees = new ArrayList<Employee>();
		c.setEmployees(employees);
		Employee e = new Employee();
		employees.add(e);
		e.setFirstname("Fontanelli");
		e.setLastname("Davide");
		
		e = new Employee();
		employees.add(e);
		e.setFirstname("Pandini");
		e.setLastname("Giacomo");

		
		c = new Company();
		result.add(c);
		c.setName("Enel");
		employees = new ArrayList<Employee>();
		c.setEmployees(employees);
		e = new Employee();
		employees.add(e);
		e.setFirstname("Albertazzi");
		e.setLastname("Luca");
		
		e = new Employee();
		employees.add(e);
		e.setFirstname("De Angelis");
		e.setLastname("Silvia");

		
		return result;
	}
}
