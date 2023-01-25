package event.events;

import org.springframework.context.ApplicationEvent;

public class MyEvent extends ApplicationEvent {

	private String field;

	public MyEvent(Object source, String field) {
		super(source);
		this.field = field;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
}
