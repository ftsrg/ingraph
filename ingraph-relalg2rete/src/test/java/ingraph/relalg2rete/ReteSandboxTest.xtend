package ingraph.relalg2rete

import org.junit.Test

class ReteSandboxTest extends Cypher2Relalg2Rete2TexTest {

	override protected directory() {
		return "sandbox"
	}

	@Test
	def void adbis1() {
		process('paper-query-1', '''''')
	}

	@Test
	def void adbis2() {
		process('paper-query-2', '''''')
	}

	@Test
	def void adbis3() {
		process('paper-query-3', '''MATCH (p:Person) UNWIND p.speaks AS language RETURN DISTINCT language''')
	}

	@Test
	def void adbis4() {
		process('paper-query-4', '''
			MATCH (p1:Person)
			UNWIND p1.speaks AS p1lang
			MATCH (p2:Person)
			WHERE p1lang IN p2.speaks // AND p1 <> p2
			RETURN p1lang
		''')
	}


}