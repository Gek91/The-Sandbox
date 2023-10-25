package testExample.integration;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import testExample.model.Expense;
import testExample.service.ExpenseService;
import testExample.service.ExpenseServiceImpl;

import java.math.BigDecimal;

//define a new context configuration that override the normal one
@TestConfiguration
public class TestContextConfiguration {

	@Bean
	public ExpenseService expenseService() {
		return new ExpenseService() {

			public BigDecimal getExpenseAmount(Expense expense) {
				return BigDecimal.valueOf(100);
			}
		};
	}
}
