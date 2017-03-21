package ingraph.bulkloader.csv.entityprocessor;

import java.util.Map;
import java.util.Set;

import org.neo4j.driver.internal.InternalNode;
import org.neo4j.driver.v1.Value;
import org.neo4j.driver.v1.types.Node;

import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;

import ingraph.bulkloader.csv.columnname.ColumnConstants;

public class NodeProcessor extends EntityProcessor<Node> {

	private final Set<String> labels;

	public NodeProcessor(Set<String> labels) {
		super();
		this.labels = labels;
	}

	@Override
	public Node processRow(final Map<String, Object> row) {
		final Long id = (Long) row.get(ColumnConstants.INTERNAL_ID);
		final Set<String> additionalLabels = (Set<String>) row.get(ColumnConstants.INTERNAL_LABEL);
		final Map<String, Value> properties = PropertyExtractor.extractProperties(row);

		final SetView<String> allLabels = Sets.union(labels, additionalLabels);

		return new InternalNode(id, allLabels, properties);
	}

}
