package ingraph.bulkloader.csv;

import ingraph.bulkloader.csv.data.CsvVertex;
import ingraph.bulkloader.csv.loader.MassCsvLoader;
import org.junit.Assert;
import org.junit.Test;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.util.List;

public class CsvLabelTest {

	@Test
	public void test() throws IOException {
		final MassCsvLoader mcl = new MassCsvLoader(CsvPreference.STANDARD_PREFERENCE);
		List<CsvVertex> nodes = mcl.loadVertices("src/test/resources/data-with-labels.csv");
		Assert.assertEquals(1, nodes.size());
	}

}
