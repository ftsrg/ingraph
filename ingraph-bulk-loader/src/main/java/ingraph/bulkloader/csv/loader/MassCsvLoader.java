package ingraph.bulkloader.csv.loader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.neo4j.driver.v1.types.Node;
import org.neo4j.driver.v1.types.Relationship;
import org.supercsv.prefs.CsvPreference;

import com.google.common.collect.Sets;

import ingraph.bulkloader.csv.entityprocessor.NodeRowParser;
import ingraph.bulkloader.csv.entityprocessor.RelationshipRowParser;
import ingraph.bulkloader.csv.parser.CsvParser;

public class MassCsvLoader {

	private final List<Node> nodes = new ArrayList<>();
	private final List<Relationship> relationships = new ArrayList<>();
	private final Map<String, Map<Object, Long>> idSpaces = new HashMap<>();

	public MassCsvLoader(Map<String, Collection<String>> nodeFilenames, Map<String, String> relationshipFilenames)
			throws IOException {
		this(nodeFilenames, relationshipFilenames, CsvPreference.STANDARD_PREFERENCE);
	}

	public MassCsvLoader(Map<String, Collection<String>> nodeFilenames, Map<String, String> relationshipFilenames,
			CsvPreference csvPreference) throws IOException {
		loadNodes(nodeFilenames, csvPreference);
		loadRelationships(relationshipFilenames, csvPreference);
	}

	private void loadNodes(Map<String, Collection<String>> nodeFilenames, CsvPreference csvPreference)
			throws IOException {
		for (final Map.Entry<String, Collection<String>> nodeFileEntry : nodeFilenames.entrySet()) {
			final String filename = nodeFileEntry.getKey();
			final Set<String> labels = Sets.newHashSet(nodeFileEntry.getValue());

			final NodeRowParser nodeProcessor = new NodeRowParser(labels);
			nodes.addAll(CsvParser.parse(filename, csvPreference, nodeProcessor));
		}
	}

	private void loadRelationships(Map<String, String> relationshipFilenames, CsvPreference csvPreference)
			throws IOException {
		for (final Map.Entry<String, String> relationshipFileEntry : relationshipFilenames.entrySet()) {
			final String filename = relationshipFileEntry.getKey();
			final String type = relationshipFileEntry.getValue();

			final RelationshipRowParser relationshipProcessor = new RelationshipRowParser(type);
			relationships.addAll(CsvParser.parse(filename, csvPreference, relationshipProcessor));
		}
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public List<Relationship> getRelationships() {
		return relationships;
	}
}
