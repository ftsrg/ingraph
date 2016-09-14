package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser
import ingraph.relalg2tex.RelAlgTreeDrawer

class MiscellaneousErrorAcceptanceVisualizationTest {

    val static RelAlgTreeDrawer drawer = new RelAlgTreeDrawer(true)
    
    /*
    Scenario: Failing on merging node with null property
    */
    @Test
    def void testMiscellaneousErrorAcceptance_02() {
        val container = RelalgParser.parse('''
        MERGE ({p: null})
        ''')
        drawer.serialize(container, "MiscellaneousErrorAcceptance_02")
    }

}
