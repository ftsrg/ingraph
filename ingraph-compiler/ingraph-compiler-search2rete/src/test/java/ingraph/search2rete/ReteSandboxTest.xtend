package ingraph.search2rete

import org.junit.Test

class ReteSandboxTest extends Cypher2Search2Rete2TexTest {

	override protected directory() {
		return "sandbox"
	}

	@Test
	def void q1() {
		process('simple-5', '''
			MATCH
			  (tag:Tag)<-[:hasTag]-(message:Message)<-[:replyOf*]-(comment:Comment)
			RETURN message.content, comment.content, tag.name
			ORDER BY message.content, comment.content, tag.name
		''')
	}

	@Test
	def void q2() {
		process('simple-6', '''
			MATCH
			  (comment:Comment)-[:replyOf*]->(message:Message)-[:hasTag]->(tag:Tag)
			RETURN message.content, comment.content, tag.name
			ORDER BY message.content, comment.content, tag.name
		''')
	}
	
}