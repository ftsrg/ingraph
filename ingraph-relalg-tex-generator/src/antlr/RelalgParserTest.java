package antlr;

import org.junit.Test;

import java.io.IOException;

public class RelalgParserTest {

    @Test
    public void testExample() throws IOException {
        RelalgParser.parse("Example");
    }

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
