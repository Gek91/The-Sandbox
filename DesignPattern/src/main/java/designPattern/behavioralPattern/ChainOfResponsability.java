package designPattern.behavioralPattern;

public class ChainOfResponsability {
	 
	public static void main(String[] args) {
		
	        ManagerPPower manager = new ManagerPPower();
	        DirectorPPower director = new DirectorPPower();
	        VicePresidentPPower vp = new VicePresidentPPower();
	        PresidentPPower president = new PresidentPPower();
	        manager.setSuccessor(director);
	        director.setSuccessor(vp);
	        vp.setSuccessor(president);

	        
	        manager.processRequest(new PurchaseRequest(21000, "General"));
	}
	    
}

/////////////////////////////
//Request
class PurchaseRequest {
    private double amount;
    private String purpose;

    public PurchaseRequest(double amount, String purpose) {
        this.amount = amount;
        this.purpose = purpose;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amt)  {
        amount = amt;
    }

    public String getPurpose() {
        return purpose;
    }
    public void setPurpose(String reason) {
        purpose = reason;
    }
}

////////////////////////////////////
//Handler
abstract class PurchasePower {
	protected static final double BASE = 500;
	protected PurchasePower successor;

	abstract protected double getAllowable();
	abstract protected String getRole();

	public void setSuccessor(PurchasePower successor) {
		 this.successor = successor;
	}
	
	
	public void processRequest(PurchaseRequest request) {
		if(request.getAmount() < this.getAllowable()) {
			System.out.println(this.getRole() + " will approve €" + request.getAmount());
		} else if( successor != null) {
			successor.processRequest(request);
		}
	}
}

//Concrete Handler
class ManagerPPower extends PurchasePower {
    
    protected double getAllowable(){
        return BASE*10;
    }

    protected String getRole(){
        return "Manager";
    }
}

class DirectorPPower extends PurchasePower {
    
    protected double getAllowable(){
        return BASE*20;
    }

    protected String getRole(){
        return "Director";
    }
}

class VicePresidentPPower extends PurchasePower {
    
    protected double getAllowable(){
        return BASE*40;
    }

    protected String getRole(){
        return "Vice President";
    }
}

class PresidentPPower extends PurchasePower {

    protected double getAllowable(){
        return BASE*60;
    }

    protected String getRole(){
        return "President";
    }
}
	
	
	
	
	
