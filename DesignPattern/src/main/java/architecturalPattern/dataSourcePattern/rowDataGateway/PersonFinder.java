package architecturalPattern.dataSourcePattern.rowDataGateway;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonFinder {

	public PersonGateway find(Long id) {
		PersonGateway result = null;
		//result = Registry.getPerson(Id); //registry caching
		if(result == null) {
			//db find logic
			ResultSet rs = null;
			try {
				rs.next();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			result = PersonGateway.load(rs);
		}
		return result;
	}

	
	public List<PersonGateway> findResponsibles() {
		
		List<PersonGateway> result = new ArrayList<>();
		//db list find logic
		ResultSet rs = null;
		
		try {
			while(rs.next()) {
				result.add(PersonGateway.load(null));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
}