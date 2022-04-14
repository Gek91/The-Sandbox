package architecturalPattern.domainLogicPattern.serviceLayer;

import patterns.architecturalPattern.domainLogicPattern.domainModel.Contract;

public interface IntegrationGateway {

	void publishRevenueRecognitionCalculation(Contract contract);
}
