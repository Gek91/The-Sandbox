package app.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class MyService {

	@PreAuthorize("hasRole('ADMIN')")
	public void onlyAdminLogic() {

		System.out.println("admin logic");
	}

	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public void userLogic() {

		System.out.println("user logic");
	}
}
