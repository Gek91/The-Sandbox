package producer.controller.data;

import java.io.Serializable;

public class Message implements Serializable {

	private String stringValue;
	private Long longValue;

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	public Long getLongValue() {
		return longValue;
	}

	public void setLongValue(Long longValue) {
		this.longValue = longValue;
	}
}
