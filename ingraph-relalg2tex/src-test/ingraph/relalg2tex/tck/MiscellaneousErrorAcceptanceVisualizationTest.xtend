package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser
import ingraph.relalg2tex.RelalgTreeSerializer

class MiscellaneousErrorAcceptanceVisualizationTest {

    val RelalgTreeSerializer serializer = new RelalgTreeSerializer(true)
    
    /*
    Scenario: Failing on merging node with null property
    */
    @Test
    def void testMiscellaneousErrorAcceptance_02() {
        val container = RelalgParser.parse('''
        MERGE ({p: null})
        ''')
        serializer.serialize(container, "MiscellaneousErrorAcceptance_02")
    }

}
