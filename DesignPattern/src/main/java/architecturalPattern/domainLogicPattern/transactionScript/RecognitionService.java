package architecturalPattern.domainLogicPattern.transactionScript;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;



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
				db.insertRecognition(contractNumber, contract.getRevenue()/3, contract.getDateSigned().plus(30, ChronoUnit.DAYS));
				db.insertRecognition(contractNumber, contract.getRevenue()/3, contract.getDateSigned().plus(90, ChronoUnit.DAYS));
				break;
				
			case "W":
				db.insertRecognition(contractNumber, contract.getRevenue(), contract.getDateSigned());
				break;
			
			case "D":
				db.insertRecognition(contractNumber, contract.getRevenue()/3, contract.getDateSigned());
				db.insertRecognition(contractNumber, contract.getRevenue()/3, contract.getDateSigned().plus(30, ChronoUnit.DAYS));
				db.insertRecognition(contractNumber, contract.getRevenue()/3, contract.getDateSigned().plus(60, ChronoUnit.DAYS));
				break;
			
			default:
				break;
			}
			
		} catch(Exception e) {
			throw new RuntimeException();
		}
	}
	
	public long recognizedRevenue(long contractNumber, Instant asOf) {
		
		long result = 0;
		
		List<Recognition> recognitions = db.findRecognitionsFor(contractNumber, asOf);
		
		for(Recognition recognition : recognitions) {
			result = result + recognition.getValue();
		}
		
		return result;
	}
}
