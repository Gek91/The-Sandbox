package architecturalPattern.objectRelationalBehavioralPattern.lazyLoad.ghost;


public class Product implements DomainObject {

	private long id;
	private String name;
	private VirtualDomainObjectLoader loader;
	private boolean ghost;

	protected DomainObject doLoadGhost(Long id, VirtualDomainObjectLoader loader) {
		
		Product product = new Product();
		product.id = id;
		product.loader = loader;
		product.ghost = true;
		
		return product;
	}
	
	public long getId() {
		return id;
	}
	
	//trigger full loading
	public String getName() {
		if(ghost) {
			loadFullObject();
		}
		
		return this.name;
	}
	
	private void loadFullObject() {
		ghost = false;
		Product fullObj = (Product) loader.load();
		//fill this whit fullObj 
	}
	
}
