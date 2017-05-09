package ingraph.relalg2rete

import org.junit.Test

class ReteSandboxTest extends Cypher2Relalg2Rete2TexTest {

	override protected directory() {
		return "sandbox"
	}

	@Test
	def void q1() {
		process('query-1', '''
		MATCH (message:Message)
		WITH message, substring(message.creationDate, 5, 2) AS messageMonth
		RETURN count(message) AS messageCount, messageMonth AS month
		ORDER BY month
		''')
	}

	@Test
	def void q2() {
		process('query-2', '''
		// Messages by Topic and Continent
		MATCH
		  (:TagClass)<-[:hasType]-(:Tag)<-[:hasTag]-(message:Message)<-[:likes]-(person:Person),
		  (message)-[:isLocatedIn]->(:Country)-[:isPartOf]->(continent:Continent)
		RETURN
		  count(message) AS messageCount,
		  count(person) AS likeCount,
		  toInt(substring(message.creationDate, 0, 4)) AS year,
		  toInt(substring(message.creationDate, 5, 2)) AS month,
		  continent.name
		ORDER BY messageCount, likeCount, year, month, continent.name
		LIMIT 100
		''')
	}

	@Test
	def void q3() {
		process('query-3', '''
		MATCH (n:Person)
		WHERE n.age > 27
		RETURN n.name
		''')
	}

}