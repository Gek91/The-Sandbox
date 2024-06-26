package jdbcExample.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jdbcExample.repository.UserCrudRepository;
import jdbcExample.service.UserDetailService;


@Service
public class UserDetailsServiceImpl implements UserDetailService {

	private UserCrudRepository userRepository;

	@Autowired
	public UserDetailsServiceImpl(UserCrudRepository userRepository) {
		this.userRepository = userRepository;
	}

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		User user = userRepository.findByUsername(username);
//
//		if(user != null) {
//			return user;
//		}
//
//		throw new EntityNotFoundException();
//	}
}
