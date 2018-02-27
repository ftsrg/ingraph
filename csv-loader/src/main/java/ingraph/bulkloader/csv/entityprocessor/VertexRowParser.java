package ingraph.bulkloader.csv.entityprocessor;

import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;
import ingraph.bulkloader.csv.columnname.ColumnDescriptor;
import ingraph.bulkloader.csv.data.CsvVertex;
import ingraph.bulkloader.csv.idspaces.IdSpaces;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import static ingraph.bulkloader.csv.columnname.ColumnConstants.INTERNAL_ID;
import static ingraph.bulkloader.csv.columnname.ColumnConstants.INTERNAL_LABEL;

public class VertexRowParser extends EntityRowParser<CsvVertex> {

	private final Set<String> labels;

	public VertexRowParser(final IdSpaces idSpaces, final Set<String> labels) {
		super(idSpaces);
		this.labels = labels;
	}

	@Override
	public CsvVertex processRow(final Map<String, Object> row, final Map< String, ColumnDescriptor> columnDescriptors) {
		// id
		final long id = getId(row, columnDescriptors, INTERNAL_ID);
		// labels
		final Set<String> additionalLabels = (Set<String>) row.getOrDefault(INTERNAL_LABEL, Collections.emptySet());
		final SetView<String> allLabels = Sets.union(labels, additionalLabels);
		// properties
		final Map<String, Object> properties = PropertyExtractor.extractProperties(row);

		return new CsvVertex(id, allLabels, properties);
	}


}
