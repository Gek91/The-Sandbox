package architecturalPattern.objectRelationalBehavioralPattern.lazyLoad.valueHolder;

import java.util.List;

public class Supplier implements DomainObject{

	private ValueHolder products;
	
	public List getProduct() {
		return (List) products.getValue();
	}

	public void setProducts(ValueHolder products) {
		this.products = products; 
	}
	
	
}
