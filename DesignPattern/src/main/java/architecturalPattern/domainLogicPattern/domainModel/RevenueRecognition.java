package architecturalPattern.domainLogicPattern.domainModel;

import java.time.Instant;
import java.util.Date;

public class RevenueRecognition {

	private long amount;
	private Instant date;
	
	/*
	 * Constructor
	 */
	public RevenueRecognition(long amount, Instant date) {
		this.amount = amount;
		this.date = date;
	}
	
	public long getAmount() {
		return this.amount;
	}

	boolean isRecognizableBy(Instant asOf) {
		return asOf.isAfter(this.date) || asOf.equals(this.date);
	}
}
