package architecturalPattern.domainLogicPattern.transactionScript;

import java.util.Date;

public class Recognition {

	private long value;
	private Date recognitionDate;
	
	public long getValue() {
		return value;
	}
	public void setValue(long value) {
		this.value = value;
	}
	public Date getRecognitionDate() {
		return recognitionDate;
	}
	public void setRecognitionDate(Date recognitionDate) {
		this.recognitionDate = recognitionDate;
	}
	
}
