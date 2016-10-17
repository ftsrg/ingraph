package ingraph.cypher2relalg.cud

import ingraph.cypher2relalg.RelalgParser
import org.junit.Test

class CudOperationsTest {

	/*
	 * Scenario: Creating a node
	 * Given any graph
	 */
	@Test
	def void testCreate_01() {
		RelalgParser.parse('''
			CREATE ()
		''')
	}

	/*
	 * Scenario: Creating two nodes
	 * Given any graph
	 */
	@Test
	def void testCreate_02() {
		RelalgParser.parse('''
			CREATE (), ()
		''')
	}

	/*
	 * Scenario: Creating two nodes and a relationship
	 * Given any graph
	 */
	@Test
	def void testCreate_03() {
		RelalgParser.parse('''
			CREATE ()-[:TYPE]->()
		''')
	}

	/*
	 * Scenario: Creating a node with a label
	 * Given any graph
	 */
	@Test
	def void testCreate_04() {
		RelalgParser.parse('''
			CREATE (:Label)
		''')
	}

	/*
	 * Scenario: Creating a node with a property
	 * Given any graph
	 */
	@Test
	def void testCreate_05() {
		RelalgParser.parse('''
			CREATE ({created: true})
		''')
	}

	/*
	 * Scenario: Create a single node
	 * Given any graph
	 */
	@Test
	def void testCreateAcceptance_01() {
		RelalgParser.parse('''
			CREATE ()
		''')
	}

	/*
	 * Scenario: Create a single node with a single label
	 * Given any graph
	 */
	@Test
	def void testCreateAcceptance_02() {
		RelalgParser.parse('''
			CREATE (:A)
		''')
	}

	/*
	 * Scenario: Create a single node with multiple labels
	 * Given any graph
	 */
	@Test
	def void testCreateAcceptance_03() {
		RelalgParser.parse('''
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
		RelalgParser.parse('''
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
		RelalgParser.parse('''
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
		RelalgParser.parse('''
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
		RelalgParser.parse('''
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
		RelalgParser.parse('''
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
		RelalgParser.parse('''
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
		RelalgParser.parse('''
			CREATE ()-[:R]->()
		''')
	}

	/*
	 * Scenario: Create a self loop
	 * Given any graph
	 */
	@Test
	def void testCreateAcceptance_11() {
		RelalgParser.parse('''
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
		RelalgParser.parse('''
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
		RelalgParser.parse('''
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
		RelalgParser.parse('''
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
		RelalgParser.parse('''
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
		RelalgParser.parse('''
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
		RelalgParser.parse('''
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
		RelalgParser.parse('''
			CREATE (:A)<-[:R]-(:B)
		''')
	}

	/*
	 * Scenario: Create a pattern with multiple hops
	 * Given an empty graph
	 */
	@Test
	def void testCreateAcceptance_19() {
		RelalgParser.parse('''
			CREATE (:A)-[:R]->(:B)-[:R]->(:C)
		''')
	}

	/*
	 * Scenario: Create a pattern with multiple hops in the reverse direction
	 * Given any graph
	 */
	@Test
	def void testCreateAcceptance_20() {
		RelalgParser.parse('''
			CREATE (:A)<-[:R]-(:B)<-[:R]-(:C)
		''')
	}

	/*
	 * Scenario: Create a pattern with multiple hops in varying directions
	 * Given any graph
	 */
	@Test
	def void testCreateAcceptance_21() {
		RelalgParser.parse('''
			CREATE (:A)-[:R]->(:B)<-[:R]-(:C)
		''')
	}

	/*
	 * Scenario: Create a pattern with multiple hops with multiple types and varying directions
	 * Given any graph
	 */
	@Test
	def void testCreateAcceptance_22() {
		RelalgParser.parse('''
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
		RelalgParser.parse('''
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
		RelalgParser.parse('''
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
		RelalgParser.parse('''
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
		RelalgParser.parse('''
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
		RelalgParser.parse('''
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
		RelalgParser.parse('''
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
		RelalgParser.parse('''
			CREATE (:A)<-[:R1]-(:B)-[:R2]->(:C)
		''')
	}

}
