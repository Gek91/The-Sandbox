package architecturalPattern.objectRelationalBehavioralPattern.lazyLoad.ghost;

public class ProductMapper {

	public Product findById(long id) {
		
		return new Product(); //retrieve logic
	}
}
