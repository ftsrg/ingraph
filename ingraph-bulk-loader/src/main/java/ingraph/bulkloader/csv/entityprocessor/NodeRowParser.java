package ingraph.bulkloader.csv.entityprocessor;

import static ingraph.bulkloader.csv.columnname.ColumnConstants.INTERNAL_ID;
import static ingraph.bulkloader.csv.columnname.ColumnConstants.INTERNAL_LABEL;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.neo4j.driver.internal.InternalNode;
import org.neo4j.driver.v1.Value;
import org.neo4j.driver.v1.types.Node;

import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;

import ingraph.bulkloader.csv.columnname.ColumnDescriptor;
import ingraph.bulkloader.csv.data.IdSpaces;

public class NodeRowParser extends EntityRowParser<Node> {

	private final Set<String> labels;

	public NodeRowParser(final IdSpaces idSpaces, final Set<String> labels) {
		super(idSpaces);
		this.labels = labels;
	}

	@Override
	public Node processRow(final Map<String, Object> row, final Map< String, ColumnDescriptor> columnDescriptors) {
		// id
		final long id = getId(row, columnDescriptors, INTERNAL_ID);
		// labels
		final Set<String> additionalLabels = (Set<String>) row.getOrDefault(INTERNAL_LABEL, Collections.emptySet());
		final SetView<String> allLabels = Sets.union(labels, additionalLabels);
		// properties
		final Map<String, Value> properties = PropertyExtractor.extractProperties(row);

		return new InternalNode(id, allLabels, properties);
	}


}
