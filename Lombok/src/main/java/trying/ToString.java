package trying;


@lombok.ToString
public class ToString {

	private static final int STATIC_VAR = 10; //not included
	private String name;
	private Square shape = new Square(5, 10);
	private String[] tags;
	@lombok.ToString.Exclude //explicit exclusion
	private int id;

	@lombok.ToString(callSuper=true, includeFieldNames=true)
	public static class Square {
		private final int width, height;

		public Square(int width, int height) {
			this.width = width;
			this.height = height;
		}
	}
}
