package architecturalPattern.domainLogicPattern.transactionScript;

import java.util.Date;
import java.util.List;

public interface Gateway {

	public List<Recognition> findRecognitionsFor(long contractId, Date asOf);
	
	public void insertRecognition(long contractNumber, long value, Date recognitionDate) ;
	
	public Contract findContract(long contractNumber) ;
}
