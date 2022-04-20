package trying;

//autogenerate equals and hashcode
@lombok.EqualsAndHashCode
public class EqualsAndHashCode {

	private static int VALUE = 10; //not included, must be done explicit
	private transient int transientVar = 10; //not included, must be done explicit
	private String name;
	private double score;
	@lombok.EqualsAndHashCode.Exclude
	private Square shape = new Square(5, 10);
	private String[] tags;
	@lombok.EqualsAndHashCode.Exclude //explicit exclusion
	private int id;


	@lombok.EqualsAndHashCode(callSuper=true) //include equals and hasCode method of superclass
	public static class Square extends Shape{
		private final int width, height;

		public Square(int width, int height) {
			this.width = width;
			this.height = height;
		}
	}

	@lombok.EqualsAndHashCode
	public static class Shape {

	}
}
