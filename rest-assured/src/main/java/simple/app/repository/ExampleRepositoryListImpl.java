package simple.app.repository;

import org.springframework.stereotype.Repository;
import simple.app.model.Example;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ExampleRepositoryListImpl implements ExampleRepository {

	public final List<Example> repository;

	public ExampleRepositoryListImpl() {
		repository = new ArrayList<>();
		repository.add(
				new Example("one",1l, LocalDate.of(2023,9,10))
				);
		repository.add(
				new Example("two",2l, LocalDate.of(2023,9,12))
		);
		repository.add(
				new Example("three",3l, LocalDate.of(2023,9,8))
		);
	}
	@Override
	public Optional<Example> getExampleByName(String name) {
		return repository.stream().filter(x -> x.getName().equals(name)).findFirst();
	}

	@Override
	public List<Example> getExamples() {
		return repository;
	}

	@Override
	public void addExample(Example example) {
		repository.add(example);
	}

	@Override
	public void updateExample(Example example) {
		Optional<Example> exampleOptional = getExampleByName(example.getName());

		if(exampleOptional.isPresent()) {
			Example oldExample = exampleOptional.get();
			oldExample.setDate(example.getDate());
			oldExample.setValue(example.getValue());
		}
	}

	@Override
	public void deleteExample(String name) {
		Optional<Example> exampleOptional = getExampleByName(name);

		if(exampleOptional.isPresent()) {
			repository.remove(exampleOptional.get());
		}
	}
}
