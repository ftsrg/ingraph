package ingraph.bulkloader.csv.entityprocessor;

import ingraph.bulkloader.csv.columnname.ColumnConstants;
import org.neo4j.driver.v1.Value;
import org.neo4j.driver.v1.Values;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class PropertyExtractor {

	public static Map<String, Value> extractProperties(final Map<String, Object> row) {
		final Map<String, Value> properties = new HashMap<>();
		for (Entry<String, Object> entry : row.entrySet()) {
			if (ColumnConstants.isInternal(entry.getKey())) {
				if (entry.getKey() == ColumnConstants.INTERNAL_ID) {
					properties.put("id", Values.value(entry.getValue()));
				}
			} else {
				properties.put(entry.getKey(), Values.value(entry.getValue()));
			}
		}
		return properties;
	}

}
