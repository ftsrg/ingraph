package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser
import ingraph.relalg2tex.RelalgTreeSerializer

class OptionalMatchAcceptanceVisualizationTest {

    val RelalgTreeSerializer serializer = new RelalgTreeSerializer(true)
    
    /*
    Scenario: Return null when no matches due to inline label predicate
    */
    @Test
    def void testOptionalMatchAcceptance_01() {
        val container = RelalgParser.parse('''
        MATCH (n:Single)
        OPTIONAL MATCH (n)-[r]-(m:NonExistent)
        RETURN r
        ''')
        serializer.serialize(container, "OptionalMatchAcceptance_01")
    }

    /*
    Scenario: Return null when no matches due to label predicate in WHERE
    */
    @Test
    def void testOptionalMatchAcceptance_02() {
        val container = RelalgParser.parse('''
        MATCH (n:Single)
        OPTIONAL MATCH (n)-[r]-(m)
        WHERE m:NonExistent
        RETURN r
        ''')
        serializer.serialize(container, "OptionalMatchAcceptance_02")
    }

    /*
    Scenario: Respect predicates on the OPTIONAL MATCH
    */
    @Test
    def void testOptionalMatchAcceptance_03() {
        val container = RelalgParser.parse('''
        MATCH (n:Single)
        OPTIONAL MATCH (n)-[r]-(m)
        WHERE m.prop = 42
        RETURN m
        ''')
        serializer.serialize(container, "OptionalMatchAcceptance_03")
    }

    /*
    Scenario: Returning label predicate on null node
    */
    @Test
    def void testOptionalMatchAcceptance_04() {
        val container = RelalgParser.parse('''
        MATCH (n:Single)
        OPTIONAL MATCH (n)-[r:TYPE]-(m)
        RETURN m:TYPE
        ''')
        serializer.serialize(container, "OptionalMatchAcceptance_04")
    }

    /*
    Scenario: MATCH after OPTIONAL MATCH
    */
    @Test
    def void testOptionalMatchAcceptance_05() {
        val container = RelalgParser.parse('''
        MATCH (a:Single)
        OPTIONAL MATCH (a)-->(b:NonExistent)
        OPTIONAL MATCH (a)-->(c:NonExistent)
        WITH coalesce(b, c) AS x
        MATCH (x)-->(d)
        RETURN d
        ''')
        serializer.serialize(container, "OptionalMatchAcceptance_05")
    }

    /*
    Scenario: WITH after OPTIONAL MATCH
    */
    @Test
    def void testOptionalMatchAcceptance_06() {
        val container = RelalgParser.parse('''
        OPTIONAL MATCH (a:A)
        WITH a AS a
        MATCH (b:B)
        RETURN a, b
        ''')
        serializer.serialize(container, "OptionalMatchAcceptance_06")
    }

    /*
    Scenario: Named paths in optional matches
    */
    @Test
    def void testOptionalMatchAcceptance_07() {
        val container = RelalgParser.parse('''
        MATCH (a:A)
        OPTIONAL MATCH p = (a)-[:X]->(b)
        RETURN p
        ''')
        serializer.serialize(container, "OptionalMatchAcceptance_07")
    }

    /*
    Scenario: OPTIONAL MATCH and bound nodes
    */
    @Test
    def void testOptionalMatchAcceptance_08() {
        val container = RelalgParser.parse('''
        MATCH (a:A), (b:C)
        OPTIONAL MATCH (x)-->(b)
        RETURN x
        ''')
        serializer.serialize(container, "OptionalMatchAcceptance_08")
    }

    /*
    Scenario: OPTIONAL MATCH with labels on the optional end node
    And having executed:
      """
      CREATE (:X), (x:X), (y1:Y), (y2:Y:Z)
      CREATE (x)-[:REL]->(y1),
             (x)-[:REL]->(y2)
      """
    */
    @Test
    def void testOptionalMatchAcceptance_09() {
        val container = RelalgParser.parse('''
        MATCH (a:X)
        OPTIONAL MATCH (a)-->(b:Y)
        RETURN b
        ''')
        serializer.serialize(container, "OptionalMatchAcceptance_09")
    }

    /*
    Scenario: Named paths inside optional matches with node predicates
    */
    @Test
    def void testOptionalMatchAcceptance_10() {
        val container = RelalgParser.parse('''
        MATCH (a:A), (b:B)
        OPTIONAL MATCH p = (a)-[:X]->(b)
        RETURN p
        ''')
        serializer.serialize(container, "OptionalMatchAcceptance_10")
    }

    /*
    Scenario: Variable length optional relationships
    */
    @Test
    def void testOptionalMatchAcceptance_11() {
        val container = RelalgParser.parse('''
        MATCH (a:Single)
        OPTIONAL MATCH (a)-[*]->(b)
        RETURN b
        ''')
        serializer.serialize(container, "OptionalMatchAcceptance_11")
    }

