package simple.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

@Data
@AllArgsConstructor
public class Example {

	private String name;
	private Long value;
	private LocalDate date;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Example example = (Example) o;
		return Objects.equals(name, example.name) && Objects.equals(value, example.value) && Objects.equals(date, example.date);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, value, date);
	}
}
