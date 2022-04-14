package architecturalPattern.domainLogicPattern.domainModel;


import java.time.temporal.ChronoUnit;

public class ThreeWayRecognitionStrategy extends RecognitionStrategy {

	private int firstRecognitionOffset;
	private int secondRecognitionOffset;
	
	/*
	 * Constructor
	 */
	public ThreeWayRecognitionStrategy(int firstRecognitionOffset, int secondRecognitionOffset) {
		this.firstRecognitionOffset = firstRecognitionOffset;
		this.secondRecognitionOffset = secondRecognitionOffset;
	}

	@Override
	void calculateRevenueRecognitions(Contract contract) {
		
		contract.addRevenueRecognition(new RevenueRecognition(contract.getRevenue()/3, contract.getWhenSigned()));
		contract.addRevenueRecognition(new RevenueRecognition(contract.getRevenue()/3, contract.getWhenSigned().plus(this.firstRecognitionOffset, ChronoUnit.DAYS)));
		contract.addRevenueRecognition(new RevenueRecognition(contract.getRevenue()/3, contract.getWhenSigned().plus(this.secondRecognitionOffset, ChronoUnit.DAYS)));
	}
	
}
