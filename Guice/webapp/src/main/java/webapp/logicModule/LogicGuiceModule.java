package webapp.logicModule;

import com.google.inject.AbstractModule;
import webapp.logicModule.rest.MyRest;
import webapp.logicModule.service.MyService;

public class LogicGuiceModule extends AbstractModule {

	@Override
	protected void configure() {

		bind(MyRest.class);
		bind(MyService.class);
	}
}
