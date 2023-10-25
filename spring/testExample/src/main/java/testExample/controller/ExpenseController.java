package testExample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import testExample.model.Expense;
import testExample.respository.ExpenseCrudRepository;
import testExample.service.ExpenseService;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
public class ExpenseController {

	@Autowired
	private ExpenseCrudRepository expenseRepository;

	@Autowired
	private ExpenseService expenseService;

	@GetMapping(path="/expenses/{id}", produces="application/json")
	public ResponseEntity<Expense> getExpenseById(@PathVariable("id") Long id) {

		Optional<Expense> optionalExpense = expenseRepository.findById(id);

		if(optionalExpense.isPresent()) {
			return new ResponseEntity<>(optionalExpense.get(), HttpStatus.OK);
		}

		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@GetMapping(path="/expenses/{id}/total-amount", produces="application/json")
	public ResponseEntity<BigDecimal> getExpenseAmount(@PathVariable("id") Long id) {

		Optional<Expense> optionalExpense = expenseRepository.findById(id);

		if(optionalExpense.isPresent()) {
			return new ResponseEntity<>(expenseService.getExpenseAmount(optionalExpense.get()), HttpStatus.OK);
		}

		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

}
