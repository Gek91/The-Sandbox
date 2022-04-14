package architecturalPattern.objectRelationalBehavioralPattern.lazyLoad.valueHolder;

import java.sql.ResultSet;

public class SupplierMapper {

	protected DomainObject doLoad(Long id, ResultSet rs) {
		
		Supplier supplier = new Supplier();
		//fills supplier data with resultSet
		supplier.setProducts(new ValueHolder(new ProductLoader(id)));
		
		return supplier;
	}
	
	public static class ProductLoader implements ValueLoader {
		
		private Long id;
		public ProductLoader(Long id ) {
			this.id = id;
		}
		
		public Object load() {
			return new ProductMapper().findForSupplier(id);	
		}
	}
}
