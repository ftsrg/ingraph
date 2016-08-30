package ingraph.cypher2relalg.tck;

import org.junit.Test;

import ingraph.cypher2relalg.RelalgParser;

public class UnionAcceptanceTest {
    
    @Test
    public void testUnionAcceptance_01() {
        RelalgParser.parse("tck/UnionAcceptance_01");
    }
    
    @Test
    public void testUnionAcceptance_02() {
        RelalgParser.parse("tck/UnionAcceptance_02");
    }
    
    @Test
    public void testUnionAcceptance_03() {
        RelalgParser.parse("tck/UnionAcceptance_03");
    }
    
    @Test
    public void testUnionAcceptance_04() {
        RelalgParser.parse("tck/UnionAcceptance_04");
    }
    
    @Test
    public void testUnionAcceptance_05() {
        RelalgParser.parse("tck/UnionAcceptance_05");
    }
    
}
    