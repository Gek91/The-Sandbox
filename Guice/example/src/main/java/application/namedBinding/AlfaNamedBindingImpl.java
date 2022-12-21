package application.namedBinding;

import javax.inject.Qualifier;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class AlfaNamedBindingImpl implements NamedBinding {

	@Override
	public void doSomething() {
		System.out.println("alfa");
	}

	@Qualifier
	@Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD })
	@Retention(RetentionPolicy.RUNTIME)
	public @interface Alfa {}
}
