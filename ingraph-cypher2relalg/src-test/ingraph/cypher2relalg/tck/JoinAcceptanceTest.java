package ingraph.cypher2relalg.tck;

import org.junit.Test;

import ingraph.cypher2relalg.RelalgParser;

public class JoinAcceptanceTest {
    
    @Test
    public void testJoinAcceptance_01() {
        RelalgParser.parse("tck/JoinAcceptance_01");
    }
    
    @Test
    public void testJoinAcceptance_02() {
        RelalgParser.parse("tck/JoinAcceptance_02");
    }
    
}
    