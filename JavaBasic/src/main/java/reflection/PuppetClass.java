package reflection;

import java.io.IOException;

public class PuppetClass {

	private Integer field1;
	public String field2;
	
	public PuppetClass() {
		
	}
	
	public PuppetClass(int a) {
		
	}
	
	public Integer getField1(String a) throws IOException{
		return field1;
	}
	
	private String getField2() {
		return field2;
	}
}
