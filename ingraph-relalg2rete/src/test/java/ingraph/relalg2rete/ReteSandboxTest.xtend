package ingraph.relalg2rete

import org.junit.Test

class ReteSandboxTest extends Cypher2Relalg2Rete2TexTest {

	override protected directory() {
		return "sandbox"
	}

	@Test
	def void adbis1() {
		process('adbis-query-1', '''
			MATCH (tag:Tag)<-[:hasTag]-(message:Message)
			RETURN tag.name, count(message) AS countMonth1, length(tag.name) AS tn
			ORDER BY tag.name
		''')
	}

}