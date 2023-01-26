package jdbcExample;

import jdbcExample.model.Expense;
import jdbcExample.model.ExpenseSplit;
import jdbcExample.model.ExpenseType;
import jdbcExample.repository.ExpenseRepositoryJdbcInsert;
import jdbcExample.repository.ExpenseRepositoryJdbcTemplate;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
//needed for application-test.property use -> set mysql mode
@ActiveProfiles("test")
@Transactional //Needed to activate test rollback. Without the database remain dirty after every test
public class ExpenseSimpleJdbcInsertRepositoryTest {

	@Autowired
	private ExpenseRepositoryJdbcInsert expenseRepositoryJdbcInsert;
	@Autowired
	private ExpenseRepositoryJdbcTemplate expenseRepositoryJdbcTemplate;

	@Test
	public void testInsertExpenseSplit() {

		List<ExpenseSplit> splits = expenseRepositoryJdbcTemplate.getExpenseExpenseSplit(1l);
		Assert.assertEquals(2, splits.size());

		ExpenseSplit newExpenseSplit = new ExpenseSplit();
		newExpenseSplit.setExpenseId(1l);
		newExpenseSplit.setAmount(new BigDecimal(20));
		newExpenseSplit.setRecordDate(LocalDate.of(2023,1,1));

		expenseRepositoryJdbcInsert.addExpenseSplit(newExpenseSplit );

		splits = expenseRepositoryJdbcTemplate.getExpenseExpenseSplit(1l);
		Assert.assertEquals(3, splits.size());
		Assert.assertEquals(LocalDate.of(2023,1,1), splits.get(2).getRecordDate());
		Assert.assertEquals(new BigDecimal(20), splits.get(2).getAmount());
	}

	@Test
	public void testInsertExpense() {

		Expense expenseToCreate = new Expense();
		expenseToCreate.setExpenseDate(LocalDate.of(2023,1,1));
		expenseToCreate.setType(new ExpenseType(1l,"type1"));
		expenseToCreate.setCreationDatetime(Instant.now());
		expenseToCreate.setModificationDatetime(Instant.now());

		expenseRepositoryJdbcInsert.addExpense(expenseToCreate);

		expenseToCreate = expenseRepositoryJdbcTemplate.getExpenseById(expenseToCreate.getId());

		Assert.assertNotNull(expenseToCreate.getId());
		Assert.assertEquals(LocalDate.of(2023,1,1), expenseToCreate.getExpenseDate());
		Assert.assertEquals(new Long(1l), expenseToCreate.getType().getId());
		Assert.assertEquals("type1", expenseToCreate.getType().getName());
		Assert.assertNotNull(expenseToCreate.getCreationDatetime());
		Assert.assertNotNull(expenseToCreate.getModificationDatetime());
	}
}
