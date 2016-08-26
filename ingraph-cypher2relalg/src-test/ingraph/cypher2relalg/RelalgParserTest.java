package ingraph.cypher2relalg;

import java.io.IOException;

import org.junit.Test;

public class RelalgParserTest {

	@Test
	public void testConnectedSegments() throws IOException {
		RelalgParser.parse("ConnectedSegments");
	}

	@Test
	public void testPosLength() throws IOException {
		RelalgParser.parse("PosLength");
	}

	@Test
	public void testRouteSensor() throws IOException {
		RelalgParser.parse("RouteSensor");
	}

	@Test
	public void testSwitchMonitored() throws IOException {
		RelalgParser.parse("SwitchMonitored");
	}

	@Test
	public void testSwitchSet() throws IOException {
		RelalgParser.parse("SwitchSet");
	}

	@Test
	public void testSemaphoreNeighbor() throws IOException {
		RelalgParser.parse("SemaphoreNeighbor");
	}

}
