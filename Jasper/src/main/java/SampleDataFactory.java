import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SampleDataFactory {

	public static Collection<Company> createBeanCollection() {
		List<Company> result = new ArrayList<Company>();
		
		Company c = new Company();
		result.add(c);
		c.setName("AZ");
		List<Employee> employees = new ArrayList<Employee>();
		c.setEmployees(employees);
		Employee e = new Employee();
		employees.add(e);
		e.setFirstname("Davide");
		e.setLastname("C");
		
		e = new Employee();
		employees.add(e);
		e.setFirstname("Giacomo");
		e.setLastname("P");

		
		c = new Company();
		result.add(c);
		c.setName("Enel");
		employees = new ArrayList<Employee>();
		c.setEmployees(employees);
		e = new Employee();
		employees.add(e);
		e.setFirstname("Luca");
		e.setLastname("Z");
		
		e = new Employee();
		employees.add(e);
		e.setFirstname("Silvia");
		e.setLastname("T");

		
		return result;
	}
}
