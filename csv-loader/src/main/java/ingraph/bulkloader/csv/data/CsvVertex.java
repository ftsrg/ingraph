package ingraph.bulkloader.csv.data;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class CsvVertex extends CsvEntity {

	private final Collection<String> labels;

	public CsvVertex(final Long id, final Collection<String> labels) {
		super(id, Collections.emptyMap());
		this.labels = labels;
	}

	public CsvVertex(final Long id, final Collection<String> labels, final Map<String, ?> properties) {
		super(id, properties);
		this.labels = labels;
	}

	public Collection<String> getLabels() {
		return labels;
	}

}
