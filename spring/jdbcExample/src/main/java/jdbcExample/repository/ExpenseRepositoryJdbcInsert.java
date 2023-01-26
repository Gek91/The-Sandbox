package jdbcExample.repository;

import jdbcExample.model.Expense;
import jdbcExample.model.ExpenseSplit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class ExpenseRepositoryJdbcInsert {

	private SimpleJdbcInsert expenseInserter;
	private SimpleJdbcInsert exepenseSplitInserter;

	@Autowired
	public ExpenseRepositoryJdbcInsert(DataSource datasource) {

		this.expenseInserter = new SimpleJdbcInsert(datasource)
				.withTableName("expense_data")
				.usingGeneratedKeyColumns("id");

		this.exepenseSplitInserter = new SimpleJdbcInsert(datasource)
			.withTableName("expense_split");
		;
	}

	public void addExpense(Expense expense) {

		SqlParameterSource parameterSource = new MapSqlParameterSource()
			.addValue("expense_date", expense.getExpenseDate())
			.addValue("creation_datetime", expense.getCreationDatetime())
			.addValue("modification_datetime", expense.getModificationDatetime())
			.addValue("type_id", expense.getType().getId());

		Number newId = this.expenseInserter.executeAndReturnKey(parameterSource);
		expense.setId(newId.longValue());
	}

	public void addExpenseSplit(ExpenseSplit split) {

		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(split);

		this.exepenseSplitInserter.execute(parameterSource);
	}

}
