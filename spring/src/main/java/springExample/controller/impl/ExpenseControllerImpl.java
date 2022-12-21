package springExample.controller.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import springExample.controller.ExpenseController;
import springExample.model.Expense;
import springExample.model.ExpenseType;
import springExample.repository.ExpenseCrudRepository;
import springExample.repository.ExpenseRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class ExpenseControllerImpl implements ExpenseController {

	private final ExpenseRepository expenseRepository;
	private final ExpenseCrudRepository expenseCrudRepository;

	@Autowired
	public ExpenseControllerImpl(ExpenseRepository expenseRepository, ExpenseCrudRepository expenseCrudRepository) {
		this.expenseRepository = expenseRepository;
		this.expenseCrudRepository = expenseCrudRepository;
	}

	@Override
	public ResponseEntity<Expense> getExpenseById(Long id) {
		Optional<Expense> expense = expenseCrudRepository.findById(id);

		if(expense.isPresent()) {
			return new ResponseEntity<>(expense.get(), HttpStatus.OK);
		}

		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<Expense> createExpense(Expense expense) {

		expenseCrudRepository.save(expense);

		return new ResponseEntity<>(expense, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Expense> updateExpense(Long id, Expense newExpense) {

		Optional<Expense> expense = expenseCrudRepository.findById(id);

		if(expense.isPresent()) {

			//TODO update expense with newExpense data

			expenseCrudRepository.save(newExpense);

			return new ResponseEntity<>(expense.get(), HttpStatus.OK);
		}

		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<Void> deleteExpense(Long id) {

		Optional<Expense> expense = expenseCrudRepository.findById(id);

		if(expense.isPresent()) {

			//TODO update expense with newExpense data

			expenseCrudRepository.deleteById(id);

			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@Override
	public List<ExpenseType> getExpenseTypeList() {

		return expenseRepository.findExpenseTypeList();
	}
}
