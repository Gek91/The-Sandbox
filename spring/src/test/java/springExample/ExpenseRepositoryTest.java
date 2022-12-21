package springExample;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import springExample.model.Expense;
import springExample.model.ExpenseType;
import springExample.repository.ExpenseCrudRepository;
import springExample.repository.ExpenseRepository;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
//needed for application-test.property use -> set mysql mode
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ExpenseRepositoryTest {

	@Autowired
	private ExpenseRepository expenseRepository;
	@Autowired
	private ExpenseCrudRepository expenseCrudRepository;

	@Test
	public void findExpenseByIdTest() {
		Expense expense = expenseRepository.find(1l);

		Assert.assertEquals(new Long(1l), expense.getId());
		Assert.assertEquals(LocalDate.of(2022,11,21), expense.getExpenseDate());
		Assert.assertEquals(new Long(1l), expense.getType().getId());
		Assert.assertEquals("type1", expense.getType().getName());
		Assert.assertNotNull(expense.getCreationDatetime());
		Assert.assertNotNull(expense.getModificationDatetime());
	}

	@Test
	public void findExpenseTypeListTest() {

		List<ExpenseType> types = expenseRepository.findExpenseTypeList();

		Assert.assertEquals(5, types.size());
	}

	@Test
	public void findExpenseByIdWithJPA() {

		Expense expense = expenseCrudRepository.findById(1l).get();

		Assert.assertEquals(new Long(1l), expense.getId());
	}
}
