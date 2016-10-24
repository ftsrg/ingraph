package ingraph.cypher2relalg.cud

import ingraph.cypherparser.CypherParser
import org.junit.Test

class CudOperationsTest {

	/*
	 * Scenario: Creating a node
	 * Given any graph
	 */
	@Test
	def void testCreate_01() {
		CypherParser.parseString('''
			CREATE ()
		''')
	}

	/*
	 * Scenario: Creating two nodes
	 * Given any graph
	 */
	@Test
	def void testCreate_02() {
		CypherParser.parseString('''
			CREATE (), ()
		''')
	}

	/*
	 * Scenario: Creating two nodes and a relationship
	 * Given any graph
	 */
	@Test
	def void testCreate_03() {
		CypherParser.parseString('''
			CREATE ()-[:TYPE]->()
		''')
	}

	/*
	 * Scenario: Creating a node with a label
	 * Given any graph
	 */
	@Test
	def void testCreate_04() {
		CypherParser.parseString('''
			CREATE (:Label)
		''')
	}

	/*
	 * Scenario: Creating a node with a property
	 * Given any graph
	 */
	@Test
	def void testCreate_05() {
		CypherParser.parseString('''
			CREATE ({created: true})
		''')
	}

	/*
	 * Scenario: Create a single node
	 * Given any graph
	 */
	@Test
	def void testCreateAcceptance_01() {
		CypherParser.parseString('''
			CREATE ()
		''')
	}

	/*
	 * Scenario: Create a single node with a single label
	 * Given any graph
	 */
	@Test
	def void testCreateAcceptance_02() {
		CypherParser.parseString('''
			CREATE (:A)
		''')
	}

	/*
	 * Scenario: Create a single node with multiple labels
	 * Given any graph
	 */
	@Test
	def void testCreateAcceptance_03() {
		CypherParser.parseString('''
			CREATE (:A:B:C:D)
		''')
	}

	/*
	 * Scenario: Combine MATCH and CREATE
	 * Given an empty graph
	 * And having executed:
	 *   """
	 *   CREATE (), ()
	 *   """
	 */
	@Test
	def void testCreateAcceptance_04() {
		CypherParser.parseString('''
			MATCH ()
			CREATE ()
		''')
	}

	/*
	 * Scenario: Combine MATCH, WITH and CREATE
	 * Given an empty graph
	 * And having executed:
	 *   """
	 *   CREATE (), ()
	 *   """
	 */
	@Test
	def void testCreateAcceptance_05() {
		CypherParser.parseString('''
			MATCH ()
			CREATE ()
			WITH *
			MATCH ()
			CREATE ()
		''')
	}

	/*
	 * Scenario: Newly-created nodes not visible to preceding MATCH
	 * Given an empty graph
	 * And having executed:
	 *   """
	 *   CREATE ()
	 *   """
	 */
	@Test
	def void testCreateAcceptance_06() {
		CypherParser.parseString('''
			MATCH ()
			CREATE ()
		''')
	}

	/*
	 * Scenario: Create a single node with properties
	 * Given any graph
	 */
	@Test
	def void testCreateAcceptance_07() {
		CypherParser.parseString('''
			CREATE (n {prop: 'foo'})
			RETURN n.prop AS p
		''')
	}

	/*
	 * Scenario: Creating a node with null properties should not return those properties
	 * Given any graph
	 */
	@Test
	def void testCreateAcceptance_08() {
		CypherParser.parseString('''
			CREATE (n {id: 12, property: null})
			RETURN n.id AS id
		''')
	}

	/*
	 * Scenario: Creating a relationship with null properties should not return those properties
	 * Given any graph
	 */
	@Test
	def void testCreateAcceptance_09() {
		CypherParser.parseString('''
			CREATE ()-[r:X {id: 12, property: null}]->()
			RETURN r.id
		''')
	}

	/*
	 * Scenario: Create a simple pattern
	 * Given any graph
	 */
	@Test
	def void testCreateAcceptance_10() {
		CypherParser.parseString('''
			CREATE ()-[:R]->()
		''')
	}

	/*
	 * Scenario: Create a self loop
	 * Given any graph
	 */
	@Test
	def void testCreateAcceptance_11() {
		CypherParser.parseString('''
			CREATE (root:R)-[:LINK]->(root)
		''')
	}

	/*
	 * Scenario: Create a self loop using MATCH
	 * Given an empty graph
	 * And having executed:
	 *   """
	 *   CREATE (:R)
	 *   """
	 */
	@Test
	def void testCreateAcceptance_12() {
		CypherParser.parseString('''
			MATCH (root:R)
			CREATE (root)-[:LINK]->(root)
		''')
	}

	/*
	 * Scenario: Create nodes and relationships
	 * Given any graph
	 */
	@Test
	def void testCreateAcceptance_13() {
		CypherParser.parseString('''
			CREATE (a), (b),
			(a)-[:R]->(b)
		''')
	}

	/*
	 * Scenario: Create a relationship with a property
	 * Given any graph
	 */
	@Test
	def void testCreateAcceptance_14() {
		CypherParser.parseString('''
			CREATE ()-[:R {prop: 42}]->()
		''')
	}

