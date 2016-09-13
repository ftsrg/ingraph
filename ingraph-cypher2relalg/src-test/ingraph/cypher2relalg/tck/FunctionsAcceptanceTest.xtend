package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class FunctionsAcceptanceTest {
    
    /*
    Scenario: Run coalesce
    Given an empty graph
    And having executed:
      """
      CREATE ({name: 'Emil Eifrem', title: 'CEO'}), ({name: 'Nobody'})
      """
    */
    @Test
    def void testFunctionsAcceptance_01() {
        RelalgParser.parse('''
        MATCH (a)
        RETURN coalesce(a.title, a.name)
        ''')
    }

    /*
    Scenario: Functions should return null if they get path containing unbound
    Given any graph
    */
    @Test
    def void testFunctionsAcceptance_02() {
        RelalgParser.parse('''
        WITH null AS a
        OPTIONAL MATCH p = (a)-[r]->()
        RETURN length(nodes(p)), type(r), nodes(p), relationships(p)
        ''')
    }

    /*
    Scenario: `split()`
    Given any graph
    */
    @Test
    def void testFunctionsAcceptance_03() {
        RelalgParser.parse('''
        UNWIND split('one1two', '1') AS item
        RETURN count(item) AS item
        ''')
    }

    /*
    Scenario: `properties()` on a node
    Given an empty graph
    And having executed:
      """
      CREATE (n:Person {name: 'Popeye', level: 9001})
      """
    */
    @Test
    def void testFunctionsAcceptance_04() {
        RelalgParser.parse('''
        MATCH (p:Person)
        RETURN properties(p) AS m
        ''')
    }

    /*
    Scenario: `properties()` on a relationship
    Given an empty graph
    And having executed:
      """
      CREATE (n)-[:R {name: 'Popeye', level: 9001}]->(n)
      """
    */
    @Test
    def void testFunctionsAcceptance_05() {
        RelalgParser.parse('''
        MATCH ()-[r:R]->()
        RETURN properties(r) AS m
        ''')
    }

    /*
    Scenario: `properties()` on a map
    Given any graph
    */
    @Test
    def void testFunctionsAcceptance_06() {
        RelalgParser.parse('''
        RETURN properties({name: 'Popeye', level: 9001}) AS m
        ''')
    }

    /*
    Scenario: `properties()` on null
    Given any graph
    */
    @Test
    def void testFunctionsAcceptance_07() {
        RelalgParser.parse('''
        RETURN properties(null)
        ''')
    }

    /*
    Scenario: `reverse()`
    Given any graph
    */
    @Test
    def void testFunctionsAcceptance_08() {
        RelalgParser.parse('''
        RETURN reverse('raksO')
        ''')
    }

    /*
    Scenario: `exists()` with dynamic property lookup
    Given an empty graph
    And having executed:
      """
      CREATE (:Person {prop: 'foo'}),
             (:Person)
      """
    */
    @Test
    def void testFunctionsAcceptance_09() {
        RelalgParser.parse('''
        MATCH (n:Person)
        WHERE exists(n['prop'])
        RETURN n
        ''')
    }

    /*
    Scenario: `percentileDisc()` failing in more involved query
    Given an empty graph
    And having executed:
      """
      UNWIND range(0, 10) AS i
      CREATE (s:S)
      WITH s, i
      UNWIND range(0, i) AS j
      CREATE (s)-[:REL]->()
      """
    */
    @Test
    def void testFunctionsAcceptance_10() {
        RelalgParser.parse('''
        MATCH (n:S)
        WITH n, size([(n)-->() | 1]) AS deg
        WHERE deg > 2
        WITH deg
        LIMIT 100
        RETURN percentileDisc(0.90, deg), deg
        ''')
    }

    /*
    Scenario: `type()`
    Given an empty graph
    And having executed:
      """
      CREATE ()-[:T]->()
      """
    */
    @Test
    def void testFunctionsAcceptance_11() {
        RelalgParser.parse('''
        MATCH ()-[r]->()
        RETURN type(r)
        ''')
    }

    /*
    Scenario: `type()` on two relationships
    Given an empty graph
    And having executed:
      """
      CREATE ()-[:T1]->()-[:T2]->()
      """
    */
    @Test
    def void testFunctionsAcceptance_12() {
        RelalgParser.parse('''
        MATCH ()-[r1]->()-[r2]->()
        RETURN type(r1), type(r2)
        ''')
    }

    /*
    Scenario: `type()` on null relationship
    Given an empty graph
    And having executed:
      """
      CREATE ()
      """
    */
    @Test
    def void testFunctionsAcceptance_13() {
        RelalgParser.parse('''
        MATCH (a)
        OPTIONAL MATCH (a)-[r:NOT_THERE]->()
        RETURN type(r)
        ''')
    }

    /*
    Scenario: `type()` on mixed null and non-null relationships
    Given an empty graph
    And having executed:
      """
      CREATE ()-[:T]->()
      """
    */
    @Test
    def void testFunctionsAcceptance_14() {
        RelalgParser.parse('''
        MATCH (a)
        OPTIONAL MATCH (a)-[r:T]->()
        RETURN type(r)
        ''')
    }

    /*
    Scenario: `type()` handling Any type
    Given an empty graph
    And having executed:
      """
      CREATE ()-[:T]->()
      """
    */
    @Test
    def void testFunctionsAcceptance_15() {
        RelalgParser.parse('''
        MATCH (a)-[r]->()
        WITH [r, 1] AS list
        RETURN type(list[0])
        ''')
    }

    /*
    Scenario: `labels()` should accept type Any
    Given an empty graph
    And having executed:
      """
      CREATE (:Foo), (:Foo:Bar)
      """
    */
    @Test
    def void testFunctionsAcceptance_16() {
        RelalgParser.parse('''
        MATCH (a)
        WITH [a, 1] AS list
        RETURN labels(list[0]) AS l
        ''')
    }

    /*
    Scenario: `labels()` should accept type Any
    Given an empty graph
    And having executed:
      """
      CREATE (:Foo), (:Foo:Bar)
      """
    */
    @Test
    def void testFunctionsAcceptance_17() {
        RelalgParser.parse('''
        MATCH (a)
        WITH [a, 1] AS list
        RETURN labels(list[1]) AS l
        ''')
    }

    /*
    Scenario: `exists()` is case insensitive
    Given an empty graph
    And having executed:
      """
      CREATE (a:X {prop: 42}), (:X)
      """
    */
    @Test
    def void testFunctionsAcceptance_18() {
        RelalgParser.parse('''
        MATCH (n:X)
        RETURN n, EXIsTS(n.prop) AS b
        ''')
    }

}
