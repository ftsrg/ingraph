package ingraph.bulkloader.csv;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.neo4j.driver.v1.types.Node;
import org.neo4j.driver.v1.types.Relationship;

import ingraph.bulkloader.csv.entityprocessor.NodeProcessor;
import ingraph.bulkloader.csv.entityprocessor.RelationshipProcessor;

public class LoaderTest {

	@Test
	public void test1() throws FileNotFoundException, IOException {
		NodeProcessor nodeProcessor = new NodeProcessor(Arrays.asList("Segment"));
		Collection<Node> nodes = CsvParser.parse("src/test/resources/trainbenchmark/railway-repair-1-Segment.csv",
				nodeProcessor);
		for (Node node : nodes) {
			System.out.println(PrettyPrinter.toString(node));
		}
	}

	@Test
	public void test2() throws FileNotFoundException, IOException {
		RelationshipProcessor relationshipProcessor = new RelationshipProcessor("CONNECTS_TO");
		Collection<Relationship> relationships = CsvParser
				.parse("src/test/resources/trainbenchmark/railway-repair-1-connectsTo.csv", relationshipProcessor);
		for (Relationship relationship : relationships) {
			System.out.println(PrettyPrinter.toString(relationship));
		}
	}

}
