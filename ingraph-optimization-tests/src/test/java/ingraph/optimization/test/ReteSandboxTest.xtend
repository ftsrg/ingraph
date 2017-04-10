package ingraph.optimization.test

import org.junit.Test

class ReteSandboxTest extends Cypher2Relalg2Rete2TexTest {

	override protected directory() {
		return "sandbox"
	}

	@Test
	def void queryx() {
		process('queryx', '''
			MATCH (tag)<-[:hasTag]-(message)
			RETURN tag, length(tag.name) AS tn, count(message) AS countMonth1
			ORDER BY tag.name
		''')
	}

	@Test
	def void query4() { processFile('query-4', 'ldbc-snb-bi/query-4') }

	@Test
	def void query23() { processFile('query-23', 'ldbc-snb-bi/query-23') }

}
