package java16.record;

public class Basic {

	//concise annotation for data classes
	//plain immutable class for data transfer
	//aim to eliminate the boilerplate code
	public record MyRecord (String value1, Integer value2) {}

	public static void main(String[] args) throws Exception {

		MyRecord myRecord = new MyRecord("a", 2);
		System.out.println(myRecord.value1());
		System.out.println(myRecord.value2());
		System.out.println(myRecord);
	}

}


