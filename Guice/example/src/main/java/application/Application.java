package application;

import application.interfaceBinding.SimpleService;
import application.multibinding.MultiBinding;
import application.namedBinding.AlfaNamedBindingImpl;
import application.namedBinding.BetaNamedBindingImpl;
import application.namedBinding.NamedBinding;
import application.simpleBinding.SimpleEagerBind;
import application.providerBiding.ToProvideEntity;
import com.google.inject.Inject;
import com.google.inject.Provider;

import java.util.Map;
import java.util.Set;

public class Application {

	private SimpleService simpleService; //simple interface
	private Provider<ToProvideEntity> simpleProvider; //provider
	private SimpleEagerBind simpleBind;

	private NamedBinding alfaNameBinding;
	private NamedBinding betaNameBinding;

	private Set<MultiBinding> setMultibindings;
	private Map<String, MultiBinding> mapMultibinding;

	//constructor injector
	@Inject
	private Application(SimpleService simpleService, Provider<ToProvideEntity> simpleProvider,
						SimpleEagerBind simpleBind, @AlfaNamedBindingImpl.Alfa NamedBinding alfaNameBinding,
						@BetaNamedBindingImpl.Beta NamedBinding betaNameBinding, Set<MultiBinding> setMultibindings,
						Map<String, MultiBinding> mapMultibinding) {

		this.simpleService = simpleService;
		this.simpleProvider = simpleProvider;
		this.simpleBind = simpleBind;

		this.alfaNameBinding = alfaNameBinding;
		this.betaNameBinding = betaNameBinding;

		this.setMultibindings = setMultibindings;
		this.mapMultibinding = mapMultibinding;
	}

	public void useSimpleService() {
		simpleService.doSomething();
	}

	public ToProvideEntity useProvider() {
		return this.simpleProvider.get();
	}

	public void userSimpleBind() {
		simpleBind.doSomething();
	}

	public void useAlfaNameBinding() {
		this.alfaNameBinding.doSomething();;
	}

	public void useBetaNameBinding() {
		this.betaNameBinding.doSomething();
	}

	public void useMultibinder() {
		this.setMultibindings.forEach(elem -> elem.doSomething());
	}

	public void useMapBinder() {
		this.mapMultibinding.entrySet().forEach(entry -> {
					System.out.print(entry.getKey() + " ");
					entry.getValue().doSomething();
				});
	}
}
