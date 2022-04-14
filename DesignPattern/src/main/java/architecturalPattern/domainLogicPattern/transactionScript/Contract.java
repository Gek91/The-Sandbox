package architecturalPattern.domainLogicPattern.transactionScript;

import java.time.Instant;

public class Contract {

	private long revenue;
	private Instant dateSigned;
	private String type;
	
	public long getRevenue() {
		return revenue;
	}
	public void setRevenue(long revenue) {
		this.revenue = revenue;
	}
	public Instant getDateSigned() {
		return dateSigned;
	}
	public void setDateSigned(Instant dateSigned) {
		this.dateSigned = dateSigned;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
