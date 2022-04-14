package architecturalPattern.domainLogicPattern.domainModel;

import java.util.Date;

public class RevenueRecognition {

	private long amount;
	private Date date;
	
	/*
	 * Constructor
	 */
	public RevenueRecognition(long amount, Date date) {
		this.amount = amount;
		this.date = date;
	}
	
	public long getAmount() {
		return this.amount;
	}

	boolean isRecognizableBy(Date asOf) {
		return asOf.after(this.date) || asOf.equals(this.date);
	}
}
