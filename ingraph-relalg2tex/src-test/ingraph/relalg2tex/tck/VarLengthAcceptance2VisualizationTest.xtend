package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser
import ingraph.relalg2tex.RelAlgTreeDrawer

class VarLengthAcceptance2VisualizationTest {

    val static RelAlgTreeDrawer drawer = new RelAlgTreeDrawer(true)
    
    /*
    Scenario: Handling relationships that are already bound in variable length paths
    Given an empty graph
    And having executed:
      """
      CREATE (n0:Node),
             (n1:Node),
             (n2:Node),
             (n3:Node),
             (n0)-[:EDGE]->(n1),
             (n1)-[:EDGE]->(n2),
             (n2)-[:EDGE]->(n3)
      """
    */
    @Test
    def void testVarLengthAcceptance2_01() {
        val container = RelalgParser.parse('''
        MATCH ()-[r:EDGE]-()
        MATCH p = (n)-[*0..1]-()-[r]-()-[*0..1]-(m)
        RETURN count(p) AS c
        ''')
        drawer.serialize(container, "VarLengthAcceptance2_01")
    }

}
