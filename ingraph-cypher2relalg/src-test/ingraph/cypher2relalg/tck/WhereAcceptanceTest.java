
package ingraph.cypher2relalg.tck;

import ingraph.cypher2relalg.RelalgParser;

import org.junit.Test;

public class WhereAcceptanceTest {
    
    @Test
    public void testWhereAcceptance01() {
        RelalgParser.parse("tck/WhereAcceptance01");
    }
    
    @Test
    public void testWhereAcceptance02() {
        RelalgParser.parse("tck/WhereAcceptance02");
    }
    
}
    