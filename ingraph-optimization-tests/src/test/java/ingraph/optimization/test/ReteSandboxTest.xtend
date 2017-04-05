package ingraph.optimization.test

import org.junit.Test

class ReteSandboxTest extends Cypher2Relalg2Rete2TexTest {

	override protected directory() {
		return "sandbox"
	}

	@Test
	def void queryx() {
		process('queryx', '''
			// Tag evolution
			MATCH (tag:Tag)<-[:hasTag]-(message:Message)
			RETURN tag, count(message) AS countMonth1, length(tag.name) AS tn
			ORDER BY tag.name
		''')
	}

	@Test
	def void query4() { processFile('query-4', 'ldbc-snb-bi/query-4') }

}
