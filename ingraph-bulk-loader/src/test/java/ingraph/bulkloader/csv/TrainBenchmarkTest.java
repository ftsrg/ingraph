package ingraph.bulkloader.csv;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;
import org.neo4j.driver.v1.types.Node;
import org.neo4j.driver.v1.types.Relationship;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import ingraph.bulkloader.csv.entityprocessor.NodeProcessor;
import ingraph.bulkloader.csv.entityprocessor.RelationshipProcessor;

public class TrainBenchmarkTest {

	private static final String PREFIX = "src/test/resources/trainbenchmark/railway-repair-1-";
	private static final String POSTFIX = ".csv";

	final Map<String, Collection<String>> nodeFilenames = ImmutableMap.<String, Collection<String>>builder() //
			.put("Region", Arrays.asList("Region")) //
			.put("Route", Arrays.asList("Route")) //
			.put("Segment", Arrays.asList("Segment", "TrackElement")) //
			.put("Semaphore", Arrays.asList("Semaphore")) //
			.put("Sensor", Arrays.asList("Sensor")) //
			.put("Switch", Arrays.asList("Switch", "TrackElement")) //
			.put("SwitchPosition", Arrays.asList("SwitchPosition")) //
			.build();

	final List<String> relationshipFilenames = ImmutableList.of("connectsTo", "entry", "exit", "follows", "monitoredBy",
			"requires", "target");

	@Test
	public void testNodes() throws FileNotFoundException, IOException {
		for (final Entry<String, Collection<String>> node : nodeFilenames.entrySet()) {
			final String filename = node.getKey();
			final Collection<String> labels = node.getValue();

			final NodeProcessor nodeProcessor = new NodeProcessor(labels);
			final List<Node> nodes = CsvParser.parse(PREFIX + filename + POSTFIX, nodeProcessor);

			for (int i = 0; i < Math.min(10, nodes.size()); i++) {
				System.out.println(PrettyPrinter.toString(nodes.get(i)));
			}
		}
	}

	@Test
	public void testRelationships() throws FileNotFoundException, IOException {
		for (String relationship : relationshipFilenames) {
			final RelationshipProcessor relationshipProcessor = new RelationshipProcessor(relationship);
			final List<Relationship> relationships = CsvParser.parse(PREFIX + relationship + POSTFIX, relationshipProcessor);

			for (int i = 0; i < Math.min(10, relationships.size()); i++) {
				System.out.println(PrettyPrinter.toString(relationships.get(i)));
			}
		}
	}

}
