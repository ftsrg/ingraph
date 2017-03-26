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
	def void query7() {
		process('query7', '''
			MATCH
				(tag:Tag)<-[:hasTag]-(:Message)-[:hasCreator]->(person1:Person)
			MATCH
				(person)<-[:hasCreator]-(message:Message)-[:hasTag]->(tag),
				(message)<-[:likes]-(person2:Person)<-[:hasCreator]-(:Message)<-[:likes]-(person3:Person)
			RETURN
				person1.id,
				count(person3) AS authorityScore
			ORDER BY
				authorityScore DESC,
				person1.name ASC
			LIMIT 100
		''')
	}

	@Test
	def void query23() {
		process('query23', '''
			MATCH (homeCountry:Country)<-[:isPartOf]-(:City)<-[:isLocatedIn]-(:Person)<-[:hasCreator]-(message:Message)-[:isLocatedIn]->(country:Country)
			WHERE homeCountry <> country
			RETURN count(message) AS messageCount, country.name,  toInt(substring(message.creationDate, 5, 2)) AS month
			ORDER BY messageCount DESC, country.name ASC, month DESC
			LIMIT 100
		''')
	}
	
	@Test
	def void query24() {
		process('query24', '''
			MATCH
			  (:TagClass)<-[:hasType]-(:Tag)<-[:hasTag]-(message:Message)<-[:likes]-(person:Person),
			  (message)-[:isLocatedIn]->(:Country)-[:isPartOf]->(continent:Continent)
			RETURN
			  count(message) AS messageCount,
			  count(person) AS likeCount,
			  toInt(substring(message.creationDate, 0, 4)) AS year,
			  toInt(substring(message.creationDate, 5, 2)) AS month,
			  continent.name
		''')
	}
	
	@Test
	def void sort() {
		process('sort', '''
			MATCH (n)-[:KNOWS]-(m)
			RETURN n, n.name, count(m) AS numberOfFriends
			ORDER BY n.age
			LIMIT 100
		''')
	}
	
	@Test
	def void sort2() {
	  process('sort2', '''
	    MATCH (n: Segment) RETURN n ORDER BY n SKIP 5 LIMIT 10
    ''')
	}

}
