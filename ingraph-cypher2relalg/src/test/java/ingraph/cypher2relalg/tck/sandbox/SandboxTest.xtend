package ingraph.cypher2relalg.tck.sandbox

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.cypherparser.CypherParser
import org.junit.Test

class SandboxTest {

		@Test
		def void test01() {
				val cypher = CypherParser.parseString('''
				MATCH (a)
				RETURN count(a)
				''')
				Cypher2Relalg.processCypher(cypher)
		}
		
		@Test
		def void test02() {
				val cypher = CypherParser.parseString('''
				MATCH (n:Person)
				RETURN
					n.name AS name,
					CASE n.eyes
						WHEN 'blue'  THEN 1
						WHEN 'brown' THEN 2
					ELSE 3
					END AS eyeCode
				''')
				Cypher2Relalg.processCypher(cypher)
		}

		@Test
		def void test03() {
				val cypher = CypherParser.parseString('''
				MATCH (row)
				UNWIND row AS node
				RETURN node.id
				''')
				Cypher2Relalg.processCypher(cypher)
		}

		@Test
		def void test04() {
				val cypher = CypherParser.parseString('''
				MATCH p=(a)-[r]->(b)
				RETURN a, r, b
				''')
				Cypher2Relalg.processCypher(cypher)
		}

		@Test
		def void test05() {
				val cypher = CypherParser.parseString('''
				MATCH p=shortestPath((a)-[r]->(b))
				RETURN a, r, b
				''')
				Cypher2Relalg.processCypher(cypher)
		}

		@Test
		def void test06() {
				val cypher = CypherParser.parseString('''
				MATCH p=allShortestPaths((a)-[r]->(b))
				RETURN a, r, b
				''')
				Cypher2Relalg.processCypher(cypher)
		}

}
