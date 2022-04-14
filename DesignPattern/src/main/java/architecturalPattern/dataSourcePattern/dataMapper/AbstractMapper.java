package architecturalPattern.dataSourcePattern.dataMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractMapper<T> {

	protected Map<Long, T> loadedMap = new HashMap<>();
	
	abstract protected String findStatement();
	abstract protected T doLoad(Long id, ResultSet rs);
	
	protected Object abstractFind(Long id) {
		
		Object result = loadedMap.get(id);

		if(result == null) {
			//db find logic
			
			ResultSet rs = null;
			try {
				rs.next();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			result = load(rs);
		}
		
		return result;
	}
	
	protected T load(ResultSet rs) {
		//build object logic
		Long id = null;
		
		T result = loadedMap.get(id);
		
		if(result == null) {
			result = doLoad(id, rs);
			loadedMap.put(id, result);
		}

		return result;
	}
	
	protected List<T> loadAll(ResultSet rs) throws SQLException {
		
		List<T> result = new ArrayList<>();
		while(rs.next()) {
			result.add(load(rs));
		}
		return result;
	}
}
