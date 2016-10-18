package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.CypherParser
import ingraph.relalg.util.SchemaInferencer
import ingraph.relalg2tex.RelalgTreeSerializer

class VarLengthAcceptanceVisualizationTest {

    val RelalgTreeSerializer serializer = new RelalgTreeSerializer
    extension SchemaInferencer inferencer = new SchemaInferencer
    
    /*
    Scenario: Handling unbounded variable length match
    */
    @Test
    def void testVarLengthAcceptance_01() {
        val container = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*]->(c)
        RETURN c.name
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "VarLengthAcceptance_01")
    }

    /*
    Scenario: Handling explicitly unbounded variable length match
    */
    @Test
    def void testVarLengthAcceptance_02() {
        val container = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*..]->(c)
        RETURN c.name
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "VarLengthAcceptance_02")
    }

    /*
    Scenario: Handling single bounded variable length match 1
    */
    @Test
    def void testVarLengthAcceptance_03() {
        val container = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*0]->(c)
        RETURN c.name
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "VarLengthAcceptance_03")
    }

    /*
    Scenario: Handling single bounded variable length match 2
    */
    @Test
    def void testVarLengthAcceptance_04() {
        val container = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*1]->(c)
        RETURN c.name
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "VarLengthAcceptance_04")
    }

    /*
    Scenario: Handling single bounded variable length match 3
    */
    @Test
    def void testVarLengthAcceptance_05() {
        val container = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*2]->(c)
        RETURN c.name
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "VarLengthAcceptance_05")
    }

    /*
    Scenario: Handling upper and lower bounded variable length match 1
    */
    @Test
    def void testVarLengthAcceptance_06() {
        val container = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*0..2]->(c)
        RETURN c.name
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "VarLengthAcceptance_06")
    }

    /*
    Scenario: Handling upper and lower bounded variable length match 2
    */
    @Test
    def void testVarLengthAcceptance_07() {
        val container = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*1..2]->(c)
        RETURN c.name
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "VarLengthAcceptance_07")
    }

    /*
    Scenario: Handling symmetrically bounded variable length match, bounds are zero
    */
    @Test
    def void testVarLengthAcceptance_08() {
        val container = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*0..0]->(c)
        RETURN c.name
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "VarLengthAcceptance_08")
    }

    /*
    Scenario: Handling symmetrically bounded variable length match, bounds are one
    */
    @Test
    def void testVarLengthAcceptance_09() {
        val container = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*1..1]->(c)
        RETURN c.name
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "VarLengthAcceptance_09")
    }

    /*
    Scenario: Handling symmetrically bounded variable length match, bounds are two
    */
    @Test
    def void testVarLengthAcceptance_10() {
        val container = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*2..2]->(c)
        RETURN c.name
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "VarLengthAcceptance_10")
    }

    /*
    Scenario: Handling upper and lower bounded variable length match, empty interval 1
    */
    @Test
    def void testVarLengthAcceptance_11() {
        val container = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*2..1]->(c)
        RETURN c.name
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "VarLengthAcceptance_11")
    }

    /*
    Scenario: Handling upper and lower bounded variable length match, empty interval 2
    */
    @Test
    def void testVarLengthAcceptance_12() {
        val container = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*1..0]->(c)
        RETURN c.name
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "VarLengthAcceptance_12")
    }

    /*
    Scenario: Handling upper bounded variable length match, empty interval
    */
    @Test
    def void testVarLengthAcceptance_13() {
        val container = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*..0]->(c)
        RETURN c.name
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "VarLengthAcceptance_13")
    }

    /*
    Scenario: Handling upper bounded variable length match 1
    */
    @Test
    def void testVarLengthAcceptance_14() {
        val container = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*..1]->(c)
        RETURN c.name
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "VarLengthAcceptance_14")
    }

    /*
    Scenario: Handling upper bounded variable length match 2
    */
    @Test
    def void testVarLengthAcceptance_15() {
        val container = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*..2]->(c)
        RETURN c.name
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "VarLengthAcceptance_15")
    }

    /*
    Scenario: Handling lower bounded variable length match 1
    */
    @Test
    def void testVarLengthAcceptance_16() {
        val container = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*0..]->(c)
        RETURN c.name
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "VarLengthAcceptance_16")
    }

    /*
    Scenario: Handling lower bounded variable length match 2
    */
    @Test
    def void testVarLengthAcceptance_17() {
        val container = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*1..]->(c)
        RETURN c.name
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "VarLengthAcceptance_17")
    }

    /*
    Scenario: Handling lower bounded variable length match 3
    */
    @Test
    def void testVarLengthAcceptance_18() {
        val container = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*2..]->(c)
        RETURN c.name
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "VarLengthAcceptance_18")
    }

    /*
    Scenario: Handling a variable length relationship and a standard relationship in chain, zero length 1
    */
    @Test
    def void testVarLengthAcceptance_19() {
        val container = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*0]->()-[:LIKES]->(c)
        RETURN c.name
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "VarLengthAcceptance_19")
    }

    /*
    Scenario: Handling a variable length relationship and a standard relationship in chain, zero length 2
    */
    @Test
    def void testVarLengthAcceptance_20() {
        val container = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES]->()-[:LIKES*0]->(c)
        RETURN c.name
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "VarLengthAcceptance_20")
    }

    /*
    Scenario: Handling a variable length relationship and a standard relationship in chain, single length 1
    */
    @Test
    def void testVarLengthAcceptance_21() {
        val container = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*1]->()-[:LIKES]->(c)
        RETURN c.name
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "VarLengthAcceptance_21")
    }

    /*
    Scenario: Handling a variable length relationship and a standard relationship in chain, single length 2
    */
    @Test
    def void testVarLengthAcceptance_22() {
        val container = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES]->()-[:LIKES*1]->(c)
        RETURN c.name
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "VarLengthAcceptance_22")
    }

    /*
    Scenario: Handling a variable length relationship and a standard relationship in chain, longer 1
    */
    @Test
    def void testVarLengthAcceptance_23() {
        val container = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*2]->()-[:LIKES]->(c)
        RETURN c.name
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "VarLengthAcceptance_23")
    }

    /*
    Scenario: Handling a variable length relationship and a standard relationship in chain, longer 2
    */
    @Test
    def void testVarLengthAcceptance_24() {
        val container = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES]->()-[:LIKES*2]->(c)
        RETURN c.name
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "VarLengthAcceptance_24")
    }

    /*
    Scenario: Handling a variable length relationship and a standard relationship in chain, longer 3
    And having executed:
      """
      MATCH (d:D)
      CREATE (e1:E {name: d.name + '0'}),
             (e2:E {name: d.name + '1'})
      CREATE (d)-[:LIKES]->(e1),
             (d)-[:LIKES]->(e2)
      """
    */
    @Test
    def void testVarLengthAcceptance_25() {
        val container = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES]->()-[:LIKES*3]->(c)
        RETURN c.name
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "VarLengthAcceptance_25")
    }

    /*
    Scenario: Handling mixed relationship patterns and directions 1
    And having executed:
      """
      MATCH (a:A)-[r]->(b)
      DELETE r
      CREATE (b)-[:LIKES]->(a)
      """
    And having executed:
      """
      MATCH (d:D)
      CREATE (e1:E {name: d.name + '0'}),
             (e2:E {name: d.name + '1'})
      CREATE (d)-[:LIKES]->(e1),
             (d)-[:LIKES]->(e2)
      """
    */
    @Test
    def void testVarLengthAcceptance_26() {
        val container = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)<-[:LIKES]-()-[:LIKES*3]->(c)
        RETURN c.name
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "VarLengthAcceptance_26")
    }

    /*
    Scenario: Handling mixed relationship patterns and directions 2
    # This gets hard to follow for a human mind. The answer is named graphs, but it's not crucial to fix.
    And having executed:
      """
      MATCH (a)-[r]->(b)
      WHERE NOT a:A
      DELETE r
      CREATE (b)-[:LIKES]->(a)
      """
    And having executed:
      """
      MATCH (d:D)
      CREATE (e1:E {name: d.name + '0'}),
             (e2:E {name: d.name + '1'})
      CREATE (d)-[:LIKES]->(e1),
             (d)-[:LIKES]->(e2)
      """
    */
    @Test
    def void testVarLengthAcceptance_27() {
        val container = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES]->()<-[:LIKES*3]->(c)
        RETURN c.name
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "VarLengthAcceptance_27")
    }

    /*
    Scenario: Handling mixed relationship patterns 1
    And having executed:
      """
      MATCH (d:D)
      CREATE (e1:E {name: d.name + '0'}),
             (e2:E {name: d.name + '1'})
      CREATE (d)-[:LIKES]->(e1),
             (d)-[:LIKES]->(e2)
      """
    */
    @Test
    def void testVarLengthAcceptance_28() {
        val container = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (p)-[:LIKES*1]->()-[:LIKES]->()-[r:LIKES*2]->(c)
        RETURN c.name
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "VarLengthAcceptance_28")
    }

    /*
    Scenario: Handling mixed relationship patterns 2
    And having executed:
      """
      MATCH (d:D)
      CREATE (e1:E {name: d.name + '0'}),
             (e2:E {name: d.name + '1'})
      CREATE (d)-[:LIKES]->(e1),
             (d)-[:LIKES]->(e2)
      """
    */
    @Test
    def void testVarLengthAcceptance_29() {
        val container = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (p)-[:LIKES]->()-[:LIKES*2]->()-[r:LIKES]->(c)
        RETURN c.name
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "VarLengthAcceptance_29")
    }

}
