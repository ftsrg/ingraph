package ingraph.bulkloader.csv.entityprocessor;

import ingraph.bulkloader.csv.columnname.ColumnDescriptor;
import ingraph.bulkloader.csv.data.CsvEdge;

import java.util.Map;

import static ingraph.bulkloader.csv.columnname.ColumnConstants.INTERNAL_END_ID;
import static ingraph.bulkloader.csv.columnname.ColumnConstants.INTERNAL_START_ID;

public class EdgeRowParser extends EntityRowParser<CsvEdge> {

	private final IdGenerator idGenerator;

	public EdgeRowParser(final IdGenerator idGenerator) {
		super();
		this.idGenerator = idGenerator;
	}

	@Override
	public CsvEdge processRow(final Map<String, Object> row, final Map<String, ColumnDescriptor> columnDescriptors) {
		// ids
		final Long edgeId = idGenerator.generateNewId();
		final Long sourceVertexId = getId(row, INTERNAL_START_ID);
		final Long targetVertexId = getId(row, INTERNAL_END_ID);
		// properties
		final Map<String, Object> properties = PropertyExtractor.extractProperties(row);

		return new CsvEdge(sourceVertexId, edgeId, targetVertexId, properties);
	}

}
