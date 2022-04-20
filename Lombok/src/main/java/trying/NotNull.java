package trying;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class NotNull {
	private String value;

	//automatic not null check
	public NotNull(@NonNull String value) {
		this.value = value;
	}

	public void setNotNull(@NonNull String value) {
		this.value = value;
	}
}
