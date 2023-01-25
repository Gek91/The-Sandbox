package jdbcExample.repository;

import jdbcExample.model.Expense;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class ExpenseRepositoryJdbcInsert {

	private SimpleJdbcInsert expenseSplitInserter;
	private JdbcTemplate jdbcTemplate;


	public ExpenseRepositoryJdbcInsert(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;

		this.expenseSplitInserter = new SimpleJdbcInsert(jdbcTemplate)
				.withTableName("expense_split");
	}
	
}
