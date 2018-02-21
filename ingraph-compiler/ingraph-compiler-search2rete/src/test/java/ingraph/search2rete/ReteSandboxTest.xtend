package ingraph.search2rete

import org.junit.Test

class ReteSandboxTest extends Cypher2Search2Rete2TexTest {

	override protected directory() {
		return "sandbox"
	}

//	val queries = #[
//	  ''''''
//	]
//
//	@Test
//	def void queries() {
//		for (i : 0 ..< queries.length) {
//			process('''query-«i»''', queries.get(i))
//		}
//	}

//	@Test
//	def void q() {
//		val directoryPath = '''../../queries/railway-verification/'''
//		val fileNames = FileUtils.listFiles(new File(directoryPath), #["cypher"], false).map[name].toList
//
//		for (fileName : fileNames) {
//			val queryName = FilenameUtils.removeExtension(fileName)
//			val querySpecification = FileUtils.readFileToString(new File(directoryPath + fileName), Charset.forName("UTF-8"))
//			process(queryName, querySpecification)
//		}
//	}

	@Test
	def void q1() {
		process("q1", '''
		MATCH (p:Person)-[:INTEREST]->(t:Tag)
		  -[:SUBCLASS*0..]->(:TagClass {name: 'Music'})
		OPTIONAL MATCH (p)-[:KNOWS]-(f:Person)
		  -[:INTEREST]->(:Tag {name: 'Mozart'})
		WITH p, t, count(f) AS mlf
		WHERE mlf > 1
		RETURN p.name, collect(t) AS ts
		''')
	}

}