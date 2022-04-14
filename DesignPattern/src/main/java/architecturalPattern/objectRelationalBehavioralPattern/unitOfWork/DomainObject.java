package architecturalPattern.objectRelationalBehavioralPattern.unitOfWork;

public abstract class DomainObject {

	private Long id;

	public Long getId() {
		return id;
	}

	
	/*
	 * object mark method
	 */
	
	protected void markNew() {
		UnitOfWork.getCurrent().registerNew(this);
	}
	
	protected void markClean() {
		UnitOfWork.getCurrent().registerClean(this);
	}
	
	protected void markDirty() {
		UnitOfWork.getCurrent().registerDirty(this);
	}
	
	protected void markRemoved() {
		UnitOfWork.getCurrent().registerRemoved(this);
	}
	
	
	////////
	
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DomainObject other = (DomainObject) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
