package ingraph.search2rete

import org.junit.Test

class ReteSandboxTest extends Cypher2Search2Rete2TexTest {

	override protected directory() {
		return "sandbox"
	}

	@Test
	def void q1() {
		process('query-1', '''
		MATCH (tag:Tag)<-[:hasTag]-(message:Message)<-[:replyOf*]-(comment:Comment)
		RETURN tag.name
		''')
	}

	@Test
	def void q2() {
		process('query-2', '''
		MATCH (tag:Tag)<-[:hasTag]-(:Message)<-[:replyOf*]-(comment:Comment)
		RETURN tag.name
		''')
	}

}