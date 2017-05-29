package ingraph.search2rete

import org.junit.Test

class ReteSandboxTest extends Cypher2Search2Rete2TexTest {

	override protected directory() {
		return "sandbox"
	}

	@Test
	def void q1() {
		process('query-1', '''
		MATCH (comment:Comment)-[r:replyOf*]->(message:Message)
		RETURN comment.content, message.content
		ORDER BY comment.content, message.content
		''')
	}

}