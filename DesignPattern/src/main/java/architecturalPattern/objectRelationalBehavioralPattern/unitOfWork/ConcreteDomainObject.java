package architecturalPattern.objectRelationalBehavioralPattern.unitOfWork;

public class ConcreteDomainObject extends DomainObject {

	private String name;
	
	public static ConcreteDomainObject create(String name) {
		
		ConcreteDomainObject obj = new ConcreteDomainObject();
		obj.name = name;
		obj.setId(1l); //generate id logic
		
		obj.markNew();
		return obj;
	}

	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		markDirty();
	}
	
}
