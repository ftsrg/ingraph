
package ingraph.cypher2relalg.tck;

import ingraph.cypher2relalg.RelalgParser;

import org.junit.Test;

public class SkipLimitAcceptanceTest {
    
    @Test
    public void testSkipLimitAcceptance01() {
        RelalgParser.parse("tck/SkipLimitAcceptance01");
    }
    
    @Test
    public void testSkipLimitAcceptance02() {
        RelalgParser.parse("tck/SkipLimitAcceptance02");
    }
    
    @Test
    public void testSkipLimitAcceptance03() {
        RelalgParser.parse("tck/SkipLimitAcceptance03");
    }
    
    @Test
    public void testSkipLimitAcceptance04() {
        RelalgParser.parse("tck/SkipLimitAcceptance04");
    }
    
}
    