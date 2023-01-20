package springAppExample.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import springAppExample.model.Expense;
import springAppExample.model.ExpenseSplit;
import springAppExample.model.ExpenseType;
import springAppExample.repository.ExpenseRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//uses jdbctemplate to implements repository
@Repository
public class ExpenseRepositoryJdbcImpl implements ExpenseRepository {

	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert expenseSplitInserter;

	@Autowired
	public ExpenseRepositoryJdbcImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.expenseSplitInserter = new SimpleJdbcInsert(jdbcTemplate)
					.withTableName("expense_split");
	}


	@Override
	public Expense find(Long id) {

		String query = "" +
				"SELECT * " +
				"FROM expense_data " +
				"JOIN expense_type ON expense_type.id = expense_data.type_id " +
				"WHERE expense_data.id = ?";

		return jdbcTemplate.query(query, new ResultSetExtractor<Expense>() {
			@Override
			public Expense extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				return rs.next() ? mapRowToExpense(rs) : null;
			}
		}, id);
	}

	@Override
	public List<ExpenseType> findExpenseTypeList() {

		String query = "" +
				"SELECT * " +
				"FROM expense_type";

		return jdbcTemplate.query(query, new ResultSetExtractor<List<ExpenseType>>() {
			@Override
			public List<ExpenseType> extractData(ResultSet rs) throws SQLException,
					DataAccessException {

				List<ExpenseType> result = new ArrayList<>();

					while(rs.next()) {
						result.add(mapRowToExpenseType(rs));
					}

				return result;
			}
		});
	}

	private Expense mapRowToExpense(ResultSet resultSet) throws SQLException {

		Expense result = new Expense();
		result.setExpenseDate(resultSet.getDate("expense_data.expense_date").toLocalDate());
		result.setCreationDatetime(resultSet.getTimestamp("expense_data.creation_datetime").toInstant());
		result.setId(resultSet.getLong("expense_data.id"));
		result.setType(mapRowToExpenseType(resultSet));
		result.setModificationDatetime(resultSet.getTimestamp("expense_data.modification_datetime").toInstant());

		return result;
	}

	private ExpenseType mapRowToExpenseType(ResultSet resultSet) throws SQLException {

		ExpenseType result = null;

		Long id = resultSet.getLong("expense_type.id");

		if(id != null) {
			result = new ExpenseType(
					id,
					resultSet.getString("expense_type.name")
					);
		}

		return result;
	}

	//JdbcTable insert example
	@Override
	public void save(Expense expense) {

		String insertQuery = "" +
				"INSERT INTO expense_data " +
				"(id, expense_date, creation_datetime, modification_datetime, type_id) " +
				"values" +
				"(?, ?, ?, ?, ?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(insertQuery,
				expense.getId(),
				expense.getExpenseDate(),
				expense.getCreationDatetime(),
				expense.getModificationDatetime(),
				expense.getType().getId(),
				keyHolder
		);

		expense.setId(keyHolder.getKey().longValue());

		saveExpenseSplits(expense.getId(), expense.getSplits());
	}

	//SimpleJdbcInsert example
	private void saveExpenseSplits(Long expenseId, List<ExpenseSplit> splits) {

		MapSqlParameterSource[] paramsList =
				splits.stream().map(split ->
							new MapSqlParameterSource()
						.addValue("expense_id", expenseId)
						.addValue("record_date", split.getRecordDate())
						.addValue("amount", split.getAmount())
				).toArray(MapSqlParameterSource[]::new);

		this.expenseSplitInserter.executeBatch(paramsList);
	}
}
