package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser
import ingraph.relalg2tex.RelAlgTreeDrawer

class FunctionsAcceptanceVisualizationTest {

    val static RelAlgTreeDrawer drawer = new RelAlgTreeDrawer(true)
    
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
        val container = RelalgParser.parse('''
        MATCH (a)
        RETURN coalesce(a.title, a.name)
        ''')
        drawer.serialize(container, "FunctionsAcceptance_01")
    }

    /*
    Scenario: Functions should return null if they get path containing unbound
    Given any graph
    */
    @Test
    def void testFunctionsAcceptance_02() {
        val container = RelalgParser.parse('''
        WITH null AS a
        OPTIONAL MATCH p = (a)-[r]->()
        RETURN length(nodes(p)), type(r), nodes(p), relationships(p)
        ''')
        drawer.serialize(container, "FunctionsAcceptance_02")
    }

    /*
    Scenario: `split()`
    Given any graph
    */
    @Test
    def void testFunctionsAcceptance_03() {
        val container = RelalgParser.parse('''
        UNWIND split('one1two', '1') AS item
        RETURN count(item) AS item
        ''')
        drawer.serialize(container, "FunctionsAcceptance_03")
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
        val container = RelalgParser.parse('''
        MATCH (p:Person)
        RETURN properties(p) AS m
        ''')
        drawer.serialize(container, "FunctionsAcceptance_04")
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
        val container = RelalgParser.parse('''
        MATCH ()-[r:R]->()
        RETURN properties(r) AS m
        ''')
        drawer.serialize(container, "FunctionsAcceptance_05")
    }

    /*
    Scenario: `properties()` on a map
    Given any graph
    */
    @Test
    def void testFunctionsAcceptance_06() {
        val container = RelalgParser.parse('''
        RETURN properties({name: 'Popeye', level: 9001}) AS m
        ''')
        drawer.serialize(container, "FunctionsAcceptance_06")
    }

    /*
    Scenario: `properties()` on null
    Given any graph
    */
    @Test
    def void testFunctionsAcceptance_07() {
        val container = RelalgParser.parse('''
        RETURN properties(null)
        ''')
        drawer.serialize(container, "FunctionsAcceptance_07")
    }

    /*
    Scenario: `reverse()`
    Given any graph
    */
    @Test
    def void testFunctionsAcceptance_08() {
        val container = RelalgParser.parse('''
        RETURN reverse('raksO')
        ''')
        drawer.serialize(container, "FunctionsAcceptance_08")
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
        val container = RelalgParser.parse('''
        MATCH (n:Person)
        WHERE exists(n['prop'])
        RETURN n
        ''')
        drawer.serialize(container, "FunctionsAcceptance_09")
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
        val container = RelalgParser.parse('''
        MATCH (n:S)
        WITH n, size([(n)-->() | 1]) AS deg
        WHERE deg > 2
        WITH deg
        LIMIT 100
        RETURN percentileDisc(0.90, deg), deg
        ''')
        drawer.serialize(container, "FunctionsAcceptance_10")
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
        val container = RelalgParser.parse('''
        MATCH ()-[r]->()
        RETURN type(r)
        ''')
        drawer.serialize(container, "FunctionsAcceptance_11")
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
        val container = RelalgParser.parse('''
        MATCH ()-[r1]->()-[r2]->()
        RETURN type(r1), type(r2)
        ''')
        drawer.serialize(container, "FunctionsAcceptance_12")
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
        val container = RelalgParser.parse('''
        MATCH (a)
        OPTIONAL MATCH (a)-[r:NOT_THERE]->()
        RETURN type(r)
        ''')
        drawer.serialize(container, "FunctionsAcceptance_13")
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
        val container = RelalgParser.parse('''
        MATCH (a)
        OPTIONAL MATCH (a)-[r:T]->()
        RETURN type(r)
        ''')
        drawer.serialize(container, "FunctionsAcceptance_14")
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
        val container = RelalgParser.parse('''
        MATCH (a)-[r]->()
        WITH [r, 1] AS list
        RETURN type(list[0])
        ''')
        drawer.serialize(container, "FunctionsAcceptance_15")
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
        val container = RelalgParser.parse('''
        MATCH (a)
        WITH [a, 1] AS list
        RETURN labels(list[0]) AS l
        ''')
        drawer.serialize(container, "FunctionsAcceptance_16")
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
        val container = RelalgParser.parse('''
        MATCH (a)
        WITH [a, 1] AS list
        RETURN labels(list[1]) AS l
        ''')
        drawer.serialize(container, "FunctionsAcceptance_17")
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
        val container = RelalgParser.parse('''
        MATCH (n:X)
        RETURN n, EXIsTS(n.prop) AS b
        ''')
        drawer.serialize(container, "FunctionsAcceptance_18")
    }

}
