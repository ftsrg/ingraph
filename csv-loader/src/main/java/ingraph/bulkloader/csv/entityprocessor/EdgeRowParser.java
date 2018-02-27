package ingraph.bulkloader.csv.entityprocessor;

import ingraph.bulkloader.csv.columnname.ColumnDescriptor;
import ingraph.bulkloader.csv.data.CsvEdge;
import ingraph.bulkloader.csv.idspaces.IdSpaces;
import org.apache.commons.lang3.tuple.Triple;

import java.util.Map;

import static ingraph.bulkloader.csv.columnname.ColumnConstants.INTERNAL_END_ID;
import static ingraph.bulkloader.csv.columnname.ColumnConstants.INTERNAL_START_ID;

public class EdgeRowParser extends EntityRowParser<CsvEdge> {

	private final IdGenerator idGenerator;
	private final String sourceVertexLabel;
	private final String edgeType;
	private final String targetVertexLabel;

	public EdgeRowParser(final IdSpaces idSpaces, final Triple<String, String, String> type, final IdGenerator idGenerator) {
		super(idSpaces);
		this.sourceVertexLabel = type.getLeft();
		this.edgeType          = type.getMiddle();
		this.targetVertexLabel = type.getRight();
		this.idGenerator = idGenerator;
	}

	@Override
	public CsvEdge processRow(final Map<String, Object> row, final Map<String, ColumnDescriptor> columnDescriptors) {
		// ids
		final Long edgeId = idGenerator.generateNewId();
		final Long sourceVertexId = getId(row, columnDescriptors, INTERNAL_START_ID);
		final Long targetVertexId = getId(row, columnDescriptors, INTERNAL_END_ID);
		// properties
		final Map<String, Object> properties = PropertyExtractor.extractProperties(row);

		return new CsvEdge(edgeId, edgeType, sourceVertexId, sourceVertexLabel, targetVertexId, targetVertexLabel, properties);
	}

}
