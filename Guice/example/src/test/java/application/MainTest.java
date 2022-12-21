package application;

import application.interfaceBinding.SimpleService;
import application.interfaceBinding.MockSimpleServiceImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MainTest {

	private Injector injector;

	@Before
	public void setUp() {
		this.injector = Guice.createInjector(new AbstractModule() {
			@Override
			protected void configure() {
				bind(SimpleService.class).to(MockSimpleServiceImpl.class);
			}
		});
	}

	@After
	public void tearDown() throws Exception {
		injector = null;
	}

	@Test
	public void test() {

		Application app = injector.getInstance(Application.class);
		app.useSimpleService();
	}
}
