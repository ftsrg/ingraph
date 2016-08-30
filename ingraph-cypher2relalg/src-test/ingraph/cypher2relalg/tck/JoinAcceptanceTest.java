
package ingraph.cypher2relalg.tck;

import ingraph.cypher2relalg.RelalgParser;

import org.junit.Test;

public class JoinAcceptanceTest {
    
    @Test
    public void testJoinAcceptance01() {
        RelalgParser.parse("tck/JoinAcceptance01");
    }
    
    @Test
    public void testJoinAcceptance02() {
        RelalgParser.parse("tck/JoinAcceptance02");
    }
    
}
    