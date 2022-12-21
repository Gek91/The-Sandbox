package springExample.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name="expense_data")
public class Expense {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	private Instant creationDatetime;
	private Instant modificationDatetime;
	private LocalDate expenseDate;
	@ManyToOne
	private ExpenseType type;

	@OneToMany(mappedBy = "expenseId")
	private List<ExpenseSplit> splits;

	public BigDecimal getAmount() {

		BigDecimal result = new BigDecimal(0);

		for (ExpenseSplit split : splits) {
			result = result.add(split.getAmount());
		}

		return result;
	}
}
