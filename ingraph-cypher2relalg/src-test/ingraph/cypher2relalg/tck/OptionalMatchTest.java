package ingraph.cypher2relalg.tck;

import org.junit.Test;

import ingraph.cypher2relalg.RelalgParser;

public class OptionalMatchTest {
    
    @Test
    public void testOptionalMatch_01() {
        RelalgParser.parse("tck/OptionalMatch_01");
    }
    
    @Test
    public void testOptionalMatch_02() {
        RelalgParser.parse("tck/OptionalMatch_02");
    }
    
    @Test
    public void testOptionalMatch_03() {
        RelalgParser.parse("tck/OptionalMatch_03");
    }
    
}
    