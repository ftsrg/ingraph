package ingraph.bulkloader.csv.loader;

import com.google.common.collect.Lists;
import ingraph.bulkloader.csv.entityprocessor.NodeProcessor;
import ingraph.bulkloader.csv.entityprocessor.RelationshipProcessor;
import ingraph.bulkloader.csv.parser.CsvParser;
import org.neo4j.driver.internal.util.Iterables;
import org.neo4j.driver.v1.types.Node;
import org.neo4j.driver.v1.types.Relationship;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class MassCsvLoader {

	private final List<Node> nodes = new ArrayList<>();
	private final List<Relationship> relationships = new ArrayList<>();

	public MassCsvLoader(Map<String, Collection<String>> nodeFilenames, Map<String, String> relationshipFilenames) throws IOException {
		for (final Map.Entry<String, Collection<String>> node : nodeFilenames.entrySet()) {
			final String filename = node.getKey();
			final Collection<String> labels = Lists.newArrayList(node.getValue());

			final NodeProcessor nodeProcessor = new NodeProcessor(labels);
			nodes.addAll(CsvParser.parse(filename, nodeProcessor));
		}

		for (final Map.Entry<String, String> relationship : relationshipFilenames.entrySet()) {
			final String filename = relationship.getKey();
			final String type = relationship.getValue();

			final RelationshipProcessor relationshipProcessor = new RelationshipProcessor(type);
			relationships.addAll(CsvParser.parse(filename, relationshipProcessor));
		}
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public List<Relationship> getRelationships() {
		return relationships;
	}
}