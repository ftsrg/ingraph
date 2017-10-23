package ingraph.bulkloader.csv;

import com.google.common.collect.ImmutableMap;
import ingraph.bulkloader.csv.loader.MassCsvLoader;
import org.junit.Test;
import org.neo4j.driver.v1.types.Node;
import org.neo4j.driver.v1.types.Relationship;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TrainBenchmarkCsvTest {

	private static final String PREFIX = TestConstants.GRAPHS_DIR + "trainbenchmark/railway-inject-1-";
	private static final String POSTFIX = ".csv";

	final Map<String, Collection<String>> nodeFilenames = ImmutableMap.<String, Collection<String>>builder() //
			.put(PREFIX + "Region"         + POSTFIX, Arrays.asList("Region")) //
			.put(PREFIX + "Route"          + POSTFIX, Arrays.asList("Route")) //
			.put(PREFIX + "Segment"        + POSTFIX, Arrays.asList("Segment", "TrackElement")) //
			.put(PREFIX + "Semaphore"      + POSTFIX, Arrays.asList("Semaphore")) //
			.put(PREFIX + "Sensor"         + POSTFIX, Arrays.asList("Sensor")) //
			.put(PREFIX + "Switch"         + POSTFIX, Arrays.asList("Switch", "TrackElement")) //
			.put(PREFIX + "SwitchPosition" + POSTFIX, Arrays.asList("SwitchPosition")) //
			.build();

	final Map<String, String> relationshipFilenames = ImmutableMap.<String, String>builder() //
			.put(PREFIX + "connectsTo"  + POSTFIX, "connectsTo") //
			.put(PREFIX + "entry"       + POSTFIX, "entry") //
			.put(PREFIX + "exit"        + POSTFIX, "exit") //
			.put(PREFIX + "follows"     + POSTFIX, "follows") //
			.put(PREFIX + "monitoredBy" + POSTFIX, "monitoredBy") //
			.put(PREFIX + "requires"    + POSTFIX, "requires") //
			.put(PREFIX + "target"      + POSTFIX, "target") //
			.build();

	@Test
	public void testLoad() throws IOException {
		final MassCsvLoader mcl =  new MassCsvLoader(nodeFilenames, relationshipFilenames, CsvPreference.STANDARD_PREFERENCE);
		List<Node> nodes = mcl.getNodes();
		List<Relationship> relationships = mcl.getRelationships();

		assertEquals(471, nodes.size());
		assertEquals(1429, relationships.size());
	}

}
