package ingraph.bulkloader.csv.data;

import java.util.Collections;
import java.util.Map;

public class CsvEdge extends CsvEntity {

	private final String type;
	private final Long sourceVertexId;
	private final String sourceVertexLabel;
	private final Long targetVertexId;
	private final String targetVertexLabel;

	public CsvEdge(final Long id, final String type,
								 final Long sourceVertexId, final String sourceVertexLabel,
								 final Long targetVertexId, final String targetVertexLabel) {
		super(id, Collections.emptyMap());
		this.type = type;
		this.sourceVertexId = sourceVertexId;
		this.sourceVertexLabel = sourceVertexLabel;
		this.targetVertexId = targetVertexId;
		this.targetVertexLabel = targetVertexLabel;
	}

	public CsvEdge(final Long id, final String type,
								 final Long sourceVertexId, final String sourceVertexLabel,
								 final Long targetVertexId, final String targetVertexLabel, final Map<String, ?> properties) {
		super(id, properties);
		this.type = type;
		this.sourceVertexId = sourceVertexId;
		this.sourceVertexLabel = sourceVertexLabel;
		this.targetVertexId = targetVertexId;
		this.targetVertexLabel = targetVertexLabel;
	}

	public String getType() {
		return type;
	}

	public Long getSourceVertexId() {
		return sourceVertexId;
	}

	public String getSourceVertexLabel() {
		return sourceVertexLabel;
	}

	public Long getTargetVertexId() {
		return targetVertexId;
	}

	public String getTargetVertexLabel() {
		return targetVertexLabel;
	}

}
