package ingraph.cypher2relalg.tck;

import org.junit.Test;

import ingraph.cypher2relalg.RelalgParser;

public class ColumnNameAcceptanceTest {
    
    @Test
    public void testColumnNameAcceptance_01() {
        RelalgParser.parse("tck/ColumnNameAcceptance_01");
    }
    
    @Test
    public void testColumnNameAcceptance_02() {
        RelalgParser.parse("tck/ColumnNameAcceptance_02");
    }
    
    @Test
    public void testColumnNameAcceptance_03() {
        RelalgParser.parse("tck/ColumnNameAcceptance_03");
    }
    
    @Test
    public void testColumnNameAcceptance_04() {
        RelalgParser.parse("tck/ColumnNameAcceptance_04");
    }
    
}
    