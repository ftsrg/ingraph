package ingraph.cypher2.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class TriadicSelectionParserTest {
    
    /*
    Scenario: Handling triadic friend of a friend
    Given the binary-tree-1 graph
    */
    @Test
    def void testTriadicSelection_01() {
        RelalgParser.parse('''
        MATCH (a:A)-[:KNOWS]->(b)-->(c)
        RETURN c.name
        ''')
    }

    /*
    Scenario: Handling triadic friend of a friend that is not a friend
    Given the binary-tree-1 graph
    */
    @Test
    def void testTriadicSelection_02() {
        RelalgParser.parse('''
        MATCH (a:A)-[:KNOWS]->(b)-->(c)
        OPTIONAL MATCH (a)-[r:KNOWS]->(c)
        WITH c WHERE r IS NULL
        RETURN c.name
        ''')
    }

    /*
    Scenario: Handling triadic friend of a friend that is not a friend with different relationship type
    Given the binary-tree-1 graph
    */
    @Test
    def void testTriadicSelection_03() {
        RelalgParser.parse('''
        MATCH (a:A)-[:KNOWS]->(b)-->(c)
        OPTIONAL MATCH (a)-[r:FOLLOWS]->(c)
        WITH c WHERE r IS NULL
        RETURN c.name
        ''')
    }

    /*
    Scenario: Handling triadic friend of a friend that is not a friend with superset of relationship type
    Given the binary-tree-1 graph
    */
    @Test
    def void testTriadicSelection_04() {
        RelalgParser.parse('''
        MATCH (a:A)-[:KNOWS]->(b)-->(c)
        OPTIONAL MATCH (a)-[r]->(c)
        WITH c WHERE r IS NULL
        RETURN c.name
        ''')
    }

    /*
    Scenario: Handling triadic friend of a friend that is not a friend with implicit subset of relationship type
    Given the binary-tree-1 graph
    */
    @Test
    def void testTriadicSelection_05() {
        RelalgParser.parse('''
        MATCH (a:A)-->(b)-->(c)
        OPTIONAL MATCH (a)-[r:KNOWS]->(c)
        WITH c WHERE r IS NULL
        RETURN c.name
        ''')
    }

    /*
    Scenario: Handling triadic friend of a friend that is not a friend with explicit subset of relationship type
    Given the binary-tree-1 graph
    */
    @Test
    def void testTriadicSelection_06() {
        RelalgParser.parse('''
        MATCH (a:A)-[:KNOWS|FOLLOWS]->(b)-->(c)
        OPTIONAL MATCH (a)-[r:KNOWS]->(c)
        WITH c WHERE r IS NULL
        RETURN c.name
        ''')
    }

    /*
    Scenario: Handling triadic friend of a friend that is not a friend with same labels
    Given the binary-tree-2 graph
    */
    @Test
    def void testTriadicSelection_07() {
        RelalgParser.parse('''
        MATCH (a:A)-[:KNOWS]->(b:X)-->(c:X)
        OPTIONAL MATCH (a)-[r:KNOWS]->(c)
        WITH c WHERE r IS NULL
        RETURN c.name
        ''')
    }

    /*
    Scenario: Handling triadic friend of a friend that is not a friend with different labels
    Given the binary-tree-2 graph
    */
    @Test
    def void testTriadicSelection_08() {
        RelalgParser.parse('''
        MATCH (a:A)-[:KNOWS]->(b:X)-->(c:Y)
        OPTIONAL MATCH (a)-[r:KNOWS]->(c)
        WITH c WHERE r IS NULL
        RETURN c.name
        ''')
    }

    /*
    Scenario: Handling triadic friend of a friend that is not a friend with implicit subset of labels
    Given the binary-tree-2 graph
    */
    @Test
    def void testTriadicSelection_09() {
        RelalgParser.parse('''
        MATCH (a:A)-[:KNOWS]->(b)-->(c:X)
        OPTIONAL MATCH (a)-[r:KNOWS]->(c)
        WITH c WHERE r IS NULL
        RETURN c.name
        ''')
    }

    /*
    Scenario: Handling triadic friend of a friend that is not a friend with implicit superset of labels
    Given the binary-tree-2 graph
    */
    @Test
    def void testTriadicSelection_10() {
        RelalgParser.parse('''
        MATCH (a:A)-[:KNOWS]->(b:X)-->(c)
        OPTIONAL MATCH (a)-[r:KNOWS]->(c)
        WITH c WHERE r IS NULL
        RETURN c.name
        ''')
    }

    /*
    Scenario: Handling triadic friend of a friend that is a friend
    Given the binary-tree-2 graph
    */
    @Test
    def void testTriadicSelection_11() {
        RelalgParser.parse('''
        MATCH (a:A)-[:KNOWS]->(b)-->(c)
        OPTIONAL MATCH (a)-[r:KNOWS]->(c)
        WITH c WHERE r IS NOT NULL
        RETURN c.name
        ''')
    }

    /*
    Scenario: Handling triadic friend of a friend that is a friend with different relationship type
    Given the binary-tree-1 graph
    */
    @Test
    def void testTriadicSelection_12() {
        RelalgParser.parse('''
        MATCH (a:A)-[:KNOWS]->(b)-->(c)
        OPTIONAL MATCH (a)-[r:FOLLOWS]->(c)
        WITH c WHERE r IS NOT NULL
        RETURN c.name
        ''')
    }

    /*
    Scenario: Handling triadic friend of a friend that is a friend with superset of relationship type
    Given the binary-tree-1 graph
    */
    @Test
    def void testTriadicSelection_13() {
        RelalgParser.parse('''
        MATCH (a:A)-[:KNOWS]->(b)-->(c)
        OPTIONAL MATCH (a)-[r]->(c)
        WITH c WHERE r IS NOT NULL
        RETURN c.name
        ''')
    }

    /*
    Scenario: Handling triadic friend of a friend that is a friend with implicit subset of relationship type
    Given the binary-tree-1 graph
    */
    @Test
    def void testTriadicSelection_14() {
        RelalgParser.parse('''
        MATCH (a:A)-->(b)-->(c)
        OPTIONAL MATCH (a)-[r:KNOWS]->(c)
        WITH c WHERE r IS NOT NULL
        RETURN c.name
        ''')
    }

    /*
    Scenario: Handling triadic friend of a friend that is a friend with explicit subset of relationship type
    Given the binary-tree-1 graph
    */
    @Test
    def void testTriadicSelection_15() {
        RelalgParser.parse('''
        MATCH (a:A)-[:KNOWS|FOLLOWS]->(b)-->(c)
        OPTIONAL MATCH (a)-[r:KNOWS]->(c)
        WITH c WHERE r IS NOT NULL
        RETURN c.name
        ''')
    }

    /*
    Scenario: Handling triadic friend of a friend that is a friend with same labels
    Given the binary-tree-2 graph
    */
    @Test
    def void testTriadicSelection_16() {
        RelalgParser.parse('''
        MATCH (a:A)-[:KNOWS]->(b:X)-->(c:X)
        OPTIONAL MATCH (a)-[r:KNOWS]->(c)
        WITH c WHERE r IS NOT NULL
        RETURN c.name
        ''')
    }

    /*
    Scenario: Handling triadic friend of a friend that is a friend with different labels
    Given the binary-tree-2 graph
    */
    @Test
    def void testTriadicSelection_17() {
        RelalgParser.parse('''
        MATCH (a:A)-[:KNOWS]->(b:X)-->(c:Y)
        OPTIONAL MATCH (a)-[r:KNOWS]->(c)
        WITH c WHERE r IS NOT NULL
        RETURN c.name
        ''')
    }

    /*
    Scenario: Handling triadic friend of a friend that is a friend with implicit subset of labels
    Given the binary-tree-2 graph
    */
    @Test
    def void testTriadicSelection_18() {
        RelalgParser.parse('''
        MATCH (a:A)-[:KNOWS]->(b)-->(c:X)
        OPTIONAL MATCH (a)-[r:KNOWS]->(c)
        WITH c WHERE r IS NOT NULL
        RETURN c.name
        ''')
    }

    /*
    Scenario: Handling triadic friend of a friend that is a friend with implicit superset of labels
    Given the binary-tree-2 graph
    */
    @Test
    def void testTriadicSelection_19() {
        RelalgParser.parse('''
        MATCH (a:A)-[:KNOWS]->(b:X)-->(c)
        OPTIONAL MATCH (a)-[r:KNOWS]->(c)
        WITH c WHERE r IS NOT NULL
        RETURN c.name
        ''')
    }

}
