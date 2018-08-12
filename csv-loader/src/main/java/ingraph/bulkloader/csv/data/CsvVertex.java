package ingraph.bulkloader.csv.data;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class CsvVertex extends CsvEntity {

	private final Collection<String> customLabels;

	public CsvVertex(final Long id) {
		this(id, Collections.emptyList(), Collections.emptyMap());
	}

	public CsvVertex(final Long id, final Collection<String> customLabels) {
		this(id, customLabels, Collections.emptyMap());
	}

	public CsvVertex(final Long id, final Map<String, ?> properties) {
		this(id, Collections.emptyList(), properties);
	}

	public CsvVertex(final Long id, final Collection<String> customLabels, final Map<String, ?> properties) {
		super(id, properties);
		this.customLabels = customLabels;
	}

	public Collection<String> getCustomLabels() {
		return customLabels;
	}

}
