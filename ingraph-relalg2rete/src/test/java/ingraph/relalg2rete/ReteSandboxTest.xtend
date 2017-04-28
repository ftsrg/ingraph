package ingraph.relalg2rete

import org.junit.Test

class ReteSandboxTest extends Cypher2Relalg2Rete2TexTest {

	override protected directory() {
		return "sandbox"
	}

	@Test
	def void adbis1() {
		process('adbis-query-1', '''
		''')
	}

}