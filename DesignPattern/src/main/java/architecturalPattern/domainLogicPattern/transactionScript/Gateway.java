package architecturalPattern.domainLogicPattern.transactionScript;

import java.time.Instant;
import java.util.Date;
import java.util.List;

public interface Gateway {

	public List<Recognition> findRecognitionsFor(long contractId, Instant asOf);
	
	public void insertRecognition(long contractNumber, long value, Instant recognitionDate) ;
	
	public Contract findContract(long contractNumber) ;
}
