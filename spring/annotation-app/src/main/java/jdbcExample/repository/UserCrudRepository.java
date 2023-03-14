package jdbcExample.repository;


import org.springframework.data.repository.CrudRepository;
import jdbcExample.model.User;

//CrudRepository, automatic crud generation of repository method
public interface UserCrudRepository extends CrudRepository<User, Long> {

	//implementation autogenerated, other than crudRepository methods
	User findByUsername(String username);
}