package architecturalPattern.domainLogicPattern.domainModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Contract {

	private Product product;
	private long revenue;
	private Date whenSigned;
	
	private List<RevenueRecognition> revenueRecognitions;
	
	/*
	 * Constructor
	 */
	public Contract(Product product, long revenue, Date whenSigned) {
		this.product = product;
		this.revenue = revenue;
		this.whenSigned = whenSigned;
		this.revenueRecognitions = new ArrayList<>();
	}
	
	public long recognizedRevenue(Date asOf) {
		
		long result = 0;
		
		Iterator<RevenueRecognition> iterator = this.revenueRecognitions.iterator();
		while(iterator.hasNext()) {
			
			RevenueRecognition recognition = iterator.next();
			
			if(recognition.isRecognizableBy(asOf)) {
				result = result + recognition.getAmount();
			}
		}
		return result;
	}

	public void calculateRecognitions() {
		product.calculateRevenueRecognitions(this);
	}
	
	
	public void addRevenueRecognition(RevenueRecognition recognition) {
		this.revenueRecognitions.add(recognition);
	}
	
	public Product getProduct() {
		return product;
	}

	public long getRevenue() {
		return revenue;
	}

	public Date getWhenSigned() {
		return whenSigned;
	}

}
