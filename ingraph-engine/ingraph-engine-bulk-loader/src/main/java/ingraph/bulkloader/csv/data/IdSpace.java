package ingraph.bulkloader.csv.data;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class IdSpace {

	private BiMap<Object, Long> idSpace = HashBiMap.create();

	public Long add(final Object o, final Long currentId) {
		idSpace.putIfAbsent(o, currentId + 1);
		return idSpace.get(o);
	}

	public Object get(Long id) {
		return idSpace.inverse().get(id);
	}

}
