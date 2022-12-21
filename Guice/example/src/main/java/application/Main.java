package application;

import application.providerBiding.ToProvideEntity;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {

	public static void main(String[] args) {

		Injector injector = Guice.createInjector(new GuiceModule());

		Application app = injector.getInstance(Application.class);

		//simple injection
		app.useSimpleService();
		app.useSimpleService();
		app.useSimpleService();

		//provider
		ToProvideEntity entity = app.useProvider();
		entity.doSomething();
		entity.doSomething();
		entity.doSomething();

		app.useProvider().doSomething();

		//simple bind eager
		app.userSimpleBind();
		app.userSimpleBind();

		//name binding
		app.useAlfaNameBinding();

		app.useBetaNameBinding();

		//muiltibidinder
		app.useMultibinder();

		app.useMapBinder();
	}
}
