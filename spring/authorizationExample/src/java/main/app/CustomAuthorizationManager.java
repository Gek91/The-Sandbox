package app;

import org.springframework.security.authorization.AuthenticatedAuthorizationManager;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.access.intercept.RequestMatcherDelegatingAuthorizationManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.function.Supplier;

public class CustomAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

	@Override
	public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext object) {

		AuthorizationManager<HttpServletRequest> manager = RequestMatcherDelegatingAuthorizationManager.builder()
//				.add(new AntPathRequestMatcher("/all/**"), )
				.add(new AntPathRequestMatcher("/admin/**"), AuthorityAuthorizationManager.hasRole("ADMIN"))
				.add(new AntPathRequestMatcher("/user/**"), new AuthenticatedAuthorizationManager<>())
				.build();

		return manager.check(authentication, object.getRequest());
	}

}
