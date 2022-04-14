package architecturalPattern.domainLogicPattern.domainModel;

import util.DateUtil;

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
		contract.addRevenueRecognition(new RevenueRecognition(contract.getRevenue()/3, DateUtil.addDays(contract.getWhenSigned(), this.firstRecognitionOffset)));
		contract.addRevenueRecognition(new RevenueRecognition(contract.getRevenue()/3, DateUtil.addDays(contract.getWhenSigned(), this.secondRecognitionOffset)));
	}
	
}
