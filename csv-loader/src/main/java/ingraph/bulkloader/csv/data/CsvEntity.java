package ingraph.bulkloader.csv.data;

import java.util.Map;

public abstract class CsvEntity {

	private final Long id;
	private final Map<String, ?> properties;

	public CsvEntity(Long id, Map<String, ?> properties) {
		this.id = id;
		this.properties = properties;
	}

	public Long getId() {
		return id;
	}

	public Map<String, ?> getProperties() {
		return properties;
	}

}
