package architecturalPattern.domainLogicPattern.serviceLayer;

import java.time.Instant;
import java.util.Date;

import architecturalPattern.domainLogicPattern.domainModel.Product;
import architecturalPattern.domainLogicPattern.domainModel.Contract;

public class ContractWithServiceLayerMethods extends Contract{

	public ContractWithServiceLayerMethods(Product product, long revenue, Instant whenSigned) {
		super(product, revenue, whenSigned);
		// TODO Auto-generated constructor stub
	}

	public static ContractWithServiceLayerMethods readForUpdate(long contractNumber) {
		//Retrieve logic
		return null;
	}
	
	public static ContractWithServiceLayerMethods read(long contractNumber) {
		//Retrieve logic
		return null;
	}
}
