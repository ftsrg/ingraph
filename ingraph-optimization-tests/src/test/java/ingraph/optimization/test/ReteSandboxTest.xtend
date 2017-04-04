package ingraph.optimization.test

import org.junit.Test
import org.eclipse.emf.ecore.util.EcoreUtil

class ReteSandboxTest extends Cypher2Relalg2Rete2TexTest {

	override protected directory() {
		return "sandbox"
	}

	@Test
	def void query4() {
	 	process('query4', '''
	  	MATCH (:Country)<-[:isPartOf]-(:City)<-[:isLocatedIn]-(person:Person)<-[:hasModerator]-(forum:Forum)-[:containerOf]->(post:Post)-[:hasTag]->(:Tag)-[:hasType]->(:TagClass)
	  	RETURN forum.id, forum.title, forum.creationDate, person.id, count(post) AS count
	  	ORDER BY count DESC, forum.id ASC
	  	LIMIT 20
	  ''')
	}
	
	@Test
	def void query13() {
		process('query13', '''
			// Popular Tags per month in a country
			MATCH (:Country)<-[:isLocatedIn]-(message:Message)-[:hasTag]->(tag:Tag)
			WITH
			  toInt(substring(message.creationDate, 0, 4)) AS year,
			  toInt(substring(message.creationDate, 5, 2)) AS month,
			  count(message) AS popularity,
			  tag
			ORDER BY popularity DESC, tag.name ASC
			RETURN year, month, collect([tag.name, popularity]) AS popularTags
			ORDER BY year DESC, month ASC
			LIMIT 100
		''')
	}
	
}
