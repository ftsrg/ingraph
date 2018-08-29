package ingraph.bulkloader.csv.entityprocessor;

import ingraph.bulkloader.csv.columnname.ColumnDescriptor;
import ingraph.bulkloader.csv.data.CsvVertex;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static ingraph.bulkloader.csv.columnname.ColumnConstants.INTERNAL_ID;
import static ingraph.bulkloader.csv.columnname.ColumnConstants.INTERNAL_LABEL;

public class VertexRowParser extends EntityRowParser<CsvVertex> {

	@Override
	public CsvVertex processRow(final Map<String, Object> row, final Map< String, ColumnDescriptor> columnDescriptors) {
		final long id = getId(row, INTERNAL_ID);
		final List<String> customLabels = (List<String>) row.getOrDefault(INTERNAL_LABEL, Collections.emptyList());
		final Map<String, Object> properties = PropertyExtractor.extractProperties(row);
		return new CsvVertex(id, customLabels, properties);
	}


}
