package ingraph.relalg2rete

import org.junit.Test

class ReteSandboxTest extends Cypher2Relalg2Rete2TexTest {

	override protected directory() {
		return "sandbox"
	}

	@Test
	def void adbis1() {
		process('adbis-query-1', '''
			MATCH (m1:Message)
			WITH m1.language AS singleLang, count(*) AS cnt
			WHERE cnt = 1
			MATCH (m2:Message)
			WHERE m2.language = singleLang
			OPTIONAL MATCH (m2)-[:REPLY_OF]->(m3:Message)
			RETURN m2.language as replyLang, m3.language as originalLang
		''')
	}

}