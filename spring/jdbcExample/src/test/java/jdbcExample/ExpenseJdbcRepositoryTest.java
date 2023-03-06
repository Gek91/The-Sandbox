package jdbcExample;

import jdbcExample.model.Expense;
import jdbcExample.model.ExpenseSplit;
import jdbcExample.model.ExpenseType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import jdbcExample.repository.ExpenseRepositoryJdbcTemplate;

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
public class ExpenseJdbcRepositoryTest {

	@Autowired
	private ExpenseRepositoryJdbcTemplate expenseRepositoryJdbcTemplate;


	@Test
	public void testGetExpenseCount() {

		int result = expenseRepositoryJdbcTemplate.getExpensesCount();

		Assert.assertEquals(1, result);
	}

	@Test
	public void testFindExpenseById() {

		Expense expense = expenseRepositoryJdbcTemplate.getExpenseById(1l);

		Assert.assertEquals(new Long(1l), expense.getId());
		Assert.assertEquals(LocalDate.of(2022,11,21), expense.getExpenseDate());
		Assert.assertEquals(new Long(1l), expense.getType().getId());
		Assert.assertEquals("type1", expense.getType().getName());
		Assert.assertNotNull(expense.getCreationDatetime());
		Assert.assertNotNull(expense.getModificationDatetime());
	}

	@Test
	public void testFindExpenseExpenseSplit() {

		List<ExpenseSplit> splits = expenseRepositoryJdbcTemplate.getExpenseExpenseSplit(1l);

		Assert.assertEquals(2, splits.size());
		Assert.assertEquals(LocalDate.of(2022,11,21), splits.get(0).getRecordDate());
		Assert.assertEquals(new BigDecimal(10), splits.get(0).getAmount());
		Assert.assertEquals(LocalDate.of(2022,12,21), splits.get(1).getRecordDate());
		Assert.assertEquals(new BigDecimal(10), splits.get(1).getAmount());
	}

	@Test
	public void testInsertExpenseSplit() {

		List<ExpenseSplit> splits = expenseRepositoryJdbcTemplate.getExpenseExpenseSplit(1l);
		Assert.assertEquals(2, splits.size());

		ExpenseSplit newExpenseSplit = new ExpenseSplit();
		newExpenseSplit.setExpenseId(1l);
		newExpenseSplit.setAmount(new BigDecimal(20));
		newExpenseSplit.setRecordDate(LocalDate.of(2023,1,1));

		expenseRepositoryJdbcTemplate.insertExpenseSplit(newExpenseSplit );

		splits = expenseRepositoryJdbcTemplate.getExpenseExpenseSplit(1l);
		Assert.assertEquals(3, splits.size());
		Assert.assertEquals(LocalDate.of(2023,1,1), splits.get(2).getRecordDate());
		Assert.assertEquals(new BigDecimal(20), splits.get(2).getAmount());
	}

	@Test
	public void testUpdateExpenseSplitAmount() {

		List<ExpenseSplit> splits = expenseRepositoryJdbcTemplate.getExpenseExpenseSplit(1l);
		Assert.assertEquals(LocalDate.of(2022,11,21), splits.get(0).getRecordDate());
		Assert.assertEquals(new BigDecimal(10), splits.get(0).getAmount());

		ExpenseSplit expenseToUpdate = splits.get(0);
		expenseToUpdate.setAmount(new BigDecimal(20));

		expenseRepositoryJdbcTemplate.updateExpenseSplitAmount(expenseToUpdate);

		splits = expenseRepositoryJdbcTemplate.getExpenseExpenseSplit(1l);
		Assert.assertEquals(LocalDate.of(2022,11,21), splits.get(0).getRecordDate());
		Assert.assertEquals(new BigDecimal(20), splits.get(0).getAmount());
	}

