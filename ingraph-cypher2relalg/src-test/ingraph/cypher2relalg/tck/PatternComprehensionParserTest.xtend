package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class PatternComprehensionParserTest {
    
    /*
    Scenario: Pattern comprehension and ORDER BY
    Given an empty graph
    And having executed:
      """
      CREATE (a {time: 10}), (b {time: 20})
      CREATE (a)-[:T]->(b)
      """
    */
    @Test
    def void testPatternComprehension_01() {
        RelalgParser.parse('''
        MATCH (liker)
        RETURN [p = (liker)--() | p] AS isNew
        ORDER BY liker.time
        ''')
    }

    /*
    Scenario: Returning a pattern comprehension
    Given an empty graph
    And having executed:
      """
      CREATE (a:A)
      CREATE (a)-[:T]->(:B),
             (a)-[:T]->(:C)
      """
    */
    @Test
    def void testPatternComprehension_02() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN [p = (n)-->() | p] AS ps
        ''')
    }

    /*
    Scenario: Returning a pattern comprehension with label predicate
    Given an empty graph
    And having executed:
      """
      CREATE (a:A), (b:B), (c:C), (d:D)
      CREATE (a)-[:T]->(b),
             (a)-[:T]->(c),
             (a)-[:T]->(d)
      """
    */
    @Test
    def void testPatternComprehension_03() {
        RelalgParser.parse('''
        MATCH (n:A)
        RETURN [p = (n)-->(:B) | p]
        ''')
    }

    /*
    Scenario: Returning a pattern comprehension with bound nodes
    Given an empty graph
    And having executed:
      """
      CREATE (a:A), (b:B)
      CREATE (a)-[:T]->(b)
      """
    */
    @Test
    def void testPatternComprehension_04() {
        RelalgParser.parse('''
        MATCH (a:A), (b:B)
        RETURN [p = (a)-[*]->(b) | p] AS paths
        ''')
    }

    /*
    Scenario: Using a pattern comprehension in a WITH
    Given an empty graph
    And having executed:
      """
      CREATE (a:A)
      CREATE (a)-[:T]->(:B),
             (a)-[:T]->(:C)
      """
    */
    @Test
    def void testPatternComprehension_05() {
        RelalgParser.parse('''
        MATCH (n)-->(b)
        WITH [p = (n)-->() | p] AS ps, count(b) AS c
        RETURN ps, c
        ''')
    }

    /*
    Scenario: Using a variable-length pattern comprehension in a WITH
    Given an empty graph
    And having executed:
      """
      CREATE (:A)-[:T]->(:B)
      """
    */
    @Test
    def void testPatternComprehension_06() {
        RelalgParser.parse('''
        MATCH (a:A), (b:B)
        WITH [p = (a)-[*]->(b) | p] AS paths, count(a) AS c
        RETURN paths, c
        ''')
    }

    /*
    Scenario: Using pattern comprehension in RETURN
    Given an empty graph
    And having executed:
      """
      CREATE (a:A), (:A), (:A)
      CREATE (a)-[:HAS]->()
      """
    */
    @Test
    def void testPatternComprehension_07() {
        RelalgParser.parse('''
        MATCH (n:A)
        RETURN [p = (n)-[:HAS]->() | p] AS ps
        ''')
    }

    /*
    Scenario: Aggregating on pattern comprehension
    Given an empty graph
    And having executed:
      """
      CREATE (a:A), (:A), (:A)
      CREATE (a)-[:HAS]->()
      """
    */
    @Test
    def void testPatternComprehension_08() {
        RelalgParser.parse('''
        MATCH (n:A)
        RETURN count([p = (n)-[:HAS]->() | p]) AS c
        ''')
    }

    /*
    Scenario: Using pattern comprehension to test existence
    Given an empty graph
    And having executed:
      """
      CREATE (a:X {prop: 42}), (:X {prop: 43})
      CREATE (a)-[:T]->()
      """
    */
    @Test
    def void testPatternComprehension_09() {
        RelalgParser.parse('''
        MATCH (n:X)
        RETURN n, size([(n)--() | 1]) > 0 AS b
        ''')
    }

    /*
    Scenario: Pattern comprehension inside list comprehension
    Given an empty graph
    And having executed:
      """
      CREATE (n1:X {n: 1}), (m1:Y), (i1:Y), (i2:Y)
      CREATE (n1)-[:T]->(m1),
             (m1)-[:T]->(i1),
             (m1)-[:T]->(i2)
      CREATE (n2:X {n: 2}), (m2), (i3:L), (i4:Y)
      CREATE (n2)-[:T]->(m2),
             (m2)-[:T]->(i3),
             (m2)-[:T]->(i4)
      """
    */
    @Test
    def void testPatternComprehension_10() {
        RelalgParser.parse('''
        MATCH p = (n:X)-->(b)
        RETURN n, [x IN nodes(p) | size([(x)-->(:Y) | 1])] AS list
        ''')
    }

    /*
    Scenario: Get node degree via size of pattern comprehension
    Given an empty graph
    And having executed:
      """
      CREATE (x:X),
        (x)-[:T]->(),
        (x)-[:T]->(),
        (x)-[:T]->()
      """
    */
    @Test
    def void testPatternComprehension_11() {
        RelalgParser.parse('''
        MATCH (a:X)
        RETURN size([(a)-->() | 1]) AS length
        ''')
    }

    /*
    Scenario: Get node degree via size of pattern comprehension that specifies a relationship type
    Given an empty graph
    And having executed:
      """
      CREATE (x:X),
        (x)-[:T]->(),
        (x)-[:T]->(),
        (x)-[:T]->(),
        (x)-[:OTHER]->()
      """
    */
    @Test
    def void testPatternComprehension_12() {
        RelalgParser.parse('''
        MATCH (a:X)
        RETURN size([(a)-[:T]->() | 1]) AS length
        ''')
    }

    /*
    Scenario: Get node degree via size of pattern comprehension that specifies multiple relationship types
    Given an empty graph
    And having executed:
      """
      CREATE (x:X),
        (x)-[:T]->(),
        (x)-[:T]->(),
        (x)-[:T]->(),
        (x)-[:OTHER]->()
      """
    */
    @Test
    def void testPatternComprehension_13() {
        RelalgParser.parse('''
        MATCH (a:X)
        RETURN size([(a)-[:T|OTHER]->() | 1]) AS length
        ''')
    }

    /*
    Scenario: Introducing new node variable in pattern comprehension
    Given an empty graph
    And having executed:
      """
      CREATE (a), (b {prop: 'val'})
      CREATE (a)-[:T]->(b)
      """
    */
    @Test
    def void testPatternComprehension_14() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN [(n)-[:T]->(b) | b.prop] AS list
        ''')
    }

    /*
    Scenario: Introducing new relationship variable in pattern comprehension
    Given an empty graph
    And having executed:
      """
      CREATE (a), (b)
      CREATE (a)-[:T {prop: 'val'}]->(b)
      """
    */
    @Test
    def void testPatternComprehension_15() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN [(n)-[r:T]->() | r.prop] AS list
        ''')
    }

}
