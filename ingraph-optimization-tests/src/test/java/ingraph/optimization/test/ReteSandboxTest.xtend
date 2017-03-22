package ingraph.optimization.test

import org.junit.Test

class ReteSandboxTest extends Cypher2Relalg2Rete2TexTest {

	override protected directory() {
		return "sandbox"
	}

	@Test
	def void constantFolding() {
		process('test-constant-folding', '''MATCH (n) WHERE 1=1 RETURN n''')
	}

	@Test
	def void undirectedEdges1() {
		process('undirectedEdges-1', '''MATCH (n)-[r1:REL1]->(m)-[r2:REL2]-(o) RETURN n''')
	}

	@Test
	def void undirectedEdges2() {
		process('undirectedEdges-2', '''MATCH (n)-[r1:REL1]->(m)-[r2:REL2]-(o) RETURN n''')
	}
	
	@Test
	def void query23() {
		process('query23', '''MATCH (homeCountry:Country)<-[:isPartOf]-(:City)<-[:isLocatedIn]-(:Person)<-[:hasCreator]-(message:Message)-[:isLocatedIn]->(country:Country)
		WHERE homeCountry <> country
		RETURN count(message) AS messageCount, country.name,  toInt(substring(message.creationDate, 5, 2)) AS month
		ORDER BY messageCount DESC, country.name ASC, month DESC
		LIMIT 100
		''')
	}
	
}
