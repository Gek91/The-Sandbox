package testExample.integration;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import testExample.model.Expense;
import testExample.model.ExpenseSplit;
import testExample.model.ExpenseType;
import testExample.respository.ExpenseCrudRepository;
import testExample.service.ExpenseService;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.*;

//create the ApplicationContext for the test, needed to use @Autowired
@SpringBootTest
@RunWith(SpringRunner.class)
//needed for application-test.property use -> set mysql mode
@ActiveProfiles("test")
@Transactional
public class ExpenseServiceIntegrationTestWithMock {

	@MockBean
	private ExpenseService expenseService;

	@Autowired
	private ExpenseCrudRepository expenseRepository;

	@Before
	public void setUp() {

		//uses mockito
		Mockito.when(expenseService.getExpenseAmount(any(Expense.class)))
				.thenReturn(BigDecimal.valueOf(20));
	}

	@Test
	public void testGetExpenseAmount() {

		Long count = expenseRepository.count();
		Assert.assertEquals(count, Long.valueOf(1l));

		Optional<Expense> expenseOptional = expenseRepository.findById(1l);

		Assert.assertTrue(expenseOptional.isPresent());

		BigDecimal result = expenseService.getExpenseAmount(expenseOptional.get());

		Assert.assertNotNull(result);
		Assert.assertEquals(BigDecimal.valueOf(20l), result);
	}

	@Test
	public void testAddExpense() {

		Long count = expenseRepository.count();

		Expense expense = new Expense();
		expense.setType(new ExpenseType(1l, ""));
		expense.setCreationDatetime(Instant.now());
		expense.setModificationDatetime(expense.getCreationDatetime());
		expense.setExpenseDate(LocalDate.now());
		List<ExpenseSplit> expenseSplitList = new ArrayList<>();
		expense.setSplits(expenseSplitList);

		ExpenseSplit split1 = new ExpenseSplit();
		split1.setAmount(BigDecimal.valueOf(10l));
		split1.setRecordDate(LocalDate.now().plusDays(1));
		expenseSplitList.add(split1);

		ExpenseSplit split2 = new ExpenseSplit();
		split2.setAmount(BigDecimal.valueOf(30l));
		split2.setRecordDate(LocalDate.now().minusDays(1));
		expenseSplitList.add(split2);

		expenseRepository.save(expense);

		Assert.assertEquals(2, expenseRepository.count());
	}
}
