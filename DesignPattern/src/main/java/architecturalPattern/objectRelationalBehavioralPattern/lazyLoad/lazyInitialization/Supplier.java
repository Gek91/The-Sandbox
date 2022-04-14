package architecturalPattern.objectRelationalBehavioralPattern.lazyLoad.lazyInitialization;

import java.util.List;


public class Supplier {

	private long id;
	//...
	private List products;
	
	
	public long getId() {
		return this.id;
	}
	
	public List getProducts() {
		if(products == null) {
			products = Product.findForSupplier(this.id);
		}
		return products;
	}
}
