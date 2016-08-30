package ingraph.cypher2relalg.tck;

import org.junit.Test;

import ingraph.cypher2relalg.RelalgParser;

public class SkipLimitAcceptanceTest {
    
    @Test
    public void testSkipLimitAcceptance_01() {
        RelalgParser.parse("tck/SkipLimitAcceptance_01");
    }
    
    @Test
    public void testSkipLimitAcceptance_02() {
        RelalgParser.parse("tck/SkipLimitAcceptance_02");
    }
    
    @Test
    public void testSkipLimitAcceptance_03() {
        RelalgParser.parse("tck/SkipLimitAcceptance_03");
    }
    
    @Test
    public void testSkipLimitAcceptance_04() {
        RelalgParser.parse("tck/SkipLimitAcceptance_04");
    }
    
}
    