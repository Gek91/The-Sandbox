package jdbcExample.repository;

import jdbcExample.model.ExpenseSplit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import jdbcExample.model.Expense;
import jdbcExample.model.ExpenseType;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class ExpenseRepositoryJdbcTemplate {

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public ExpenseRepositoryJdbcTemplate(DataSource dataSource) {
		this.jdbcTemplate =  new JdbcTemplate(dataSource);;
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	//COUNT
	public int getExpensesCount() {

		String query = "" +
				"SELECT count(*) " +
				"FROM expense_data ";

		return this.jdbcTemplate.queryForObject(query, Integer.class);
	}

	//GET with row mapper
	public Expense getExpenseById(Long id) {

		String query = "" +
				"SELECT * " +
				"FROM expense_data " +
				"JOIN expense_type ON expense_type.id = expense_data.type_id " +
				"WHERE expense_data.id = ?";

		return this.jdbcTemplate.queryForObject(query,
				//rowMapper
				(resultSet, rowNum)-> {
					return mapRowToExpense(resultSet);
				}, id);
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

	private ExpenseSplit mapRowToExpenseSplit(ResultSet resultSet) throws SQLException {

		ExpenseSplit result = new ExpenseSplit();
		result.setExpenseId(resultSet.getLong("expense_id"));
		result.setAmount(new BigDecimal(resultSet.getLong("amount")));
		result.setRecordDate(resultSet.getDate("record_date").toLocalDate());

		return result;
	}

	//LIST with row manager
	public List<ExpenseSplit> getExpenseExpenseSplit(Long expenseId) {

		String query = "" +
				"SELECT * " +
				"FROM expense_split " +
				"WHERE expense_id = ?";

		return this.jdbcTemplate.query(query,
				//rowMapper
				(resultSet, rowNum)-> {
					return mapRowToExpenseSplit(resultSet);
				}, expenseId);
	}

	//LIST with ResultSetExtractor
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

	//INSERT
	public void insertExpenseSplit(ExpenseSplit splitData)  {

		String query = "" +
				"INSERT INTO expense_split " +
				"(expense_id,record_date,`amount`)" +
				"values (?,?,?)";

		this.jdbcTemplate.update(query,
				splitData.getExpenseId(),
				splitData.getRecordDate(),
				splitData.getAmount());
	}

	//UPDATE
	public void updateExpenseSplitAmount(ExpenseSplit splitDataToUpdate) {

		String query = "" +
				"UPDATE expense_split " +
				"SET `amount` = ? " +
				"WHERE expense_id = ? and record_date = ?";

		this.jdbcTemplate.update(query,
				splitDataToUpdate.getAmount(),
				splitDataToUpdate.getExpenseId(),
				splitDataToUpdate.getRecordDate());
	}

	//DELETE
	public void deleteExpenseSplint(Long expenseId, LocalDate splitDate) {

		String query = "" +
				"DELETE FROM expense_split " +
				"WHERE expense_id = ? and record_date = ?";

		this.jdbcTemplate.update(query,
				expenseId,
				splitDate);
	}

	//Use namedParameterJdbcTemplate
	public ExpenseType getExpenseTypeByName(String name) {
		String query = "" +
				"SELECT * " +
				"FROM expense_type " +
				"WHERE name = :name";

		Map<String, String> namedParameters = Collections.singletonMap("name", name);

		return this.namedParameterJdbcTemplate.queryForObject(query,
				namedParameters,
				(resultSet, rowNum)-> {
					return mapRowToExpenseType(resultSet);
				});
	}

	//UPDATE with named parameter
	public void updateExpenseSplitAmountWithNamedParameter(ExpenseSplit splitDataToUpdate) {

		String query = "" +
				"UPDATE expense_split " +
				"SET `amount` = :amount " +
				"WHERE expense_id = :expenseId and record_date = :recordDate";

		SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(splitDataToUpdate);

		this.namedParameterJdbcTemplate.update(query,
				namedParameters);
	}

	//INSERT that return key generated
	public void insertExpense(Expense expense) {


		String query = "" +
				"INSERT INTO expense_data " +
				"(id, expense_date, creation_datetime, modification_datetime, type_id) " +
				"values (:id, :expenseDate, :creationDatetime, :modificationDatetime, :type.id)"; //NB:type.id!!!

		KeyHolder keyHolder = new GeneratedKeyHolder();

		SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(expense);

		this.namedParameterJdbcTemplate.update(query, namedParameters, keyHolder, new String[] { "id" });

		expense.setId(keyHolder.getKey().longValue());
	}

	//batch update
	public void batchUpdateExpensesSplitValues(List<ExpenseSplit> splits) {

		String query = "" +
				"UPDATE expense_split " +
				"SET `amount` = :amount " +
				"WHERE expense_id = :expenseId and record_date = :recordDate";

		this.namedParameterJdbcTemplate.batchUpdate(query,
				SqlParameterSourceUtils.createBatch(splits));
	}

}
