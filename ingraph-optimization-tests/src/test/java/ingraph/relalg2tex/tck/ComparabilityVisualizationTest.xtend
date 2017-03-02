package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.relalg.inferencers.BasicSchemaInferencer
import ingraph.relalg2tex.converters.relalgconverters.Relalg2TexTreeConverter

class ComparabilityVisualizationTest {

    extension Relalg2TexTreeConverter converter = new Relalg2TexTreeConverter
    extension BasicSchemaInferencer inferencer = new BasicSchemaInferencer
    
    /*
    Scenario: Comparing strings and integers using > in an AND'd predicate
    Given an empty graph
    And having executed:
      """
      CREATE (root:Root)-[:T]->(:Child {id: 0}),
             (root)-[:T]->(:Child {id: 'xx'}),
             (root)-[:T]->(:Child)
      """
    */
    @Test
    def void testComparability_01() {
        val container = Cypher2Relalg.processString('''
        MATCH (:Root)-->(i:Child)
        WHERE exists(i.id) AND i.id > 'x'
        RETURN i.id
        ''')
        container.inferBasicSchema
        container.convert("tck/Comparability_01")
    }

    /*
    Scenario: Comparing strings and integers using > in a OR'd predicate
    Given an empty graph
    And having executed:
      """
      CREATE (root:Root)-[:T]->(:Child {id: 0}),
             (root)-[:T]->(:Child {id: 'xx'}),
             (root)-[:T]->(:Child)
      """
    */
    @Test
    def void testComparability_02() {
        val container = Cypher2Relalg.processString('''
        MATCH (:Root)-->(i:Child)
        WHERE NOT exists(i.id) OR i.id > 'x'
        RETURN i.id
        ''')
        container.inferBasicSchema
        container.convert("tck/Comparability_02")
    }

}
