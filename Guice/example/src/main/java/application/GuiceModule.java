package application;

import application.multibinding.AlfaMultibiding;
import application.multibinding.BetaMultibiding;
import application.multibinding.DeltaMultibiding;
import application.multibinding.MultiBinding;
import application.namedBinding.AlfaNamedBindingImpl;
import application.namedBinding.BetaNamedBindingImpl;
import application.namedBinding.NamedBinding;
import application.providerBiding.SimpleProvider;
import application.interfaceBinding.SimpleService;
import application.simpleBinding.SimpleEagerBind;
import application.interfaceBinding.SimpleServiceImpl;
import application.providerBiding.ToProvideEntity;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;
import com.google.inject.multibindings.Multibinder;

public class GuiceModule extends AbstractModule {

	@Override
	protected void configure() {
		//bind service to implementation, created at first invocation
		bind(SimpleService.class).to(SimpleServiceImpl.class);
		//provider
		bind(ToProvideEntity.class).toProvider(SimpleProvider.class);

		//simple bind, created at startup
		bind(SimpleEagerBind.class).asEagerSingleton();

		//name binding
		bind(NamedBinding.class).annotatedWith(AlfaNamedBindingImpl.Alfa.class).to(AlfaNamedBindingImpl.class);
		bind(NamedBinding.class).annotatedWith(BetaNamedBindingImpl.Beta.class).to(BetaNamedBindingImpl.class);

		//multi binding
		Multibinder<MultiBinding> setMultibiding = Multibinder.newSetBinder(binder(), MultiBinding.class);
		setMultibiding.addBinding().to(AlfaMultibiding.class);
		setMultibiding.addBinding().to(BetaMultibiding.class);
		setMultibiding.addBinding().to(DeltaMultibiding.class);

		MapBinder<String, MultiBinding> mapMultibinder = MapBinder.newMapBinder(binder(), String.class, MultiBinding.class);
		mapMultibinder.addBinding("alfa").to(AlfaMultibiding.class);
		mapMultibinder.addBinding("beta").to(BetaMultibiding.class);
		mapMultibinder.addBinding("delta").to(DeltaMultibiding.class);
	}
}
