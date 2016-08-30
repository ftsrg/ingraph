
package ingraph.cypher2relalg.tck;

import ingraph.cypher2relalg.RelalgParser;

import org.junit.Test;

public class OptionalMatchTest {
    
    @Test
    public void testOptionalMatch01() {
        RelalgParser.parse("tck/OptionalMatch01");
    }
    
    @Test
    public void testOptionalMatch02() {
        RelalgParser.parse("tck/OptionalMatch02");
    }
    
    @Test
    public void testOptionalMatch03() {
        RelalgParser.parse("tck/OptionalMatch03");
    }
    
}
    