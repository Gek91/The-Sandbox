package architecturalPattern.domainLogicPattern.serviceLayer;

import java.util.Date;

import patterns.architecturalPattern.domainLogicPattern.domainModel.Contract;
import patterns.architecturalPattern.domainLogicPattern.domainModel.Product;

public class ContractWithServiceLayerMethods extends Contract{

	public ContractWithServiceLayerMethods(Product product, long revenue, Date whenSigned) {
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
