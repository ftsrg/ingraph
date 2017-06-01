package ingraph.cypher2relalg.tck.tests

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.cypher2relalg.tck.FailingTests
import ingraph.cypher2relalg.tck.RegressionTests
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil
import ingraph.relalg.util.RelalgUtil
import org.junit.Test
import org.junit.experimental.categories.Category

class VarLengthAcceptanceParserTest {
    
    /*
    Scenario: Handling unbounded variable length match
    */
    @Test
    @Category(FailingTests)
    def void testVarLengthAcceptance_01() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*]->(c)
        RETURN c.name
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/VarLengthAcceptance_01")
        val container = Cypher2Relalg.processCypher(cypher, "testVarLengthAcceptance_01")
        RelalgUtil.save(container, "relalg-models/tck/VarLengthAcceptance_01")
    }

    /*
    Scenario: Handling explicitly unbounded variable length match
    */
    @Test
    @Category(FailingTests)
    def void testVarLengthAcceptance_02() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*..]->(c)
        RETURN c.name
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/VarLengthAcceptance_02")
        val container = Cypher2Relalg.processCypher(cypher, "testVarLengthAcceptance_02")
        RelalgUtil.save(container, "relalg-models/tck/VarLengthAcceptance_02")
    }

    /*
    Scenario: Handling single bounded variable length match 1
    */
    @Test
    @Category(FailingTests)
    def void testVarLengthAcceptance_03() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*0]->(c)
        RETURN c.name
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/VarLengthAcceptance_03")
        val container = Cypher2Relalg.processCypher(cypher, "testVarLengthAcceptance_03")
        RelalgUtil.save(container, "relalg-models/tck/VarLengthAcceptance_03")
    }

    /*
    Scenario: Handling single bounded variable length match 2
    */
    @Test
    @Category(FailingTests)
    def void testVarLengthAcceptance_04() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*1]->(c)
        RETURN c.name
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/VarLengthAcceptance_04")
        val container = Cypher2Relalg.processCypher(cypher, "testVarLengthAcceptance_04")
        RelalgUtil.save(container, "relalg-models/tck/VarLengthAcceptance_04")
    }

    /*
    Scenario: Handling single bounded variable length match 3
    */
    @Test
    @Category(FailingTests)
    def void testVarLengthAcceptance_05() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*2]->(c)
        RETURN c.name
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/VarLengthAcceptance_05")
        val container = Cypher2Relalg.processCypher(cypher, "testVarLengthAcceptance_05")
        RelalgUtil.save(container, "relalg-models/tck/VarLengthAcceptance_05")
    }

    /*
    Scenario: Handling upper and lower bounded variable length match 1
    */
    @Test
    @Category(FailingTests)
    def void testVarLengthAcceptance_06() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*0..2]->(c)
        RETURN c.name
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/VarLengthAcceptance_06")
        val container = Cypher2Relalg.processCypher(cypher, "testVarLengthAcceptance_06")
        RelalgUtil.save(container, "relalg-models/tck/VarLengthAcceptance_06")
    }

    /*
    Scenario: Handling upper and lower bounded variable length match 2
    */
    @Test
    @Category(FailingTests)
    def void testVarLengthAcceptance_07() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*1..2]->(c)
        RETURN c.name
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/VarLengthAcceptance_07")
        val container = Cypher2Relalg.processCypher(cypher, "testVarLengthAcceptance_07")
        RelalgUtil.save(container, "relalg-models/tck/VarLengthAcceptance_07")
    }

    /*
    Scenario: Handling symmetrically bounded variable length match, bounds are zero
    */
    @Test
    @Category(FailingTests)
    def void testVarLengthAcceptance_08() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*0..0]->(c)
        RETURN c.name
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/VarLengthAcceptance_08")
        val container = Cypher2Relalg.processCypher(cypher, "testVarLengthAcceptance_08")
        RelalgUtil.save(container, "relalg-models/tck/VarLengthAcceptance_08")
    }

    /*
    Scenario: Handling symmetrically bounded variable length match, bounds are one
    */
    @Test
    @Category(FailingTests)
    def void testVarLengthAcceptance_09() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*1..1]->(c)
        RETURN c.name
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/VarLengthAcceptance_09")
        val container = Cypher2Relalg.processCypher(cypher, "testVarLengthAcceptance_09")
        RelalgUtil.save(container, "relalg-models/tck/VarLengthAcceptance_09")
    }

    /*
    Scenario: Handling symmetrically bounded variable length match, bounds are two
    */
    @Test
    @Category(FailingTests)
    def void testVarLengthAcceptance_10() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*2..2]->(c)
        RETURN c.name
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/VarLengthAcceptance_10")
        val container = Cypher2Relalg.processCypher(cypher, "testVarLengthAcceptance_10")
        RelalgUtil.save(container, "relalg-models/tck/VarLengthAcceptance_10")
    }

    /*
    Scenario: Handling upper and lower bounded variable length match, empty interval 1
    */
    @Test
    @Category(FailingTests)
    def void testVarLengthAcceptance_11() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*2..1]->(c)
        RETURN c.name
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/VarLengthAcceptance_11")
        val container = Cypher2Relalg.processCypher(cypher, "testVarLengthAcceptance_11")
        RelalgUtil.save(container, "relalg-models/tck/VarLengthAcceptance_11")
    }

    /*
    Scenario: Handling upper and lower bounded variable length match, empty interval 2
    */
    @Test
    @Category(FailingTests)
    def void testVarLengthAcceptance_12() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*1..0]->(c)
        RETURN c.name
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/VarLengthAcceptance_12")
        val container = Cypher2Relalg.processCypher(cypher, "testVarLengthAcceptance_12")
        RelalgUtil.save(container, "relalg-models/tck/VarLengthAcceptance_12")
    }

    /*
    Scenario: Handling upper bounded variable length match, empty interval
    */
    @Test
    @Category(FailingTests)
    def void testVarLengthAcceptance_13() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*..0]->(c)
        RETURN c.name
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/VarLengthAcceptance_13")
        val container = Cypher2Relalg.processCypher(cypher, "testVarLengthAcceptance_13")
        RelalgUtil.save(container, "relalg-models/tck/VarLengthAcceptance_13")
    }

    /*
    Scenario: Handling upper bounded variable length match 1
    */
    @Test
    @Category(FailingTests)
    def void testVarLengthAcceptance_14() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*..1]->(c)
        RETURN c.name
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/VarLengthAcceptance_14")
        val container = Cypher2Relalg.processCypher(cypher, "testVarLengthAcceptance_14")
        RelalgUtil.save(container, "relalg-models/tck/VarLengthAcceptance_14")
    }

    /*
    Scenario: Handling upper bounded variable length match 2
    */
    @Test
    @Category(FailingTests)
    def void testVarLengthAcceptance_15() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*..2]->(c)
        RETURN c.name
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/VarLengthAcceptance_15")
        val container = Cypher2Relalg.processCypher(cypher, "testVarLengthAcceptance_15")
        RelalgUtil.save(container, "relalg-models/tck/VarLengthAcceptance_15")
    }

    /*
    Scenario: Handling lower bounded variable length match 1
    */
    @Test
    @Category(FailingTests)
    def void testVarLengthAcceptance_16() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*0..]->(c)
        RETURN c.name
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/VarLengthAcceptance_16")
        val container = Cypher2Relalg.processCypher(cypher, "testVarLengthAcceptance_16")
        RelalgUtil.save(container, "relalg-models/tck/VarLengthAcceptance_16")
    }

    /*
    Scenario: Handling lower bounded variable length match 2
    */
    @Test
    @Category(FailingTests)
    def void testVarLengthAcceptance_17() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*1..]->(c)
        RETURN c.name
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/VarLengthAcceptance_17")
        val container = Cypher2Relalg.processCypher(cypher, "testVarLengthAcceptance_17")
        RelalgUtil.save(container, "relalg-models/tck/VarLengthAcceptance_17")
    }

    /*
    Scenario: Handling lower bounded variable length match 3
    */
    @Test
    @Category(FailingTests)
    def void testVarLengthAcceptance_18() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*2..]->(c)
        RETURN c.name
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/VarLengthAcceptance_18")
        val container = Cypher2Relalg.processCypher(cypher, "testVarLengthAcceptance_18")
        RelalgUtil.save(container, "relalg-models/tck/VarLengthAcceptance_18")
    }

    /*
    Scenario: Handling a variable length relationship and a standard relationship in chain, zero length 1
    */
    @Test
    @Category(FailingTests)
    def void testVarLengthAcceptance_19() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*0]->()-[:LIKES]->(c)
        RETURN c.name
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/VarLengthAcceptance_19")
        val container = Cypher2Relalg.processCypher(cypher, "testVarLengthAcceptance_19")
        RelalgUtil.save(container, "relalg-models/tck/VarLengthAcceptance_19")
    }

    /*
    Scenario: Handling a variable length relationship and a standard relationship in chain, zero length 2
    */
    @Test
    @Category(FailingTests)
    def void testVarLengthAcceptance_20() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES]->()-[:LIKES*0]->(c)
        RETURN c.name
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/VarLengthAcceptance_20")
        val container = Cypher2Relalg.processCypher(cypher, "testVarLengthAcceptance_20")
        RelalgUtil.save(container, "relalg-models/tck/VarLengthAcceptance_20")
    }

    /*
    Scenario: Handling a variable length relationship and a standard relationship in chain, single length 1
    */
    @Test
    @Category(FailingTests)
    def void testVarLengthAcceptance_21() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*1]->()-[:LIKES]->(c)
        RETURN c.name
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/VarLengthAcceptance_21")
        val container = Cypher2Relalg.processCypher(cypher, "testVarLengthAcceptance_21")
        RelalgUtil.save(container, "relalg-models/tck/VarLengthAcceptance_21")
    }

    /*
    Scenario: Handling a variable length relationship and a standard relationship in chain, single length 2
    */
    @Test
    @Category(FailingTests)
    def void testVarLengthAcceptance_22() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES]->()-[:LIKES*1]->(c)
        RETURN c.name
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/VarLengthAcceptance_22")
        val container = Cypher2Relalg.processCypher(cypher, "testVarLengthAcceptance_22")
        RelalgUtil.save(container, "relalg-models/tck/VarLengthAcceptance_22")
    }

    /*
    Scenario: Handling a variable length relationship and a standard relationship in chain, longer 1
    */
    @Test
    @Category(FailingTests)
    def void testVarLengthAcceptance_23() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*2]->()-[:LIKES]->(c)
        RETURN c.name
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/VarLengthAcceptance_23")
        val container = Cypher2Relalg.processCypher(cypher, "testVarLengthAcceptance_23")
        RelalgUtil.save(container, "relalg-models/tck/VarLengthAcceptance_23")
    }

    /*
    Scenario: Handling a variable length relationship and a standard relationship in chain, longer 2
    */
    @Test
    @Category(FailingTests)
    def void testVarLengthAcceptance_24() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES]->()-[:LIKES*2]->(c)
        RETURN c.name
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/VarLengthAcceptance_24")
        val container = Cypher2Relalg.processCypher(cypher, "testVarLengthAcceptance_24")
        RelalgUtil.save(container, "relalg-models/tck/VarLengthAcceptance_24")
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
    @Category(FailingTests)
    def void testVarLengthAcceptance_25() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES]->()-[:LIKES*3]->(c)
        RETURN c.name
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/VarLengthAcceptance_25")
        val container = Cypher2Relalg.processCypher(cypher, "testVarLengthAcceptance_25")
        RelalgUtil.save(container, "relalg-models/tck/VarLengthAcceptance_25")
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
    @Category(FailingTests)
    def void testVarLengthAcceptance_26() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)<-[:LIKES]-()-[:LIKES*3]->(c)
        RETURN c.name
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/VarLengthAcceptance_26")
        val container = Cypher2Relalg.processCypher(cypher, "testVarLengthAcceptance_26")
        RelalgUtil.save(container, "relalg-models/tck/VarLengthAcceptance_26")
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
    @Category(FailingTests)
    def void testVarLengthAcceptance_27() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[:LIKES]->()<-[:LIKES*3]->(c)
        RETURN c.name
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/VarLengthAcceptance_27")
        val container = Cypher2Relalg.processCypher(cypher, "testVarLengthAcceptance_27")
        RelalgUtil.save(container, "relalg-models/tck/VarLengthAcceptance_27")
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
    @Category(FailingTests)
    def void testVarLengthAcceptance_28() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (p)-[:LIKES*1]->()-[:LIKES]->()-[r:LIKES*2]->(c)
        RETURN c.name
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/VarLengthAcceptance_28")
        val container = Cypher2Relalg.processCypher(cypher, "testVarLengthAcceptance_28")
        RelalgUtil.save(container, "relalg-models/tck/VarLengthAcceptance_28")
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
    @Category(FailingTests)
    def void testVarLengthAcceptance_29() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (p)-[:LIKES]->()-[:LIKES*2]->()-[r:LIKES]->(c)
        RETURN c.name
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/VarLengthAcceptance_29")
        val container = Cypher2Relalg.processCypher(cypher, "testVarLengthAcceptance_29")
        RelalgUtil.save(container, "relalg-models/tck/VarLengthAcceptance_29")
    }

}
