package ingraph.relalg2rete

import org.junit.Test

class ReteSandboxTest extends Cypher2Relalg2Rete2TexTest {

	override protected directory() {
		return "sandbox"
	}

	@Test
	def void sandbox() {
		process('sandbox', '''
			MATCH (p1:Person)
			UNWIND p1.speaks AS p1lang
			MATCH (p2:Person)
			WHERE p1lang IN p2.speaks // AND p1 <> p2
			RETURN p1lang
		''')
	}

	@Test
	def void schemaCalculation4() {
	  process('query-4', '''
		  MATCH (:Country)<-[:isPartOf]-(:City)<-[:isLocatedIn]-(person:Person)<-[:hasModerator]-(forum:Forum)-[:containerOf]->(post:Post)-[:hasTag]->(:Tag)-[:hasType]->(:TagClass)
		  RETURN forum.id, forum.title, forum.creationDate, person.id, count(post) AS count
		  ORDER BY count DESC, forum.id ASC
		  LIMIT 20
	  ''')
	}

	@Test
	def void schemaCalculation23() {
		process('query-23', '''
			MATCH (country:Country)<-[:isPartOf]-(:City)<-[:isLocatedIn]-(:Person)<-[:hasCreator]-(message:Message)
			RETURN count(message) AS messageCount, country.name
			ORDER BY country.name ASC
		''')
	}


}