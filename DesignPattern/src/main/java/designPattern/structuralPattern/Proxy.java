package designPattern.structuralPattern;

import java.util.Scanner;

public class Proxy {
	  public static void main(String[] args) {
	       
		  	Client client = new ProtectionProxyClientImpl(null);
       		System.out.println();
       		System.out.println("main received: " + client.getAccountNo());
		  
		  ///////////////////////////////////
		  
       		Image image1 = new ProxyImage("HiRes_10MB_Photo1");
	        Image image2 = new ProxyImage("HiRes_10MB_Photo2");   
	        Image image3 = new ProxyImage("HiRes_10MB_Photo3");

	        image1.displayImage(); // loading necessary
	        image2.displayImage(); // loading necessary
	        image1.displayImage(); // no loading necessary; already done
	        image2.displayImage(); // no loading necessary; already done
	        image3.displayImage(); // loading necessary	        
	    }
}

/////////////////////////////////////////////////////////
//VIRTUAL PROXY

//Subject
interface Image {
    public void displayImage();
}
 
//Real Subject
class RealImage implements Image {
	
    private String filename;
    
    public RealImage(String filename) { 
        this.filename = filename;
        loadImageFromDisk();
    }

    // Potentially expensive operation
    private void loadImageFromDisk() {
        System.out.println("Loading " + filename);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }

    public void displayImage() { System.out.println("Displaying "+filename); }
}
 
//Proxy
class ProxyImage implements Image {
	
    private String filename;
    private RealImage image;
 
    public ProxyImage(String filename) { this.filename = filename; }
    
    public void displayImage() {
        if (image == null) {
            image = new RealImage(filename); // load only on demand
        }
        image.displayImage();
    }
}

//////////////////////////////////////////////////////
//Protection proxy

//Subject
interface Client {
    String getAccountNo();
}

//Real Subject
class RealClientImpl implements Client {
    private String accountNo = "12345";
    public RealClientImpl() {
        System.out.println("RealClient: Initialized");
    }
    @Override
    public String getAccountNo() {
        System.out.println("RealClient's AccountNo: " + accountNo);
        return accountNo;
    }
}
 
//Proxy
class ProtectionProxyClientImpl implements Client {
    private String password;  //password to get secret
    private Client client;
    private Scanner scanner = new Scanner(System.in);

    public ProtectionProxyClientImpl(String pwd) {
        System.out.println("ProtectionProxy: Initialized");
        password = pwd;
        client = new RealClientImpl();
    }

    /**
     *  Authenticate the user and return the Account Number
     */
    @Override
    public String getAccountNo() {
        System.out.print("Password: ");
        String tmpPwd = scanner.nextLine();

        if (password == null || password.equals(tmpPwd)) {
            return client.getAccountNo();
        } else {
            System.out.println("ProtectionProxy: Illegal password!");
            return "";
        }
    }
}
