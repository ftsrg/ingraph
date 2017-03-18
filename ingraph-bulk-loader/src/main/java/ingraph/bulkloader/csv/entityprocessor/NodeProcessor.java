package ingraph.bulkloader.csv.entityprocessor;

import java.util.Collection;
import java.util.Map;

import org.neo4j.driver.internal.InternalNode;
import org.neo4j.driver.v1.Value;
import org.neo4j.driver.v1.types.Node;

import ingraph.bulkloader.csv.columnname.ColumnConstants;

public class NodeProcessor extends EntityProcessor<Node> {

	private final Collection<String> labels;

	public NodeProcessor(Collection<String> labels) {
		super();
		this.labels = labels;
	}

	@Override
	public Node processRow(final Map<String, Object> row) {
		final Long id = (Long) row.get(ColumnConstants.INTERNAL_ID);
		final Map<String, Value> properties = PropertyExtractor.extractProperties(row);

		return new InternalNode(id, labels, properties);
	}

}
