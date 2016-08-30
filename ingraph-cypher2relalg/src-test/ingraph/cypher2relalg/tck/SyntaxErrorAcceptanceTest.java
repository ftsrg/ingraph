package ingraph.cypher2relalg.tck;

import org.junit.Test;

import ingraph.cypher2relalg.RelalgParser;

public class SyntaxErrorAcceptanceTest {
    
    @Test
    public void testSyntaxErrorAcceptance_01() {
        RelalgParser.parse("tck/SyntaxErrorAcceptance_01");
    }
    
    @Test
    public void testSyntaxErrorAcceptance_02() {
        RelalgParser.parse("tck/SyntaxErrorAcceptance_02");
    }
    
    @Test
    public void testSyntaxErrorAcceptance_03() {
        RelalgParser.parse("tck/SyntaxErrorAcceptance_03");
    }
    
    @Test
    public void testSyntaxErrorAcceptance_04() {
        RelalgParser.parse("tck/SyntaxErrorAcceptance_04");
    }
    
}
    