package ingraph.bulkloader.csv.data;

import java.util.Collections;
import java.util.Map;

public class CsvEdge extends CsvEntity {

	private final Long sourceVertexId;
	private final Long targetVertexId;

	public CsvEdge(final Long sourceVertexId, final Long id, final Long targetVertexId) {
		this(sourceVertexId, id, targetVertexId, Collections.emptyMap());
	}

	public CsvEdge(final Long sourceVertexId, final Long id, final Long targetVertexId, final Map<String, ?> properties) {
		super(id, properties);
		this.sourceVertexId = sourceVertexId;
		this.targetVertexId = targetVertexId;
	}

	public Long getSourceVertexId() {
		return sourceVertexId;
	}

	public Long getTargetVertexId() {
		return targetVertexId;
	}

}
