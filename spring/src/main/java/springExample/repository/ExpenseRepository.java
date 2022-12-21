package springExample.repository;

import springExample.model.Expense;
import springExample.model.ExpenseType;

import java.util.List;

public interface ExpenseRepository {

	Expense find(Long id);

	List<ExpenseType> findExpenseTypeList();

	void save(Expense expense);
}
