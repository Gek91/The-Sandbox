package architecturalPattern.objectRelationalBehavioralPattern.lazyLoad.valueHolder;

public class ValueHolder {

	private Object value;
	private ValueLoader loader;

	public ValueHolder(ValueLoader loader) {
		this.loader = loader;
	}
	
	public Object getValue() {
		if(value == null) {
			value = loader.load();
		}
		return value;
	}
}
