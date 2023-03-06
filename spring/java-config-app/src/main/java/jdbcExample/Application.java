package jdbcExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import jdbcExample.controller.ExpenseController;
import jdbcExample.controller.impl.ExpenseControllerImpl;
import jdbcExample.repository.ExpenseCrudRepository;
import jdbcExample.repository.ExpenseRepository;
import jdbcExample.repository.impl.ExpenseRepositoryJdbcImpl;

//application entrypoint
@SpringBootApplication
public class Application {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private ExpenseCrudRepository expenseCrudRepository;

	@Bean
	public ExpenseRepository expenseRepository() {
		return new ExpenseRepositoryJdbcImpl(jdbcTemplate);
	}

	@Bean
	public ExpenseController expenseController() {
		return new ExpenseControllerImpl(expenseRepository(), expenseCrudRepository);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
