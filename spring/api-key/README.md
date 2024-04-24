# Spring API Key

The idea is to get the hTTP Api Key header from the request and check the secret with the configuration.<br>
We will add a custom filter in the Spring Security configuration class
- AuthenticationFilter: extend GenericFilterBean, doFilter method evaluate the api key header and set the authentication object in the current SecurityContext instance
- AuthenticationService: check the request for the api key header, perform the authentication and add the secret to the security context
- ApiKeyAuthentication: implement Authentication interface (AbstractAuthenticationToken), representing the entity authenticated in the request
- The filter is the registered in the SecurityFilterChain adding it before UsernamePasswordAuthenticationFilter

To test call http://localhost:8080/test adding header X-API-KEY : MYTOKEN


