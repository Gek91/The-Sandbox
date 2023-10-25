package testExample.integration;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import testExample.model.Expense;
import testExample.model.ExpenseSplit;
import testExample.model.ExpenseType;
import testExample.respository.ExpenseCrudRepository;
import testExample.service.ExpenseService;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class ExpenseServiceIntegrationDataJpaTest {

	@Autowired
	private ExpenseCrudRepository expenseRepository;

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
