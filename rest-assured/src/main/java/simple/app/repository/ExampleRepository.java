package simple.app.repository;

import simple.app.model.Example;

import java.util.List;
import java.util.Optional;

public interface ExampleRepository {

	Optional<Example> getExampleByName(String name);

	List<Example> getExamples();

	void addExample(Example example);

	void updateExample(Example example);

	void deleteExample(String name);
}
