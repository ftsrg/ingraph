package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class VarLengthAcceptanceTest {
    
    /*
    Scenario: Handling unbounded variable length match
    */
    @Test
    def void testVarLengthAcceptance_01() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*]->(c)
        RETURN c.name
        ''')
    }

    /*
    Scenario: Handling explicitly unbounded variable length match
    */
    @Test
    def void testVarLengthAcceptance_02() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*..]->(c)
        RETURN c.name
        ''')
    }

    /*
    Scenario: Handling single bounded variable length match 1
    */
    @Test
    def void testVarLengthAcceptance_03() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*0]->(c)
        RETURN c.name
        ''')
    }

    /*
    Scenario: Handling single bounded variable length match 2
    */
    @Test
    def void testVarLengthAcceptance_04() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*1]->(c)
        RETURN c.name
        ''')
    }

    /*
    Scenario: Handling single bounded variable length match 3
    */
    @Test
    def void testVarLengthAcceptance_05() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*2]->(c)
        RETURN c.name
        ''')
    }

    /*
    Scenario: Handling upper and lower bounded variable length match 1
    */
    @Test
    def void testVarLengthAcceptance_06() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*0..2]->(c)
        RETURN c.name
        ''')
    }

    /*
    Scenario: Handling upper and lower bounded variable length match 2
    */
    @Test
    def void testVarLengthAcceptance_07() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*1..2]->(c)
        RETURN c.name
        ''')
    }

    /*
    Scenario: Handling symmetrically bounded variable length match, bounds are zero
    */
    @Test
    def void testVarLengthAcceptance_08() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*0..0]->(c)
        RETURN c.name
        ''')
    }

    /*
    Scenario: Handling symmetrically bounded variable length match, bounds are one
    */
    @Test
    def void testVarLengthAcceptance_09() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*1..1]->(c)
        RETURN c.name
        ''')
    }

    /*
    Scenario: Handling symmetrically bounded variable length match, bounds are two
    */
    @Test
    def void testVarLengthAcceptance_10() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*2..2]->(c)
        RETURN c.name
        ''')
    }

    /*
    Scenario: Handling upper and lower bounded variable length match, empty interval 1
    */
    @Test
    def void testVarLengthAcceptance_11() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*2..1]->(c)
        RETURN c.name
        ''')
    }

    /*
    Scenario: Handling upper and lower bounded variable length match, empty interval 2
    */
    @Test
    def void testVarLengthAcceptance_12() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*1..0]->(c)
        RETURN c.name
        ''')
    }

    /*
    Scenario: Handling upper bounded variable length match, empty interval
    */
    @Test
    def void testVarLengthAcceptance_13() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*..0]->(c)
        RETURN c.name
        ''')
    }

    /*
    Scenario: Handling upper bounded variable length match 1
    */
    @Test
    def void testVarLengthAcceptance_14() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*..1]->(c)
        RETURN c.name
        ''')
    }

    /*
    Scenario: Handling upper bounded variable length match 2
    */
    @Test
    def void testVarLengthAcceptance_15() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*..2]->(c)
        RETURN c.name
        ''')
    }

    /*
    Scenario: Handling lower bounded variable length match 1
    */
    @Test
    def void testVarLengthAcceptance_16() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*0..]->(c)
        RETURN c.name
        ''')
    }

    /*
    Scenario: Handling lower bounded variable length match 2
    */
    @Test
    def void testVarLengthAcceptance_17() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*1..]->(c)
        RETURN c.name
        ''')
    }

    /*
    Scenario: Handling lower bounded variable length match 3
    */
    @Test
    def void testVarLengthAcceptance_18() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*2..]->(c)
        RETURN c.name
        ''')
    }

    /*
    Scenario: Handling a variable length relationship and a standard relationship in chain, zero length 1
    */
    @Test
    def void testVarLengthAcceptance_19() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*0]->()-[:LIKES]->(c)
        RETURN c.name
        ''')
    }

    /*
    Scenario: Handling a variable length relationship and a standard relationship in chain, zero length 2
    */
    @Test
    def void testVarLengthAcceptance_20() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES]->()-[:LIKES*0]->(c)
        RETURN c.name
        ''')
    }

    /*
    Scenario: Handling a variable length relationship and a standard relationship in chain, single length 1
    */
    @Test
    def void testVarLengthAcceptance_21() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*1]->()-[:LIKES]->(c)
        RETURN c.name
        ''')
    }

    /*
    Scenario: Handling a variable length relationship and a standard relationship in chain, single length 2
    */
    @Test
    def void testVarLengthAcceptance_22() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES]->()-[:LIKES*1]->(c)
        RETURN c.name
        ''')
    }

    /*
    Scenario: Handling a variable length relationship and a standard relationship in chain, longer 1
    */
    @Test
    def void testVarLengthAcceptance_23() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*2]->()-[:LIKES]->(c)
        RETURN c.name
        ''')
    }

    /*
    Scenario: Handling a variable length relationship and a standard relationship in chain, longer 2
    */
    @Test
    def void testVarLengthAcceptance_24() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES]->()-[:LIKES*2]->(c)
        RETURN c.name
        ''')
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
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES]->()-[:LIKES*3]->(c)
        RETURN c.name
        ''')
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
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)<-[:LIKES]-()-[:LIKES*3]->(c)
        RETURN c.name
        ''')
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
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES]->()<-[:LIKES*3]->(c)
        RETURN c.name
        ''')
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
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (p)-[:LIKES*1]->()-[:LIKES]->()-[r:LIKES*2]->(c)
        RETURN c.name
        ''')
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
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (p)-[:LIKES]->()-[:LIKES*2]->()-[r:LIKES]->(c)
        RETURN c.name
        ''')
    }

}
