package architecturalPattern.dataSourcePattern.dataMapper;

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
	
}
