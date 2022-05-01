package basic;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;

public class ConfigurationExample {

	public static void main(String... args) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
				.setMethodAccessLevel(Configuration.AccessLevel.PROTECTED); //set fields and method access level to protected

		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); //set matching strategy to strict

	}
}
