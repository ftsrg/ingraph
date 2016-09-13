package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser
import ingraph.relalg2tex.AlgebraTreeDrawer

class VarLengthAcceptanceVisualizationTest {

    val static AlgebraTreeDrawer drawer = new AlgebraTreeDrawer(true)
    
    /*
    Scenario: Handling unbounded variable length match
    */
    @Test
    def void testVarLengthAcceptance_01() {
        val container = RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*]->(c)
        RETURN c.name
        ''')
        drawer.serialize(container, "VarLengthAcceptance")
    }

    /*
    Scenario: Handling explicitly unbounded variable length match
    */
    @Test
    def void testVarLengthAcceptance_02() {
        val container = RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*..]->(c)
        RETURN c.name
        ''')
        drawer.serialize(container, "VarLengthAcceptance")
    }

    /*
    Scenario: Handling single bounded variable length match 1
    */
    @Test
    def void testVarLengthAcceptance_03() {
        val container = RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*0]->(c)
        RETURN c.name
        ''')
        drawer.serialize(container, "VarLengthAcceptance")
    }

    /*
    Scenario: Handling single bounded variable length match 2
    */
    @Test
    def void testVarLengthAcceptance_04() {
        val container = RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*1]->(c)
        RETURN c.name
        ''')
        drawer.serialize(container, "VarLengthAcceptance")
    }

    /*
    Scenario: Handling single bounded variable length match 3
    */
    @Test
    def void testVarLengthAcceptance_05() {
        val container = RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*2]->(c)
        RETURN c.name
        ''')
        drawer.serialize(container, "VarLengthAcceptance")
    }

    /*
    Scenario: Handling upper and lower bounded variable length match 1
    */
    @Test
    def void testVarLengthAcceptance_06() {
        val container = RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*0..2]->(c)
        RETURN c.name
        ''')
        drawer.serialize(container, "VarLengthAcceptance")
    }

    /*
    Scenario: Handling upper and lower bounded variable length match 2
    */
    @Test
    def void testVarLengthAcceptance_07() {
        val container = RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*1..2]->(c)
        RETURN c.name
        ''')
        drawer.serialize(container, "VarLengthAcceptance")
    }

    /*
    Scenario: Handling symmetrically bounded variable length match, bounds are zero
    */
    @Test
    def void testVarLengthAcceptance_08() {
        val container = RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*0..0]->(c)
        RETURN c.name
        ''')
        drawer.serialize(container, "VarLengthAcceptance")
    }

    /*
    Scenario: Handling symmetrically bounded variable length match, bounds are one
    */
    @Test
    def void testVarLengthAcceptance_09() {
        val container = RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*1..1]->(c)
        RETURN c.name
        ''')
        drawer.serialize(container, "VarLengthAcceptance")
    }

    /*
    Scenario: Handling symmetrically bounded variable length match, bounds are two
    */
    @Test
    def void testVarLengthAcceptance_10() {
        val container = RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*2..2]->(c)
        RETURN c.name
        ''')
        drawer.serialize(container, "VarLengthAcceptance")
    }

    /*
    Scenario: Handling upper and lower bounded variable length match, empty interval 1
    */
    @Test
    def void testVarLengthAcceptance_11() {
        val container = RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*2..1]->(c)
        RETURN c.name
        ''')
        drawer.serialize(container, "VarLengthAcceptance")
    }

    /*
    Scenario: Handling upper and lower bounded variable length match, empty interval 2
    */
    @Test
    def void testVarLengthAcceptance_12() {
        val container = RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*1..0]->(c)
        RETURN c.name
        ''')
        drawer.serialize(container, "VarLengthAcceptance")
    }

    /*
    Scenario: Handling upper bounded variable length match, empty interval
    */
    @Test
    def void testVarLengthAcceptance_13() {
        val container = RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*..0]->(c)
        RETURN c.name
        ''')
        drawer.serialize(container, "VarLengthAcceptance")
    }

    /*
    Scenario: Handling upper bounded variable length match 1
    */
    @Test
    def void testVarLengthAcceptance_14() {
        val container = RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*..1]->(c)
        RETURN c.name
        ''')
        drawer.serialize(container, "VarLengthAcceptance")
    }

    /*
    Scenario: Handling upper bounded variable length match 2
    */
    @Test
    def void testVarLengthAcceptance_15() {
        val container = RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*..2]->(c)
        RETURN c.name
        ''')
        drawer.serialize(container, "VarLengthAcceptance")
    }

    /*
    Scenario: Handling lower bounded variable length match 1
    */
    @Test
    def void testVarLengthAcceptance_16() {
        val container = RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*0..]->(c)
        RETURN c.name
        ''')
        drawer.serialize(container, "VarLengthAcceptance")
    }

    /*
    Scenario: Handling lower bounded variable length match 2
    */
    @Test
    def void testVarLengthAcceptance_17() {
        val container = RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*1..]->(c)
        RETURN c.name
        ''')
        drawer.serialize(container, "VarLengthAcceptance")
    }

    /*
    Scenario: Handling lower bounded variable length match 3
    */
    @Test
    def void testVarLengthAcceptance_18() {
        val container = RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*2..]->(c)
        RETURN c.name
        ''')
        drawer.serialize(container, "VarLengthAcceptance")
    }

    /*
    Scenario: Handling a variable length relationship and a standard relationship in chain, zero length 1
    */
    @Test
    def void testVarLengthAcceptance_19() {
        val container = RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*0]->()-[:LIKES]->(c)
        RETURN c.name
        ''')
        drawer.serialize(container, "VarLengthAcceptance")
    }

    /*
    Scenario: Handling a variable length relationship and a standard relationship in chain, zero length 2
    */
    @Test
    def void testVarLengthAcceptance_20() {
        val container = RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES]->()-[:LIKES*0]->(c)
        RETURN c.name
        ''')
        drawer.serialize(container, "VarLengthAcceptance")
    }

    /*
    Scenario: Handling a variable length relationship and a standard relationship in chain, single length 1
    */
    @Test
    def void testVarLengthAcceptance_21() {
        val container = RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*1]->()-[:LIKES]->(c)
        RETURN c.name
        ''')
        drawer.serialize(container, "VarLengthAcceptance")
    }

    /*
    Scenario: Handling a variable length relationship and a standard relationship in chain, single length 2
    */
    @Test
    def void testVarLengthAcceptance_22() {
        val container = RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES]->()-[:LIKES*1]->(c)
        RETURN c.name
        ''')
        drawer.serialize(container, "VarLengthAcceptance")
    }

    /*
    Scenario: Handling a variable length relationship and a standard relationship in chain, longer 1
    */
    @Test
    def void testVarLengthAcceptance_23() {
        val container = RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*2]->()-[:LIKES]->(c)
        RETURN c.name
        ''')
        drawer.serialize(container, "VarLengthAcceptance")
    }

    /*
    Scenario: Handling a variable length relationship and a standard relationship in chain, longer 2
    */
    @Test
    def void testVarLengthAcceptance_24() {
        val container = RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES]->()-[:LIKES*2]->(c)
        RETURN c.name
        ''')
        drawer.serialize(container, "VarLengthAcceptance")
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
        val container = RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES]->()-[:LIKES*3]->(c)
        RETURN c.name
        ''')
        drawer.serialize(container, "VarLengthAcceptance")
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
        val container = RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)<-[:LIKES]-()-[:LIKES*3]->(c)
        RETURN c.name
        ''')
        drawer.serialize(container, "VarLengthAcceptance")
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
        val container = RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES]->()<-[:LIKES*3]->(c)
        RETURN c.name
        ''')
        drawer.serialize(container, "VarLengthAcceptance")
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
        val container = RelalgParser.parse('''
        MATCH (a:A)
        MATCH (p)-[:LIKES*1]->()-[:LIKES]->()-[r:LIKES*2]->(c)
        RETURN c.name
        ''')
        drawer.serialize(container, "VarLengthAcceptance")
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
        val container = RelalgParser.parse('''
        MATCH (a:A)
        MATCH (p)-[:LIKES]->()-[:LIKES*2]->()-[r:LIKES]->(c)
        RETURN c.name
        ''')
        drawer.serialize(container, "VarLengthAcceptance")
    }

}
