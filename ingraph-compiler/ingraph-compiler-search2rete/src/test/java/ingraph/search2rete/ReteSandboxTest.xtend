package ingraph.search2rete

import org.junit.Test

class ReteSandboxTest extends Cypher2Search2Rete2TexTest {

	override protected directory() {
		return "sandbox"
	}

	@Test
	def void q1() {
		process('query-1', '''
		MATCH (message:Message)<-[r:replyOf*]-(comment:Comment)
		RETURN message.content, comment.content
		ORDER BY message.content, comment.content
		''')
	}

}