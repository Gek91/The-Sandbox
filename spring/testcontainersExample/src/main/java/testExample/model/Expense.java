package testExample.model;

import lombok.Data;

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
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private Instant creationDatetime;
	private Instant modificationDatetime;
	private LocalDate expenseDate;
	@ManyToOne
	private ExpenseType type;

	@OneToMany(mappedBy = "expenseId")
	private List<ExpenseSplit> splits;

}
