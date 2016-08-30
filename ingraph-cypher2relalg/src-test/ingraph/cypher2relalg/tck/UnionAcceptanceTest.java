
package ingraph.cypher2relalg.tck;

import ingraph.cypher2relalg.RelalgParser;

import org.junit.Test;

public class UnionAcceptanceTest {
    
    @Test
    public void testUnionAcceptance01() {
        RelalgParser.parse("tck/UnionAcceptance01");
    }
    
    @Test
    public void testUnionAcceptance02() {
        RelalgParser.parse("tck/UnionAcceptance02");
    }
    
    @Test
    public void testUnionAcceptance03() {
        RelalgParser.parse("tck/UnionAcceptance03");
    }
    
    @Test
    public void testUnionAcceptance04() {
        RelalgParser.parse("tck/UnionAcceptance04");
    }
    
    @Test
    public void testUnionAcceptance05() {
        RelalgParser.parse("tck/UnionAcceptance05");
    }
    
}
    