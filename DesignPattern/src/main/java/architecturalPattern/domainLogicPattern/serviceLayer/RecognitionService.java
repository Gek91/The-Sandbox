package architecturalPattern.domainLogicPattern.serviceLayer;

import java.util.Date;

public class RecognitionService extends ApplicationService {

	public void calculateRevenueRecognitions(long contractNumber) {
		
		ContractWithServiceLayerMethods contract = ContractWithServiceLayerMethods.readForUpdate(contractNumber);
		
		contract.calculateRecognitions();
		
		getIntegrationGateway().publishRevenueRecognitionCalculation(contract);
	}
	
	public long recognizedRevenue(long contractNumber, Date asOf) {
		return ContractWithServiceLayerMethods.read(contractNumber).recognizedRevenue(asOf);
	}
}
