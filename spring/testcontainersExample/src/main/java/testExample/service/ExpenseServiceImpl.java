package testExample.service;

import org.springframework.stereotype.Service;
import testExample.model.Expense;
import testExample.model.ExpenseSplit;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService{


	public BigDecimal getExpenseAmount(Expense expense) {

		List<ExpenseSplit> splits = expense.getSplits();

		return splits.stream().map(ExpenseSplit::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
	}
}
