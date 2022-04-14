package architecturalPattern.objectRelationalBehavioralPattern.unitOfWork;

public class Main {

	
	public static void main(String[] args) {
		
		
		UnitOfWork.newCurrent();
		
		ConcreteDomainObject.create("my object");
		
		UnitOfWork.getCurrent().commit();
	}
}
