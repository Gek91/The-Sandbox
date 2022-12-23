package webapp.app;

import com.google.inject.AbstractModule;

import java.util.List;

//application guice entrypoint module
public class AppGuiceModule extends AbstractModule {

	private List<Class<? extends AbstractModule>> modulesToLoad;

	public AppGuiceModule(List<Class<? extends AbstractModule>> modulesToLoad) {
		this.modulesToLoad = modulesToLoad;
	}

	@Override
	protected void configure() {

		loadApplicationModules();

		//application guice configurations
	}

	private void loadApplicationModules() {
		for (Class<? extends AbstractModule> module : modulesToLoad) {
			try {
				install(module.newInstance());
			} catch (InstantiationException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