	/*
	 * Scenario: Create a relationship with the correct direction
	 * Given an empty graph
	 * And having executed:
	 *   """
	 *   CREATE (:X)
	 *   CREATE (:Y)
	 *   """
	 */
	@Test
	def void testCreateAcceptance_15() {
		CypherParser.parseString('''
			MATCH (x:X), (y:Y)
			CREATE (x)<-[:TYPE]-(y)
		''')
	}

	/*
	 * Scenario: Create a relationship and an end node from a matched starting node
	 * Given an empty graph
	 * And having executed:
	 *   """
	 *   CREATE (:Begin)
	 *   """
	 */
	@Test
	def void testCreateAcceptance_16() {
		CypherParser.parseString('''
			MATCH (x:Begin)
			CREATE (x)-[:TYPE]->(:End)
		''')
	}

	/*
	 * Scenario: Create a single node after a WITH
	 * Given an empty graph
	 * And having executed:
	 *   """
	 *   CREATE (), ()
	 *   """
	 */
	@Test
	def void testCreateAcceptance_17() {
		CypherParser.parseString('''
			MATCH ()
			CREATE ()
			WITH *
			CREATE ()
		''')
	}

	/*
	 * Scenario: Create a relationship with a reversed direction
	 * Given an empty graph
	 */
	@Test
	def void testCreateAcceptance_18() {
		CypherParser.parseString('''
			CREATE (:A)<-[:R]-(:B)
		''')
	}

	/*
	 * Scenario: Create a pattern with multiple hops
	 * Given an empty graph
	 */
	@Test
	def void testCreateAcceptance_19() {
		CypherParser.parseString('''
			CREATE (:A)-[:R]->(:B)-[:R]->(:C)
		''')
	}

	/*
	 * Scenario: Create a pattern with multiple hops in the reverse direction
	 * Given any graph
	 */
	@Test
	def void testCreateAcceptance_20() {
		CypherParser.parseString('''
			CREATE (:A)<-[:R]-(:B)<-[:R]-(:C)
		''')
	}

	/*
	 * Scenario: Create a pattern with multiple hops in varying directions
	 * Given any graph
	 */
	@Test
	def void testCreateAcceptance_21() {
		CypherParser.parseString('''
			CREATE (:A)-[:R]->(:B)<-[:R]-(:C)
		''')
	}

	/*
	 * Scenario: Create a pattern with multiple hops with multiple types and varying directions
	 * Given any graph
	 */
	@Test
	def void testCreateAcceptance_22() {
		CypherParser.parseString('''
			CREATE ()-[:R1]->()<-[:R2]-()-[:R3]->()
		''')
	}

	/*
	 * Scenario: Nodes are not created when aliases are applied to variable names
	 * Given an empty graph
	 * And having executed:
	 *   """
	 *   CREATE ({foo: 1})
	 *   """
	 */
	@Test
	def void testCreateAcceptance_23() {
		CypherParser.parseString('''
			MATCH (n)
			MATCH (m)
			WITH n AS a, m AS b
			CREATE (a)-[:T]->(b)
			RETURN a, b
		''')
	}

	/*
	 * Scenario: Only a single node is created when an alias is applied to a variable name
	 * Given an empty graph
	 * And having executed:
	 *   """
	 *   CREATE (:X)
	 *   """
	 */
	@Test
	def void testCreateAcceptance_24() {
		CypherParser.parseString('''
			MATCH (n)
			WITH n AS a
			CREATE (a)-[:T]->()
			RETURN a
		''')
	}

	/*
	 * Scenario: Nodes are not created when aliases are applied to variable names multiple times
	 * Given an empty graph
	 * And having executed:
	 *   """
	 *   CREATE ({foo: 'A'})
	 *   """
	 */
	@Test
	def void testCreateAcceptance_25() {
		CypherParser.parseString('''
			MATCH (n)
			MATCH (m)
			WITH n AS a, m AS b
			CREATE (a)-[:T]->(b)
			WITH a AS x, b AS y
			CREATE (x)-[:T]->(y)
			RETURN x, y
		''')
	}

	/*
	 * Scenario: Only a single node is created when an alias is applied to a variable name multiple times
	 * Given an empty graph
	 * And having executed:
	 *   """
	 *   CREATE ({foo: 5})
	 *   """
	 */
	@Test
	def void testCreateAcceptance_26() {
		CypherParser.parseString('''
			MATCH (n)
			WITH n AS a
			CREATE (a)-[:T]->()
			WITH a AS x
			CREATE (x)-[:T]->()
			RETURN x
		''')
	}

	/*
	 * Scenario: A bound node should be recognized after projection with WITH + WITH
	 * Given any graph
	 */
	@Test
	def void testCreateAcceptance_27() {
		CypherParser.parseString('''
			CREATE (a)
			WITH a
			WITH *
			CREATE (b)
			CREATE (a)<-[:T]-(b)
		''')
	}

	/*
	 * Scenario: A bound node should be recognized after projection with WITH + UNWIND
	 * Given any graph
	 */
	@Test
	def void testCreateAcceptance_28() {
		CypherParser.parseString('''
			CREATE (a)
			WITH a
			UNWIND [0] AS i
			CREATE (b)
			CREATE (a)<-[:T]-(b)
		''')
	}

	/*
	 * Scenario: Creating a pattern with multiple hops and changing directions
	 * Given an empty graph
	 */
	@Test
	def void testCreateAcceptance_31() {
		CypherParser.parseString('''
			CREATE (:A)<-[:R1]-(:B)-[:R2]->(:C)
		''')
	}

}
