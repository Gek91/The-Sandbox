package architecturalPattern.dataSourcePattern.dataMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PersonMapper extends AbstractMapper<Person> {

	public Person find(Long id) {
		return (Person) abstractFind(id);
	}
	
	public List<Person> findByLastName(String lastname) {
		
		List<Person> result = null;
		
		//db query logic
		ResultSet rs = null;
		try {
			result = loadAll(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	protected String findStatement() {
		// db find statement
		return "SELECT ... FROM ... WHERE ...";
	}

	@Override
	protected Person doLoad(Long id, ResultSet rs) {
		// create object from result set logic
		return null;
	}
}
