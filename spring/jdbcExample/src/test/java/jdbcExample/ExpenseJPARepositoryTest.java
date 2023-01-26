package jdbcExample;

import jdbcExample.model.Expense;
import jdbcExample.repository.ExpenseJPARepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDate;

@SpringBootTest
@RunWith(SpringRunner.class)
//needed for application-test.property use -> set mysql mode
@ActiveProfiles("test")
@Transactional //Needed to activate test rollback. Without the database remain dirty after every test
public class ExpenseJPARepositoryTest {

	@Autowired
	private ExpenseJPARepository expenseJPARepository;

	@Test
	public void testFindExpenseById() {

		Expense expense = expenseJPARepository.getExpenseById(1l);

		Assert.assertEquals(new Long(1l), expense.getId());
		Assert.assertEquals(LocalDate.of(2022,11,21), expense.getExpenseDate());
		Assert.assertEquals(new Long(1l), expense.getType().getId());
		Assert.assertEquals("type1", expense.getType().getName());
		Assert.assertNotNull(expense.getCreationDatetime());
		Assert.assertNotNull(expense.getModificationDatetime());
	}
}
