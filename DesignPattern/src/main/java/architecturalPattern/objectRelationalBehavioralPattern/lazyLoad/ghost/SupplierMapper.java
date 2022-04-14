package architecturalPattern.objectRelationalBehavioralPattern.lazyLoad.ghost;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SupplierMapper {

	protected DomainObject doLoad(Long id, ResultSet rs) throws SQLException {
		
		Supplier supplier = new Supplier();
		//fill supplier with resultSet
		
		supplier.setProducts(buildGhostListFromResultSet(rs));
		
		return supplier;
	}

	private List<Product> buildGhostListFromResultSet(ResultSet rs) throws SQLException {
		
		 List<Product> products = new ArrayList<>();
		
		//ghost logic from result set
		while(rs.next()) {
			long productId = rs.getLong("product_id");
			VirtualDomainObjectLoader loader = new ProductLoader(productId);

			products.add((Product) new Product().doLoadGhost(productId, loader));
		}
		
		return products;
	}
	
	public static class ProductLoader implements VirtualDomainObjectLoader{
		
		private Long id;
		
		public ProductLoader(Long id) {
			this.id = id;
		}
		
		public Product load() {
			return new ProductMapper().findById(id);
		}
	}
}
