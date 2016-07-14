import cypher.main.MyParser;
import org.junit.Test;

import java.io.IOException;

public class CypherParserTest {

    @Test
    public void testExample() throws IOException {
        MyParser.parse("Example");
    }

    @Test
    public void testConnectedSegments() throws IOException {
        MyParser.parse("ConnectedSegments");
    }

    @Test
    public void testPosLength() throws IOException {
        MyParser.parse("PosLength");
    }

    @Test
    public void testRouteSensor() throws IOException {
        MyParser.parse("RouteSensor");
    }

    @Test
    public void testSwitchMonitored() throws IOException {
        MyParser.parse("SwitchMonitored");
    }

    @Test
    public void testSwitchSet() throws IOException {
        MyParser.parse("SwitchSet");
    }

    @Test
    public void testSemaphoreNeighbor() throws IOException {
        MyParser.parse("SemaphoreNeighbor");
    }

}
