package ingraph.bulkloader.csv.data;

import java.util.HashMap;
import java.util.Map;

public class IdSpaces {

	private Map<String, IdSpace> idSpaces = new HashMap<>();
	private Long currentId = 0L;

	public Long add(String idSpaceName, Object o) {
		idSpaces.putIfAbsent(idSpaceName, new IdSpace());
		currentId = idSpaces.get(idSpaceName).add(o, currentId);
		return currentId;
	}

	public Object get(String idSpaceName, Long id) {
		return idSpaces.get(idSpaceName).get(id);
	}
}
