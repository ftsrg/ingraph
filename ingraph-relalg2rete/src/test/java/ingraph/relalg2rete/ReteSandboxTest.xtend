package ingraph.relalg2rete

import org.junit.Test

class ReteSandboxTest extends Cypher2Relalg2Rete2TexTest {

	override protected directory() {
		return "sandbox"
	}

	@Test
	def void create1() {
		process('create-example', '''CREATE (n) RETURN n''')
	}

}