	@Test
	public void testDeleteExpenseSplit() {

		List<ExpenseSplit> splits = expenseRepositoryJdbcTemplate.getExpenseExpenseSplit(1l);
		Assert.assertEquals(2, splits.size());

		expenseRepositoryJdbcTemplate.deleteExpenseSplint(1l, LocalDate.of(2022,11,21));

		splits = expenseRepositoryJdbcTemplate.getExpenseExpenseSplit(1l);
		Assert.assertEquals(1, splits.size());
	}

	@Test
	public void testGetExpenseTypeByName() {

		ExpenseType type = expenseRepositoryJdbcTemplate.getExpenseTypeByName("type2");

		Assert.assertEquals("type2", type.getName());
	}

	@Test
	public void testUpdateExpenseSplitAmountWithNamedParameter() {

		List<ExpenseSplit> splits = expenseRepositoryJdbcTemplate.getExpenseExpenseSplit(1l);
		Assert.assertEquals(LocalDate.of(2022,11,21), splits.get(0).getRecordDate());
		Assert.assertEquals(new BigDecimal(10), splits.get(0).getAmount());

		ExpenseSplit expenseToUpdate = splits.get(0);
		expenseToUpdate.setAmount(new BigDecimal(20));

		expenseRepositoryJdbcTemplate.updateExpenseSplitAmountWithNamedParameter(expenseToUpdate);

		splits = expenseRepositoryJdbcTemplate.getExpenseExpenseSplit(1l);
		Assert.assertEquals(LocalDate.of(2022,11,21), splits.get(0).getRecordDate());
		Assert.assertEquals(new BigDecimal(20), splits.get(0).getAmount());
	}

	@Test
	public void testInsertExpense() {

		Expense expenseToCreate = new Expense();
		expenseToCreate.setExpenseDate(LocalDate.of(2023,1,1));
		expenseToCreate.setType(new ExpenseType(1l,"type1"));
		expenseToCreate.setCreationDatetime(Instant.now());
		expenseToCreate.setModificationDatetime(Instant.now());

		expenseRepositoryJdbcTemplate.insertExpense(expenseToCreate);

		expenseToCreate = expenseRepositoryJdbcTemplate.getExpenseById(expenseToCreate.getId());

		Assert.assertNotNull(expenseToCreate.getId());
		Assert.assertEquals(LocalDate.of(2023,1,1), expenseToCreate.getExpenseDate());
		Assert.assertEquals(new Long(1l), expenseToCreate.getType().getId());
		Assert.assertEquals("type1", expenseToCreate.getType().getName());
		Assert.assertNotNull(expenseToCreate.getCreationDatetime());
		Assert.assertNotNull(expenseToCreate.getModificationDatetime());
	}

	@Test
	public void testBatchUpdateExpensesSplitValues() {

		List<ExpenseSplit> splits = expenseRepositoryJdbcTemplate.getExpenseExpenseSplit(1l);
		Assert.assertEquals(LocalDate.of(2022,11,21), splits.get(0).getRecordDate());
		Assert.assertEquals(new BigDecimal(10), splits.get(0).getAmount());
		Assert.assertEquals(LocalDate.of(2022,12,21), splits.get(1).getRecordDate());
		Assert.assertEquals(new BigDecimal(10), splits.get(1).getAmount());

		splits.get(0).setAmount(new BigDecimal(20));
		splits.get(1).setAmount(new BigDecimal(30));

		expenseRepositoryJdbcTemplate.batchUpdateExpensesSplitValues(splits);

		splits = expenseRepositoryJdbcTemplate.getExpenseExpenseSplit(1l);
		Assert.assertEquals(LocalDate.of(2022,11,21), splits.get(0).getRecordDate());
		Assert.assertEquals(new BigDecimal(20), splits.get(0).getAmount());
		Assert.assertEquals(LocalDate.of(2022,12,21), splits.get(1).getRecordDate());
		Assert.assertEquals(new BigDecimal(30), splits.get(1).getAmount());
	}
}
