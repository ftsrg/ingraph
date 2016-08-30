
package ingraph.cypher2relalg.tck;

import ingraph.cypher2relalg.RelalgParser;

import org.junit.Test;

public class StartingPointAcceptanceTest {
    
    @Test
    public void testStartingPointAcceptance01() {
        RelalgParser.parse("tck/StartingPointAcceptance01");
    }
    
    @Test
    public void testStartingPointAcceptance02() {
        RelalgParser.parse("tck/StartingPointAcceptance02");
    }
    
    @Test
    public void testStartingPointAcceptance03() {
        RelalgParser.parse("tck/StartingPointAcceptance03");
    }
    
}
    