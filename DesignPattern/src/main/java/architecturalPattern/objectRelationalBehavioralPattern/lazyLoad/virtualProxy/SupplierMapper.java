package architecturalPattern.objectRelationalBehavioralPattern.lazyLoad.virtualProxy;

import java.sql.ResultSet;
import java.util.List;

public class SupplierMapper {

	protected DomainObject doLoad(Long id, ResultSet rs) {
		
		Supplier supplier = new Supplier();
		//fill supplier with resultSet
		
		supplier.setProducts(new VirtualList(new ProductLoader(id)));
		
		return supplier;
	}
	
	
	public static class ProductLoader implements VirtualListLoader {
		
		private Long id;
		
		public ProductLoader(Long id) {
			this.id = id;
		}
		
		public List load() {
			return new ProductMapper().findForSupplier(id);
		}
	}
}
