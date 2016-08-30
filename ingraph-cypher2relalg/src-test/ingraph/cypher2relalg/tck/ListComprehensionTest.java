
package ingraph.cypher2relalg.tck;

import ingraph.cypher2relalg.RelalgParser;

import org.junit.Test;

public class ListComprehensionTest {
    
    @Test
    public void testListComprehension01() {
        RelalgParser.parse("tck/ListComprehension01");
    }
    
    @Test
    public void testListComprehension02() {
        RelalgParser.parse("tck/ListComprehension02");
    }
    
    @Test
    public void testListComprehension03() {
        RelalgParser.parse("tck/ListComprehension03");
    }
    
}
    