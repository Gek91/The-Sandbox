package jdbcExample.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import javax.persistence.Entity;
import javax.persistence.Id;

@Value
@Entity
//required for JPA
@RequiredArgsConstructor //because noArgCostructor
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true) //required by JPA
public class ExpenseType {
	@Id
	private final Long id;
	private final String name;
}
