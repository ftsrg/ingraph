package ingraph.cypher2relalg;

import org.junit.Test;

public class RelalgParserTckTests {

	@Test
	public void test1() {
		RelalgParser.parse("tck/AggregationAcceptance-01");
	}

}
