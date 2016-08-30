package ingraph.cypher2relalg.trainbenchmark;

import java.io.IOException;

import org.junit.Test;

import ingraph.cypher2relalg.RelalgParser;

public class RelalgParserTrainBencharkTest {

	@Test
	public void testConnectedSegments() throws IOException {
		RelalgParser.parse("trainbenchmark/ConnectedSegments");
	}

	@Test
	public void testPosLength() throws IOException {
		RelalgParser.parse("trainbenchmark/PosLength");
	}

	@Test
	public void testRouteSensor() throws IOException {
		RelalgParser.parse("trainbenchmark/RouteSensor");
	}

	@Test
	public void testSwitchMonitored() throws IOException {
		RelalgParser.parse("trainbenchmark/SwitchMonitored");
	}

	@Test
	public void testSwitchSet() throws IOException {
		RelalgParser.parse("trainbenchmark/SwitchSet");
	}

	@Test
	public void testSemaphoreNeighbor() throws IOException {
		RelalgParser.parse("trainbenchmark/SemaphoreNeighbor");
	}

}
