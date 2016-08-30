
package ingraph.cypher2relalg.tck;

import ingraph.cypher2relalg.RelalgParser;

import org.junit.Test;

public class ColumnNameAcceptanceTest {
    
    @Test
    public void testColumnNameAcceptance01() {
        RelalgParser.parse("tck/ColumnNameAcceptance01");
    }
    
    @Test
    public void testColumnNameAcceptance02() {
        RelalgParser.parse("tck/ColumnNameAcceptance02");
    }
    
    @Test
    public void testColumnNameAcceptance03() {
        RelalgParser.parse("tck/ColumnNameAcceptance03");
    }
    
    @Test
    public void testColumnNameAcceptance04() {
        RelalgParser.parse("tck/ColumnNameAcceptance04");
    }
    
}
    