    /*
    Scenario: Variable length optional relationships with length predicates
    */
    @Test
    def void testOptionalMatchAcceptance_12() {
        val container = RelalgParser.parse('''
        MATCH (a:Single)
        OPTIONAL MATCH (a)-[*3..]-(b)
        RETURN b
        ''')
        serializer.serialize(container, "OptionalMatchAcceptance_12")
    }

    /*
    Scenario: Optionally matching self-loops
    */
    @Test
    def void testOptionalMatchAcceptance_13() {
        val container = RelalgParser.parse('''
        MATCH (a:B)
        OPTIONAL MATCH (a)-[r]-(a)
        RETURN r
        ''')
        serializer.serialize(container, "OptionalMatchAcceptance_13")
    }

    /*
    Scenario: Optionally matching self-loops without matches
    */
    @Test
    def void testOptionalMatchAcceptance_14() {
        val container = RelalgParser.parse('''
        MATCH (a)
        WHERE NOT (a:B)
        OPTIONAL MATCH (a)-[r]->(a)
        RETURN r
        ''')
        serializer.serialize(container, "OptionalMatchAcceptance_14")
    }

    /*
    Scenario: Variable length optional relationships with bound nodes
    */
    @Test
    def void testOptionalMatchAcceptance_15() {
        val container = RelalgParser.parse('''
        MATCH (a:Single), (x:C)
        OPTIONAL MATCH (a)-[*]->(x)
        RETURN x
        ''')
        serializer.serialize(container, "OptionalMatchAcceptance_15")
    }

    /*
    Scenario: Variable length optional relationships with bound nodes, no matches
    */
    @Test
    def void testOptionalMatchAcceptance_16() {
        val container = RelalgParser.parse('''
        MATCH (a:A), (b:B)
        OPTIONAL MATCH p = (a)-[*]->(b)
        RETURN p
        ''')
        serializer.serialize(container, "OptionalMatchAcceptance_16")
    }

    /*
    Scenario: Longer pattern with bound nodes
    */
    @Test
    def void testOptionalMatchAcceptance_17() {
        val container = RelalgParser.parse('''
        MATCH (a:Single), (c:C)
        OPTIONAL MATCH (a)-->(b)-->(c)
        RETURN b
        ''')
        serializer.serialize(container, "OptionalMatchAcceptance_17")
    }

    /*
    Scenario: Longer pattern with bound nodes without matches
    */
    @Test
    def void testOptionalMatchAcceptance_18() {
        val container = RelalgParser.parse('''
        MATCH (a:A), (c:C)
        OPTIONAL MATCH (a)-->(b)-->(c)
        RETURN b
        ''')
        serializer.serialize(container, "OptionalMatchAcceptance_18")
    }

    /*
    Scenario: Handling correlated optional matches; first does not match implies second does not match
    */
    @Test
    def void testOptionalMatchAcceptance_19() {
        val container = RelalgParser.parse('''
        MATCH (a:A), (b:B)
        OPTIONAL MATCH (a)-->(x)
        OPTIONAL MATCH (x)-[r]->(b)
        RETURN x, r
        ''')
        serializer.serialize(container, "OptionalMatchAcceptance_19")
    }

    /*
    Scenario: Handling optional matches between optionally matched entities
    */
    @Test
    def void testOptionalMatchAcceptance_20() {
        val container = RelalgParser.parse('''
        OPTIONAL MATCH (a:NotThere)
        WITH a
        MATCH (b:B)
        WITH a, b
        OPTIONAL MATCH (b)-[r:NOR_THIS]->(a)
        RETURN a, b, r
        ''')
        serializer.serialize(container, "OptionalMatchAcceptance_20")
    }

    /*
    Scenario: Handling optional matches between nulls
    */
    @Test
    def void testOptionalMatchAcceptance_21() {
        val container = RelalgParser.parse('''
        OPTIONAL MATCH (a:NotThere)
        OPTIONAL MATCH (b:NotThere)
        WITH a, b
        OPTIONAL MATCH (b)-[r:NOR_THIS]->(a)
        RETURN a, b, r
        ''')
        serializer.serialize(container, "OptionalMatchAcceptance_21")
    }

    /*
    Scenario: OPTIONAL MATCH and `collect()`
    And having executed:
      """
      CREATE (:DoesExist {property: 42})
      CREATE (:DoesExist {property: 43})
      CREATE (:DoesExist {property: 44})
      """
    */
    @Test
    def void testOptionalMatchAcceptance_22() {
        val container = RelalgParser.parse('''
        OPTIONAL MATCH (f:DoesExist)
        OPTIONAL MATCH (n:DoesNotExist)
        RETURN collect(DISTINCT n.property) AS a, collect(DISTINCT f.property) AS b
        ''')
        serializer.serialize(container, "OptionalMatchAcceptance_22")
    }

}
