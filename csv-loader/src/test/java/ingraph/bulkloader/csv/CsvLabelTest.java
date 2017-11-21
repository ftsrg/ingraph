package ingraph.bulkloader.csv;

import com.google.common.collect.ImmutableMap;
import ingraph.bulkloader.csv.loader.MassCsvLoader;
import neo4j.driver.util.GraphPrettyPrinter;
import org.junit.Test;
import org.neo4j.driver.v1.types.Node;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CsvLabelTest {

	@Test
	public void test() throws IOException {
		Map<String, Collection<String>> nodeFilenames = ImmutableMap.<String, Collection<String>>builder() //
				.put("src/test/resources/data-with-labels.csv", Arrays.asList()) //
				.build();
		final MassCsvLoader mcl = new MassCsvLoader(nodeFilenames, ImmutableMap.of(), CsvPreference.STANDARD_PREFERENCE);
		List<Node> nodes = mcl.getNodes();
		System.out.println(GraphPrettyPrinter.toString(nodes));
	}

}
