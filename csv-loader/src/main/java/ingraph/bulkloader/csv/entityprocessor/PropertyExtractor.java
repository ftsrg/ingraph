package ingraph.bulkloader.csv.entityprocessor;

import ingraph.bulkloader.csv.columnname.ColumnConstants;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class PropertyExtractor {

	public static Map<String, Object> extractProperties(final Map<String, Object> row) {
		final Map<String, Object> properties = new HashMap<>();
		for (Entry<String, Object> entry : row.entrySet()) {
			if (ColumnConstants.isInternal(entry.getKey())) {
				if (entry.getKey() == ColumnConstants.INTERNAL_ID) {
					properties.put("id", entry.getValue());
				}
			} else {
				properties.put(entry.getKey(), entry.getValue());
			}
		}
		return properties;
	}

}
