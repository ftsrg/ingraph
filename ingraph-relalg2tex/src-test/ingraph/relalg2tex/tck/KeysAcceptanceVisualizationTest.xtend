package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.CypherParser
import ingraph.optimization.transformations.SchemaInferencer
import ingraph.relalg2tex.RelalgTreeSerializer

class KeysAcceptanceVisualizationTest {

    val RelalgTreeSerializer serializer = new RelalgTreeSerializer
    extension SchemaInferencer inferencer = new SchemaInferencer
    
    /*
    Scenario: Using `keys()` on a single node, non-empty result
    Given an empty graph
    And having executed:
      """
      CREATE ({name: 'Andres', surname: 'Lopez'})
      """
    */
    @Test
    def void testKeysAcceptance_01() {
        val container = CypherParser.parseString('''
        MATCH (n)
        UNWIND keys(n) AS x
        RETURN DISTINCT x AS theProps
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "KeysAcceptance_01")
    }

    /*
    Scenario: Using `keys()` on multiple nodes, non-empty result
    Given an empty graph
    And having executed:
      """
      CREATE ({name: 'Andres', surname: 'Lopez'}),
             ({otherName: 'Andres', otherSurname: 'Lopez'})
      """
    */
    @Test
    def void testKeysAcceptance_02() {
        val container = CypherParser.parseString('''
        MATCH (n)
        UNWIND keys(n) AS x
        RETURN DISTINCT x AS theProps
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "KeysAcceptance_02")
    }

    /*
    Scenario: Using `keys()` on a single node, empty result
    Given an empty graph
    And having executed:
      """
      CREATE ()
      """
    */
    @Test
    def void testKeysAcceptance_03() {
        val container = CypherParser.parseString('''
        MATCH (n)
        UNWIND keys(n) AS x
        RETURN DISTINCT x AS theProps
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "KeysAcceptance_03")
    }

    /*
    Scenario: Using `keys()` on an optionally matched node
    Given an empty graph
    And having executed:
      """
      CREATE ()
      """
    */
    @Test
    def void testKeysAcceptance_04() {
        val container = CypherParser.parseString('''
        OPTIONAL MATCH (n)
        UNWIND keys(n) AS x
        RETURN DISTINCT x AS theProps
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "KeysAcceptance_04")
    }

    /*
    Scenario: Using `keys()` on a relationship, non-empty result
    Given an empty graph
    And having executed:
      """
      CREATE ()-[:KNOWS {level: 'bad', year: '2015'}]->()
      """
    */
    @Test
    def void testKeysAcceptance_05() {
        val container = CypherParser.parseString('''
        MATCH ()-[r:KNOWS]-()
        UNWIND keys(r) AS x
        RETURN DISTINCT x AS theProps
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "KeysAcceptance_05")
    }

    /*
    Scenario: Using `keys()` on a relationship, empty result
    Given an empty graph
    And having executed:
      """
      CREATE ()-[:KNOWS]->()
      """
    */
    @Test
    def void testKeysAcceptance_06() {
        val container = CypherParser.parseString('''
        MATCH ()-[r:KNOWS]-()
        UNWIND keys(r) AS x
        RETURN DISTINCT x AS theProps
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "KeysAcceptance_06")
    }

    /*
    Scenario: Using `keys()` on an optionally matched relationship
    Given an empty graph
    And having executed:
      """
      CREATE ()-[:KNOWS]->()
      """
    */
    @Test
    def void testKeysAcceptance_07() {
        val container = CypherParser.parseString('''
        OPTIONAL MATCH ()-[r:KNOWS]-()
        UNWIND keys(r) AS x
        RETURN DISTINCT x AS theProps
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "KeysAcceptance_07")
    }

    /*
    Scenario: Using `keys()` on a literal map
    Given any graph
    */
    @Test
    def void testKeysAcceptance_08() {
        val container = CypherParser.parseString('''
        RETURN keys({name: 'Alice', age: 38, address: {city: 'London', residential: true}}) AS k
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "KeysAcceptance_08")
    }

    /*
    Scenario: Using `keys()` on a parameter map
    Given any graph
    And parameters are:
      | param | {name: 'Alice', age: 38, address: {city: 'London', residential: true}} |
    */
    @Test
    def void testKeysAcceptance_09() {
        val container = CypherParser.parseString('''
        RETURN keys($param) AS k
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "KeysAcceptance_09")
    }

}
