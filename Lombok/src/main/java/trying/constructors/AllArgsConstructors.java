package trying.constructors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
@Getter
public class AllArgsConstructors {

	//check on null values
	@NonNull
	private String val1;
	@NonNull
	private int val2;
}
