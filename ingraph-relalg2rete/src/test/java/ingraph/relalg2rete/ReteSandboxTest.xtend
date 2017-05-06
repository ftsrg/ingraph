package ingraph.relalg2rete

import org.junit.Test

class ReteSandboxTest extends Cypher2Relalg2Rete2TexTest {

	override protected directory() {
		return "sandbox"
	}

	@Test
	def void q1() {
		process('query-1', '''
		MATCH (:Country)<-[:isLocatedIn]-(message:Message)-[:hasTag]->(tag:Tag)
		WITH
		  toInt(substring(message.creationDate, 0, 4)) AS year,
		  toInt(substring(message.creationDate, 5, 2)) AS month,
		  message,
		  tag
		WITH
		  year,
		  month,
		  count(message) AS popularity,
		  tag
		ORDER BY popularity DESC, tag.name ASC
		RETURN year, month, collect([tag.name, popularity]) AS popularTags
		ORDER BY year DESC, month ASC
		LIMIT 100
		''')
	}

}