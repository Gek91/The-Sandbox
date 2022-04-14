package architecturalPattern.domainLogicPattern.transactionScript;

import java.util.Date;

public class Contract {

	private long revenue;
	private Date dateSigned;
	private String type;
	
	public long getRevenue() {
		return revenue;
	}
	public void setRevenue(long revenue) {
		this.revenue = revenue;
	}
	public Date getDateSigned() {
		return dateSigned;
	}
	public void setDateSigned(Date dateSigned) {
		this.dateSigned = dateSigned;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
