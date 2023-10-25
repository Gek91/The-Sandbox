package testExample.unit;


import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import testExample.model.Expense;
import testExample.model.ExpenseSplit;
import testExample.service.ExpenseService;
import testExample.service.ExpenseServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ExpenseServiceUnitTest {

	private static ExpenseService expenseService;

	@BeforeClass
	public static void initService() {
		expenseService = new ExpenseServiceImpl();
	}


	@Test
	public void testGetExpenseAmount() {

		Expense expense = new Expense();

		List<ExpenseSplit> expenseSplitList = new ArrayList<>();

		ExpenseSplit split1 = new ExpenseSplit();
		split1.setAmount(BigDecimal.valueOf(10l));
		expenseSplitList.add(split1);

		ExpenseSplit split2 = new ExpenseSplit();
		split2.setAmount(BigDecimal.valueOf(30l));
		expenseSplitList.add(split2);

		expense.setSplits(expenseSplitList);

		BigDecimal result = expenseService.getExpenseAmount(expense);

		Assert.assertNotNull(result);
		Assert.assertEquals(BigDecimal.valueOf(40l), result);
	}
}
