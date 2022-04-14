package architecturalPattern.objectRelationalBehavioralPattern.lazyLoad.ghost;

import java.util.List;


public class Supplier implements DomainObject{

		private List products;

		public List getProducts() {
			return products;
		}

		public void setProducts(List products) {
			this.products = products;
		}
}
