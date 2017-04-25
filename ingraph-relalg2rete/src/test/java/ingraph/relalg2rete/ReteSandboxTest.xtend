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