package ingraph.bulkloader.csv;

import ingraph.bulkloader.csv.loader.LdbcUpdateStreamCsvLoader;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class LdbcSnbUpdateStreamCsvTest {

	public static final String CSV_DIR = TestConstants.GRAPHS_DIR + "ldbc-snb-bi/csv-sftiny/";

	@Test
	public void testLoad() throws IOException {
		final LdbcUpdateStreamCsvLoader loader = new LdbcUpdateStreamCsvLoader(CSV_DIR);
		Assert.assertEquals(7, loader.getUpdates().size());
	}

}
