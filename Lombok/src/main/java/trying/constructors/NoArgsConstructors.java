package trying.constructors;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor //default no args constructor
@Getter
public class NoArgsConstructors {

	private String val1;
	private int val2;

	public NoArgsConstructors(String val1) {
		this.val1 = val1;
	}
}
