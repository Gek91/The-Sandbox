package architecturalPattern.domainLogicPattern.domainModel;

public class Product {

	private String name;
	private RecognitionStrategy recognitionStrategy;
	
	/*
	 * Constructor
	 */
	public Product(String name, RecognitionStrategy recognitionStrategy) {
		this.name = name;
		this.recognitionStrategy = recognitionStrategy;
	}

	
	void calculateRevenueRecognitions(Contract contract) {
		recognitionStrategy.calculateRevenueRecognitions(contract);
	}
	
	
	public static Product newWordProcessor(String name) {
		return new Product(name, new CompleteRecognitionStrategy());
	}
	
	public static Product newSpreadsheet(String name) {
		return new Product(name, new ThreeWayRecognitionStrategy(60, 90));
	}
	
	public static Product newDatabase(String name) {
		return new Product(name, new ThreeWayRecognitionStrategy(30, 60));
	}
}
