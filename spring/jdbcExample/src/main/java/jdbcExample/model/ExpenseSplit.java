package jdbcExample.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "expense_split")
public class ExpenseSplit implements Serializable {
	@Id
	private Long expenseId;
	@Id
	private LocalDate recordDate;

	private BigDecimal amount;
}
