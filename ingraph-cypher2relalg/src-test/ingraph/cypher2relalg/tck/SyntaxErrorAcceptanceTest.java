
package ingraph.cypher2relalg.tck;

import ingraph.cypher2relalg.RelalgParser;

import org.junit.Test;

public class SyntaxErrorAcceptanceTest {
    
    @Test
    public void testSyntaxErrorAcceptance01() {
        RelalgParser.parse("tck/SyntaxErrorAcceptance01");
    }
    
    @Test
    public void testSyntaxErrorAcceptance02() {
        RelalgParser.parse("tck/SyntaxErrorAcceptance02");
    }
    
    @Test
    public void testSyntaxErrorAcceptance03() {
        RelalgParser.parse("tck/SyntaxErrorAcceptance03");
    }
    
    @Test
    public void testSyntaxErrorAcceptance04() {
        RelalgParser.parse("tck/SyntaxErrorAcceptance04");
    }
    
}
    