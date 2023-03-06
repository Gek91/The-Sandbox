package jdbcExample.repository;

import jdbcExample.model.Expense;
import jdbcExample.model.ExpenseType;

import java.util.List;

public interface ExpenseRepository {

	Expense find(Long id);

	List<ExpenseType> findExpenseTypeList();

	void save(Expense expense);
}
