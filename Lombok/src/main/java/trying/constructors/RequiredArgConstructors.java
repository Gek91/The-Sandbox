package trying.constructors;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class RequiredArgConstructors {

	private String var1;
	@NonNull
	private int var2;
	private final long var3;
}
