package ingraph.cypher2relalg.tck;

import org.junit.Test;

import ingraph.cypher2relalg.RelalgParser;

public class WhereAcceptanceTest {
    
    @Test
    public void testWhereAcceptance_01() {
        RelalgParser.parse("tck/WhereAcceptance_01");
    }
    
    @Test
    public void testWhereAcceptance_02() {
        RelalgParser.parse("tck/WhereAcceptance_02");
    }
    
}
    