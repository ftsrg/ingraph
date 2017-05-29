package ingraph.search2rete

import org.junit.Test

class ReteSandboxTest extends Cypher2Search2Rete2TexTest {

	override protected directory() {
		return "sandbox"
	}

	@Test
	def void q1() {
		process('query-1', '''
		MATCH (t:Train)-[r:ON]->(seg1:Segment)-[:NEXT]->(seg2:Segment)
		DELETE r
		CREATE (t)-[:ON]->(seg2)
		''')
	}

	@Test
	def void q2() {
		process('query-2', '''
		MATCH (t:Train {number: 1})-[r:ON]->(seg1:Segment)-[:NEXT]->(seg2:Segment)
		DELETE r
		CREATE (t)-[:ON]->(seg2)
		''')
	}

}