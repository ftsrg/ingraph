package ingraph.bulkloader.csv;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;

public class LoaderTest {

	@Test
	public void test() throws FileNotFoundException, IOException {
		CsvParser parser = new CsvParser();
		parser.parse("src/test/resources/trainbenchmark/railway-repair-1-Segment.csv", Arrays.asList("Segment"));
	}

}
