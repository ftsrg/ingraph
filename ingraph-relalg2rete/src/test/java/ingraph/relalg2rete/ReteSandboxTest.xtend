package ingraph.relalg2rete

import org.junit.Test

class ReteSandboxTest extends Cypher2Relalg2Rete2TexTest {

	override protected directory() {
		return "sandbox"
	}

	@Test
	def void q1() {
		process('query-1', '''
			MATCH (comment:Comment)-[r:replyOf*]->(message:Message)
			RETURN message, r, comment
		''')
	}

	@Test
	def void q2() {
		process('query-2', '''
			MATCH (person:Person)-[created:created]->(comment:Comment)-[r:replyOf*]->(message:Message)
			RETURN person, created, message, r, comment
		''')
	}

}