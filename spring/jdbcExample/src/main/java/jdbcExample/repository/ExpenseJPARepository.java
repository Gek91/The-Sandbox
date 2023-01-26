package jdbcExample.repository;

import jdbcExample.model.Expense;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class ExpenseJPARepository {

	private EntityManager entityManager;

	@Autowired
	public ExpenseJPARepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Expense getExpenseById(Long id) {
		return this.entityManager
				.createQuery("from Expense where id = ?1", Expense.class)
				.setParameter(1, id)
				.getSingleResult();
	}


}
