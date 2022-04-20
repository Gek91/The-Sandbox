package trying;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetterSetter {

	private String val1;
	@Setter(AccessLevel.PRIVATE) //private setter
	private int val2;
}
