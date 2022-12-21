package application.providerBiding;

import com.google.inject.Provider;

public class SimpleProvider implements Provider<ToProvideEntity> {

	@Override
	public ToProvideEntity get() {
		return new ToProvideEntity();
	}
}
