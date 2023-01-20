package springAppExample.repository;

import springAppExample.model.Expense;
import springAppExample.model.ExpenseType;

import java.util.List;

public interface ExpenseRepository {

	Expense find(Long id);

	List<ExpenseType> findExpenseTypeList();

	void save(Expense expense);
}
