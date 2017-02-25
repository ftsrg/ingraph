package ingraph.cypher2relalg.tck.tests

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.cypher2relalg.tck.FailingTests
import ingraph.cypher2relalg.tck.RegressionTests
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil
import org.junit.Test
import org.junit.experimental.categories.Category

class VarLengthAcceptance2ParserTest {
		
		/*
		Scenario: Handling relationships that are already bound in variable length paths
		Given an empty graph
		And having executed:
			"""
			CREATE (n0:Node),
						 (n1:Node),
						 (n2:Node),
						 (n3:Node),
						 (n0)-[:EDGE]->(n1),
						 (n1)-[:EDGE]->(n2),
						 (n2)-[:EDGE]->(n3)
			"""
		*/
		@Test
		@Category(FailingTests)
		def void testVarLengthAcceptance2_01() {
				val cypher = CypherParser.parseString('''
				MATCH ()-[r:EDGE]-()
				MATCH p = (n)-[*0..1]-()-[r]-()-[*0..1]-(m)
				RETURN count(p) AS c
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/VarLengthAcceptance2_01")
				Cypher2Relalg.processCypher(cypher)
		}

}
