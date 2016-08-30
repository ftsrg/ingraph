package ingraph.cypher2relalg.tck;

import org.junit.Test;

import ingraph.cypher2relalg.RelalgParser;

public class StartingPointAcceptanceTest {
    
    @Test
    public void testStartingPointAcceptance_01() {
        RelalgParser.parse("tck/StartingPointAcceptance_01");
    }
    
    @Test
    public void testStartingPointAcceptance_02() {
        RelalgParser.parse("tck/StartingPointAcceptance_02");
    }
    
    @Test
    public void testStartingPointAcceptance_03() {
        RelalgParser.parse("tck/StartingPointAcceptance_03");
    }
    
}
    