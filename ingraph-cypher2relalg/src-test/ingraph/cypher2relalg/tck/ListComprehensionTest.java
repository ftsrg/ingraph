package ingraph.cypher2relalg.tck;

import org.junit.Test;

import ingraph.cypher2relalg.RelalgParser;

public class ListComprehensionTest {
    
    @Test
    public void testListComprehension_01() {
        RelalgParser.parse("tck/ListComprehension_01");
    }
    
    @Test
    public void testListComprehension_02() {
        RelalgParser.parse("tck/ListComprehension_02");
    }
    
    @Test
    public void testListComprehension_03() {
        RelalgParser.parse("tck/ListComprehension_03");
    }
    
}
    