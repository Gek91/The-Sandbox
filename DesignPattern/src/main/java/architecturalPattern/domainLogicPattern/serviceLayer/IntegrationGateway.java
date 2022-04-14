package architecturalPattern.domainLogicPattern.serviceLayer;

import architecturalPattern.domainLogicPattern.domainModel.Contract;

public interface IntegrationGateway {

	void publishRevenueRecognitionCalculation(Contract contract);
}
