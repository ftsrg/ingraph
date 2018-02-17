package ingraph.bulkloader.csv;

import com.google.common.collect.ImmutableList;
import ingraph.bulkloader.csv.loader.LdbcUpdateStreamCsvLoader;
import org.junit.Test;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class LdbcSnbUpdateStreamCsvTest {

	public static final String PREFIX = "src/test/resources/updateStream_0_0_";
	public static final String POSTFIX = ".csv";
	public static final CsvPreference LDBC_CSV_PREFERENCE =
			new CsvPreference.Builder('"', '|', "\n").build();

	final List<String> updateStreamCsvs = ImmutableList.of("forum", "person").stream().map(e -> PREFIX + e + POSTFIX)
		.collect(Collectors.toList());

	@Test
	public void testLoad() throws IOException {
		final LdbcUpdateStreamCsvLoader mcl = new LdbcUpdateStreamCsvLoader(updateStreamCsvs, LDBC_CSV_PREFERENCE);

	}

}
