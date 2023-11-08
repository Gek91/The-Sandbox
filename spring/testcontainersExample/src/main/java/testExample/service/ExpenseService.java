package testExample.service;

import testExample.model.Expense;

import java.math.BigDecimal;

public interface ExpenseService {

	public BigDecimal getExpenseAmount(Expense expense);
}
