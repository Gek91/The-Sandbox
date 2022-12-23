package webapp;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import org.jboss.resteasy.plugins.guice.GuiceResteasyBootstrapServletContextListener;
import webapp.app.AppGuiceModule;
import webapp.logicModule.LogicGuiceModule;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ApplicationGuiceBootstrapContextListener extends GuiceResteasyBootstrapServletContextListener {

	private AppGuiceModule appModule;

	public ApplicationGuiceBootstrapContextListener() {

		//set application module
		List<Class<? extends AbstractModule>> modulesToLoad = new ArrayList<>();
		//module list to load
		modulesToLoad.add(LogicGuiceModule .class);
		//other modules...

		this.appModule = new AppGuiceModule(modulesToLoad);
	}

	@Override
	protected List<? extends Module> getModules(ServletContext context) {
		return Collections.singletonList(appModule);
	}
}
