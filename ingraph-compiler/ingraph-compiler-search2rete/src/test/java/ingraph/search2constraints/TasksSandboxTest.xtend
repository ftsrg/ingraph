package ingraph.search2constraints

import org.junit.Test

class TasksSandboxTest extends Search2ConstraintsTransformationTest {

	override protected directory() {
		return "sandbox"
	}

	@Test
	def void q1() {
		process('query-1', '''
			MATCH (node)
			RETURN exists(node.attr)
		''')
	}

	@Test
	def void q2() {
		process('query-2', '''
		MATCH (tag:Tag)<-[:hasTag]-(:Message)<-[:replyOf*]-(comment:Comment)
		RETURN tag.name
		''')
	}
	
	@Test
	def void q3() {
		process('query-3', '''
		MATCH (country:Country)<-[:isPartOf]-(city:City)<-[:isLocatedIn]-(person:Person)-[:knows*1..2]-(:Person)
		RETURN person.id
		''')
	}
	
	@Test
	def void q4() {
		process('query-4', '''
		MATCH (p1:Person)-[:knows*1..2]->(p2:Person)
		RETURN p1.id, p2.id
	''')
	}

}