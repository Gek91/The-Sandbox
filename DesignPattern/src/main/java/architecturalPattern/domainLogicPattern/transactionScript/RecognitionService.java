package architecturalPattern.domainLogicPattern.transactionScript;

import java.util.Date;
import java.util.List;

import util.DateUtil;


public class RecognitionService {

	private Gateway db;
	
	public RecognitionService(Gateway db) {
		this.db = db;
	}
	
	public void calculateRevenueRecognitions(long contractNumber) {
		
		try {
			Contract contract = db.findContract(contractNumber);
			
			
			switch (contract.getType()) {
			case "S":
				db.insertRecognition(contractNumber, contract.getRevenue()/3, contract.getDateSigned());
				db.insertRecognition(contractNumber, contract.getRevenue()/3, DateUtil.addDays(contract.getDateSigned(), 30));
				db.insertRecognition(contractNumber, contract.getRevenue()/3, DateUtil.addDays(contract.getDateSigned(), 90));
				break;
				
			case "W":
				db.insertRecognition(contractNumber, contract.getRevenue(), contract.getDateSigned());
				break;
			
			case "D":
				db.insertRecognition(contractNumber, contract.getRevenue()/3, contract.getDateSigned());
				db.insertRecognition(contractNumber, contract.getRevenue()/3, DateUtil.addDays(contract.getDateSigned(), 30));
				db.insertRecognition(contractNumber, contract.getRevenue()/3, DateUtil.addDays(contract.getDateSigned(), 60));
				break;
			
			default:
				break;
			}
			
		} catch(Exception e) {
			throw new RuntimeException();
		}
	}
	
	public long recognizedRevenue(long contractNumber, Date asOf) {
		
		long result = 0;
		
		List<Recognition> recognitions = db.findRecognitionsFor(contractNumber, asOf);
		
		for(Recognition recognition : recognitions) {
			result = result + recognition.getValue();
		}
		
		return result;
	}
}
