package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.Cypher2RelAlg
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil

class VarLengthAcceptance2ParserTest {
    
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
        val cypher = CypherParser.parseString('''
        MATCH ()-[r:EDGE]-()
        MATCH p = (n)-[*0..1]-()-[r]-()-[*0..1]-(m)
        RETURN count(p) AS c
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/VarLengthAcceptance2_01")
        Cypher2RelAlg.processCypher(cypher)
    }

}
