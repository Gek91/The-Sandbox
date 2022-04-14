package architecturalPattern.dataSourcePattern.activeRecord;

import java.sql.ResultSet;
import java.sql.SQLException;

//table people (int Id PK, string lastname, string firstname, int numberOfDependents)
public class Person {

	private String lastName;
	private String firstName;
	private int numberOfDependents;
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public int getNumberOfDependents() {
		return numberOfDependents;
	}
	public void setNumberOfDependents(int numberOfDependents) {
		this.numberOfDependents = numberOfDependents;
	}
	
	
	public static Person find(long id) {
		Person result = null;
//		result =  Registry.getPerson(Id); //registry caching
		if(result == null) {
			//db find logic
			ResultSet rs = null;
			try {
				rs.next();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			result = load(rs);
		}
		
		return result;
	}
	
	public void update() {
		//db update logic
	}
	
	public Long insert() {
		//Db insert logic
		return 0L;
	}
	
	private static Person load(ResultSet rs) {
		//build object form resultset logic
		return null;
	}
	
	
	//business logic in the same class
	public long getExemption() {
		return 10 * this.getNumberOfDependents();
	}
}
