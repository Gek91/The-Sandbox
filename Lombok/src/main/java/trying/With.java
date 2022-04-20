package trying;

import lombok.AllArgsConstructor;
import lombok.Getter;

// used of immutability. Create method with to create a new class object with a changed value
@lombok.With
@AllArgsConstructor //With need all field constructor, this will autogenerate it
@Getter
public class With {
	//Immutable class with final fields
	private final int var1;
	private final String var2;
}
