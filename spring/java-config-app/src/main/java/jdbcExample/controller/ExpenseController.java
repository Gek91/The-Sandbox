package jdbcExample.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jdbcExample.model.Expense;
import jdbcExample.model.ExpenseType;

import java.util.List;

@RestController
public interface ExpenseController {

	//@RequestMapping(method = RequestMethod.GET, path = "/{id}") //same as GetMapping
	@GetMapping(path="/my-expenses/{id}", produces="application/json")
	ResponseEntity<Expense> getExpenseById(@PathVariable("id") Long id);

	@PostMapping(path="/my-expenses/{id}", consumes="application/json", produces="application/json")
	ResponseEntity<Expense> createExpense(@RequestBody Expense expense);

	@PutMapping(path="/my-expenses/{id}", consumes="application/json", produces="application/json")
	ResponseEntity<Expense> updateExpense(@PathVariable("id") Long id, @RequestBody Expense expense);

	@DeleteMapping(path="/my-expenses/{id}", produces="application/json")
	ResponseEntity<Void> deleteExpense(@PathVariable("id") Long id);

	@GetMapping(path="/my-expenses/types", produces="application/json")
	List<ExpenseType> getExpenseTypeList();
}
